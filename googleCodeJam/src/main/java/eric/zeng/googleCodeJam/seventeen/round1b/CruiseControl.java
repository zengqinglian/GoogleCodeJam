package eric.zeng.googleCodeJam.seventeen.round1b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

public class CruiseControl {
    public static void main(String[] args) throws FileNotFoundException {

        URL path = CruiseControl.class.getResource(args[0]);
        File file = new File(path.getFile());
        Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
        int counter = 1;
        in.nextInt();
        in.nextLine();
        try {
            while (in.hasNext()) {
                String row1 = in.nextLine();
                String[] dAndRows = row1.split(" ");

                long distance = Integer.valueOf(dAndRows[0]);
                int rows = Integer.valueOf(dAndRows[1]);
                long[][] horses = new long[rows][2];
                for (int i = 0; i <= rows - 1; i++) {
                    String dAndS = in.nextLine();
                    String[] dAndSArr = dAndS.split(" ");
                    long d = Integer.valueOf(dAndSArr[0]);
                    long s = Integer.valueOf(dAndSArr[1]);
                    horses[i][0] = d;
                    horses[i][1] = s;
                }
                output(horses, distance, counter);
                counter++;
            }
        } finally {
            in.close();
        }
    }

    private static void output(long[][] hourse, long distance, int counter) {
        String result = getResult(hourse, distance);

        System.out.println("Case #" + counter + ": " + result);
    }

    private static String getResult(long[][] horse, long distance) {

        double time = findLastArrivedHorse(horse, distance);
        double maxSpeed = distance / time;

        return String.format("%.6f", maxSpeed);

    }

    private static double findLastArrivedHorse(long[][] horse, long distance) {
        long[] horse1 = horse[horse.length - 1];
        double time = ((double) (distance - horse1[0]) / horse1[1]);
        for (int i = horse.length - 2; i >= 0; i--) {
            long[] horse2 = horse[i];
            double time2 = ((double) (distance - horse2[0]) / horse2[1]);
            time = Math.max(time, time2);
        }
        return time;
    }
}
