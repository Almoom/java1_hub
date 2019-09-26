import java.util.Arrays;

public class WheelArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        moveArr(arr, -1);
        System.out.println(Arrays.toString(arr));
    }

    public static void moveArr(int arr[], int n) {
        int i, j, temp, temp2;

        i = 0;
        n = n >= 0 ? n % arr.length : (arr.length + n) % arr.length;
        while (i < n) {
            temp = arr[arr.length - 1];
            j = 0;
            while (j < arr.length) {
                temp2 = arr[j];
                arr[j] = temp;
                temp = temp2;
                j++;
            }
            i++;
        }
    }
}
