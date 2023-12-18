import java.util.ArrayList;

public class Hall
{
    private String name;
    private int capacity;

    public ArrayList<Row> getRows() {
        return rows;
    }

    private ArrayList<Row> rows;
    private int numSeatsPerRow;
    private int numRows;

    public int getNumSeatsPerRow() {
        return numSeatsPerRow;
    }


    public int getNumRows() {
        return numRows;
    }
    public Hall(String name, int numSeatsPerRow, int numRows)
    {
        this.name = name;
        this.numSeatsPerRow = numSeatsPerRow;
        this.numRows = numRows;
        this.capacity = numSeatsPerRow * numRows;
        this.rows = new ArrayList<>();
        for (int i = 1; i <= numRows; i++)
        {
            this.rows.add(new Row(i, numSeatsPerRow));
        }
    }


    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}
