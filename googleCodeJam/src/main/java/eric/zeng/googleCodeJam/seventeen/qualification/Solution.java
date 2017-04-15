package eric.zeng.googleCodeJam.seventeen.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;
public class Solution {
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
	        while(index<in.length()){
	        	if(in.charAt(index)=='-'){
	        		int i = index+1;
	        		int headMinus = 1;
	        		int tailMinus = 0;
	        		int midPlus = 0;
	        		while(i<in.length() && in.charAt(i)=='-'){
	        			headMinus++;
	        			i++;
	        		}
	        		if(headMinus==len){
	        			result++;
	        			index=i;
	        			continue;
	        		}
	        		
	        		while(i<in.length() && in.charAt(i)=='+'){
	        			midPlus++;
	        			i++;
	        		}
	        		while(i<in.length() && in.charAt(i)=='-' && tailMinus<(headMinus % len)){
	        			tailMinus++;
	        			i++;
	        		}
	        		
	        		result += headMinus / len;
	        		headMinus = headMinus % len;
	        		if(headMinus !=tailMinus || (headMinus + midPlus) % len!= 0){
	        			return -1;
	        		}else{
	        			result +=  ((headMinus + midPlus) / len) * 2;
	        		}
	        		
	        		index=i;
	        		
	        		
	        	}else{
	        		index++;
	        	}
	        	
	        
	        }
	        return result;
	    }

}
