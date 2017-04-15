package eric.zeng.googleCodeJam.seventeen.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

public class TestTidyNumber {
	public static void main(String[] args) throws FileNotFoundException {

        URL path = Solution.class.getResource(args[0]);
        File file = new File(path.getFile());
        Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
        int counter = 1;
        in.nextInt();
        in.nextLine();
        try {
            while (in.hasNext()) {
                output(in.nextLine(), counter);
                counter++;
            }
        } finally {
            in.close();
        }
    }

    private static void output(String s, int counter) {
        String result = getResult(s);

        System.out.println("Case #" + counter + ": " + result);
       

    }

	private static String getResult(String s) {
		int i = Integer.valueOf(s);
		while(true){
			if(isTidyNumber(i)){
				return String.valueOf(i);
			}
			i--;
		}
	}
	
	private static boolean isTidyNumber(int i){
		if (i<10){
			return true;
		}
		String s = String.valueOf(i);
		char[] cs = s.toCharArray();
		for(int m=0; m<cs.length-1; m++){
			if(cs[m]>cs[m+1]){
				return false;
			}
		}
		return true;
		
	}
		
    
}
