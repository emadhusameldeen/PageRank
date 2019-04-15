public class Link {
    private String identifier;
    private GraphNode alpha;
    private GraphNode omega;

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setAlpha(GraphNode alpha) {
        this.alpha = alpha;
    }

    public void setOmega(GraphNode omega) {
        this.omega = omega;
    }

    public GraphNode getAlpha() {
        return alpha;
    }

    public GraphNode getOmega() {
        return omega;
    }
}
