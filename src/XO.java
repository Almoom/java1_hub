import java.util.Scanner;

public class XO {
    public static Scanner scanner = new Scanner(System.in);
    public static char map[][];
    public static final int SIZE = 3;
    public static final int LINE_WIN = 3;
    public static final char XXX = 'X';
    public static boolean FIRST;
    public static char PLAYER;
    public static char COMP;
    public static final char OOO = 'O';
    public static final char NOTHING = '.';
    public static void main(String[] args) {
        initMap();
        printMap();
        theChoice();
        stepPlayer();
        printMap();
//        FIRST == true ? stepPlayer() : stepComp();
    }

    public static void stepComp() {

    }

    public static void stepPlayer() {
        System.out.println("Input X and Y in this format: \"X/Y\"");
        do {
            String tmp = scanner.next();
            if (tmp.length() == 3 && tmp.charAt(1) == '/') {
                int j = tmp.charAt(0) - 48;
                int i = tmp.charAt(2) - 48;
                if (i < SIZE + 1 && i > 0 && j > 0 && j < SIZE + 1 && map[i - 1][j - 1] == '.') {
                    map[i - 1][j - 1] = PLAYER;
                    break;
                }
            }
            System.out.println("Try else time");
        } while (true);
    }

    public static void theChoice() {
        System.out.println("Choose your sign: X or O");
        do {
            String tmp = scanner.next();
            if (tmp.toUpperCase().charAt(0) == XXX) {
                PLAYER = XXX;
                COMP = OOO;
                FIRST = true;
                break;
            }
            if (tmp.toUpperCase().charAt(0) == OOO) {
                PLAYER = OOO;
                COMP = XXX;
                FIRST = false;
                break;
            }
            System.out.println("Try else time");
        } while (true);

    }

    public static void initMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = NOTHING;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i < SIZE + 1; i++) {
            for (int j = 0; j < SIZE + 1; j++) {
                if (i == 0) System.out.print(j + " ");
                else {
                    if (j == 0) System.out.print(i + " ");
                    else System.out.print(map[i - 1][j - 1] + " ");
                }
            }
            System.out.println();
        }
    }
}
