package Figures;

public class King extends Figure
{
    public King(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1)
    {
        if ((Math.abs(row - row1)==1 && col ==col1) ||(row == row1 && Math.abs(col -col1)==1) || ((Math.abs(row - row1) ==1 && Math.abs(col-col1)==1)))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return this.canMove(row, col, row1, col1);
    }
}
