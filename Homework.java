import java.util.*;

class Homework {

    static void Square(int n)
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
    }

    public static void main (String[] args)
    {   
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Square(n);
    }
    
}
