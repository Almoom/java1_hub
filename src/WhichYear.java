import java.util.Scanner;

public class WhichYear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year;

        while (true) {
            System.out.printf("Input the year: (exit by \"-1\" or less)\n");
            year = scanner.nextInt();
            if (year >= 0) solve(year);
            else break;
        }
    }

    public static void solve(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 4 == 0 && year % 400 == 0)) {
            System.out.printf("%d - is the leap-year\n", year);
        }
        else System.out.printf("%d - is the lean-year\n", year);
    }
}
