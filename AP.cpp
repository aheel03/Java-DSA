#include <bits/stdc++.h>
using namespace std;

const int MAX_N = 1000;

vector<vector<int>> adj(MAX_N);               // Adjacency list for the graph.
vector<int> disc(MAX_N, -1);                  // Discovery times: initialized to -1 (unvisited).
vector<int> low(MAX_N, 0);                    // Low link values.
vector<int> parent(MAX_N, -1);                // Parent of each node in the DFS tree.
vector<bool> isArticulationPoint(MAX_N, false); // Marker for articulation points.
int timer = 0;                                // Global timer for DFS discovery times.

void dfsAP(int u) {
    timer++;
    disc[u]=timer;
    low[u]=timer;
    int children=0;

    for (int v : adj[u]) {
        if (disc[v] == -1) {
            children++;
            parent[v] = u;
            dfsAP(v);

            low[u] = min(low[u], low[v]);

            if (parent[u]!=-1 && low[v]>=disc[u]){
                isArticulationPoint[u] = true;
            }
        }
        else if (v!=parent[u]) {
            low[u]=min(low[u],disc[v]);
        }
    }

    // Special case for root: if it has more than one child, it is an articulation point.
    if (parent[u]==-1 && children>1){
        isArticulationPoint[u] = true;
    }
}

int main() {
    int n, m;
    cout << "Enter the number of vertices and edges: ";
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        adj[i].clear();
        disc[i] = -1;
        low[i] = 0;
        parent[i] = -1;
        isArticulationPoint[i] = false;
    }
    timer = 0;

    cout << "Enter the edges (u v) (0-indexed):" << endl;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    for (int i = 0; i < n; i++) {
        if (disc[i] == -1)
            dfsAP(i);
    }

    cout << "Articulation Points:" << endl;
    for (int i = 0; i < n; i++) {
        if (isArticulationPoint[i])
            cout << i << " ";
    }
    cout << endl;
    
    return 0;
}
