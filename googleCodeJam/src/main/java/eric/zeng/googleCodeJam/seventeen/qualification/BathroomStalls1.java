package eric.zeng.googleCodeJam.seventeen.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class BathroomStalls1 {
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
        long[] result = getResult(s);
     
        System.out.println("Case #" + counter + ": " + result[0] + " " + result[1]);
    }

    private static long[] getResult(String s) {
    	long result[] = new long[2];
        String[] array=s.split(" ");
        long stalls = Long.valueOf(array[0]);
        long people = Long.valueOf(array[1]);
        if(stalls == people){
        	result[0] = 0;
        	result[1] = 0;
        	return result;
        }
        
        TreeMap<Long,Long> orderedKeyMap = new TreeMap<>(Collections.reverseOrder());
        orderedKeyMap.put(stalls, 1L);
        
        long left = -1L;
        long right = -1L;
        
        while(people>0){
        	long longestStalls = orderedKeyMap.firstKey();
        	long remain = orderedKeyMap.get(longestStalls);
        	
        	left = (longestStalls-1)/2;
        	right = (longestStalls-1)/2+ ((longestStalls-1) % 2);
        	
        	orderedKeyMap.remove(longestStalls);
        	
        	if(right>0){
        		if(orderedKeyMap.containsKey(right)){
        			orderedKeyMap.put(right, orderedKeyMap.get(right)+remain);
        		}else{
        			orderedKeyMap.put(right, remain);
        		}
        	}
        	
        	if(left>0){
        		if(orderedKeyMap.containsKey(left)){
        			orderedKeyMap.put(left, orderedKeyMap.get(left)+remain);
        		}else{
        			orderedKeyMap.put(left, remain);
        		}
        	}
        	
        	people=people-remain;
        }
        
        result[0]=Math.max(left, right);
        result[1]=Math.min(left, right);
        
        return result;
       
    }
}
