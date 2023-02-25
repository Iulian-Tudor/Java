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

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (i + j) % n + 1;
            }
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) { 
            String rowString = "Row " + (i+1) + ": "; 
            String colString = "Col " + (i+1) + ": "; 

            for (int j = 0; j < n; j++) { 
                rowString += matrix[i][j]; 
                colString += matrix[j][i]; 
            }

            System.out.println(rowString); 
            System.out.println(colString); 

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
