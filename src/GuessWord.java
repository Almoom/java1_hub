import java.util.Scanner;

public class GuessWord {
    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String w = words[(int)(Math.random() * words.length)];
        Scanner scanner = new Scanner(System.in);

        int i;
        String ver;
        while (true) {
            ver = scanner.next();
            if (ver.equals(w)) {
                System.out.println("Win!");
                break;
            }
            i = 0;
            while (i < ver.length() && i < w.length() && ver.charAt(i) == w.charAt(i)) {
                System.out.printf("%c", w.charAt(i));
                i++;
            }
            for (int j = i; j < 15; j++) System.out.printf("%c", '#');
            System.out.println("");
        }
    }
}
