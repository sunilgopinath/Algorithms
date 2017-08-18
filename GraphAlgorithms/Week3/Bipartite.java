import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        return bfs(adj, 0);
    }

    private static int bfs(ArrayList<Integer>[] adj, int s) {
      int[] color = new int[adj.length];
      Queue<Integer> q = new LinkedList<Integer>();
      for(int i = 0; i < adj.length;i++) {
        color[i] = -1;
      }
      color[s] = 0;
      q.offer(s);
      while(!q.isEmpty()) {
        int u = q.poll();
        for(int v : adj[u]) {
          // Case 1: v has no color, assign opposite
          // Case 2: v has a color, check if opposite to u
          if(color[v] == -1) {
            int m = color[u] ^ 1;
            color[v] = color[u] ^ 1;
            q.offer(v);
          } else { // color[v] should be set
            if(color[v] == color[u]) {
              return 0;
            }
          }
        }
      }
      return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}
