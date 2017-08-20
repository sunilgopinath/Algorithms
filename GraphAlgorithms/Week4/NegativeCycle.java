import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
        int[] dist = new int[adj.length];
        for(int i = 0; i < adj.length;i++) {
          dist[i] = Integer.MAX_VALUE;
        }
        // pick arbitrary source vertex
        dist[0] = 0;
        for(int i = 0; i < adj.length; i++) {
          for(int j = 0; j < adj[i].size();j++) {
            if(i == adj.length - 1 && relax(adj, cost, dist, i, adj[i].get(j))){
              return 1;
            }
          }
        }
        return 0;
    }

    private static boolean relax(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist, int u, int v) {
      if(dist[v] > dist[u] + weight(adj, cost, u, v)) {
        dist[v] = dist[u] + weight(adj, cost, u, v);
        return true;
      }
      return false;
    }

    private static int weight(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int u, int v) {
      int x = adj[u].indexOf(v);
      return cost[u].get(x);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}
