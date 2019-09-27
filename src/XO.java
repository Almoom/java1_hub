import java.util.Scanner;

public class XO {
    public static Scanner scanner = new Scanner(System.in);
    public static char map[][];
    public static int arr[][];
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
        initArr();

        printMap();
        //printArr();

        theChoice();
        if (FIRST) {
            stepPlayer();
            printMap();
        }
        while (true) {
            checkXO();
            //printArr();
            stepComp();
            nullArr();
            printMap();

            if (checkWin("Computer", COMP) || check()) break;

            stepPlayer();
            printMap();

            if (checkWin("Player", PLAYER) || check()) break;
        }
    }

    public static boolean checkWin(String s, char ch) {
        int gor;
        int ver;
        int dia1 = 0;
        int dia2 = 0;

        for (int i = 0; i < SIZE; i++) {
            gor = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == ch) gor++;
            }
            if (gor == 3) {
                System.out.printf(s + " is win!\n");
                return true;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            ver = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == ch) ver++;
            }
            if (ver == 3) {
                System.out.printf(s + " is win!\n");
                return true;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == j && map[i][j] == COMP) dia1++;
                if (i == SIZE - 1 - j && map[i][j] == ch) dia2++;
            }
            if (dia1 == 3 || dia2 == 3) {
                System.out.printf(s + " is win!\n");
                return true;
            }
        }
        return false;
    }

    public static boolean check() {
        int flag = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (arr[i][j] > -1) flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("Dead heat!");
            return true;
        }
        return false;
    }

    public static void nullArr() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (arr[i][j] > -1) arr[i][j] = 0;
            }
        }
    }

    public static void stepComp() {
        int max = -1;
        int maxi = 0;
        int maxj = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                    maxi = i;
                    maxj = j;
                }
            }
        }
        map[maxi][maxj] = COMP;
    }

    public static void calkPosition(int ii, int jj) {
        int mini = ii == 0 ? ii : ii - 1;
        int maxi = ii == SIZE - 1 ? ii : ii + 1;
        int minj = jj == 0 ? jj : jj - 1;
        int maxj = jj == SIZE - 1 ? jj : jj + 1;

        for (int i = mini; i <= maxi; i++) {
            for (int j = minj; j <= maxj; j++) {
                if (arr[i][j] < 0) ;
                else arr[i][j] += 1;
            }
        }
    }

    public static void hotMap(int ii, int jj) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i == ii || j == jj) && arr[i][j] >= 0) arr[i][j] += 1;
            }
        }
        if (ii == jj || ii == SIZE - jj - 1) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (arr[i][j] >= 0 && (i - ii == j - jj || ii + jj == i + j)) arr[i][j] += 1;
                }
            }
        }
    }

    public static void checkXO() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == PLAYER) {
                    arr[i][j] = -2;
                    calkPosition(i, j);
                    hotMap(i, j);
                    checkPotWin(-2, 5);
                }
                if (map[i][j] == COMP) {
                    arr[i][j] = -1;
                    hotMap(i, j);
                    checkPotWin(-1, 20);
                }
            }
        }
    }

    public static void checkPotWin_add(int ii, int jj, int id, int prior) {
        int gor = 0;
        int ver = 0;
        int dia = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (arr[i][j] == id) {
                    if (i == ii) gor++;
                    if (j == jj) ver++;
                    if ((ii == jj || ii == SIZE - jj - 1) && (i - ii == j - jj || ii + jj == i + j)) dia++;
                }
            }
        }
        if (gor > 1 || ver > 1 || dia > 1) arr[ii][jj] += prior;
    }

    public static void checkPotWin(int id, int prior) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (arr[i][j] > -1) checkPotWin_add(i, j, id, prior);
            }
        }
    }

    public static void initArr() {
        arr = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == SIZE / 2 && j == SIZE / 2) arr[i][j] += 1;
                else arr[i][j] = 0;
            }
        }
    }

    public static void printArr() {
        System.out.println("==========");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==========");
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
        System.out.println("========");
    }
}
