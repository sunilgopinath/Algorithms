import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        return bfs(adj, s, t);
    }

    private static int bfs(ArrayList<Integer>[] adj, int s, int t) {
      if(s == t) {
        return 0;
      }
      int[] dist = new int[adj.length];
      for(int i = 0; i < adj.length; i++) {
        dist[i] = Integer.MAX_VALUE;
      }
      dist[s] = 0;
      Queue<Integer> q = new LinkedList<Integer>();
      q.offer(s);
      while(!q.isEmpty()) {
        int u = q.poll();
        for(int i = 0; i < adj[u].size();i++) {
          if(dist[adj[u].get(i)] == Integer.MAX_VALUE) {
            q.offer(adj[u].get(i));
            dist[adj[u].get(i)] = dist[u] + 1;
          }
        }
      }
      if(dist[t] != Integer.MAX_VALUE) {
        return dist[t];
      }
      return -1;
    }

    private static List<Integer> reconstructPath(int s, int t, int[] prev) {
      List<Integer> result = new ArrayList<Integer>();
      while(t != s) {
        result.add(t);
        t = prev[t];
      }
      Collections.reverse(result);
      return result;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}
