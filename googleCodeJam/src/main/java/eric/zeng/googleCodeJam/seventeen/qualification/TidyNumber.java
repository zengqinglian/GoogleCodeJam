package eric.zeng.googleCodeJam.seventeen.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

public class TidyNumber {
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
			char[] chars = s.toCharArray();
			if(chars.length==1){
				return s;
			}
			
			for(int i=chars.length-1; i>=1; i--){
				for(int j=i-1; j>=0; j--){
					if(chars[i]<chars[j]){
						if(chars[i-1]!='0'){
							chars[i-1]=String.valueOf(Character.getNumericValue(chars[i-1])-1).charAt(0);
						}
						for(int m=i; m<chars.length; m++){
							chars[m]='9';
						}
						break;
					}
				}
			}
			
			return String.valueOf(chars).replaceAll("^0+(?!$)", "");
			
		}
	    
}
