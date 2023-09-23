import java.util.Scanner;
public class Main
{
    public static void problem5()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("введите целое трехзначное число: \n");
        int num = in.nextInt();

        //проверка
        if (num > 999 || num < 100) {
            System.out.println("число введено неправильно\n") ;
        }
        else
        {

            //найдем разряды числа num
            int hundred = num / 100;
            int ten = num % 100 / 10;
            int one = num % 10;

            System.out.println(hundred);
            System.out.println(ten);
            System.out.println(one);

            if ((hundred + ten + one) % 2 == 0 && (hundred * ten * one) % 2 == 0)
            {
                System.out.println("число является дважды четным \n");
            }
            else
            {
                System.out.println("число не является дважды четным \n");
            }

        }


    }
}