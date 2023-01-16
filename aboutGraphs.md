Study notes on [Graph Algorithms](https://www.youtube.com/watch?v=09_LlHjoEiY&list=WL&index=2) presented by William Fiset and freecodecamp.org

### BASICS

- v - Node, Vertex
- e - Edge, association between nodes

Edges often represented as (u, v, w)
- u - from edge
- v - to edge
- w - weight of edge

Directed (Digraph) or Undirected

Cyclical or Acyclical

Weighted - edges have a value, a 'cost'

A path is said to be shorter than another path if is cumulative weights are less. Shortest path as in number of edges / nodes is one where all edges are assumed a constant weight.


### FEW TYPES OF GRAPHS

A. Tree
- Undirected, Acyclic
- N nodes, N-1 edges

B. Rooted tree
- has a root
- in-tree - all nodes point towards too
- out-tree - all nodes point away from root

C. Directed-Acyclic Graph, DAG
- may diverge (like all trees)
- may converge
- may not cycle
- example - dependencies in code

D. Bipartite graph
- Nodes may be split in two groups such that every edge is a transition between the groups
- Two colourable
- No odd-length cycle
- Eg when computing dependencies of supply and demand units, workers and jobs

E. Complete graphs
- Every Node are connected to all other nodes
- Single node graph has no edges as nodes need not connect to themselves


### REPRESENTATION

Adjacency Matrix
- node by node matrix, quadratic over node count
- value in Matrix[i][j] (also coordination (y, x)) represents the edge weight of that traversal
- value from Edge to itself often assumed to be 0
- Pros
    - Lookup time is O(1)
    - Spatially efficient for dense graphs
- Cons
    - Scan traversal time is O(V^2) - though still 'minimal' for completely dense graphs
    - Spatially inefficient for graphs that are sparse or very big

Adjacency List
- A Map of Nodes to List of Transitions
- A transition is a tuple of the edge and the target node
- Pros
    - Scan traversal is O(count of value elements in Map)
        - there are no 'null' values to loop over, other than for isolated Nodes
    - Spatially efficient for sparse graphs
- Cons
    - Lookup time is O(count of Edges)
    - Spatially inefficient for dense graphs

Edge List
- List[(u, v, w)]
- Great format to pass around and as inputs
- Particularly if unordered suffices


### GENERAL PROBLEMS FOR GRAPHS

A. Shortest path - traversal from source to destination with least weight, sum of costs

B. Connectivity - is there a path between given source and destination nodes?

C. Negative Cycles - when an edge may have negative weights
- individual edges with negative weights matters not
    - only if the is a cycle and its sum of weights is negative
- some algorithms require nonexistence of negative cycles
- some problems represented as graphs with negative weights may introduce hidden opportunities

D. Strongly Connected Components, SCC -  self-contained cycles within a directed graph
- discovering an SCC may be an intermediate step in the full solution

E. Traveling Salesman's Problem - what is the shortest path that traverses all the nodes?

F. Finding Bridges - edges that are the only connection between two groups of nodes
- bridges usually identify weak points eg. in terms of total flow, or load capacity through the system

G. Articulation Points - similar to bridges but for nodes

H. Minimum Spanning Tree
- Given a weighted graph
- Does it contain a tree that spans all nodes?
- If contains multiple, which tree has the leas cumulative cost?
- good to discover transportation and more ideal cache locations

I. Max Flow in a Flow Network
- sources and sinks, intermediate nodes that take from sources to sinks
- intermediate nodes have a weight, a capacity
- given an infinite stream, how much capacity can maximally be in the flow
- should help find bottlenecks
- should help to identify boost points with optimal cost to increase in flow?
