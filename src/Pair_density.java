import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by lethien_96 on 3/12/17.
 */

public class Pair_density {
    final static int e = 2;
//    final static int b = 10;
    final static int largePrime = 99991;
    final static int runs = 1000000; //10^8

    final static double powerScaleBase = Math.pow(10.0, 0.25);


    public static void run(int b) throws FileNotFoundException{
        long startTime = System.currentTimeMillis();

        HashSet<Integer> powerScale = new HashSet<>();
        for (int i = 1; (int) Math.pow(powerScaleBase, i) < runs; i ++ ) powerScale.add((int)Math.pow(powerScaleBase, i));

        HashMap<Integer, Integer> pairCount = new HashMap<>();
        HashMap<Integer, ArrayList<Double>> pairDensityCount = new HashMap<>();
        HashMap<Integer, Integer> indivCount = new HashMap<>();
//        HashMap<Integer, ArrayList<Double>> densityDifference = new HashMap<>();

//        ArrayList<Double> sumOverTypes = new ArrayList<>();

        int[] dynamic = new int[2];

        for (int m : (Happy_number_utility.cycleReferences.get(b*10+e))) {
            for (int n : (Happy_number_utility.cycleReferences.get(b * 10 + e))) {
                pairDensityCount.put(m*largePrime + n, new ArrayList<>());
//                densityDifference.put(m*largePrime + n, new ArrayList<>());
            }
        }

        PrintWriter pw = new PrintWriter(new File(runs+"pair_density_differences" + b + "power" + e  + ".csv"));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < runs; i++) {
            System.out.println(i);
            int dynamic_pointer = i%2;
            dynamic[dynamic_pointer] = Happy_number_utility.getCycleStartingPoint(i, b , e);

            if (dynamic[1] == 0) {
                indivCount.put(dynamic[0],indivCount.getOrDefault(dynamic[0],0)+1);
            }

            if (dynamic[0] != 0 && dynamic[1] != 0) {
                pairCount.put(dynamic[dynamic_pointer] * largePrime + dynamic[1-dynamic_pointer],
                        pairCount.getOrDefault(dynamic[dynamic_pointer] * largePrime + dynamic[1-dynamic_pointer],0) + 1);

                indivCount.put(dynamic[dynamic_pointer],indivCount.getOrDefault(dynamic[dynamic_pointer],0)+1);
            }


            for (int k : powerScale) {
//            int k = i;
                if (k == i) {
                    double sum = 0.0;
//                    double prod = 0.0;
//                    double foo = 0.0;
                    for (int j : pairDensityCount.keySet()) {

                        double currentCount = (double) pairCount.getOrDefault(j, 0);
                        double currentDensity = currentCount/(i - 1);
//                        ArrayList<Double> currentArray = pairDensityCount.get(j);
//                        currentArray.add(currentDensity);
//                        pairDensityCount.put(j, currentArray);

                        double firstDensity = ((double) indivCount.getOrDefault(j%largePrime,0))/ (i);
                        double secondDensity = ((double) indivCount.getOrDefault(j/largePrime,0))/ (i);

                        if (currentDensity > 1 ||firstDensity > 1 ||secondDensity > 1  ) System.exit(0);
//                        ArrayList<Double> currentDensityArray = densityDifference.get(j);
//                        currentDensityArray.add(Math.abs(
//                                currentDensity - firstDensity * secondDensity));
//                        densityDifference.put(j, currentDensityArray);

                        sum += currentDensity - firstDensity * secondDensity;

//                        prod +=  firstDensity * secondDensity;
//                        foo += currentDensity;
//
//                        System.out.println(j%largePrime + "wdad " + j/largePrime + " : " + currentDensity + " " + firstDensity + " " + secondDensity + " " + prod +" " + foo +" " + sum + " ");

                    }



                    sb.append(i);
                    sb.append(',');
                    sb.append(Math.abs(sum));
                    sb.append('\n');

//                    if (k == 86) System.exit(0);
                }
            }
//            if (dynamic[0] == dynamic[1] && dynamic[0] == 20 && i != 137890) System.exit(0);
        }

        for (int i : pairCount.keySet()) System.out.println("for pair " + i%largePrime + " : " + i/largePrime + " there is " + pairCount.get(i));


        System.out.println("size is " + pairCount.size());

//        PrintWriter pw = new PrintWriter(new File(runs+"pair_density" + b + "power" + e + ".csv"));
//        StringBuilder sb = new StringBuilder();
//
//        for (int i : (Happy_number_utility.cycleReferences.get(b*10+e))){
//            for (int j : (Happy_number_utility.cycleReferences.get(b*10+e))){
//                sb.append(i);
//                sb.append(',');
//                sb.append(j);
//                sb.append(',');
//
//                sb.append(pairCount.getOrDefault(i*largePrime + j,0));
//                sb.append(',');
//
//                ArrayList<Double> currentArray = pairDensityCount.get(i*largePrime + j);
//
//                for (int k = 0; k < currentArray.size(); k++) {
//                    sb.append(currentArray.get(k));
//                    sb.append(',');
//                }
//                sb.append('\n');
//            }
//        }

//        for (int j = 0; j < sumOverTypes.size(); j ++) {
//            sb.append(j);
//            sb.append(',');
//            sb.append(sumOverTypes.get(j));
//            sb.append('\n');
//        }

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");

        System.out.println("Timing is " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] arg) throws FileNotFoundException {
//        for(int b = 3; b < 9; b ++) {
//            if (b == 4) continue;
//            run(b);
//        }
        run(10);
    }
}
