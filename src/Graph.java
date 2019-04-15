import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private String type;
    private HashMap<Integer, GraphNode> graphNodes ;
    private HashMap<Integer, Link> links;

    Graph ()
    {
        graphNodes = new HashMap<Integer, GraphNode>();
        links = new HashMap<Integer, Link>();
    }

    public void addNode (int id, GraphNode node)
    {
        graphNodes.put(id,node);
    }

    public void addLink(int id, Link link)
    {
        links.put(id,link);
    }

    public GraphNode getNode(int xmlNodeId){
        if (graphNodes.containsKey(xmlNodeId))
        {
            return graphNodes.get(xmlNodeId);
        }
        return null;
    }

    public int getNumberOfNodes ()
    {
        return graphNodes.size();
    }

    public HashMap<Integer, Link> getLinks() {
        return links;
    }
}
