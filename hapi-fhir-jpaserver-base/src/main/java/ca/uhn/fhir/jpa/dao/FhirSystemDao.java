package ca.uhn.fhir.jpa.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hamcrest.generator.qdox.parser.structs.TagDef;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.entity.ResourceTable;
import ca.uhn.fhir.jpa.entity.TagDefinition;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.TagList;
import ca.uhn.fhir.model.dstu.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.util.FhirTerser;

public class FhirSystemDao extends BaseFhirDao implements IFhirSystemDao {
	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(FhirSystemDao.class);

	@PersistenceContext()
	private EntityManager myEntityManager;

	private FhirContext myContext = new FhirContext();

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void transaction(List<IResource> theResources) {
		ourLog.info("Beginning transaction with {} resources", theResources.size());

		FhirTerser terser = myContext.newTerser();

		Map<IdDt, IdDt> idConversions = new HashMap<IdDt, IdDt>();
		List<ResourceTable> persistedResources = new ArrayList<ResourceTable>();
		for (IResource nextResource : theResources) {
			IdDt nextId = nextResource.getId();
			if (nextId == null) {
				nextId = new IdDt();
			}

			String resourceName = toResourceName(nextResource);

			// IFhirResourceDao<? extends IResource> dao = getDao(nextResource.getClass());
			// if (dao == null) {
			// throw new InvalidRequestException("This server is not able to handle resources of type: " +
			// nextResource.getResourceId().getResourceType());
			// }

			ResourceTable entity;
			if (nextId.isEmpty()) {
				entity = null;
			} else if (!nextId.isValidLong()) {
				entity = null;
			} else {
				entity = myEntityManager.find(ResourceTable.class, nextId.asLong());
			}

			if (entity == null) {
				entity = toEntity(nextResource);
				myEntityManager.persist(entity);
				myEntityManager.flush();
			}

			IdDt newId = new IdDt(resourceName + '/' + entity.getId());
			if (nextId.isEmpty()) {
				ourLog.info("Transaction resource (with no preexisting ID) has been assigned new ID[{}]", nextId, newId);
			} else if (newId.equals(entity.getId())) {
				ourLog.info("Transaction resource ID[{}] is being updated", newId);
			} else {
				ourLog.info("Transaction resource ID[{}] has been assigned new ID[{}]", nextId, newId);
				idConversions.put(nextId, newId);
			}
			
			persistedResources.add(entity);

		}

		for (IResource nextResource : theResources) {
			List<ResourceReferenceDt> allRefs = terser.getAllPopulatedChildElementsOfType(nextResource, ResourceReferenceDt.class);
			for (ResourceReferenceDt nextRef : allRefs) {
				IdDt nextId = nextRef.getResourceId();
				if (idConversions.containsKey(nextId)) {
					IdDt newId = idConversions.get(nextId);
					ourLog.info(" * Replacing resource ref {} with {}", nextId, newId);
					nextRef.setResourceId(newId);
				} else {
					ourLog.info(" * Reference [{}] does not exist in bundle", nextId);
				}
			}
		}

		for (int i = 0; i < theResources.size(); i++) {
			IResource resource = theResources.get(i);
			ResourceTable table = persistedResources.get(i);
			updateEntity(resource, table, table.getId() != null);
		}

	}

	@Override
	public List<IResource> history(Date theSince, Integer theLimit) {
		return super.history(null, null, theSince, theLimit);
	}

	@Override
	public TagList getAllTags() {
		return super.getTags(null, null);
	}

}