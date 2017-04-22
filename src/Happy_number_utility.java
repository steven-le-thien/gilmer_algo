import com.sun.tools.javac.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by lethien_96 on 2/19/17.
 */
public class Happy_number_utility {
    //source http://math.bard.edu/student/pdfs/alison-mutter.pdf
    final static int[] cycleStartingPointTwoThree = {1, 4, 5, 6, 8};
    final static int[] cycleStartingPointTwoSix = {1, 20, 13, 5, 25, 17, 29, 41, 26};
    final static int[] cycleStartingPointTwoTen = {1, 145, 42, 20, 4, 16, 37, 58, 89};
    final static int[] cycleStartingPointThreeTen = {1, 55, 250, 133, 136, 244, 153, 160, 217, 352, 370, 371, 407, 919, 1459};
    final static int[] cycleStartingPointTwoFive = {1, 13, 18, 4, 16, 10};
    final static int[] cycleStartingPointTwoSeven = {1, 10, 25, 32, 45, 2, 4, 16, 8,17,13, 37,29};
    final static int[] cycleStartingPointTwoEight = {1, 20, 52, 4, 16, 26, 13, 5, 25, 10};
    final static int[] cycleStartingPointTwoNine = {1, 41, 50, 68, 74, 53, 89, 65};
    final static int[] cycleStartingPointTwoEleven = {1, 61, 72, 5, 25, 13, 74, 100, 82};


    final static HashMap<Integer, int[]> cycleReferences = new HashMap<Integer, int[]>(){{
        put(102, cycleStartingPointTwoTen);
        put(103, cycleStartingPointThreeTen);
        put(32, cycleStartingPointTwoThree);
        put(62, cycleStartingPointTwoSix);
        put(52, cycleStartingPointTwoFive);
        put(72, cycleStartingPointTwoSeven);
        put(82, cycleStartingPointTwoEight);
        put(92, cycleStartingPointTwoNine);
    }};

    static int getCycleStartingPoint(ArrayList<Integer> digits, int base, int e) {
        while(true){
            int sum = 0;
            if (e == 2) {
                for (int digit : digits) {
                    sum += digit*digit;
                }
            } else {
                for (int digit : digits) {
                    sum += Math.pow(digit, e);
                }
            }

            for (int i : cycleReferences.get(base*10 + e)) if (sum == i) return sum;
            else {
                digits = getBaseNumber(sum, base);
            }
        }
    }

    static int getCycleStartingPoint(int n, int base, int e) {
        if (n == 0) return 0;
        for (int i : cycleReferences.get(base*10 + e)) if (n == i) return n;
        return getCycleStartingPoint(getBaseNumber(n, base), base, e);
    }

    public static int ssds(int n, int base, int e) {
        ArrayList<Integer> digits = getBaseNumber(n, base);

        int sum = 0;
        if (e == 2) {
            for (int digit : digits) {
                sum += digit*digit;
            }
        } else {
            for (int digit : digits) {
                sum += Math.pow(digit, e);
            }
        }

        return sum;
    }

    static ArrayList<Integer> getBaseNumber(int number, int base) {
        ArrayList<Integer> digit = new ArrayList<>();
        int copy = number;
        while (copy > 0) {
            digit.add(copy % base);
            copy = copy/base;
        }
        return digit;
    }


    static void printToFile(double[] array, int m, int startingPoint, int base, int power) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(m+"runs" + startingPoint+ "base" + base + "power" + power + ".csv"));
        StringBuilder sb = new StringBuilder();
        for (int p = 0; p < m; p ++) {
            sb.append(array[p]);
            sb.append(',');
            sb.append('\n');
        }

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
    }

//    static void printToFile(double[] array) throws FileNotFoundException {
//        PrintWriter pw = new PrintWriter(new File("type89logdensityTest.csv"));
//        StringBuilder sb = new StringBuilder();
//        for (int p = 0; p < array.length; p ++) {
//            sb.append(array[p]);
//            sb.append(',');
//            sb.append('\n');
//        }
//
//        pw.write(sb.toString());
//        pw.close();
//        System.out.println("done!");
//    }
}
