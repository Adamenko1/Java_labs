import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), j = 0, sum = 0;

        int[] num = new int[n]; // создание массива
        for (int i = 0; i < n; i++)
        {
            num[i] = in.nextInt();
            if (j % 2 == 0)
            {
                sum = sum + num[i];
            }
            else
            {
                sum = sum - num[i];
            }
            j++;
        }
        in.close();
        System.out.println("сумма:"+sum);
    }
}