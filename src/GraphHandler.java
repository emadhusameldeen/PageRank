import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

public class GraphHandler extends DefaultHandler {

    private Object Node = new Stack();




    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        //if the xml element is a node
        if (qName.equals("node")) {

            if (attributes != null) {

            }
        }
    }

        public void endElement(String uri, String localName, String qName) throws SAXException
        {
            //Remove last added  element
            //this.elementStack.pop();
            System.out.println("11");

        }

    public void characters(char[] ch, int start, int length) throws SAXException
    {
        String value = new String(ch, start, length).trim();

        if (value.length() == 0)
        {
            return; // ignore white space
        }

        //handle the value based on to which element it belongs
        if ("firstName".equals(currentElement().toString()))
        {

        }
        else if ("lastName".equals(currentElement().toString()))
        {

        }
    }
    private Object currentElement()
    {
        return this.Node;
    }
    }
