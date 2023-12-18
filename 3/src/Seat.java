public class Seat {
    public int getSeatNumber() {
        return seatNumber;
    }

    private int seatNumber;
    private boolean occupied;
    public Seat(int seatNumber)
    {
        this.seatNumber = seatNumber;
        this.occupied = false;
    }

    public void setOccupied() {
        this.occupied = true;
    }

    public boolean isOccupied() {
        return this.occupied;
    }
}
