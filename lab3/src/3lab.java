import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос длины массива и проверка на корректность ввода
        System.out.print("Введите длину массива: ");
        int n = scanner.nextInt();

        // Проверка на положительную длину массива
        if (n <= 0) {
            System.out.println("Длина массива должна быть положительным числом.");
            scanner.close();
            return; // Завершаем программу, если длина некорректна
        }

        int[] array = new int[n];
        System.out.println("Введите " + n + " отсортированных элементов массива:");

        // Ввод элементов массива
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        // Запрос элемента для поиска
        System.out.print("Введите элемент для поиска: ");
        int target = scanner.nextInt();

        // Выполнение экспоненциального поиска
        int result = exponentialSearch(array, target);
        if (result != -1) {
            System.out.println("Элемент найден на индексе: " + result);
        } else {
            System.out.println("Элемент не найден.");
        }

        scanner.close();
    }

    // Метод для экспоненциального поиска
    public static int exponentialSearch(int[] array, int target) {
        // Проверка на пустой массив
        if (array.length == 0) {
            return -1; // Если массив пустой, элемент не найден.
        }

        // Если первый элемент является искомым.
        if (array[0] == target) {
            return 0;
        }

        int i = 1;
        // Поиск диапазона
        while (i < array.length && array[i] <= target) {
            i *= 2; // Удваиваем индекс
        }

        // Выполнение бинарного поиска в найденном диапазоне.
        return binarySearch(array, target, i / 2, Math.min(i, array.length - 1));
    }

    // Метод для бинарного поиска
    private static int binarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2; // Находим средний индекс

            if (array[mid] == target) {
                return mid; // Элемент найден
            }
            if (array[mid] < target) {
                left = mid + 1; // Ищем в правой половине.
            } else {
                right = mid - 1; // Ищем в левой половине.
            }
        }
        return -1; // Элемент не найден.
    }
}
