import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину массива: ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        System.out.println("Введите " + n + " отсортированных элементов массива:");
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }
        System.out.print("Введите элемент для поиска: ");
        int target = scanner.nextInt();

        int result = exponentialSearch(array, target);
        if (result != -1) {
            System.out.println("Элемент найден на индексе: " + result);
        } else {
            System.out.println("Элемент не найден.");
        }

        scanner.close();
    }

    public static int exponentialSearch(int[] array, int target) {
        if (array[0] == target) {
            return 0;
        }

        int i = 1;
        while (i < array.length && array[i] <= target) {
            i *= 2;
        }

        return binarySearch(array, target, i / 2, Math.min(i, array.length - 1));
    }

    private static int binarySearch(int[] array, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Элемент не найден
    }
}
