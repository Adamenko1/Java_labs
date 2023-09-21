#include <iostream>
#include <fstream>

int Sircuz(int n)
{
    if (n % 2 == 0)
    {
        return n / 2;
    }
    else
    {
        return 3*n + 1;
    }
}

int main()
{
    int s = 10; // изначальное число, которые нам дано
    int step = 1;
    while (s != 1)
    {
        s = Sircuz(s);
        step++;
    }
    std::cout << step - 1;
}
