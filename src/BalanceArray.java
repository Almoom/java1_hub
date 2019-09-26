import java.util.Scanner;

public class BalanceArray {
    public static void main(String[] args) {
        int[] arr = new int[5];
        Scanner scanner = new Scanner(System.in);
        int i;

        i = 0;
        while (i < arr.length){
            arr[i] = scanner.nextInt();
            i++;
        }
        if (checkBalance(arr)) System.out.printf("is it\n");
        else System.out.printf("isn't\n");
    }

    public static boolean checkBalance(int arr[]){
        int i, half_sum, sum;

        i = half_sum = 0;
        sum = sumArr(arr);
        while (i < arr.length) {
            half_sum += arr[i];
            if (half_sum == sum / 2 && sum % 2 == 0) return true;
            i++;
        }
        return false;
    }

    public static int sumArr(int arr[]) {
        int i, rez;

        i = rez = 0;
        while (i < arr.length) {
            rez += arr[i];
            i++;
        }
        return rez;
    }
}
