import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by lethien_96 on 3/12/17.
 */
public class HappyTyping {
    final static int e = 2;
    //    final static int b = 10;
    final static int largePrime = 99991;
    final static int runs = 10000000; //10^8

    public static void run(int b) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();


        PrintWriter pw = new PrintWriter(new File(runs+"type_documentation" + b + "power" + e + ".csv"));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < runs; i++) {

            sb.append(i);
            sb.append(',');
            sb.append(Happy_number_utility.getCycleStartingPoint(i, b , e));
            sb.append(',');
            sb.append('\n');
        }


        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");

        System.out.println("Timing is " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] arg) throws FileNotFoundException {
        for(int b = 3; b < 11; b ++) {
            if (b == 4) continue;
            run(b);
        }
    }
}
