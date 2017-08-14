import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
        if(x == y) {
          return 1;
        }
        Set<Integer> set = explore(adj, x);
        return set.contains(y) ? 1 : 0;
    }

    private static Set<Integer> explore(ArrayList<Integer>[] adj, int x) {
      Set<Integer> set = new HashSet<Integer>();
      explore(adj, x, set);
      return set;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}
