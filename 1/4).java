import java.util.Scanner;

public class Main
{
    static int min(int[] mas, int length) // определение минимального элемента массива
    {
        int min = 100000;
        for (int i = 0; i < length; i++)
        {
            if (mas[i] < min)
            {
                min = mas[i];
            }
        }
        return min;
    }

    public static void main(String[] args)
    {
        int min = 1000, d = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), t = 1000;
        int[] weight = new int[t];

        for (int i = 0; i < n; i++) // n - кол-во дорог
        {
            t = in.nextInt(); // вводим кол-во тоннелей
            for (int j = 0; j < t; j++) // t - кол-во тоннелей
            {
                weight[j] = in.nextInt(); // weight высоты, вводим их
            }
            if (min > min(weight, t)) // определение минимальной высоты
            {
                min = min(weight, t);
                d = i + 1; // запоминаем значение дороги (+ 1 т.к. начальное значение i было 0)
            }
        }
        System.out.println(d+" "+min);
    }
}