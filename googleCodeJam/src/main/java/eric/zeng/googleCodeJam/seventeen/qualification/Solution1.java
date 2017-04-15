package eric.zeng.googleCodeJam.seventeen.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

public class Solution1 {
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
	        int result = getResult(s);

	        if (result == -1) {
	            System.out.println("Case #" + counter + ": " + "IMPOSSIBLE");
	        } else {
	            System.out.println("Case #" + counter + ": " + result);
	        }

	    }

	    private static int getResult(String s) {
	    	int result = 0;
	        String[] array=s.split(" ");
	        String in = array[0];
	        int len = Integer.valueOf(array[1]);
	       
	        if(in.length()<len){
	    	   return -1;
	        }
	       
	        if(len==1){
	    	   for(char c : in.toCharArray()){
	    		   if(c=='-'){
	    			   result++;
	    		   }
	    	   }
	    	   return result;
	        }
	       
	        if(in.length()==len){
	    	   char first = in.charAt(0);
	    	   for(char c : in.toCharArray()){
	    		   if(c!=first){
	    			   return -1;
	    		   }
	    	   }
	    	   if(first=='+'){
	    		   return 0;
	    	   }else{
	    		   return 1;
	    	   }
	        }
	       
	        int index = 0;
	        char[] arr = in.toCharArray();
	        while(index<arr.length - len){
	        	if(arr[index]=='-'){
	        		arr[index] = '+';
	        		for(int j=1; j<len;j++){
	        			if(arr[index+j]=='-'){
	        				arr[index+j] = '+';
	        			}else{
	        				arr[index+j] = '-';
	        			}
	        		}
	        		result++;
	        	}
	        		
	        	index++;	
	        }
	        
	        for(int i=0 ; i<len-1;i++){
	        	if(arr[i+index]!=arr[i+index+1]){
	        		return -1;
	        	}
	        }
 
	        return arr[index]=='+' ? result : result+1;
	    }

}
