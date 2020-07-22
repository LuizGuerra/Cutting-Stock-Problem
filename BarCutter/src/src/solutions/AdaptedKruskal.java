package src.solutions;

import src.models.Bar;
import src.models.Bars;
import src.models.Graph;
import src.models.Vertex;

import java.util.ArrayList;
import java.util.List;

public class AdaptedKruskal {
    private final Graph graph;
    private List<Bar> kruskalSolution;

    public AdaptedKruskal(Graph graph) {
        verifyIllegalArgument(graph.getVertexes());
        this.graph = graph;
        this.kruskalSolution = new ArrayList<>();
    }

    public List<Bar> solve() {
        findBar(new Bar(), graph.getVertexes());
        return kruskalSolution;
    }

    private void findBar(Bar bar, List<Vertex> vertexes) {
        if (vertexes == null || bar == null) {
            return;
        }
        if (vertexes.isEmpty() && !bar.isEmpty()) {
            kruskalSolution.add(bar);
            return;
        }
        Vertex smallest = null;
        for (Vertex v : vertexes) {
            if (smallest == null || v.getSize() < smallest.getSize()) {
                smallest = v;
            }
        }
        if (!bar.cut(smallest.getSize())) {
            kruskalSolution.add(bar);
            bar = new Bar();
            bar.cut(smallest.getSize());
        }
        vertexes.remove(smallest);
        findBar(bar, vertexes);
    }

    private void verifyIllegalArgument(List<Vertex> list) {
        if (list.stream().anyMatch( x -> x.getSize() > Bars.maximumBarSize)) {
            throw new IllegalArgumentException("Cuts cannot be bigger than the bar.");
        }
        if (list.stream().anyMatch( x -> x.getSize() <= 0)) {
            throw new IllegalArgumentException("Cuts cannot be negative.");
        }
    }

}
