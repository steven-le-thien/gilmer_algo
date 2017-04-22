import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by lethien_96 on 4/21/17.
 */

//Doing 89
public class ElSedy {
    final static int b = 10;
    final static int e = 2;
    final static int[] cycleStartingPointTwoTen = {1 , 4, 16 , 20, 37, 42, 58, 89 ,145,};//, ,
    final static int tester = 4;



    public static void main(String[] args) throws IOException{
        ArrayList<Integer> answer = new ArrayList<>();
//        for (int i : cycleStartingPointTwoTen) {
//            System.out.println(Happy_number_utility.getCycleStartingPoint(226500 + Happy_number_utility.ssds(i + 11,b,e), b, e));
//        }

        int[] lookme = new int[100000000];

        BufferedReader br = new BufferedReader(new FileReader("lookup.csv"));


        for (int i = 0; i < 100000000; i++) {
            String line = br.readLine();
            int current1 = Integer.parseInt(line.substring(0, line.length() - 1));
            lookme[i] = current1;
        }

        int counter = 1;

        while(answer.size() == 0) {
            for (int i = 1; i < 90000000; i++) {
//            System.out.println(i);
                boolean foundFault = false;

//            if (i % 10 == 9) continue;



                for (int j : cycleStartingPointTwoTen) {
//                if (lookme[Happy_number_utility.ssds(i/10, b, e) + Happy_number_utility.ssds(j + 14989 + (i%10)*100000 , b, e)] != tester) {
                    if (lookme[i + Happy_number_utility.ssds(j + counter, b, e)] != tester) {
//                if (lookme[i + j] != tester){
                        foundFault = true;
                        break;
                    }
                }
                if (!foundFault) {
                    System.out.println("This is it " + i);
                    answer.add(i);
//                System.out.println(answer.size());
//                System.exit(0);
                }
            }

            System.out.println("Answer is");
            for (int i = 0; i < answer.size(); i++) {
                System.out.println(answer.get(i));
            }
            counter ++;
        }
        System.out.println("counter is" + counter);
    }
}
