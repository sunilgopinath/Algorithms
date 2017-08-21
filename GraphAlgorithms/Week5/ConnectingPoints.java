import java.util.*;

public class ConnectingPoints {

  static class Vertex implements Comparable<Vertex>{
    int x;
    int y;
    double cost;

    public Vertex(int x, int y, double cost) {
      this.x = x;
      this.y = y;
      this.cost = cost;
    }

    @Override
    public int compareTo(Vertex v) {
      if(this.cost > v.cost) return 1;
      else if(this.cost < v.cost) return -1;
      else return 0;
    }

    @Override
    public String toString() {
      return "x:" + this.x + ", y:"+this.y+", cost:"+this.cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex c = (Vertex) o;

        // Compare the data members and return accordingly
        return Integer.compare(x, c.x) == 0
                && Integer.compare(y, c.y) == 0;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

  }
    private static double minimumDistance(int[] x, int[] y) {
        //assume at least 1 point, so pick first point
        Vertex[] vertices = new Vertex[x.length];
        for(int i = 0; i < x.length;i++) {
          Vertex v = new Vertex(x[i],y[i],Integer.MAX_VALUE);
          vertices[i] = v;
        }
        Vertex v = new Vertex(x[0], y[0], 0);
        PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
        Set<Vertex> s = new HashSet<Vertex>();
        q.offer(v);
        while(!q.isEmpty()) {
          Vertex min = q.remove();
          s.add(min);
          // explore neighbors
          for(int i = 0; i < vertices.length; i++) {
            // every point in the graph is reachable from any point
            // so they are all neighbors!
            double currCost = vertices[i].cost;
            double cost = calculateCost(min, vertices[i]);
            if(!s.contains(vertices[i]) && !vertices[i].equals(min) && currCost > cost) {
              vertices[i].cost = cost;
              Vertex vtx = new Vertex(vertices[i].x, vertices[i].y, cost);
              q.offer(vtx);
            }
          }
        }

        double result = 0.;
        for(Vertex vt : s) {
          result += vt.cost;
        }
        return result;
    }

    private static double calculateCost(Vertex v1, Vertex v2) {
      return Math.sqrt(Math.pow((v1.x-v2.x), 2) + Math.pow((v1.y-v2.y), 2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}
