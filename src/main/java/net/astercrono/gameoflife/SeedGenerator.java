package net.astercrono.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SeedGenerator {
    public static List<List<Boolean>> generateSeed(int rows, int cols) {
    	List<List<Boolean>> cellRows = new ArrayList<>(rows);
    	Random random = new Random();
    	
         for (int r = 0; r < rows; r++) {
        	List<Boolean> cellCols = new ArrayList<>();
        	
            for (int c = 0; c < cols; c++) {
            	cellCols.add(random.nextBoolean());
            }
            
            cellRows.add(cellCols);
        }
         
         return cellRows;
    }
}