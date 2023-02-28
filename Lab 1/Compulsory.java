public class Compulsory 
{

    public static void main(String args[]) {
        System.out.println("Hello World!");
    
    String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    int sum = 0;
    int n = (int) (Math.random() * 1_000_000);
    int a = 10101;
    int produs = n*3;
    int suma = produs + a;
    String hex = "FF";
    int c = Integer.parseInt(hex, 16);
    int suma2 = suma + c;
    int number=suma2 * 6;

        while (number > 0 || sum > 9)
        {
            if(number==0)
            {
                number=sum;
                sum=0;
            }
            sum = sum + number % 10;
            number = number/10;

        } 
        System.out.println("Willy-nilly, this semester I will learn " + languages[sum]);
    }
}
