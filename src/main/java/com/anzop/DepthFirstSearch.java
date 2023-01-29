package com.anzop;

import com.anzop.graph.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;

/*
    Depth First Search, DFS is a Base traversal algorithm

    time complexity O(V + E) - which is linear to the size of graph
 */


public class DepthFirstSearch extends BaseSearch {

    private int groupCode = 0;
    private int linkSerial = 0;

    private final Stack<Edge> edges;

    private final Set<String> articulationPoints;
    private final Map<String, List<Edge>> bridges;

    protected void initialize() {
        super.initialize();

        groupCode = 0;
        linkSerial = 0;
    }

    protected void bookkeeping(Edge edge) {
        super.bookkeeping(edge);

        Vertex vertex = edge.getDestination();
        vertex.setGroupCode(groupCode);
        vertex.setLinkSerial(linkSerial);
        vertex.setLowestLink(linkSerial);
        linkSerial++;
    }

    private void addArticulationPoints(Vertex v) {
        articulationPoints.add(v.getLabel());
    }

    private void addBridge(Vertex v, Edge e) {
        List<Edge> edges = bridges.getOrDefault(v.getLabel(), new ArrayList<>());
        edges.add(e);
        bridges.put(v.getLabel(), edges);
    }

    private void findArticulationsAndBridges() {
        for (Vertex from : graph.getVertices()) {
            for (Edge edge : graph.getEdges(from)) {
                Vertex to = edge.getDestination();

                if (from.getLowestLink() < to.getLowestLink()) {
                    addBridge(from, edge);

                    if (graph.getEdges(from).size() > 1) { addArticulationPoints(from); }
                    if (graph.getEdges(to).size() > 0) { addArticulationPoints(to); }
                }

                if (graph.getEdges(from).size() > 1) { addArticulationPoints(from); }
            }
        }
    }

    private void traverse() {
        Edge from = edges.pop();
        bookkeeping(from);

        for (Edge edge : graph.getEdges(from.getDestination())) {
            if (!visited.contains(edge.getDestination())) {

                edges.push(edge);
                traverse();

                from.getDestination().setLowestLink(min(
                        min(
                                from.getDestination().getLinkSerial(),
                                edge.getDestination().getLinkSerial()
                        ),
                        min(
                                from.getDestination().getLowestLink(),
                                edge.getDestination().getLowestLink()
                        )
                ));
            }

            from.getDestination().setLowestLink(min(
                    from.getDestination().getLowestLink(),
                    edge.getDestination().getLowestLink())
            );
        }
    }

    private void traverse(Edge from, Vertex to) {
        Vertex vertex = from.getDestination();
        bookkeeping(from);

        if (vertex.equals(to)) {
            return;
        }

        graph.getEdges(vertex).forEach(edge -> {
            if (!visited.contains(edge.getDestination())) {
                traverse(edge, to);
            }
        });
    }

    public DepthFirstSearch(Graph graph) {
        super(graph);

        edges = new Stack<>();
        articulationPoints = new HashSet<>();
        bridges = new HashMap<>();
    }

    public SearchResult traverseWhileNext(Vertex from) {
        initialize();

        if (graph.getVertices().contains(from)) {
            edges.push(makeStarterEdge(from));
            traverse();
        }
        return makeResponse(path);
    }

    public SearchResult traverseInto(Vertex from, Vertex to) {
        initialize();

        if (graph.getVertices().contains(from)) {
            traverse(makeStarterEdge(from), to);
        }
        return makeResponse(path);
    }

    public SearchResult fullTraverse() {
        initialize();

        graph.getVerticesSorted().forEach(vertex -> {
            if (!visited.contains(vertex)) {
                edges.push(makeStarterEdge(vertex));
                traverse();
                groupCode++;
            }
        });

        findArticulationsAndBridges();

        return makeResponse(path);
    }

    public Set<String> getArticulationPoints() {
        return articulationPoints;
    }

    public Map<String, List<Edge>> getBridges() {
        return bridges;
    }

    public List<Integer> showComponents() {
        return graph
                .getVertices()
                .stream()
                .map(Vertex::getGroupCode)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> showArticulationPoints() {
        return articulationPoints
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> showBridges() {
        return bridges
                .entrySet()
                .stream()
                .map(e -> e.getKey() + " => " + e.getValue()
                        .stream()
                        .map(Edge::getDestination)
                        .map(Vertex::getLabel)
                        .collect(Collectors.joining(", "))
                )
                .collect(Collectors.toList());
    }
}
