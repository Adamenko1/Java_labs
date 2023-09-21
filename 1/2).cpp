#include <iostream>
#include <fstream>
#include <vector>

int sum(std::vector <int> numbers)
{
    int s = 0;
    int sum = 0;
    for (int i = 0; i < numbers.size(); i++)
    {
        auto test = numbers[i];
        if (s == 0)
        {
            sum = sum + numbers[i];
            s++;
        }
        else
        {
            sum = sum - numbers[i];
            s--;
        }
    }
    return sum;
}

int main()
{
    int n, s;
    std::cin >> n;
    std::vector <int> numbers(n);
    for (int i = 0; i < n; i++)
    {
        std::cin >> numbers[i];
    }
    std::cout << sum(numbers);
}