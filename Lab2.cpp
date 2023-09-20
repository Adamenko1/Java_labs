#include <iostream>
#include <queue>
#include <vector>
#include <fstream>
#include <string>

typedef std::vector<std::vector<int>> graph; // Для удобства 

int fillMatrix(graph& matrix, std::ifstream& file,int n)
{
	if (file.is_open())
	{
		file.seekg(0); // Вернул указаетль на начало файла.
		std::string line;
		int i = 0;

		while (std::getline(file, line)) // заполение матрицы смежности
		{
			for (int j = 0; j < n; j++)
			{
				matrix[i][j] = line.at(j) - 48;
			}

			i++;
		}
	}
	else
	{
		std::cout << "Error open file!" << std::endl;
	}

	return matrix.size();
}

void bfs(graph &matrix, int s,int n) // ф-ция поиска в ширину
{
	std::queue<int> q; // очередь
	std::vector<int> d(n);
	std::vector<bool> used(n); // цвета
	d[s] = 0; // s - начальная вершина
	q.push(s);
	used[s] = true;

	while (!q.empty())
	{
		int v = q.front();
		q.pop();
		for (int i = 0; i < matrix[v].size(); i++)
		{
			auto test = matrix[v][i];
			auto test1 = used[i];
			if (matrix[v][i]!=0 && used[i] == false)
			{
				d[i] = d[v] + 1;
				used[i] = true;
				q.push(i);
			}
		}
	}
	
	for (const auto i : d) // Массив расстояний
	{
		std::cout << i << std::endl;
	}
}


int countingTops(std::ifstream& file) //кол-во вершин в графе
{
	if (file.is_open())
	{
		std::string line;
		std::getline(file, line);
		return line.size();
	}
	else
	{
		std::cout << "Error open file!" << std::endl;
		return -1;
	}
}
void main()
{
	
	std::ifstream file;
	file.open("doublegraph.txt");
	int n = countingTops(file); //кол-во вершин в графе
	graph matrix(n, std::vector<int>(n));
	fillMatrix(matrix, file,n);  //Заполнение матрицы смежности
	bfs(matrix,1,n); // поиск в ширину от 1-ой вершины до всех остальных
}