import java.util.Scanner;

public class CycleGraph {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] adjMatrix = createCycleGraph(n);
        System.out.println("Matricea C" + n + ":");
        printMatrix(adjMatrix);
        System.out.println();

        sc.close();



        for (int i = 1; i <= n; i++) {
            int[][] A_pow_i = power(adjMatrix, i);
            System.out.println("A^" + i + ":");
            printMatrix(A_pow_i);
        }

    }

    public static int[][] createCycleGraph(int n) {
        int[][] adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            adjMatrix[i][(i+1)%n] = 1;
            adjMatrix[(i+1)%n][i] = 1;
        }
        return adjMatrix;
    }

    public static int[][] power(int[][] A, int n) {
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            result[i][i] = 1;
        }
        while (n > 0) {
            if (n % 2 == 1) {
                result = multiply(result, A);
            }
            A = multiply(A, A);
            n /= 2;
        }
        return result;
    }


    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

