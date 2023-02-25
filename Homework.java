import java.util.*;

class Homework {

    /*static void Square(int n)
    {
        int k = n+1;

        for (int i=1; i<=n;i++)
        {
            int placeholder = k;

            while (placeholder<=n)
            {
                System.out.print(placeholder + " ");
                placeholder++;
            }

            for (int j = 1; j < k; j++)
                System.out.print(j + " ");
            
            k--;
            System.out.println();
        }
    } */

    public static void main (String[] args)
    {   
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //Square(n);
        System.out.println();
        
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (i + j) % n + 1;
            }
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            StringBuilder rowString = new StringBuilder();
            StringBuilder colString = new StringBuilder();
            for (int j = 0; j < n; j++) {
                rowString.append(matrix[i][j]).append(" ");
                colString.append(matrix[j][i]).append(" ");
            }
            System.out.println("Row " + (i+1) + ": " + rowString.toString());
            System.out.println("Col " + (i+1) + ": " + colString.toString());
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        if (elapsedTime < 1000000) {
            System.out.println("Elapsed time: " + elapsedTime + " ns");
        } else {
            System.out.println("Elapsed time: " + elapsedTime / 1000000 + " ms");
        }
    }
    
}
