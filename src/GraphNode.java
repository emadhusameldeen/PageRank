import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class GraphNode {
    private String identifier;
    private ArrayList<xmlAttribute> attributes = new ArrayList<xmlAttribute>();

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void addAttribute(xmlAttribute xmlAttribute)
    {
        attributes.add(xmlAttribute);
    }

    public ArrayList<xmlAttribute> getAttributes() {
        return attributes;
    }

    public String getIdentifier() {
        return identifier;
    }
}
