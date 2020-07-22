package src.models;

import java.util.*;

public class Graph {
    
    private Integer V, E;
    private List<Vertex> vertices;

    public Graph(Integer V) {
        this.V = V;
        this.E = E;
        this.vertices = new ArrayList<>();
    }

    public Graph(Graph graph) {
        this.V = graph.getV();
        this.E = graph.getE();
        this.vertices = new ArrayList<>(graph.getVertexes());
    }

    public Graph(List<Float> sizes, List<Integer> amounts) {
        if (amounts == null || sizes == null) {
            throw new NullPointerException("Initializer input cannot be null");
        }
        if (amounts.isEmpty()) {
            throw new IllegalArgumentException("Initializer input cannot be empty");
        }
        if (amounts.size() != sizes.size()) {
            throw new IllegalArgumentException("Initializer input have different sizes");
        }
        V = amounts.size();
        E = 0;
        vertices = new ArrayList<>();
        for (int i = 0; i < sizes.size(); i++) {
            for (int j = 0; j < amounts.get(i); j++) {
                vertices.add(new Vertex(sizes.get(i)));
            }
        }
        for (Vertex v1 : vertices) {
            for (Vertex v2 : vertices) {
                if (v1 != v2) {
                    v1.addRelative(v2);
                    E++;
                }
            }
        }
    }

    public Integer getV() {
        return V;
    }
    public Integer getE() {
        return E;
    }
    public List<Vertex> getVertexes() {
        return new ArrayList<>(vertices);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Vertex vertex : vertices) {
            str.append("\t").append(vertex.toString()).append(";\n");
        }
        return "Graph {\n" + str + "}";
    }
}
