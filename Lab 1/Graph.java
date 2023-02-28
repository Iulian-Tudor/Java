import java.util.Scanner;

public class Graph {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[][] adjMatrix = createRegularGraphAdjacencyMatrix(n, d);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] createRegularGraphAdjacencyMatrix(int n, int d) {
        int[][] adjMatrix = new int[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= d/2; j++) {
                int v1 = (i + j) % n;
                int v2 = (i - j + n) % n;
                adjMatrix[i][v1] = 1;
                adjMatrix[i][v2] = 1;
            }
        }

        return adjMatrix;
    }
}
