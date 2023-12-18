import java.util.ArrayList;

public class Showing
{
    private String startTime;
    private int duration;
    private Hall hall;

    private int soldTickets = 0;

    public Showing(String startTime, int duration, Hall hall)
    {
        this.startTime = startTime;
        this.duration = duration;
        this.hall = hall;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public Hall getHall() {
        return hall;
    }

    public int getSoldTickets() {
        return soldTickets;
    }

    public boolean isSeatAvailable(int row, int seat) {
        return getHall().getRows().get(row).isSeatAvailable(seat);
    }

    public void sellTickets(int numTickets, ArrayList<Integer> selectedSeats)
    {
        soldTickets += numTickets;
        for (int i = 0; i < selectedSeats.size()/2; i++)
        {
            this.hall.getRows().get(selectedSeats.get(i*2)).markSeatAsOccupied(selectedSeats.get(2*i+1));
        }

    }

}