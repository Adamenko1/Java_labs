import java.util.ArrayList;

public class Row
{

    private int rowNumber;


    private int numSeats;

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    private ArrayList<Seat> seats;

    public Row(int rowNumber, int numSeats)
    {
        this.rowNumber = rowNumber;
        this.numSeats = numSeats;
        seats = new ArrayList<>();
        for (int i = 1; i <= this.numSeats; i++)
        {
            seats.add(new Seat(i));
        }
    }
    public void markSeatAsOccupied(int seatNumber)
    {
        Seat seat = seats.get(seatNumber - 1);
        seat.setOccupied();
    }

    public boolean isSeatAvailable(int seatNumber)
    {
        Seat seat = seats.get(seatNumber - 1);
        return !seat.isOccupied();
    }


}