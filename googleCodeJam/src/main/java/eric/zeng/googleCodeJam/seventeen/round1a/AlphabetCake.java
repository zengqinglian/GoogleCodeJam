package eric.zeng.googleCodeJam.seventeen.round1a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

import eric.zeng.googleCodeJam.seventeen.qualification.Solution;

public class AlphabetCake {
	public static void main(String[] args) throws FileNotFoundException {

        URL path = AlphabetCake.class.getResource(args[0]);
        File file = new File(path.getFile());
        Scanner in = new Scanner(new BufferedReader(new FileReader(file)));
        int counter = 1;
        in.nextInt();
        in.nextLine();
        try {
            while (in.hasNext()) {
            	String r1 = in.nextLine();
            	String[] sizeAndInit = r1.split(" ");
            	int r = Integer.valueOf(sizeAndInit[0]);
            	int c = Integer.valueOf(sizeAndInit[1]);
            	char[][] board = new char[r][c];
            	for(int i = 0; i<r; i++){
            		String row = in.nextLine();
            		for(int j=0; j<c; j++){
            			board[i][j] = row.charAt(j);
            		}
            	}
            	output(board, counter, r, c);
                counter++;
            }
        } finally {
            in.close();
        }
    }

    private static void output(char[][] board, int counter, int row, int col) {
        getResult(board, row, col);
        
        System.out.println("Case #" + counter + ": ");
        for(int i=0; i<row; i++){
        	StringBuilder sb = new StringBuilder();
        	for(int j=0; j<col;j++){
        		sb.append(board[i][j]);
        	}
        	System.out.println(sb.toString());
        }
    }

    private static void getResult(char[][] board, int row, int col) {
    	for(int i=0; i<row; i++){
    		char c = '0';
    		for(int j=0; j<col; j++){
    			if(c=='0'){
    				if(board[i][j]!='?'){
    					c = board[i][j];
    					for(int m=j-1; m>=0;m--){
    						board[i][m]=c;
    					}
    				}else{
    					continue;
    				}
    			}else{
    				if(board[i][j]!='?'){
    					c = board[i][j];
    				}else{
    					board[i][j] = c;
    				}
    			}
    		}
    	}
    	
    	for(int i=0; i<col; i++){
    		char c = '0';
    		for(int j=0; j<row; j++){
    			if(c=='0'){
    				if(board[j][i]!='?'){
    					c = board[j][i];
    					for(int m=j-1; m>=0;m--){
    						board[m][i]=c;
    					}
    				}else{
    					continue;
    				}
    			}else{
    				if(board[j][i]!='?'){
    					c = board[j][i];
    				}else{
    					board[j][i] = c;
    				}
    			}
    		}
    	}
       
    }
}
