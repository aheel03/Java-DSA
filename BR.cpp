#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX_N = 1000;  // Maximum number of vertices expected

// Global data structures allocated with fixed size.
vector<vector<int>> adj(MAX_N);      // Adjacency list for the graph.
vector<int> disc(MAX_N, -1);         // Discovery times for vertices; -1 means unvisited.
vector<int> low(MAX_N, 0);           // Low link values.
vector<int> parent(MAX_N, -1);       // Parent of each vertex in the DFS tree.
int timer = 0;                       // Global timer used to assign discovery times.

// To store bridges, we'll keep a vector of pairs (u, v).
vector<pair<int, int>> bridges;

// DFS function that detects bridges.
void dfsBridge(int u) {
    // Set discovery time and low value of vertex u.
    disc[u] = low[u] = ++timer;
    
    // Explore all adjacent vertices.
    for (int v : adj[u]) {
        // If v is not visited, it's a tree edge.
        if (disc[v] == -1) {
            parent[v] = u;
            dfsBridge(v);
            
            // After DFS, update low[u] based on child's low value.
            low[u] = min(low[u], low[v]);
            
            // Condition for a bridge: no back edge from v or its descendants 
            // can reach u or an earlier vertex.
            if (low[v] > disc[u])
                bridges.push_back({u, v});
        }
        // If v is visited and isn't the parent, then it's a back edge.
        else if (v != parent[u]) {
            low[u] = min(low[u], disc[v]);
        }
    }
}

int main() {
    int n, m;
    cout << "Enter the number of vertices and edges: ";
    cin >> n >> m;

    // Prepare the first n entries of our global vectors.
    // (Clear adj lists for the first n nodes and reinitialize discovery times.)
    for (int i = 0; i < n; i++) {
        adj[i].clear();
        disc[i] = -1;
        low[i] = 0;
        parent[i] = -1;
    }
    timer = 0;
    bridges.clear();

    cout << "Enter the edges (u v) (0-indexed):" << endl;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        // As the graph is undirected, add both directions.
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    // Run DFS from every vertex to cover all connected components.
    for (int i = 0; i < n; i++) {
        if (disc[i] == -1)
            dfsBridge(i);
    }

    // Output the detected bridges.
    cout << "Bridges in the graph:" << endl;
    for (const auto &edge : bridges)
        cout << edge.first << " - " << edge.second << endl;

    return 0;
}
