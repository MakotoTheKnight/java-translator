package com.stackoverflow.sample;

import java.util.Scanner;

/**
 * Reference: http://stackoverflow.com/q/21473142
 */
public class GradeAverager {
    public static void main(String[] args) {
        int score1, score2, score3;
        double average, average_no_lowest;

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter three scores: ");
        score1 = keyboard.nextInt();
        score2 = keyboard.nextInt();
        score3 = keyboard.nextInt();

        average = (score1 + score2 + score3) / 3.0;

        System.out.println();
        System.out.println("The average is: " + average);

        try{



            if(score1 < score2 && score1 < score3) {
                System.out.println("The lowest score was:" + score1);
            } else if(score2 < score1 && score2 < score3) {
                System.out.println("The lowest score was:" + score2);
            } else if(score3 < score1 && score3 < score2) {
                System.out.println("The lowest score was:" + score3);
            }

            if(score1 < score2 && score1 < score3) {
                average_no_lowest = (score2 + score3) / 2.0;
                System.out.println("The average without the lowest score is: " + average_no_lowest);
                if(average_no_lowest > 90) {
                    System.out.print('A');
                } else if(average_no_lowest < 90 && average_no_lowest > 80) {
                    System.out.print('B');
                } else if(average_no_lowest > 70 && average_no_lowest < 80) {
                    System.out.print('C');
                } else {
                    System.out.print('D');
                }
            } else if(score2 < score1 && score2 < score3) {
                average_no_lowest = (score1 + score3) / 2.0;
                System.out.println("The average without the lowest score is: " + average_no_lowest);
                if(average_no_lowest > 90) {
                    System.out.print('A');
                } else if(average_no_lowest < 90 && average_no_lowest > 80) {
                    System.out.print('B');
                } else if(average_no_lowest > 70 && average_no_lowest < 80) {
                    System.out.print('C');
                } else {
                    System.out.print('D');
                }
            } else if(score3 < score1 && score3 < score2) {
                average_no_lowest = (score1 + score3) / 2.0;
                System.out.println("The average without the lowest score is: " + (score1 + score3) / 2.0);
                if(average_no_lowest > 90) {
                    System.out.print('A');
                } else if(average_no_lowest < 90 && average_no_lowest > 80) {
                    System.out.print('B');
                } else if(average_no_lowest > 70 && average_no_lowest < 80) {
                    System.out.print('C');
                } else {
                    System.out.print('D');
                }
            }

        }   catch (Exception e){

            System.out.println(e);

        }


    }
}
