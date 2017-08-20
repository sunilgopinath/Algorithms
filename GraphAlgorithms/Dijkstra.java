import java.util.*;

public class Dijkstra {

  static class Node implements Comparable<Node> {
    int index;
    long distance;

    public Node(int index, long distance) {
      this.index = index;
      this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
      if(this.distance > o.distance) return 1;
      else if(this.distance < o.distance) return -1;
      else return 0;
    }
  }
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
      Set<Integer> determined = new HashSet<Integer>();
      int[] dist = new int[adj.length];
      for (int i = 0; i < adj.length; i++) {
        dist[i] = Integer.MAX_VALUE;
      }
      dist[s] = 0;
      PriorityQueue<Node> q = new PriorityQueue<Node>();
      q.offer(new Node(s, dist[s]));

      while(!q.isEmpty()) {
        Node min = q.remove();
        int u = min.index;
        // explore neighbors:
        for(int i = 0; i < adj[u].size();i++) {
          int v = adj[u].get(i);
          if(dist[v] > dist[u] + cost(adj, cost, u, v)) {
            dist[v] = dist[u] + cost(adj, cost, u, v);
            Node n = new Node(v, dist[v]);
            q.offer(n);
          }
        }
      }
      if(dist[t] == Integer.MAX_VALUE) {
			   return -1;
      }
      //System.out.println(Arrays.toString(dist));
      return dist[t];
    }

    private static int cost(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
      int v = adj[s].indexOf(t);
      return cost[s].get(v);
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}
