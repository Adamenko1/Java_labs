#include <iostream>
#include <fstream>
#include <vector>

int MIN(int* height, int size) // определение минимального элемента вектора
{
    int min = LONG_MAX;
    for (int a = 0; a < size; a++)
    {
        if (height[a] < min)
        {
            min = height[a];
        }
    }
    return min;
}

int main()
{
    int n, t = 0, t1, min = LONG_MIN, d;
    int* height = new int[t];
    std::cin >> n; // n - кол-во дорог

    for (int i = 0; i < n; i++)
    {
        std::cin >> t; // кол-во тоннелей
        t1 = t;
        for (int j = 0; j < t; j++)
        {
            std::cin >> height[j];
        }
        if (min < MIN(height, t))
        {
            min = MIN(height, t);
            d = n;
        }
    }
    std::cout << d << " " << min;
}