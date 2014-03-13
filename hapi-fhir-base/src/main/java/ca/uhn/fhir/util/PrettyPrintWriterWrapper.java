package ca.uhn.fhir.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.lang3.StringUtils;

public class PrettyPrintWriterWrapper implements XMLStreamWriter {

	private XMLStreamWriter myTarget;
	private int depth = 0;
	private Map<Integer, Boolean> hasChildElement = new HashMap<Integer, Boolean>();

	private static final String INDENT_CHAR = " ";
	private static final String LINEFEED_CHAR = "\n";

	public PrettyPrintWriterWrapper(XMLStreamWriter target) {
		myTarget = target;
	}

	private String repeat(int d, String s) {
		return StringUtils.repeat(s, d * 3);
	}

	@Override
	public void flush() throws XMLStreamException {
		myTarget.flush();
	}

	@Override
	public void close() throws XMLStreamException {
		myTarget.close();
	}

	@Override
	public String getPrefix(String theUri) throws XMLStreamException {
		return myTarget.getPrefix(theUri);
	}

	@Override
	public void setPrefix(String thePrefix, String theUri) throws XMLStreamException {
		myTarget.setPrefix(thePrefix, theUri);
	}

	@Override
	public void setDefaultNamespace(String theUri) throws XMLStreamException {
		myTarget.setDefaultNamespace(theUri);
	}

	@Override
	public void setNamespaceContext(NamespaceContext theContext) throws XMLStreamException {
		myTarget.setNamespaceContext(theContext);
	}

	@Override
	public NamespaceContext getNamespaceContext() {
		return myTarget.getNamespaceContext();
	}

	@Override
	public void writeStartElement(String theLocalName) throws XMLStreamException {
		indentAndAdd();
		myTarget.writeStartElement(theLocalName);
	}

	private void indentAndAdd() throws XMLStreamException {
		indent();

		// update state of parent node
		if (depth > 0) {
			hasChildElement.put(depth - 1, true);
		}

		// reset state of current node
		hasChildElement.put(depth, false);

		depth++;
	}

	private void indent() throws XMLStreamException {
		myTarget.writeCharacters(LINEFEED_CHAR + repeat(depth, INDENT_CHAR));
	}

	@Override
	public void writeStartElement(String theNamespaceURI, String theLocalName) throws XMLStreamException {
		indentAndAdd();
		myTarget.writeStartElement(theNamespaceURI, theLocalName);
	}

	@Override
	public void writeStartElement(String thePrefix, String theLocalName, String theNamespaceURI) throws XMLStreamException {
		indentAndAdd();
		myTarget.writeStartElement(thePrefix, theNamespaceURI, theLocalName);
	}

	@Override
	public void writeEmptyElement(String theNamespaceURI, String theLocalName) throws XMLStreamException {
		indent();
		writeEmptyElement(theNamespaceURI, theLocalName);
	}

	@Override
	public void writeEmptyElement(String thePrefix, String theLocalName, String theNamespaceURI) throws XMLStreamException {
		indent();
		writeEmptyElement(thePrefix, theLocalName, theNamespaceURI);
	}

	@Override
	public void writeEmptyElement(String theLocalName) throws XMLStreamException {
		indent();
		writeEmptyElement(theLocalName);
	}

	@Override
	public void writeEndElement() throws XMLStreamException {
		decrementAndIndent();

		myTarget.writeEndElement();
	}

	private void decrementAndIndent() throws XMLStreamException {
		depth--;

		if (hasChildElement.get(depth) == true) {
			// indent for current depth
			myTarget.writeCharacters(LINEFEED_CHAR + repeat(depth, INDENT_CHAR));
		}
	}

	@Override
	public void writeEndDocument() throws XMLStreamException {
		decrementAndIndent();
		myTarget.writeEndDocument();
	}

	@Override
	public void writeAttribute(String theLocalName, String theValue) throws XMLStreamException {
		myTarget.writeAttribute(theLocalName, theValue);
	}

	@Override
	public void writeAttribute(String thePrefix, String theNamespaceURI, String theLocalName, String theValue) throws XMLStreamException {
		myTarget.writeAttribute(thePrefix, theNamespaceURI, theLocalName, theValue);
	}

	@Override
	public void writeAttribute(String theNamespaceURI, String theLocalName, String theValue) throws XMLStreamException {
		myTarget.writeAttribute(theNamespaceURI, theLocalName, theValue);
	}

	@Override
	public void writeNamespace(String thePrefix, String theNamespaceURI) throws XMLStreamException {
		myTarget.writeNamespace(thePrefix, theNamespaceURI);
	}

	@Override
	public void writeDefaultNamespace(String theNamespaceURI) throws XMLStreamException {
		myTarget.writeDefaultNamespace(theNamespaceURI);
	}

	@Override
	public void writeComment(String theData) throws XMLStreamException {
		myTarget.writeComment(theData);
	}

	@Override
	public void writeProcessingInstruction(String theTarget) throws XMLStreamException {
		myTarget.writeProcessingInstruction(theTarget);
	}

	@Override
	public void writeProcessingInstruction(String theTarget, String theData) throws XMLStreamException {
		myTarget.writeProcessingInstruction(theTarget, theData);
	}

	@Override
	public void writeCData(String theData) throws XMLStreamException {
		myTarget.writeCData(theData);
	}

	@Override
	public void writeDTD(String theDtd) throws XMLStreamException {
		myTarget.writeDTD(theDtd);
	}

	@Override
	public void writeEntityRef(String theName) throws XMLStreamException {
		myTarget.writeEntityRef(theName);
	}

	@Override
	public void writeStartDocument() throws XMLStreamException {
		myTarget.writeStartDocument();
	}

	@Override
	public void writeStartDocument(String theVersion) throws XMLStreamException {
		myTarget.writeStartDocument(theVersion);
	}

	@Override
	public void writeStartDocument(String theEncoding, String theVersion) throws XMLStreamException {
		myTarget.writeStartDocument(theEncoding, theVersion);
	}

	@Override
	public void writeCharacters(String theText) throws XMLStreamException {
		myTarget.writeCharacters(theText);
	}

	@Override
	public void writeCharacters(char[] theText, int theStart, int theLen) throws XMLStreamException {
		myTarget.writeCharacters(theText, theStart, theLen);
	}

	@Override
	public Object getProperty(String theName) throws IllegalArgumentException {
		return myTarget.getProperty(theName);
	}

}