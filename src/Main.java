import javax.xml.parsers.*;

import Jama.Matrix;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {

        Graph graph = new Graph();
        File inputFile = new File("C://1000Nds_Undir.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());


        //parsing the nodes
        NodeList nodeList = doc.getElementsByTagName("node");
        //for each node
        for (int nodeId = 0; nodeId < nodeList.getLength(); nodeId++) {
            Node node = nodeList.item(nodeId);
            System.out.println("\nCurrent Element: Node " + nodeId);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                GraphNode currentNode = new GraphNode();

                Node identifier = element.getElementsByTagName("identifier").item(0);
                if (identifier != null) {
                    graph.addNode(Integer.valueOf(identifier.getTextContent()), currentNode);
                    currentNode.setIdentifier(identifier.getTextContent());
                }

                NodeList attributes = element.getElementsByTagName("attribute");
                //for each attribute

                for (int attributeId = 0; attributeId < attributes.getLength(); attributeId++) {
                    Element attributeElement = (Element) attributes.item(attributeId);
                    xmlAttribute attribute = new xmlAttribute();
                    currentNode.addAttribute(attribute);
                    //check the type
                    switch (attributeElement.getElementsByTagName("type").item(0).getTextContent()) {
                        case "easting": {
                            attribute.setType("easting");
                            attribute.setValueDouble(attributeElement.getElementsByTagName("valueDouble").item(0).getTextContent());
                            break;
                        }
                        case "northing": {
                            attribute.setType("northing");
                            attribute.setValueDouble(attributeElement.getElementsByTagName("valueDouble").item(0).getTextContent());
                            break;
                        }
                        case "c": {
                            attribute.setType("c");
                            attribute.setValueInteger(attributeElement.getElementsByTagName("valueInteger").item(0).getTextContent());
                            break;
                        }
                    }
                }
                System.out.println("has " + attributes.getLength() + " attributes");

            }
        }

        //parsing the links
        NodeList linkList = doc.getElementsByTagName("link");
        //for each link
        for (int linkId = 0; linkId < linkList.getLength(); linkId++) {
            Node link = linkList.item(linkId);
            System.out.println("\nCurrent Element: Link " + linkId);

            if (link.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) link;
                Link currentLink = new Link();

                Node identifier = element.getElementsByTagName("identifier").item(0);
                if (identifier != null) {
                    graph.addLink(Integer.valueOf(identifier.getTextContent()), currentLink);
                    currentLink.setIdentifier(identifier.getTextContent());
                }

                Node alpha = element.getElementsByTagName("alpha").item(0);
                if (alpha != null) {
                    int alphaNodeId = Integer.valueOf(alpha.getTextContent());
                    GraphNode alphaNode = graph.getNode(alphaNodeId);
                    if (alphaNode != null) {
                        currentLink.setAlpha(alphaNode);
                    }
                }

                Node omega = element.getElementsByTagName("omega").item(0);
                if (omega != null) {
                    int omegaNodeId = Integer.valueOf(omega.getTextContent());
                    GraphNode omegaNode = graph.getNode(omegaNodeId);
                    if (omegaNode != null) {
                        currentLink.setOmega(omegaNode);
                    }
                }


            }

        }

        int numberOfNodes= graph.getNumberOfNodes()+1;
        HashMap<Integer,Link> graphLinks = graph.getLinks();
        HashSet<Integer> visitedNodes = new HashSet<Integer>();

        Matrix adjMatrix = new Matrix (numberOfNodes,numberOfNodes);
        for (Link link: graphLinks.values())
        {
            int alphaNodeId =Integer.valueOf(link.getAlpha().getIdentifier());
            int omegaNodeId = Integer.valueOf(link.getOmega().getIdentifier());
            if (visitedNodes.contains(alphaNodeId) || visitedNodes.contains(omegaNodeId))
            {
                continue;
            }

            adjMatrix.set(alphaNodeId,omegaNodeId,adjMatrix.get(alphaNodeId,omegaNodeId)+1);
            visitedNodes.add(alphaNodeId);
            visitedNodes.add(omegaNodeId);
        }




        PrintStream fileOut = new PrintStream("C://Users/emad_/Desktop/out.txt");
        System.setOut(fileOut);
        adjMatrix.print(1,0);
    }
}
