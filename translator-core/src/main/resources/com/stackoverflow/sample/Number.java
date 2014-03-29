package com.stackoverflow.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Reference: http://stackoverflow.com/q/21475448
 */

public class Number {
    static boolean primes;

    public static boolean number() {
        try {
            for(int i = 0; i < 2; i++) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String num1 = br.readLine();
                String num2 = br.readLine();

                int number1 = Integer.parseInt(num1);
                int number2 = Integer.parseInt(num2);

                for(int j = number1; j < number2; j++) {
                    if((j % 2) == 0) {
                        return true;
                    }
                    for(int k = 3; (k * k) <= j; k += 2) {
                        if(j % k == 0) {
                            return false;
                        }
                        System.out
                            .println("All the primes b/n number " + number1 + " and number" + number2 + "  are :" + j);
                        return true;
                    }
                    System.out.println(primes);
                    // br.close();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } return primes;
    }
}
