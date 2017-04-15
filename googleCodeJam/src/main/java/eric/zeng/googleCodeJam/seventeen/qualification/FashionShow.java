package eric.zeng.googleCodeJam.seventeen.qualification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FashionShow {
	 public static void main(String[] args) throws FileNotFoundException {

	        URL path = FashionShow.class.getResource(args[0]);
	        File file = new File(path.getFile());
	        Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
	        int counter = 1;
	        in.nextInt();
	        in.nextLine();
	        try {
	            while (in.hasNext()) {
	            	List<String> init = new ArrayList<>();
	            	String r1 = in.nextLine();
	            	String[] sizeAndInit = r1.split(" ");
	            	int d = Integer.valueOf(sizeAndInit[0]);
	            	for(int i = 0; i<Integer.valueOf(sizeAndInit[1]); i++){
	            		init.add(in.nextLine());
	            	}
	            	output(d, init, counter, new ArrayList<String>());
	                counter++;
	            }
	        } finally {
	            in.close();
	        }
	    }

	    private static void output(int d, List<String> init, int counter, List<String> out) {
	        int[] result = getResult(d, init, out);
 
	        BufferedWriter bw = null;
			FileWriter fw = null;
			
			URL path = FashionShow.class.getResource("fashionSmallResult.txt");
			File file = new File(path.getFile());
			
			try {
				System.out.println("Case #" + counter + ": " + result[0] + " "+ result[1]);

				if (!file.exists()) {
					file.createNewFile();
				}
				// true = append file
				fw = new FileWriter(file.getAbsoluteFile(), true);
				bw = new BufferedWriter(fw);

				bw.write("Case #" + counter + ": " + result[0] + " "+ result[1]);
				bw.newLine();
				
				for(String s : out){
					 bw.write(s);
		        	 System.out.println(s);
					 bw.newLine();
		        }
				

				bw.flush();
			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}
			} 
	    }

	    private static int[] getResult(int d,  List<String> init, List<String> out) {
	    	char[][] board = new char[d][d];
	    	int total=0;
	    	for(int i=0; i<d;i++){
	    		for(int j=0; j<d; j++){
	    			board[i][j] = '.';
	    		}
	    	}
	    	for(String s : init){
	    		String[] info = s.split(" ");
	    		char c = info[0].charAt(0);
	    		int row = Integer.valueOf(info[1])-1;
	    		int col = Integer.valueOf(info[2])-1;
	    		board[row][col] = c;
	    	}
	    	
	    	if(d==1){
	    		if(board[0][0]!='o'){
	    			out.add("o 1 1");
	    		}
	    		int[] result ={2,0};
	    		return result;
	    	}
	    	
	    	int changed=0;
	    	
	    	for(int i=0; i<d;i++){
	    		for(int j=0; j<d; j++){
	    			//find row
	    			char r = findRow(i,j, board, d);
	    			//find col
	    			char c = findCol(i,j, board, d);
	    			//find diagonal
	    			char dg = findDiagonal(i,j, board, d);
	    			
	    			
	    			Set<Character> set = new HashSet<>();
    				set.add(r);
    				set.add(c);
    				set.add(dg);
    				
	    			if(set.size()==1 && set.contains('?')){
	    				if(board[i][j]!='o'){
	    					board[i][j]='o';
	    					out.add("o " + (i+1) + " " + (j+1));
	    					changed++;
	    				}
	    			}else if(set.contains('x') && set.contains('+')) {
	    				if(board[i][j]!='.'){
	    					throw new RuntimeException("invalid board");
	    				}
	    			}else {
	    				if(set.contains('x') && board[i][j]!='x'){
	    					board[i][j] = 'x';
	    					out.add( "x " + (i+1) + " " + (j+1));
	    					changed++;
	    				}
	    				if(set.contains('+') && board[i][j]!='+'){
	    					board[i][j] = '+';
	    					out.add( "+ " + (i+1) + " " + (j+1));
	    					changed++;
	    				}
	    			}
	    		}
	    	}
	    	
	    	for(int i=0; i<d;i++){
	    		for(int j=0; j<d; j++){
	    			if(board[i][j]=='+' || board[i][j]=='x'){
	    				total++;
	    			}
	    			if(board[i][j]=='o'){
	    				total+=2;
	    			}
	    		}
	    	}
	    	
	    	int[] result = new int[2];
	    	
	    	result[0] = total;
	    	result[1] = changed;
	    	
	    	return result;
	    }
	    
	    private static char findRow(int row,int col, char[][] board, int d ){
	    	Set<Character> set = new HashSet<>();
	    	for(int i=0; i<d; i++){
	    		if(board[row][i]!='.' && i!=col){
	    			set.add(board[row][i]);
	    		}
	    	}
	    	if(set.size()==0){
	    		return '?';
	    	}else if(set.size()==1){
	    		if(set.contains('+')){
	    			return '?';
	    		}else{
	    			return '+';
	    		}
	    	}else{
	    		return '+';
	    	}
	    }
	    
	    private static char findCol(int row, int col, char[][] board, int d ){
	    	Set<Character> set = new HashSet<>();
	    	for(int i=0; i<d; i++){
	    		if(board[i][col]!='.' && i!=row){
	    			set.add(board[i][col]);
	    		}
	    	}
	    	if(set.size()==0){
	    		return '?';
	    	}else if(set.size()==1){
	    		if(set.contains('+')){
	    			return '?';
	    		}else{
	    			return '+';
	    		}
	    	}else{
	    		return '+';
	    	}
	    }
	    
	    private static char findDiagonal (int row, int col, char[][] board, int d ){
	    	Set<Character> set = new HashSet<>();
	    	for(int i=0;i<d; i++){
	    		for(int j=0;j<d;j++){
	    			if((i+j == row+col || i-j == row-col) && i!=row && j!=col && board[i][j]!='.'){
	    				set.add(board[i][j]);
	    			}
	    		}
	    	}
	    	if(set.size()==0){
	    		return '?';
	    	}else if(set.size()==1){
	    		if(set.contains('x')){
	    			return '?';
	    		}else{
	    			return 'x';
	    		}
	    	}else{
	    		return 'x';
	    	}
	    }

}
