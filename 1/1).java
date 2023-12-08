class Main
{
    static int Siracus(int n)
    {
        if (n%2==0)
        {
            n=n/2;
        }
        else
        {
            n=3*n+1;
        }
        return n;
    }
    public static void main(String[] args)
    {
        int x = 10, count = 0;
        while (x != 1)
        {
            x = Siracus(x);
            count++;
        }
        System.out.println(count);
    }
}