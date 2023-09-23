import java.util.Scanner;
public class Main
{
    public static void problem3()
    {

        Scanner in = new Scanner(System.in);
        int cross = 0;
        int X = 0;
        int Y = 0;

        System.out.println("введите координаты клада: \n");
        int finishX = in.nextInt();
        int finishY = in.nextInt();

        String direction = "";//направление (с,ю,з,в)
        int move = 0; // количество шагов в определенном направлении,которое мы ввели

        int j = 0; //шаг //int count = 0;
        int C = 0;// конечное число шагов,потом сравним с i

        if (X == finishX && Y == finishY)
        {
            System.out.println("вы находитесь на месте клада! \n");
        }
        else
        {
            while (true)
            {
                direction = in.next();
                if (direction.equals("стоп"))
                {
                    break;
                }
                move = in.nextInt();
                in.nextLine();
                if (direction.equals("север"))
                {
                    Y += move;
                    j += 1;
                }
                else if (direction.equals("юг"))
                {
                    Y -= move;
                    j += 1;
                }
                else if (direction.equals("запад"))
                {
                    X -= move;
                    j += 1;
                }
                else if (direction.equals("восток"))
                {
                    X += move;
                    j += 1;
                }

                if (X == finishX && Y == finishY)
                {
                    cross += 1;
                    if (cross == 1)
                    {
                        C = j;

                    }
                }
            }
        }
        System.out.println(C);
    }
}