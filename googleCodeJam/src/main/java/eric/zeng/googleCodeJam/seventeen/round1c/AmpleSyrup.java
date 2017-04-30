package eric.zeng.googleCodeJam.seventeen.round1c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class AmpleSyrup {
  public static void main(String[] args) throws FileNotFoundException {

    URL path = AmpleSyrup.class.getResource(args[0]);
    File file = new File(path.getFile());
    Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
    int counter = 1;
    in.nextInt();
    in.nextLine();
    try {
      while (in.hasNext()) {
        String r1 = in.nextLine();
        String[] nAndK = r1.split(" ");
        int n = Integer.valueOf(nAndK[0]);
        int k = Integer.valueOf(nAndK[1]);
        int[][] cakes = new int[n][2];
        for (int i = 0; i < n; i++) {
          String r = in.nextLine();
          String[] rh = r.split(" ");
          int R = Integer.valueOf(rh[0]);
          int H = Integer.valueOf(rh[1]);
          int[] cake = new int[2];
          cake[0] = R;
          cake[1] = H;
          cakes[i] = cake;
        }
        output(n, k, cakes, counter);
        counter++;
      }
    } finally {
      in.close();
    }
  }

  private static void output(int n, int k, int[][] cakes, int counter) {
    double result = getResult(n, k, cakes);

    System.out.println("Case #" + counter + ": " + String.format("%.9f", result));

  }

  private static double getResult(int n, int k, int[][] cakes) {
    Arrays.sort(cakes, new Comparator<int[]>() {

      @Override
      public int compare(int[] o1, int[] o2) {
        if (o1[0] - o2[0] > 0) {
          return -1;
        } else if (o1[0] - o2[0] < 0) {
          return 1;
        } else {
          return -(o1[1] - o2[1]);
        }
      }
    });

    double[] bases = new double[n];
    double[] height = new double[n];
    int index = 0;
    for (int[] cake : cakes) {
      bases[index] = ((double) Math.pow(cake[0], 2)) * Math.PI;
      height[index] = (double) (2.0 * Math.PI * cake[0] * cake[1]);
      index++;
    }
    double max = 0.0;
    for (int i = 0; i <= n - k; i++) {
      double area = bases[i] + height[i];
      PriorityQueue<Double> queue = new PriorityQueue<>(Collections.reverseOrder());
      for (int j = i + 1; j < n; j++) {
        queue.add(height[j]);
      }
      for (int m = 0; m < k - 1; m++) {
        area += queue.poll();
      }
      max = Math.max(max, area);
    }
    return max;
  }
}
