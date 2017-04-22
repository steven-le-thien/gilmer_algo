import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;

public class Gilmer_algo {

//    final static int startingPoint = 8;
    final static int e = 2;
    final static int b = 9;
    final static int m = 8000;
    final static int alpha = (int) Math.pow((b-1), e);
    final static int colnum = m * alpha;


    final static int map_base = colnum + 1;

    public static int key_generator(int row, int col) {
        if (col < 0) return -1;
        return row * map_base + col;
    }

    public static int return_row(int key) {
        return (int) key / map_base;
    }

    public static int return_col(int key) {
        return (int) key % map_base;
    }

    public static void initialize(HashMap<Integer, Double> dynamic_array){
        dynamic_array.put(key_generator(0, 0), (double) 1);
        dynamic_array.put(-1, 0.0);

        for (int i = 1; i < colnum; i++) {
            dynamic_array.put(key_generator(0, i), 0.0);
        }
    }

    public static void run(int startingPoint) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();

        HashMap<Integer, Double> dynamic_array = new HashMap<>();
        initialize(dynamic_array);


//        System.out.println(System.currentTimeMillis() - startTime);

        HashSet<Integer> cTypeInt = new HashSet<>();
        double[] finalProb = new double[m];
//        double[] logDensity = new double[m];
        for (int i = 1; i < m; i++) {
            System.out.println(i);
            for (int l  = (i-1)*alpha; l < i*alpha; l ++) {
                if (Happy_number_utility.getCycleStartingPoint(l, b , e) == startingPoint) cTypeInt.add(l);
            }
//            System.out.println(System.currentTimeMillis() - startTime);


            for (int j = 0; j < colnum; j++) {
                double sum = 0;
                for (int k = 0; k < b; k++) {
                    sum += dynamic_array.get(key_generator((i - 1) % 2, j - (int)Math.pow(k, e)));
                }

                dynamic_array.put(key_generator((i) % 2, j), sum/b);
            }
//            System.out.println(System.currentTimeMillis() - startTime);


            double finalEntry = 0;
            for (int o : cTypeInt) {
                finalEntry += dynamic_array.get(key_generator((i) % 2, o));
            }
            finalProb[i] = finalEntry;
//            System.out.println(System.currentTimeMillis() - startTime);


//            double sum = 0;
//            for (int j = 0; j < i; j++) {
//                sum += finalProb[j];
//            }
//
//            logDensity[i] = sum/i;
            System.out.println(finalProb[i]);
        }

        Happy_number_utility.printToFile(finalProb, m, startingPoint, b, e);


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime / 1000);
    }



    public static void main(String[] args) throws  FileNotFoundException{
        int[] currentRun = {1, 41, 50, 68, 74, 53, 89, 65};

        for (int i : currentRun) run(i);

    }
}
