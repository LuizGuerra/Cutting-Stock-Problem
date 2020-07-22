package src;

import src.models.Bar;
import src.models.Bars;
import src.models.Graph;

import src.solutions.AdaptedKruskal;
import src.solutions.ExaustiveSearch;
import src.solutions.SearchWithAcceptance;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        Graph graph = new Graph(Arrays.asList(1.1f, 2.3f, 3.8f, 4.2f, 5.5f, 6.8f), Arrays.asList(2,2,2,2,2,2));
        Graph graph = new Graph(Arrays.asList(1.1f, 2.3f, 3.8f, 4.2f, 5.5f), Arrays.asList(6,6,6,6,6));
//        System.out.println(graph);
        adaptedKruskalApproach(graph);
        exaustiveSearchApproach(graph);
        searchWithAcceptanceApproach(graph);
    }

    private static void adaptedKruskalApproach(Graph graph) {
        printStart("Kruskal Approach");
        graph = new Graph(graph);
        AdaptedKruskal adaptedKruskal = new AdaptedKruskal(graph);
        for (Bar bar : adaptedKruskal.solve()) {
            System.out.println(bar);
        }
        printEnd("Kruskal Approach");
    }

    private static void exaustiveSearchApproach(Graph graph) {
        printStart("Exaustive Approach");
        graph = new Graph(graph);
        ExaustiveSearch exaustiveSearch = new ExaustiveSearch(graph);
        for (Bar bar : exaustiveSearch.solve()) {
            System.out.println(bar);
        }
        System.out.println("Cicles : " + exaustiveSearch.getCicles());
        printEnd("Exaustive Approach");
    }

    private static void searchWithAcceptanceApproach(Graph graph) {
        printStart(Bars.acceptancePercentage*100+"% Acceptance Approach");
        graph = new Graph(graph);
        SearchWithAcceptance searchWithAcceptance = new SearchWithAcceptance(graph);
        for (Bar bar : searchWithAcceptance.solve()) {
            System.out.println(bar);
        }
        System.out.println("Cicles : " + searchWithAcceptance.getCicles());
        printEnd(Bars.acceptancePercentage*100+"% Acceptance Approach");
    }

    private static void printStart(String str) {
        System.out.println("Starting " + str + " software...");
        System.out.println("Executing...");
    }
    private static void printEnd(String str) {
        System.out.println(str + " execution ended...");
        System.out.println("---END---");
    }
}
