import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board
{
    //TODO: Список фигур и начальное положение всех фигур
    private Figure  fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorOfCheckedKing() {
        return colorOfCheckedKing;
    }

    private char colorOfCheckedKing = ' ';

    int[] bK = new int[2];
    int[] localbK = new int[2];
    int[] wK = new int[2];
    int[] localwK = new int[2];
    ArrayList<String> pathToCheckMateB = new ArrayList<>();
    ArrayList<String> pathToCheckMateW = new ArrayList<>();
    ArrayList<String> attackingFiguresB = new ArrayList<>();
    ArrayList<String> attackingFiguresW = new ArrayList<>();
    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init()
    {
        this.fields[0] = new Figure[]
        {
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'),new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]
        {
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
        };

        this.fields[7] = new Figure[]
        {
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'),new Rook("R", 'b')
        };
        this.fields[6] = new Figure[]
        {
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col)
    {
        Figure figure = this.fields[row][col];
        if (figure == null)
        {
            return "    ";
        }
        return " "+figure.getColor()+figure.getName()+" ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public boolean canMoveWithAccountOfOtherFigures(int row1, int col1, int row2, int col2)
    {
        boolean moveFlag = true;
        if (this.fields[row1][col1].canMove(row1, col1, row2, col2) && (row1 >=0 && row1 < 8 )&&(col1 >=0 && col1 < 8)&&(row2 >=0 && row2 < 8 )&&(col2 >=0 && col2 < 8) && !((row1==row2) && (col1==col2)))
        {
            //TODO: нужна, чтобы проверять, может ли король передвинуться на другую клетку или могут ли другие фигуры передвинуться, чтобы прикрыть короля
            for (int i = 1; i <= 7 - (7 - Math.max(Math.abs(row1 - row2), Math.abs(col1 - col2))); i++)
            {
                if (row1 - row2 > 0 && col1 - col2 > 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 - i][col1 - i] == null))
                    {// && this.fields[row1-i][col1-i].getColor() == figure.getColor()) {
                        moveFlag = false;
                        break;
                    }
                } else if (row1 - row2 > 0 && col1 - col2 < 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 - i][col1 + i] == null))
                    {// && this.fields[row1-i][col1+i].getColor() == figure.getColor()) {
                        moveFlag = false;
                        break;
                    }
                } else if (row1 - row2 < 0 && col1 - col2 > 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 + i][col1 - i] == null))
                    {// && this.fields[row1+i][col1-i].getColor() == figure.getColor()) {
                        moveFlag = false;
                        break;
                    }
                } else if (row1 - row2 < 0 && col1 - col2 < 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 + i][col1 + i] == null))
                    {// && this.fields[row1+i][col1+i].getColor() == figure.getColor()) {
                        moveFlag = false;
                        break;
                    }
                } else if (row1 - row2 < 0 && col1 - col2 == 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 + i][col1] == null))
                    { // && this.fields[row1+i][col1].getColor() == figure.getColor()) {
                        moveFlag = false;
                        break;
                    }
                } else if (row1 - row2 > 0 && col1 - col2 == 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 - i][col1] == null))
                    {// && this.fields[row1-i][col1].getColor() == figure.getColor()) {
                        moveFlag = false;
                        break;
                    }
                } else if (row1 - row2 == 0 && col1 - col2 < 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1][col1 + i] == null))
                    { // && this.fields[row1][col1+i].getColor() == figure.getColor()) {
                        moveFlag = false;
                        break;
                    }
                } else if (row1 - row2 == 0 && col1 - col2 > 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1][col1 - i] == null))
                    {// && this.fields[row1][col1-i].getColor() == figure.getColor()) {
                        moveFlag = false;
                        break;
                    }
                }
            }
        } else
        {
            moveFlag = false;
        }
        return moveFlag;
    }

    public boolean canAttackWithAccountOfOtherFigures(int row1, int col1, int row2, int col2) {
        boolean attackFlag = true;
        if (this.fields[row1][col1].canMove(row1, col1, row2, col2) && (row1 >=0 && row1 < 8 )&&(col1 >=0 && col1 < 8)&&(row2 >=0 && row2 < 8 )&&(col2 >=0 && col2 < 8) && !((row1==row2) && (col1==col2)))
        {
            //TODO: нужна, чтобы проверять, может ли король передвинуться на другую клетку или могут ли другие фигуры передвинуться, чтобы прикрыть короля
            for (int i = 1; i < 7 - (7 - Math.max(Math.abs(row1 - row2), Math.abs(col1 - col2))); i++)
            {
                if (row1 - row2 > 0 && col1 - col2 > 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 - i][col1 - i] == null) && this.fields[row1-i][col1-i].getColor() != this.fields[row2][col2].getColor())
                    {
                        attackFlag = false;
                        break;
                    }
                } else if (row1 - row2 > 0 && col1 - col2 < 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 - i][col1 + i] == null) && this.fields[row1-i][col1+i].getColor() != this.fields[row2][col2].getColor())
                    {
                        attackFlag = false;
                        break;
                    }
                } else if (row1 - row2 < 0 && col1 - col2 > 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 + i][col1 - i] == null) && this.fields[row1+i][col1-i].getColor() != this.fields[row2][col2].getColor())
                    {
                        attackFlag = false;
                        break;
                    }
                } else if (row1 - row2 < 0 && col1 - col2 < 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 + i][col1 + i] == null) && this.fields[row1+i][col1+i].getColor() != this.fields[row2][col2].getColor())
                    {
                        attackFlag = false;
                        break;
                    }
                } else if (row1 - row2 < 0 && col1 - col2 == 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 + i][col1] == null) && this.fields[row1+i][col1].getColor() != this.fields[row2][col2].getColor())
                    {
                        attackFlag = false;
                        break;
                    }
                } else if (row1 - row2 > 0 && col1 - col2 == 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1 - i][col1] == null) && this.fields[row1-i][col1].getColor() != this.fields[row2][col2].getColor())
                    {
                        attackFlag = false;
                        break;
                    }
                } else if (row1 - row2 == 0 && col1 - col2 < 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1][col1 + i] == null) && this.fields[row1][col1+i].getColor() != this.fields[row2][col2].getColor())
                    {
                        attackFlag = false;
                        break;
                    }
                } else if (row1 - row2 == 0 && col1 - col2 > 0 && !this.fields[row1][col1].getName().equals("N"))
                {
                    if (!(this.fields[row1][col1 - i] == null) && this.fields[row1][col1-i].getColor() != this.fields[row2][col2].getColor())
                    {
                        attackFlag = false;
                        break;
                    }
                }
            }
        } else {
            attackFlag = false;
        }
        return attackFlag;
    }

    public boolean isCheck()
    {
        boolean otherFigureFlag = false;
        boolean canAddAttackingFigureB = true;
        boolean canAddAttackingFigureW = true;
        for (int i = 0; i < this.fields.length; i++)
        {
            for (int j = 0; j < this.fields[0].length; j++)
            {
                if (!(this.fields[i][j] == null))
                    if (this.fields[i][j].getName().equals("K"))
                    {
                        if (this.fields[i][j].getColor() == 'b')
                        {
                            localbK[0] = i;
                            localbK[1] = j;
                        } else
                        {
                            localwK[0] = i;
                            localwK[1] = j;
                        }
                    }
            }
        }
        for (int i = 0; i < this.fields.length; i++)
        {
            for (int j = 0; j < this.fields[0].length; j++)
            {
                if (!(this.fields[i][j] == null))
                {
                    if (this.fields[i][j].canMove(i, j, localbK[0], localbK[1]) && this.fields[i][j].getColor() == 'w')
                    {
                        otherFigureFlag = false;
                        for (int k = 1; k < 7 - (7 - Math.max(Math.abs(i - localbK[0]), Math.abs(j - localbK[1]))); k++)
                        {

                            if (!otherFigureFlag)
                            {
                                if (i - localbK[0] > 0 && j - localbK[1] > 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i - k][j - k] == null))
                                    {// && this.fields[row1-i][col1-i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateB.add((i-k) + " " + (j-k));
                                } else if (i - localbK[0] > 0 && j - localbK[1] < 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i - k][j + k] == null))
                                    {// && this.fields[row1-i][col1+i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateB.add((i-k) + " " + (j+k));
                                } else if (i - localbK[0] < 0 && j - localbK[1] > 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i + k][j - k] == null))
                                    {// && this.fields[row1+i][col1-i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateB.add((i+k) + " " + (j-k));
                                } else if (i - localbK[0] < 0 && j - localbK[1] < 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i + k][j + k] == null))
                                    {// && this.fields[row1+i][col1+i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateB.add((i+k) + " " + (j+k));
                                } else if (i - localbK[0] < 0 && j - localbK[1] == 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i + k][j] == null))
                                    { // && this.fields[row1+i][col1].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateB.add((i+k) + " " + (j));
                                } else if (i - localbK[0] > 0 && j - localbK[1] == 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i - k][j] == null))
                                    {// && this.fields[row1-i][col1].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateB.add((i-k) + " " + (j));
                                } else if (i - localbK[0] == 0 && j - localbK[1] < 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i][j + k] == null))
                                    { // && this.fields[row1][col1+i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateB.add((i) + " " + (j+k));
                                } else if (i - localbK[0] == 0 && j - localbK[1] > 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i][j - k] == null))
                                    {// && this.fields[row1][col1-i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateB.add((i) + " " + (j-k));
                                }
                                if (!otherFigureFlag && canAddAttackingFigureB)
                                {
                                    attackingFiguresB.add(i + " " + j);
                                    canAddAttackingFigureB = false;
                                }
                            }
                        }
                        if (otherFigureFlag)
                        {
                            pathToCheckMateB.clear();
                            if (!attackingFiguresB.isEmpty())
                            {
                                attackingFiguresB.remove(attackingFiguresB.size()-1);
                                canAddAttackingFigureB = true;
                            }
                        } else {
                            this.colorOfCheckedKing = 'b';
                            return true;
                        }

                    }

                    if (this.fields[i][j].canMove(i, j, localwK[0], localwK[1]) && this.fields[i][j].getColor() == 'b')
                    {
                        otherFigureFlag = false;
                        for (int k = 1; k < 7 - (7 - Math.max(Math.abs(i - localwK[0]), Math.abs(j - localwK[1]))); k++)
                        {
                            if (!otherFigureFlag)
                            {
                                if (i - localwK[0] > 0 && j - localwK[1] > 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i - k][j - k] == null))
                                    {// && this.fields[row1-i][col1-i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateW.add((i - k) + " " + (j - k));
                                } else if (i - localwK[0] > 0 && j - localwK[1] < 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i - k][j + k] == null))
                                    {// && this.fields[row1-i][col1+i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateW.add((i - k) + " " + (j + k));
                                } else if (i - localwK[0] < 0 && j - localwK[1] > 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i + k][j - k] == null))
                                    {// && this.fields[row1+i][col1-i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateW.add((i + k) + " " + (j - k));
                                } else if (i - localwK[0] < 0 && j - localwK[1] < 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i + k][j + k] == null))
                                    {// && this.fields[row1+i][col1+i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateW.add((i + k) + " " + (j + k));
                                } else if (i - localwK[0] < 0 && j - localwK[1] == 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i + k][j] == null))
                                    { // && this.fields[row1+i][col1].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateW.add((i + k) + " " + (j));
                                } else if (i - localwK[0] > 0 && j - localwK[1] == 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i - k][j] == null))
                                    {// && this.fields[row1-i][col1].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateW.add((i - k) + " " + (j));
                                } else if (i - localwK[0] == 0 && j - localwK[1] < 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i][j + k] == null))
                                    { // && this.fields[row1][col1+i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateW.add((i) + " " + (j + k));
                                } else if (i - localwK[0] == 0 && j - localwK[1] > 0 && !this.fields[i][j].getName().equals("N"))
                                {
                                    if (!(this.fields[i][j - k] == null))
                                    {// && this.fields[row1][col1-i].getColor() == figure.getColor()) {
                                        otherFigureFlag = true;
                                    }
                                    pathToCheckMateW.add((i) + " " + (j + k));
                                }
                                if (!otherFigureFlag && canAddAttackingFigureW)
                                {
                                    attackingFiguresW.add(i + " " + j);
                                    canAddAttackingFigureW = false;
                                }
                            }
                        }
                        if (otherFigureFlag)
                        {
                            pathToCheckMateW.clear();
                            if (!attackingFiguresW.isEmpty())
                            {
                                attackingFiguresW.remove(attackingFiguresW.size()-1);
                                canAddAttackingFigureW = true;
                            }
                        }
                        else
                        {
                            this.colorOfCheckedKing = 'w';
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean canSaveKing()
    {
        for (int i = 0; i < pathToCheckMateB.size(); i++)
        {
            for (int j = 0; j < fields.length; j++)
            {
                for (int k = 0; k < fields[0].length; k++)
                {
                    if ((this.fields[j][k] != null) && !pathToCheckMateB.isEmpty()
                            && (this.fields[j][k].canMove(j, k, Integer.parseInt(String.valueOf(pathToCheckMateB.get(i).charAt(0))), Integer.parseInt(String.valueOf(pathToCheckMateB.get(i).charAt(2)))))
                            && this.fields[j][k].getColor() == 'b'
                            && (canMoveWithAccountOfOtherFigures(j, k, Integer.parseInt(String.valueOf(pathToCheckMateB.get(i).charAt(0))), Integer.parseInt(String.valueOf(pathToCheckMateB.get(i).charAt(2))))
                            || (!this.fields[j][k].getName().equals("N") && canAttackWithAccountOfOtherFigures(j, k, Integer.parseInt(String.valueOf(pathToCheckMateB.get(i).charAt(0))), Integer.parseInt(String.valueOf(pathToCheckMateB.get(i).charAt(2)))))))
                    {
                        return true;
                    } else if ((this.fields[j][k] != null) && !pathToCheckMateW.isEmpty()
                            && (this.fields[j][k].canMove(j, k, Integer.parseInt(String.valueOf(pathToCheckMateW.get(i).charAt(0))), Integer.parseInt(String.valueOf(pathToCheckMateW.get(i).charAt(2)))))
                            && this.fields[j][k].getColor() == 'w'
                            && (canMoveWithAccountOfOtherFigures(j, k, Integer.parseInt(String.valueOf(pathToCheckMateW.get(i).charAt(0))), Integer.parseInt(String.valueOf(pathToCheckMateW.get(i).charAt(2))))
                            || (!this.fields[j][k].getName().equals("N") && canAttackWithAccountOfOtherFigures(j, k, Integer.parseInt(String.valueOf(pathToCheckMateW.get(i).charAt(0))), Integer.parseInt(String.valueOf(pathToCheckMateW.get(i).charAt(2)))))))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String isCheckMate()
    {
        //int[] tmpbK = bK;
        //int[] tmpwK = wK;
        boolean canExitFromCheckMateB = false;
        boolean canExitFromCheckMateW = false;
        String checkMate = "";

        if (isCheck()) {

            if (this.colorOfCheckedKing == 'b')
            {
                if (!canSaveKing())
                {
                    int[] tmpBK = localbK;
                    if (canMoveWithAccountOfOtherFigures(tmpBK[0], tmpBK[1], tmpBK[0] - 1, tmpBK[1]) && ((tmpBK[0] >= 0 && tmpBK[0] < 8) && (tmpBK[1] >= 0 && tmpBK[1] < 8) && (tmpBK[0] - 1 >= 0)))
                    {
                        this.fields[tmpBK[0] - 1][tmpBK[1]] = new King("K", 'b');
                        this.fields[tmpBK[0]][tmpBK[1]] = null;
                        if (isCheck())
                        {
                            this.localbK[0] = tmpBK[0] + 1;
                            this.fields[tmpBK[0] - 1][tmpBK[1]] = null;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');

                            checkMate = "b";
                        }
                        else
                        {
                            this.localbK[0] = tmpBK[0] + 1;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            this.fields[tmpBK[0] - 1][tmpBK[1]] = null;
                            //checkMate = "";
                            canExitFromCheckMateB = true;
                        }

                    }
                    if (canMoveWithAccountOfOtherFigures(tmpBK[0], tmpBK[1], tmpBK[0] + 1, tmpBK[1]) && ((tmpBK[0] >= 0 && tmpBK[0] < 8) && (tmpBK[1] >= 0 && tmpBK[1] < 8) && tmpBK[0] + 1 < 8))
                    {
                        this.fields[tmpBK[0] + 1][tmpBK[1]] = new King("K", 'b');
                        this.fields[tmpBK[0]][tmpBK[1]] = null;
                        if (isCheck())
                        {
                            this.localbK[0] = tmpBK[0] - 1;
                            this.fields[tmpBK[0] + 1][tmpBK[1]] = null;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            checkMate = "b";
                        }
                        else
                        {
                            this.localbK[0] = tmpBK[0] - 1;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            this.fields[tmpBK[0] + 1][tmpBK[1]] = null;
                            //checkMate = "";
                            canExitFromCheckMateB = true;
                        }

                    }
                    if (canMoveWithAccountOfOtherFigures(tmpBK[0], tmpBK[1], tmpBK[0], tmpBK[1] - 1) && ((tmpBK[0] >= 0 && tmpBK[0] < 8) && (tmpBK[1] >= 0 && tmpBK[1] < 8) && (tmpBK[1] - 1 >= 0)))
                    {
                        this.fields[tmpBK[0]][tmpBK[1] - 1] = new King("K", 'b');
                        this.fields[tmpBK[0]][tmpBK[1]] = null;
                        if (isCheck())
                        {
                            this.localbK[1] = tmpBK[1] + 1;
                            this.fields[tmpBK[0]][tmpBK[1] - 1] = null;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            checkMate = "b";
                        }
                        else
                        {
                            this.localbK[1] = tmpBK[1] + 1;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            this.fields[tmpBK[0]][tmpBK[1] - 1] = null;
                            //checkMate = "";
                            canExitFromCheckMateB = true;
                        }

                    }
                    if (canMoveWithAccountOfOtherFigures(tmpBK[0], tmpBK[1], tmpBK[0], tmpBK[1] + 1) && ((tmpBK[0] >= 0 && tmpBK[0] < 8) && (tmpBK[1] >= 0 && tmpBK[1] < 8) && tmpBK[1] + 1 < 8))
                    {
                        this.fields[tmpBK[0]][tmpBK[1] + 1] = new King("K", 'b');
                        this.fields[tmpBK[0]][tmpBK[1]] = null;
                        if (isCheck())
                        {
                            this.localbK[1] = tmpBK[1] - 1;
                            this.fields[tmpBK[0]][tmpBK[1] + 1] = null;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            checkMate = "b";
                        }
                        else
                        {
                            this.localbK[1] = tmpBK[1] - 1;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            this.fields[tmpBK[0]][tmpBK[1] + 1] = null;
                            //checkMate = "";
                            canExitFromCheckMateB = true;
                        }

                    }
                    if (canMoveWithAccountOfOtherFigures(tmpBK[0], tmpBK[1], tmpBK[0] - 1, tmpBK[1] + 1) && ((tmpBK[0] >= 0 && tmpBK[0] < 8) && (tmpBK[1] >= 0 && tmpBK[1] < 8) && (tmpBK[0] - 1 >= 0) && tmpBK[1] + 1 < 8))
                    {
                        this.fields[tmpBK[0] - 1][tmpBK[1] + 1] = new King("K", 'b');
                        this.fields[tmpBK[0]][tmpBK[1]] = null;
                        if (isCheck())
                        {
                            this.localbK[0] = tmpBK[0] + 1;
                            this.localbK[1] = tmpBK[1] - 1;
                            this.fields[tmpBK[0] - 1][tmpBK[1] + 1] = null;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            checkMate = "b";
                        }
                        else
                        {
                            this.localbK[0] = tmpBK[0] + 1;
                            this.localbK[1] = tmpBK[1] - 1;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            this.fields[tmpBK[0] - 1][tmpBK[1] + 1] = null;
                            //checkMate = "";
                            canExitFromCheckMateB = true;
                        }

                    }
                    if (canMoveWithAccountOfOtherFigures(tmpBK[0], tmpBK[1], tmpBK[0] + 1, tmpBK[1] + 1) && ((tmpBK[0] >= 0 && tmpBK[0] < 8) && (tmpBK[1] >= 0 && tmpBK[1] < 8) && (tmpBK[0] + 1 < 8) && tmpBK[1] + 1 < 8))
                    {
                        this.fields[tmpBK[0] + 1][tmpBK[1] + 1] = new King("K", 'b');
                        this.fields[tmpBK[0]][tmpBK[1]] = null;
                        if (isCheck())
                        {
                            this.localbK[0] = tmpBK[0] + 1;
                            this.localbK[1] = tmpBK[1] - 1;
                            this.fields[tmpBK[0] + 1][tmpBK[1] + 1] = null;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            checkMate = "b";
                        }
                        else
                        {
                            this.localbK[0] = tmpBK[0] + 1;
                            this.localbK[1] = tmpBK[1] - 1;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            this.fields[tmpBK[0] + 1][tmpBK[1] + 1] = null;
                            //checkMate = "";
                            canExitFromCheckMateB = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpBK[0], tmpBK[1], tmpBK[0] - 1, tmpBK[1] - 1) && ((tmpBK[0] >= 0 && tmpBK[0] < 8) && (tmpBK[1] >= 0 && tmpBK[1] < 8) && (tmpBK[0] - 1 >= 0) && (tmpBK[1] - 1 >= 0)))
                    {
                        this.fields[tmpBK[0] - 1][tmpBK[1] - 1] = new King("K", 'b');
                        this.fields[tmpBK[0]][tmpBK[1]] = null;
                        if (isCheck())
                        {
                            this.localbK[0] = tmpBK[0] + 1;
                            this.localbK[1] = tmpBK[1] + 1;
                            this.fields[tmpBK[0] - 1][tmpBK[1] - 1] = null;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            checkMate = "b";
                        }
                        else
                        {
                            this.localbK[0] = tmpBK[0] + 1;
                            this.localbK[1] = tmpBK[1] + 1;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            this.fields[tmpBK[0] - 1][tmpBK[1] - 1] = null;
                            //checkMate = "";
                            canExitFromCheckMateB = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpBK[0], tmpBK[1], tmpBK[0] + 1, tmpBK[1] - 1) && ((tmpBK[0] >= 0 && tmpBK[0] < 8) && (tmpBK[1] >= 0 && tmpBK[1] < 8) && tmpBK[0] + 1 < 8 && (tmpBK[1] - 1 >= 0)))
                    {
                        this.fields[tmpBK[0] + 1][tmpBK[1] - 1] = new King("K", 'b');
                        this.fields[tmpBK[0]][tmpBK[1]] = null;
                        if (isCheck())
                        {
                            this.localbK[0] = tmpBK[0] - 1;
                            this.localbK[1] = tmpBK[1] + 1;
                            this.fields[tmpBK[0] + 1][tmpBK[1] - 1] = null;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            checkMate = "b";
                        }
                        else
                        {
                            this.localbK[0] = tmpBK[0] - 1;
                            this.localbK[1] = tmpBK[1] + 1;
                            this.fields[tmpBK[0]][tmpBK[1]] = new King("K", 'b');
                            this.fields[tmpBK[0] + 1][tmpBK[1] - 1] = null;
                            canExitFromCheckMateB = true;
                        }
                    }
                }
            }
            else
            {
                if (!canSaveKing())
                {
                    int[] tmpWK = localwK;
                    if (canMoveWithAccountOfOtherFigures(tmpWK[0], tmpWK[1], tmpWK[0] - 1, tmpWK[1]) && ((tmpWK[0] >= 0 && tmpWK[0] < 8) && (tmpWK[1] >= 0 && tmpWK[1] < 8) && (tmpWK[0] - 1 >= 0)))
                    {
                        this.fields[tmpWK[0] - 1][tmpWK[1]] = new King("K", 'w');
                        this.fields[tmpWK[0]][tmpWK[1]] = null;
                        if (isCheck())
                        {
                            this.localwK[0] = tmpWK[0] + 1;
                            this.fields[tmpWK[0] - 1][tmpWK[1]] = null;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            checkMate = "w";

                        }
                        else
                        {
                            this.localwK[0] = tmpWK[0] + 1;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            this.fields[tmpWK[0] - 1][tmpWK[1]] = null;
                            canExitFromCheckMateW = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpWK[0], tmpWK[1], tmpWK[0] + 1, tmpWK[1]) && ((tmpWK[0] >= 0 && tmpWK[0] < 8) && (tmpWK[1] >= 0 && tmpWK[1] < 8) && tmpWK[0] + 1 < 8))
                    {
                        this.fields[tmpWK[0] + 1][tmpWK[1]] = new King("K", 'w');
                        this.fields[tmpWK[0]][tmpWK[1]] = null;
                        if (isCheck())
                        {
                            this.localwK[0] = tmpWK[0] - 1;
                            this.fields[tmpWK[0] + 1][tmpWK[1]] = null;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            checkMate = "w";
                        }
                        else
                        {
                            this.localwK[0] = tmpWK[0] - 1;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            this.fields[tmpWK[0] + 1][tmpWK[1]] = null;
                            canExitFromCheckMateW = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpWK[0], tmpWK[1], tmpWK[0], tmpWK[1] - 1) && ((tmpWK[0] >= 0 && tmpWK[0] < 8) && (tmpWK[1] >= 0 && tmpWK[1] < 8) && (tmpWK[1] - 1 >= 0)))
                    {
                        this.fields[tmpWK[0]][tmpWK[1] - 1] = new King("K", 'w');
                        this.fields[tmpWK[0]][tmpWK[1]] = null;
                        if (isCheck())
                        {
                            this.localwK[1] = tmpWK[1] + 1;
                            this.fields[tmpWK[0]][tmpWK[1] - 1] = null;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            checkMate = "w";
                        }
                        else
                        {
                            this.localwK[1] = tmpWK[1] + 1;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            this.fields[tmpWK[0]][tmpWK[1] - 1] = null;
                            canExitFromCheckMateW = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpWK[0], tmpWK[1], tmpWK[0], tmpWK[1] + 1) && ((tmpWK[0] >= 0 && tmpWK[0] < 8) && (tmpWK[1] >= 0 && tmpWK[1] < 8) && tmpWK[1] + 1 < 8))
                    {
                        this.fields[tmpWK[0]][tmpWK[1] + 1] = new King("K", 'w');
                        this.fields[tmpWK[0]][tmpWK[1]] = null;
                        if (isCheck())
                        {
                            this.localwK[1] = tmpWK[1] - 1;
                            this.fields[tmpWK[0]][tmpWK[1] + 1] = null;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            checkMate = "w";
                        }
                        else
                        {
                            this.localwK[1] = tmpWK[1] - 1;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            this.fields[tmpWK[0]][tmpWK[1] + 1] = null;
                            canExitFromCheckMateW = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpWK[0], tmpWK[1], tmpWK[0] - 1, tmpWK[1] + 1) && ((tmpWK[0] >= 0 && tmpWK[0] < 8) && (tmpWK[1] >= 0 && tmpWK[1] < 8) && (tmpWK[0] - 1 >= 0) && tmpWK[1] + 1 < 8))
                    {
                        this.fields[tmpWK[0] - 1][tmpWK[1] + 1] = new King("K", 'w');
                        this.fields[tmpWK[0]][tmpWK[1]] = null;
                        if (isCheck())
                        {
                            this.localwK[0] = tmpWK[0] + 1;
                            this.localwK[1] = tmpWK[1] - 1;
                            this.fields[tmpWK[0] - 1][tmpWK[1] + 1] = null;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            checkMate = "w";
                        }
                        else
                        {
                            this.localwK[0] = tmpWK[0] + 1;
                            this.localwK[1] = tmpWK[1] - 1;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            this.fields[tmpWK[0] - 1][tmpWK[1] + 1] = null;
                            canExitFromCheckMateW = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpWK[0], tmpWK[1], tmpWK[0] + 1, tmpWK[1] + 1) && ((tmpWK[0] >= 0 && tmpWK[0] < 8) && (tmpWK[1] >= 0 && tmpWK[1] < 8) && (tmpWK[0] + 1 < 8) && tmpWK[1] + 1 < 8))
                    {
                        this.fields[tmpWK[0] + 1][tmpWK[1] + 1] = new King("K", 'w');
                        this.fields[tmpWK[0]][tmpWK[1]] = null;
                        if (isCheck())
                        {
                            this.localwK[0] = tmpWK[0] - 1;
                            this.localwK[1] = tmpWK[1] - 1;
                            this.fields[tmpWK[0] + 1][tmpWK[1] + 1] = null;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            checkMate = "w";
                        }
                        else
                        {
                            this.localwK[0] = tmpWK[0] - 1;
                            this.localwK[1] = tmpWK[1] - 1;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            this.fields[tmpWK[0] + 1][tmpWK[1] + 1] = null;
                            canExitFromCheckMateW = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpWK[0], tmpWK[1], tmpWK[0] - 1, tmpWK[1] - 1) && ((tmpWK[0] >= 0 && tmpWK[0] < 8) && (tmpWK[1] >= 0 && tmpWK[1] < 8) && (tmpWK[0] - 1 >= 0) && (tmpWK[1] - 1 >= 0)))
                    {
                        this.fields[tmpWK[0] - 1][tmpWK[1] - 1] = new King("K", 'w');
                        this.fields[tmpWK[0]][tmpWK[1]] = null;
                        if (isCheck())
                        {
                            this.localwK[0] = tmpWK[0] + 1;
                            this.localwK[1] = tmpWK[1] + 1;
                            this.fields[tmpWK[0] - 1][tmpWK[1] - 1] = null;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            checkMate = "w";
                        }
                        else
                        {
                            this.localwK[0] = tmpWK[0] + 1;
                            this.localwK[1] = tmpWK[1] + 1;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            this.fields[tmpWK[0] - 1][tmpWK[1] - 1] = null;
                            canExitFromCheckMateW = true;
                        }
                    }
                    if (canMoveWithAccountOfOtherFigures(tmpWK[0], tmpWK[1], tmpWK[0] + 1, tmpWK[1] - 1) && ((tmpWK[0] >= 0 && tmpWK[0] < 8) && (tmpWK[1] >= 0 && tmpWK[1] < 8) && tmpWK[0] + 1 < 8 && (tmpWK[1] - 1 >= 0)))
                    {
                        this.fields[tmpWK[0] + 1][tmpWK[1] - 1] = new King("K", 'w');
                        this.fields[tmpWK[0]][tmpWK[1]] = null;
                        if (isCheck())
                        {
                            this.localwK[0] = tmpWK[0] - 1;
                            this.localwK[1] = tmpWK[1] + 1;
                            this.fields[tmpWK[0] + 1][tmpWK[1] - 1] = null;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            checkMate = "w";
                        }
                        else
                        {
                            this.localwK[0] = tmpWK[0] - 1;
                            this.localwK[1] = tmpWK[1] + 1;
                            this.fields[tmpWK[0]][tmpWK[1]] = new King("K", 'w');
                            this.fields[tmpWK[0] + 1][tmpWK[1] - 1] = null;
                            canExitFromCheckMateW = true;
                        }
                    }
                }
            }
            if (!checkMate.equals(""))
            {
                switch (checkMate)
                {
                    case "b":
                        if (!canExitFromCheckMateB)
                        {
                            return checkMate;
                        }
                        break;
                    case "w":
                        if (!canExitFromCheckMateW)
                        {
                            return checkMate;
                        }
                        break;
                }
            }
        }
        return "";
    }

    public boolean move_figure(int row1, int col1, int row2, int col2 )
    {

        Figure figure =  this.fields[row1][col1];
        boolean moveFlag = false;
        boolean attackFlag = false;

        for (int i = 1; i <= 7 - (7 - Math.max(Math.abs(row1 - row2), Math.abs(col1 - col2))); i++)
        {
            if (row1-row2 > 0 && col1 - col2 > 0  && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1-i][col1-i]==null))
                {// && this.fields[row1-i][col1-i].getColor() == figure.getColor()) {
                    moveFlag = true;
                    break;
                }
            } else if (row1-row2 > 0 && col1 - col2 < 0 && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1-i][col1+i]==null))
                {// && this.fields[row1-i][col1+i].getColor() == figure.getColor()) {
                    moveFlag = true;
                    break;
                }
            } else if (row1-row2 < 0 && col1 - col2 > 0  && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1+i][col1-i]==null))
                {// && this.fields[row1+i][col1-i].getColor() == figure.getColor()) {
                    moveFlag = true;
                    break;
                }
            } else if (row1-row2 < 0 && col1 - col2 < 0  && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1+i][col1+i]==null))
                {// && this.fields[row1+i][col1+i].getColor() == figure.getColor()) {
                    moveFlag = true;
                    break;
                }
            } else if (row1 - row2 < 0 && col1 - col2 == 0 && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1+i][col1]==null))
                { // && this.fields[row1+i][col1].getColor() == figure.getColor()) {
                    moveFlag = true;
                    break;
                }
            } else if (row1 - row2 > 0 && col1 - col2 == 0  && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1-i][col1]==null))
                {// && this.fields[row1-i][col1].getColor() == figure.getColor()) {
                    moveFlag = true;
                    break;
                }
            } else if (row1 - row2 == 0 && col1 - col2 < 0 && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1][col1+i]==null))
                { // && this.fields[row1][col1+i].getColor() == figure.getColor()) {
                    moveFlag = true;
                    break;
                }
            } else if (row1 - row2 == 0 && col1 - col2 > 0 && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1][col1-i]==null))
                {// && this.fields[row1][col1-i].getColor() == figure.getColor()) {
                    moveFlag = true;
                    break;
                }
            }
        }


        for (int i = 1; i < 7 - (7 - Math.max(Math.abs(row1 - row2), Math.abs(col1 - col2))); i++)
        {
            if (row1-row2 > 0 && col1 - col2 > 0  && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1-i][col1-i]==null) && this.fields[row1-i][col1-i].getColor() != figure.getColor())
                {
                    attackFlag = true;
                    break;
                }
            } else if (row1-row2 > 0 && col1 - col2 < 0 && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1-i][col1+i]==null) && this.fields[row1-i][col1+i].getColor() != figure.getColor()) {
                    attackFlag = true;
                    break;
                }
            } else if (row1-row2 < 0 && col1 - col2 > 0  && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1+i][col1-i]==null) && this.fields[row1+i][col1-i].getColor() != figure.getColor()) {
                    attackFlag = true;
                    break;
                }
            } else if (row1-row2 < 0 && col1 - col2 < 0  && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1+i][col1+i]==null) && this.fields[row1+i][col1+i].getColor() != figure.getColor()) {
                    attackFlag = true;
                    break;
                }
            } else if (row1 - row2 < 0 && col1 - col2 == 0 && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1+i][col1]==null) && this.fields[row1+i][col1].getColor() != figure.getColor())
                {
                    attackFlag = true;
                    break;
                }
            } else if (row1 - row2 > 0 && col1 - col2 == 0  && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1-i][col1]==null) && this.fields[row1-i][col1].getColor() != figure.getColor())
                {
                    attackFlag = true;
                    break;
                }
            } else if (row1 - row2 == 0 && col1 - col2 < 0 && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1][col1+i]==null) && this.fields[row1][col1+i].getColor() != figure.getColor())
                {
                    attackFlag = true;
                    break;
                }
            } else if (row1 - row2 == 0 && col1 - col2 > 0 && !figure.getName().equals("N"))
            {
                if (!(this.fields[row1][col1-i]==null) && this.fields[row1][col1-i].getColor() != figure.getColor())
                {
                    attackFlag = true;
                    break;
                }
            }
        }

        if (figure.canMove(row1, col1, row2, col2) && this.fields[row2][col2]==null  && (!moveFlag) && figure.getColor() == this.getColorGaming())
        {
            System.out.println("move");
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null && this.fields[row2][col2].getColor() != this.fields[row1][col1].getColor() && (!attackFlag) && figure.getColor() == this.getColorGaming() && !this.fields[row2][col2].getName().equals("K"))
        {
            System.out.println("attack");
            switch (this.fields[row2][col2].getColor())
            {
                case 'w': this.takeWhite.add(this.fields[row2][col2].getColor()+this.fields[row2][col2].getName());break;
                case 'b': this.takeBlack.add(this.fields[row2][col2].getColor()+this.fields[row2][col2].getName());break;
            }
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }
        return false;





    }

    public void print_board()
    {
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for(int row = 7; row > -1; row--)
        {
            System.out.print(row);
            for(int col = 0; col< 8; col++)
            {
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col = 0; col < 8; col++)
        {
            System.out.print("    "+col);
        }


    }


}
