import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        //write your code here
        return dfs(adj);
    }

    private static int dfs(ArrayList<Integer>[] adj) {
      int cc = 0;
      Set<Integer> set = new HashSet<Integer>();
      for(int i = 0; i < adj.length; i++){
        if(!set.contains(i)) {
          explore(adj, i, set);
          cc++;
        }
      }
      return cc;
    }

    private static void explore(ArrayList<Integer>[] adj, int x, Set<Integer> set) {
      set.add(x);
      for(int i = 0; i < adj[x].size();i++) {
        if(!set.contains(adj[x].get(i))) {
          explore(adj, adj[x].get(i), set);
        }
      }
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
        System.out.println(numberOfComponents(adj));
    }
}
