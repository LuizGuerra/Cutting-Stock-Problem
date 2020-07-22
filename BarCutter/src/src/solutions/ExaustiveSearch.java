package src.solutions;

import src.models.Bar;
import src.models.Bars;
import src.models.Graph;
import src.models.Vertex;

import java.util.ArrayList;
import java.util.List;

public class ExaustiveSearch {
    private Graph graph;
    private List<Bar> bestSolution;
    private int cicles, auxx;

    public ExaustiveSearch(Graph graph) {
        this.graph = graph;
        this.bestSolution = new ArrayList<>();
        cicles = 0;
    }

    public List<Bar> solve() {
        findBar((new Bar()), (new ArrayList<>()), graph.getVertexes());
        return new ArrayList<>(bestSolution);
    }

    public void findBar(Bar bar, List<Bar> solution, List<Vertex> vertexes) {
        cicles++;
        if (bar == null || vertexes == null || solution == null) { return; }
        if (!bestSolution.isEmpty() && solution.size() > bestSolution.size() ) { return; }
        if (vertexes.isEmpty()) {
            if (!bar.isEmpty()) {
                solution.add(bar);
            }
            if (bestSolution.size() > solution.size() || bestSolution.isEmpty()) {
                bestSolution = solution;
            }
            return;
        }
        float previous = -1;
        for (Vertex v : vertexes) {
            if (previous == v.getSize()) { continue; }
            previous = v.getSize();
            Bar auxBar = new Bar(bar);
            List<Bar> solutionCopy = new ArrayList<>(solution);
            List<Vertex> vertexList = new ArrayList<>(vertexes);
            vertexList.remove(v);
            if (!auxBar.cut(v.getSize())) {
                solutionCopy.add(auxBar);
                auxBar = new Bar();
                auxBar.cut(v.getSize());
            }
            findBar(auxBar, solutionCopy, vertexList);
        }
    }

    private void verifyIllegalArgument(List<Vertex> list) {
        if (list.stream().anyMatch( x -> x.getSize() > Bars.maximumBarSize)) {
            throw new IllegalArgumentException("Cuts cannot be bigger than the bar.");
        }
        if (list.stream().anyMatch( x -> x.getSize() <= 0)) {
            throw new IllegalArgumentException("Cuts cannot be negative.");
        }
    }
    public int getCicles() { return cicles; }

}
