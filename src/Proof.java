/**
 * Created by lethien_96 on 3/26/17.
 */
public class Proof {
    static final int e = 2;
    static final int b = 10;

//    static final int testEntry = 1;

    static final int runs = 10000;

    public static void runInterval(int testEntry) {
        int[] result = new int[runs];
        int counter = 1;
        for (int i = 1; i < runs; i++) {
            int entry = Happy_number_utility.getCycleStartingPoint(i, b, e);
            if (entry == testEntry) {
                result[counter]++;
                counter = 1;
            } else counter++;
        }

        System.out.println("Entry is " + testEntry);

        for (int i = 0; i < result.length; i++) {
            int foo = result[i];
            if (foo != 0) System.out.println(i + " is " + foo);
        }

        System.out.println(" --------- " + "\n");
    }

    public static void runAP(int testEntry) {
        int[] result = new int[146];
        for (int i = runs - 300; i < runs; i++) {
            System.out.println(i);

            int entry1 = Happy_number_utility.getCycleStartingPoint(81*i, b, e);
            result[entry1]++;
            System.out.println("result is " + entry1);
            if (entry1 == testEntry) {
                System.out.println("First config");
                System.exit(0);
            }

//            int entry2 = Happy_number_utility.getCycleStartingPoint(4 + 49 + 81*i , b, e);
//            if (entry2 == testEntry) {
//                System.out.println("Sec config");
//                System.exit(0);
//            }

//            int entry3 = Happy_number_utility.getCycleStartingPoint(16 + 25 + 81*i , b, e);
//            if (entry3 == testEntry) {
//                System.out.println("Third config");
//                System.exit(0);
//            }
            if (i == runs - 1) {
                for (int j = 1; j < result.length; j++) {
                    if (result[j] != 0) System.out.println(j + " : " + result[j]);
                }
            }
        }
    }

    public static void main (String[] args) {
//        for (int i : Happy_number_utility.cycleReferences.get(b*10 + e)) {
//            runAP(i);
//        }
        runAP(2);
//        System.out.println(Happy_number_utility.getCycleStartingPoint(64 + 81, b, e));
    }
}
