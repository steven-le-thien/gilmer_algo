import java.io.FileNotFoundException;

/**
 * Created by lethien_96 on 2/19/17.
 */
public class Small_interval {
    final static int startingPoint = 89;

    final static double mu = 57/2;//mean from 0^2 to 9^2
    final static double sigma = Math.sqrt(72105)/10;//std from 0^2 to 9^2
    final static double delta = 3* sigma;//interval radius

    final static int runs = 10000;

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println(delta);

        double[] array = new double[runs - 6492];//normalizing factor to start the array from non-zero density

        for (int i = 6492; i < runs; i++) {
            int lowerBound = (int) (i - delta*Math.sqrt(i));
            int upperBound = (int) (i + delta*Math.sqrt(i));
            if (lowerBound < 1) continue;

            int happyCounter = 0;
            for (int j = lowerBound; j < upperBound + 1; j++) {
//                if (Happy_number_utility.getCycleStartingPoint(j) == startingPoint) happyCounter++;
            }
            System.out.println(i);
            array[i-6492] = (double) happyCounter/(upperBound - lowerBound + 1);
        }

        for (int i = 0; i < runs - 6492; i++) System.out.println(array[i]);


//        Happy_number_utility.printToFile(array);
    }
}
