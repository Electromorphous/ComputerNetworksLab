import java.util.*;

public class BellmanFord {
    
    private final int num;
    private final int[] dist;
    public static int MAX_VALUE = Integer.MAX_VALUE;

    public B(int n) {
        num = n;
        dist = new int[num + 1];
    }

    private void func(int source, int[][] matrix) {
        for (int i = 1; i <= num; i++)
            dist[i] = MAX_VALUE;
        dist[source] = 0;

        for (int i = 1; i <= num - 1; i++) {
            for (int sNode = 1; sNode <= num; sNode++) {
                for (int dNode = 1; dNode <= num; dNode++) {
                    if (matrix[sNode][dNode] != MAX_VALUE && (dist[dNode] > dist[sNode] + matrix[sNode][dNode]))
                        dist[dNode] = dist[sNode] + matrix[sNode][dNode];
                }
            }
        }
        for (int sNode = 1; sNode <= num; sNode++) {
            for (int dNode = 1; dNode <= num; dNode++) {
                if (matrix[sNode][dNode] != MAX_VALUE && (dist[dNode] > dist[sNode] + matrix[sNode][dNode])) {
                    System.out.println("Negative edge cycle present. GTFO.");
                    return;
                }
            }
        }
        for (int i = 1; i <= num; i++) 
            System.out.println("Distance from " + source + " to " + i + " is : " + dist[i]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        int[][] matrix;
        System.out.println("Enter no. of nodes");
        n = sc.nextInt();

        matrix = new int[n + 1][n + 1];
        System.out.println("Enter matrix. 0 for no node.");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = sc.nextInt();
                if (i == j) {
                    matrix[i][j] = 0;
                    continue;
                }
                if (matrix[i][j] == 0)
                    matrix[i][j] = MAX_VALUE;
            }
        }

        System.out.println("Enter the source vertex.");
        int source = sc.nextInt();

        B obj = new B(n);
        obj.func(source, matrix);

        sc.close();
    }    
}
