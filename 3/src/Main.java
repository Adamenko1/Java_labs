import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Cinema cinema = new Cinema("MyCinema");
        cinema.addHall(new Hall("Зал 1", 6, 4));
        cinema.addHall(new Hall("Зал 2", 5, 5));
        cinema.addShowing(new Showing("9:00", 120, cinema.halls.get(0)));
        cinema.addShowing(new Showing("18:30", 90, cinema.halls.get(1)));

        System.out.println("Добро пожаловать в кинотеатр " + cinema.getName());
        Row r = new Row(1, 5);
        System.out.println(r.getSeats().get(1).getSeatNumber());


        while (true)
        {
            System.out.println("\nПожалуйста, выберите действие:");
            System.out.println("1. Просмотреть сеансы");
            System.out.println("2. Купить билеты");
            System.out.println("0. Выйти из программы");

            int choice = scanner.nextInt();
            if (choice == 0)
            {
                System.out.println("До свидания!");
                break;
            } else if (choice == 1)
            {
                // Просмотр сеансов
                System.out.println("\nДоступные сеансы:");
                ArrayList<Showing> showings = cinema.getShowings();
                for (int i = 0; i < showings.size(); i++) {
                    Showing showing = showings.get(i);
                    Hall hall = showing.getHall();
                    int availableSeats = hall.getCapacity() - showing.getSoldTickets();
                    System.out.println((i + 1) + ". Зал: " + hall.getName() +
                            ", Время: " + showing.getStartTime() +
                            ", Продолжительность: " + showing.getDuration() +
                            " минут, Доступно мест: " + availableSeats);
                }
            }
            else if (choice == 2)
            {
                // Покупка билетов
                System.out.println("\nВведите номер сеанса:");
                int showingNum = scanner.nextInt();
                Showing showing = cinema.getShowings().get(showingNum - 1);
                Hall hall = showing.getHall();

                System.out.println("Введите количество билетов:");
                int numTickets = scanner.nextInt();
                System.out.println("Выберите места:");
                for (int i = 0; i < hall.getNumRows(); i++)
                {
                    for (int j = 0; j < hall.getNumSeatsPerRow(); j++)
                    {
                        if (showing.isSeatAvailable(i, j+1))
                        {
                            System.out.print(hall.getRows().get(i).getSeats().get(j).getSeatNumber() + " ");
                        } else
                        {
                            System.out.print("X ");
                        }
                    }
                    System.out.println("");
                }

                ArrayList<Integer> selectedSeats = new ArrayList<>();
                ArrayList<String> tmpArr = new ArrayList<>();
                boolean tmpFlag = false;
                for (int i = 0; i < numTickets; i++)
                {
                    System.out.println("Выберите ряд");
                    int seatRow = scanner.nextInt();
                    System.out.println("Выберите место");
                    int seatNumber = scanner.nextInt();
                    String tmpStr = seatRow + "-" + seatNumber;
                    tmpArr.add(tmpStr);
                    for (int j = 0; j < tmpArr.size(); j++)
                    {
                        for (int k = j+1; k < tmpArr.size(); k++)
                        {
                            if (tmpArr.get(j).equals(tmpArr.get(k)))
                            {
                                tmpFlag = true;
                            }
                        }
                    }
                    if (showing.isSeatAvailable(seatRow-1, seatNumber) && !tmpFlag)
                    {
                        selectedSeats.add(seatRow-1);
                        selectedSeats.add(seatNumber);
                    }
                    else
                    {
                        System.out.println("Место " + seatRow + "-" + seatNumber + " уже занято!");
                        break;
                    }
                }

                if (selectedSeats.size()/2 == numTickets)
                {
                    System.out.println("Покупка успешна!");
                    System.out.println("Количество проданных билетов: " + numTickets);
                    showing.sellTickets(numTickets, selectedSeats);
                }
            }
        }
    }
}