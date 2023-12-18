package code;

import java.util.Scanner;

public class j11720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        String temp = sc.next();

        char[] num = temp.toCharArray();

        int result = 0;



        for (int i = 0; i < N; i++) {
            result += (num[i] - '0');
        }

        System.out.println(result);
    }
}
