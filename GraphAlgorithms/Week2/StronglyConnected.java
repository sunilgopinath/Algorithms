import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] adjT) {
        // Step 1: find topological ordering of graph
        Stack<Integer> stack = new Stack<Integer>(); // items are added end first
        int[] visited = new int[adj.length];
        for(int i = 0; i < adj.length; i++) {
          if(visited[i] != 1) {
            dfs(adjT, visited, stack, i);
          }
        }
        // Step 2: compute connected components as per undirected graph
        int scc = 0;
        visited = new int[adj.length];
        while(!stack.isEmpty()) {
          int v = stack.pop();
          if(visited[v] != 1) {
            dfs(adj, visited, v);
            scc++;
          }
        }
        return scc;
    }

    // we need plain old dfs here
    private static void dfs(ArrayList<Integer>[] adj, int[] visited, int v) {
      visited[v] = 1;
      for(int i = 0; i < adj[v].size();i++) {
        if(visited[adj[v].get(i)] != 1) {
          dfs(adj, visited, adj[v].get(i));
        }
      }
    }

    // must keep track of order
    private static void dfs(ArrayList<Integer>[] adj, int[] visited, Stack<Integer> stack, int v) {
      visited[v] = 1;
      for(int i = 0; i < adj[v].size();i++) {
        if(visited[adj[v].get(i)] != 1) {
          dfs(adj, visited, stack, adj[v].get(i));
        }
      }
      stack.push(v);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] adjT = (ArrayList<Integer>[])new ArrayList[n]; // lazy way of gT
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            adjT[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adjT[y - 1].add(x - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, adjT));
    }
}
