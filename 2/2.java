import java.util.Arrays;

public class Main
{
    static String problem_1(String str)
    {
        String maxStr = "";
        String tempStr = "";

        for(int i=0; i<str.length(); i++)
        {
            if(tempStr.contains("" + str.charAt(i)))//если переменная темп содержит новый символ, то заменяем темп
            {
                tempStr = tempStr.substring(tempStr.lastIndexOf(str.charAt(i)) + 1);
            }
            tempStr = tempStr + str.charAt(i); //добавляем новый уникальный символ

            if(maxStr.length() < tempStr.length()) //если максимальная длина строки короче временной
            {
                maxStr = tempStr; //то присваиваем
            }
        }
        return maxStr;
    }

    static int[] problem_2(int[] arr1, int[] arr2)
    {
        int s = arr1.length + arr2.length;
        int[] arr3 = new int[s];

        for (int i = 0; i < arr1.length; i++)
        {
            arr3[i] = arr1[i];
            arr3[i+arr1.length] = arr2[i];
        }
        Arrays.sort(arr3);

        return arr3;
    }

    static int ploblem_3(int[] arr)
    {
        int sum1 = 0;
        int sum = arr[0];
        for (int i = 0; i < arr.length; i++)
        {
            if (i+1 != arr.length && arr[i] < arr[i+1])
            {
                sum += arr[i+1];
            }
            else
            {
                if (sum1 < sum)
                    sum1 = sum;
                if (i+1 != arr.length)
                    sum = arr[i+1];
            }
        }
        return sum1;
    }

    static int[][] problem_4(int[][] arr)
    {
        int[][] arr1 = new int[arr[0].length][arr.length];

        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr.length; j++)
                arr1[i][j] = arr[arr.length - j - 1][i];

        return arr1;
    }

    static void problem_5(int[] arr, int target)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] + arr[j] == target)
                {
                    System.out.print(arr[i]+" "+arr[j]);
                    return;
                }
            }
        }
        System.out.print("null");
    }

    static int problem_6(int[][] arr)
    {
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                sum += arr[i][j];

        return sum;
    }

    static int[] problem_7(int[][] arr)
    {
        int[] sum = new int[arr.length];
        int max = 0;
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr[0].length; j++)
            {
                if (max < arr[i][j])
                    max = arr[i][j];
            }
            sum[i]=max;
        }
        return sum;
    }

    static int[][] problem_8(int[][] arr)
    {
        int[][] arr1 = new int[arr[0].length][arr.length];

        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr.length; j++)
                arr1[i][j] = arr[j][arr[0].length - i - 1];

        return arr1;
    }

    public static void main(String[] args)
    {
        String str = "ABCJGYHH";

        System.out.println(problem_1(str));
        //----------------------------------------------------
        int[] arr1 = {5, 2, 3, 4, 5};
        int[] arr2 = {6, 1, 8, 9, 10};
        int[] arr3;

        arr3 = problem_2(arr1, arr2);
        for (int i = 0; i < arr3.length; i++)
            System.out.println(" "+arr3[i]);
        //----------------------------------------------------
        int[] arr4 = {1, 2, 3, 6, 5, 9, 1, 2, 300, 301};
        System.out.println(" "+ploblem_3(arr4));
        //----------------------------------------------------
        int[][] arr5 = {{0, 1, 2, 3}, {0, 6, 1, 7}};
        int[][] arr6 = problem_4(arr5);

        for (int i = 0; i < arr6.length; i++)
        {
            System.out.print("\n");
            for (int j = 0; j < arr6[0].length; j++)
                System.out.print(arr6[i][j]+" ");
        }
        //----------------------------------------------------
        int[] arr7 = {1, 2, 3, 4, 5, 6, 7, 8};
        int a = 10;
        problem_5(arr7, a);
        //----------------------------------------------------
        int[][] arr8 = {{1, 2, 3, 4, 5, 6, 7, 8}, {5, 2, 3, 2, 5, 3, 7, 8}};
        System.out.print(""+problem_6(arr8));
        //----------------------------------------------------
        int[][] arr9 = {{1, 2, 3, 4, 5, 6, 7, 8}, {5, 2, 300, 2, 5, 3, 7, 8}};
        int[] arr10 = problem_7(arr9);
        for (int i = 0; i < arr10.length; i++)
            System.out.print(arr10[i]+" ");
        //----------------------------------------------------
        int[][] arr11 = {{0, 1, 2, 3}, {0, 6, 1, 7}};
        int[][] arr12 = problem_8(arr5);

        for (int i = 0; i < arr12.length; i++)
        {
            System.out.print("\n");
            for (int j = 0; j < arr12[0].length; j++)
                System.out.print(arr12[i][j]+" ");
        }
    }
}