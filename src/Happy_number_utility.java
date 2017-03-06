import com.sun.tools.javac.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lethien_96 on 2/19/17.
 */
public class Happy_number_utility {
    final static int[] cycleStartingPointTwoTen = {1, 145, 42, 20, 4, 16, 37, 58, 89};
    final static int[] cycleStartingPointThreeTen = {1, 55, 250, 133, 136, 244, 153, 160,217,352, 370, 371, 407, 919,1459};

    final static HashMap<Integer, int[]> cycleReferences = new HashMap<Integer, int[]>(){{
        put(12, cycleStartingPointTwoTen);
        put(13, cycleStartingPointThreeTen);
    }};

    static int getCycleStartingPoint(int n, int base, int e) {
        if (n == 0) return 0;
        ArrayList<Integer> digits = getBaseNumber(n, base);

        while(true){
            int sum = 0;
            for (int i = 0; i < digits.size(); i++) {
                int charac = digits.get(i);
                sum += Math.pow(charac, e);
            }
            boolean findCycle = false;
            for (int i : cycleReferences.get(base + e)) if (sum == i) findCycle = true;
            if (findCycle) return sum;
            else {
                digits = getBaseNumber(sum, base);
            }
        }
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
