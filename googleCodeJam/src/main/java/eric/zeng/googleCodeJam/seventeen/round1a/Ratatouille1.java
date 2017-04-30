package eric.zeng.googleCodeJam.seventeen.round1a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Ratatouille1 {
	public static void main(String[] args) throws FileNotFoundException {

        URL path = Ratatouille.class.getResource(args[0]);
        File file = new File(path.getFile());
        Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
        int counter = 1;
        in.nextInt();
        in.nextLine();
        try {
            while (in.hasNext()) {
            	String r1 = in.nextLine();
            	String[] ingredientAndPackage = r1.split(" ");
            	int ingredient = Integer.valueOf(ingredientAndPackage[0]);
            	int packageNum = Integer.valueOf(ingredientAndPackage[1]);
            	
            	String r2 = in.nextLine();
            	String[] standardWeight = r2.split(" ");
            	
            	int[] standards = new int[ingredient];
            	for(int i =0; i<ingredient;i++){
            		standards[i] = Integer.valueOf(standardWeight[i]);
            	}
            	
            	int[][] board = new int[ingredient][packageNum];
            	
            	for(int i=0;i<ingredient; i++){
            		String r3 = in.nextLine();
            		String[] w = r3.split(" ");
            		for(int j=0; j<packageNum;j++){
            			board[i][j] = Integer.valueOf(w[j]);
            		}
            	}

            	output(board, standards, counter, ingredient, packageNum);
                counter++;
            }
        } finally {
            in.close();
        }
    }

    private static void output(int[][] board,int[] standards, int counter, int row, int col) {
  
        System.out.println("Case #" + counter + ": " + getResult(board, standards,row, col));
        
    }

    private static int getResult(int[][] board,int[] standards, int row, int col) {
    	int[] indexes = new int[row];
    	
    	//sorted board
    	for(int i=0; i<row; i++){
    		Arrays.sort(board[i]);
    	}
    	
    	int result = 0;
    	
        while(true){
        	int[] weights = new int[row];
        	for(int i=0; i< row; i++){
        		weights[i] = board[i][indexes[i]];
        	}
        	int maxlow = (int)(weights[0] / (standards[0] *1.1));
        	long minhigh =(int)(weights[0] / (standards[0] * 0.9));
        	int moveIndex = 0;
        	for(int i=0; i<row; i++){
        		int low = (int)(weights[i] / (standards[i] * 1.1));
        		long high =(int)(weights[i] / (standards[i] *0.9));
        		
        		if(low>maxlow){
        			maxlow = low;
        		}
        		
        		if(high < minhigh){
        			minhigh = high;
        			moveIndex = i;
        		}
        	}
        	
        	if(maxlow<=minhigh){
        		boolean matched=false; 
        		for(int i=maxlow; i<=minhigh; i++){
        			boolean found = true;
        			for(int j=0; j<row; j++){
        				if(weights[j] < standards[j]*i*0.9 || weights[j] > standards[j]*i*1.1){
        					found = false;
        				}
        			}
        			if(found){
        				matched=true;
        				result++;
        				for(int m=0; m<row; m++){
                			if(indexes[m]+1>=col){
                    			return result;
                    		}else{
                    			indexes[m] = indexes[m]+1;
                    		}
                		}
        				break;
        			}
        		}

        		if(!matched){
        			if(indexes[moveIndex]+1>=col){
            			return result;
            		}else{
            			indexes[moveIndex] = indexes[moveIndex]+1;
            		}
        		}
        	}else{
        		if(indexes[moveIndex]+1>=col){
        			return result;
        		}
        		indexes[moveIndex] = indexes[moveIndex]+1;
        	}
        } 
    }
}
