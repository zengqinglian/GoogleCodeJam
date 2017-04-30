package eric.zeng.googleCodeJam.seventeen.round1c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParentingPartnering {
  public static void main(String[] args) throws FileNotFoundException {

    URL path = ParentingPartnering.class.getResource(args[0]);
    File file = new File(path.getFile());
    Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
    int counter = 1;
    in.nextInt();
    in.nextLine();
    try {
      while (in.hasNext()) {
        String r1 = in.nextLine();
        String[] candj = r1.split(" ");
        int c = Integer.valueOf(candj[0]);
        int j = Integer.valueOf(candj[1]);
        List<int[]> cActives = new ArrayList<>();
        List<int[]> jActives = new ArrayList<>();
        for (int i = 0; i < c; i++) {
          String r = in.nextLine();
          String[] rh = r.split(" ");
          int S = Integer.valueOf(rh[0]);
          int E = Integer.valueOf(rh[1]);
          int[] time = new int[2];
          time[0] = S;
          time[1] = E;
          cActives.add(time);
        }

        for (int i = 0; i < j; i++) {
          String r = in.nextLine();
          String[] rh = r.split(" ");
          int S = Integer.valueOf(rh[0]);
          int E = Integer.valueOf(rh[1]);
          int[] time = new int[2];
          time[0] = S;
          time[1] = E;
          jActives.add(time);
        }
        output(cActives, jActives, counter);
        counter++;
      }
    } finally {
      in.close();
    }
  }

  private static void output(List<int[]> cActives, List<int[]> jActives, int counter) {
    int result = getResult(cActives, jActives);

    System.out.println("Case #" + counter + ": " + result);

  }

  private static int getResult(List<int[]> cActives, List<int[]> jActives) {
    if (cActives.size() == 1 && jActives.size() == 1) {
      return 2;
    }
    if (cActives.size() + jActives.size() <= 1) {
      return 2;
    }
    if (cActives.size() == 0 || jActives.size() == 0) {
      if (cActives.size() != 0) {
        return getNumber(cActives);
      }

      if (jActives.size() != 0) {
        return getNumber(jActives);
      }
    }

    return -1;
  }

  private static int getNumber(List<int[]> actives) {
    int[] p1 = actives.get(0);
    int[] p2 = actives.get(1);

    int head = 0;
    int between = 0;
    int tail = 0;
    if (p1[0] < p2[0]) {
      head = p1[0];
      between = p2[0] - p1[1];
      tail = 1440 - p2[1];
    }

    if (p1[0] > p2[0]) {
      head = p2[0];
      between = p1[0] - p2[1];
      tail = 1440 - p1[1];
    }

    if (head + tail >= 720 || between >= 720) {
      return 2;
    } else {
      return 4;
    }
  }

}
