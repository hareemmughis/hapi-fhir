















package ca.uhn.fhir.model.dstu.composite;

import java.util.*;
import ca.uhn.fhir.model.api.*;
import ca.uhn.fhir.model.api.annotation.*;
import ca.uhn.fhir.model.primitive.*;
import ca.uhn.fhir.model.dstu.valueset.*;
import ca.uhn.fhir.model.dstu.resource.*;

/**
 * HAPI/FHIR <b>HumanName</b> Datatype
 * (Name of a human - parts and usage)
 *
 * <p>
 * <b>Definition:</b>
 * A human's name with the ability to identify parts and usage
 * </p> 
 *
 * <p>
 * <b>Requirements:</b>
 * Need to be able to record names, along with notes about their use
 * </p> 
 */
@DatatypeDef(name="HumanName") 
public class HumanNameDt 
        extends  BaseElement         implements ICompositeDatatype  {


	@Child(name="use", type=CodeDt.class, order=0, min=0, max=1)	
	@Description(
		shortDefinition="usual | official | temp | nickname | anonymous | old | maiden",
		formalDefinition="Identifies the purpose for this name"
	)
	private BoundCodeDt<NameUseEnum> myUse;
	
	@Child(name="text", type=StringDt.class, order=1, min=0, max=1)	
	@Description(
		shortDefinition="Text representation of the full name",
		formalDefinition="A full text representation of the name"
	)
	private StringDt myText;
	
	@Child(name="family", type=StringDt.class, order=2, min=0, max=Child.MAX_UNLIMITED)	
	@Description(
		shortDefinition="Family name (often called 'Surname')",
		formalDefinition="The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father."
	)
	private List<StringDt> myFamily;
	
	@Child(name="given", type=StringDt.class, order=3, min=0, max=Child.MAX_UNLIMITED)	
	@Description(
		shortDefinition="Given names (not always 'first'). Includes middle names",
		formalDefinition="Given name"
	)
	private List<StringDt> myGiven;
	
	@Child(name="prefix", type=StringDt.class, order=4, min=0, max=Child.MAX_UNLIMITED)	
	@Description(
		shortDefinition="Parts that come before the name",
		formalDefinition="Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name"
	)
	private List<StringDt> myPrefix;
	
	@Child(name="suffix", type=StringDt.class, order=5, min=0, max=Child.MAX_UNLIMITED)	
	@Description(
		shortDefinition="Parts that come after the name",
		formalDefinition="Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name"
	)
	private List<StringDt> mySuffix;
	
	@Child(name="period", type=PeriodDt.class, order=6, min=0, max=1)	
	@Description(
		shortDefinition="Time period when name was/is in use",
		formalDefinition="Indicates the period of time when this name was valid for the named person."
	)
	private PeriodDt myPeriod;
	

	@Override
	public boolean isEmpty() {
		return super.isBaseEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(  myUse,  myText,  myFamily,  myGiven,  myPrefix,  mySuffix,  myPeriod);
	}

	/**
	 * Gets the value(s) for <b>use</b> (usual | official | temp | nickname | anonymous | old | maiden).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * Identifies the purpose for this name
     * </p> 
	 */
	public BoundCodeDt<NameUseEnum> getUse() {  
		if (myUse == null) {
			myUse = new BoundCodeDt<NameUseEnum>(NameUseEnum.VALUESET_BINDER);
		}
		return myUse;
	}

	/**
	 * Sets the value(s) for <b>use</b> (usual | official | temp | nickname | anonymous | old | maiden)
	 *
     * <p>
     * <b>Definition:</b>
     * Identifies the purpose for this name
     * </p> 
	 */
	public void setUse(BoundCodeDt<NameUseEnum> theValue) {
		myUse = theValue;
	}


	/**
	 * Sets the value(s) for <b>use</b> (usual | official | temp | nickname | anonymous | old | maiden)
	 *
     * <p>
     * <b>Definition:</b>
     * Identifies the purpose for this name
     * </p> 
	 */
	public void setUse(NameUseEnum theValue) {
		getUse().setValueAsEnum(theValue);
	}

  
	/**
	 * Gets the value(s) for <b>text</b> (Text representation of the full name).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * A full text representation of the name
     * </p> 
	 */
	public StringDt getText() {  
		if (myText == null) {
			myText = new StringDt();
		}
		return myText;
	}

	/**
	 * Sets the value(s) for <b>text</b> (Text representation of the full name)
	 *
     * <p>
     * <b>Definition:</b>
     * A full text representation of the name
     * </p> 
	 */
	public void setText(StringDt theValue) {
		myText = theValue;
	}


 	/**
	 * Sets the value for <b>text</b> (Text representation of the full name)
	 *
     * <p>
     * <b>Definition:</b>
     * A full text representation of the name
     * </p> 
	 */
	public void setText( String theString) {
		myText = new StringDt(theString); 
	}

 
	/**
	 * Gets the value(s) for <b>family</b> (Family name (often called 'Surname')).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
     * </p> 
	 */
	public List<StringDt> getFamily() {  
		if (myFamily == null) {
			myFamily = new ArrayList<StringDt>();
		}
		return myFamily;
	}

	/**
	 * Sets the value(s) for <b>family</b> (Family name (often called 'Surname'))
	 *
     * <p>
     * <b>Definition:</b>
     * The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
     * </p> 
	 */
	public void setFamily(List<StringDt> theValue) {
		myFamily = theValue;
	}

	/**
	 * Adds and returns a new value for <b>family</b> (Family name (often called 'Surname'))
	 *
     * <p>
     * <b>Definition:</b>
     * The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
     * </p> 
	 */
	public StringDt addFamily() {
		StringDt newType = new StringDt();
		getFamily().add(newType);
		return newType; 
	}

 	/**
	 * Adds a new value for <b>family</b> (Family name (often called 'Surname'))
	 *
     * <p>
     * <b>Definition:</b>
     * The part of a name that links to the genealogy. In some cultures (e.g. Eritrea) the family name of a son is the first name of his father.
     * </p> 
	 */
	public void addFamily( String theString) {
		if (myFamily == null) {
			myFamily = new ArrayList<StringDt>();
		}
		myFamily.add(new StringDt(theString)); 
	}

 
	/**
	 * Gets the value(s) for <b>given</b> (Given names (not always 'first'). Includes middle names).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * Given name
     * </p> 
	 */
	public List<StringDt> getGiven() {  
		if (myGiven == null) {
			myGiven = new ArrayList<StringDt>();
		}
		return myGiven;
	}

	/**
	 * Sets the value(s) for <b>given</b> (Given names (not always 'first'). Includes middle names)
	 *
     * <p>
     * <b>Definition:</b>
     * Given name
     * </p> 
	 */
	public void setGiven(List<StringDt> theValue) {
		myGiven = theValue;
	}

	/**
	 * Adds and returns a new value for <b>given</b> (Given names (not always 'first'). Includes middle names)
	 *
     * <p>
     * <b>Definition:</b>
     * Given name
     * </p> 
	 */
	public StringDt addGiven() {
		StringDt newType = new StringDt();
		getGiven().add(newType);
		return newType; 
	}

 	/**
	 * Adds a new value for <b>given</b> (Given names (not always 'first'). Includes middle names)
	 *
     * <p>
     * <b>Definition:</b>
     * Given name
     * </p> 
	 */
	public void addGiven( String theString) {
		if (myGiven == null) {
			myGiven = new ArrayList<StringDt>();
		}
		myGiven.add(new StringDt(theString)); 
	}

 
	/**
	 * Gets the value(s) for <b>prefix</b> (Parts that come before the name).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name
     * </p> 
	 */
	public List<StringDt> getPrefix() {  
		if (myPrefix == null) {
			myPrefix = new ArrayList<StringDt>();
		}
		return myPrefix;
	}

	/**
	 * Sets the value(s) for <b>prefix</b> (Parts that come before the name)
	 *
     * <p>
     * <b>Definition:</b>
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name
     * </p> 
	 */
	public void setPrefix(List<StringDt> theValue) {
		myPrefix = theValue;
	}

	/**
	 * Adds and returns a new value for <b>prefix</b> (Parts that come before the name)
	 *
     * <p>
     * <b>Definition:</b>
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name
     * </p> 
	 */
	public StringDt addPrefix() {
		StringDt newType = new StringDt();
		getPrefix().add(newType);
		return newType; 
	}

 	/**
	 * Adds a new value for <b>prefix</b> (Parts that come before the name)
	 *
     * <p>
     * <b>Definition:</b>
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the start of the name
     * </p> 
	 */
	public void addPrefix( String theString) {
		if (myPrefix == null) {
			myPrefix = new ArrayList<StringDt>();
		}
		myPrefix.add(new StringDt(theString)); 
	}

 
	/**
	 * Gets the value(s) for <b>suffix</b> (Parts that come after the name).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name
     * </p> 
	 */
	public List<StringDt> getSuffix() {  
		if (mySuffix == null) {
			mySuffix = new ArrayList<StringDt>();
		}
		return mySuffix;
	}

	/**
	 * Sets the value(s) for <b>suffix</b> (Parts that come after the name)
	 *
     * <p>
     * <b>Definition:</b>
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name
     * </p> 
	 */
	public void setSuffix(List<StringDt> theValue) {
		mySuffix = theValue;
	}

	/**
	 * Adds and returns a new value for <b>suffix</b> (Parts that come after the name)
	 *
     * <p>
     * <b>Definition:</b>
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name
     * </p> 
	 */
	public StringDt addSuffix() {
		StringDt newType = new StringDt();
		getSuffix().add(newType);
		return newType; 
	}

 	/**
	 * Adds a new value for <b>suffix</b> (Parts that come after the name)
	 *
     * <p>
     * <b>Definition:</b>
     * Part of the name that is acquired as a title due to academic, legal, employment or nobility status, etc. and that appears at the end of the name
     * </p> 
	 */
	public void addSuffix( String theString) {
		if (mySuffix == null) {
			mySuffix = new ArrayList<StringDt>();
		}
		mySuffix.add(new StringDt(theString)); 
	}

 
	/**
	 * Gets the value(s) for <b>period</b> (Time period when name was/is in use).
	 * creating it if it does
	 * not exist. Will not return <code>null</code>.
	 *
     * <p>
     * <b>Definition:</b>
     * Indicates the period of time when this name was valid for the named person.
     * </p> 
	 */
	public PeriodDt getPeriod() {  
		if (myPeriod == null) {
			myPeriod = new PeriodDt();
		}
		return myPeriod;
	}

	/**
	 * Sets the value(s) for <b>period</b> (Time period when name was/is in use)
	 *
     * <p>
     * <b>Definition:</b>
     * Indicates the period of time when this name was valid for the named person.
     * </p> 
	 */
	public void setPeriod(PeriodDt theValue) {
		myPeriod = theValue;
	}


  



}