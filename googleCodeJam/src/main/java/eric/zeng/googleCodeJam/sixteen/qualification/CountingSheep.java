package eric.zeng.googleCodeJam.sixteen.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CountingSheep {
    public static void main(String[] args) throws FileNotFoundException {

        URL path = CountingSheep.class.getResource(args[0]);
        File file = new File(path.getFile());
        Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
        int counter = 1;
        in.nextInt();
        try {
            while (in.hasNext()) {
                output(in.nextInt(), counter);
                counter++;
            }
        } finally {
            in.close();
        }
    }

    private static void output(int i, int counter) {
        long result = getResult(i);

        if (result == -1) {
            System.out.println("Case #" + counter + ": " + "INSOMNIA");
        } else {
            System.out.println("Case #" + counter + ": " + result);
        }

    }

    private static long getResult(int i) {
        if (i == 0) {
            return -1;
        }
        long result = i;
        Set<Integer> digitSet = new HashSet<Integer>();
        int l = 1;
        while (true) {
            result = i * l;
            insertDigit(result, digitSet);
            l++;
            if (digitSet.size() == 10) {
                return result;
            }
        }
    }

    private static void insertDigit(long i, Set<Integer> set) {
        while (i > 0) {
            set.add((int) (i % 10));
            i = i / 10;
        }
    }
}
