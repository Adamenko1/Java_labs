import java.util.ArrayList;

public class Cinema
{
    public String getName() {
        return name;
    }

    private String name;

    public ArrayList<Hall> halls = new ArrayList<>();
    private ArrayList<Showing> showings = new ArrayList<>();

    public Cinema(String name) {
        this.name = name;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    public void addShowing(Showing showing) {
        showings.add(showing);
    }

    public ArrayList<Showing> getShowings() {
        return showings;
    }
}

