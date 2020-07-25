import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        //quantity correct braces
        int N = 0;
        try(Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String str = scanner.nextLine();
                if (str.equals("stop")){
                    break;
                }
                int rightBraces = 0;
                int leftBraces = 0;

                //convert to array and counting braces
                char[] arr = str.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    switch (arr[i]) {
                        case '(':
                            leftBraces++;
                            break;
                        case ')':
                            rightBraces++;
                            break;
                    }
                }
                if (leftBraces == rightBraces) {
                    N++;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Correct braces: " + N);
    }
}
