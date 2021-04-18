package net.astercrono.gameoflife.seed;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.astercrono.gameoflife.seed.model.LoadedSeed;

public class RandomSeedLoader implements SeedLoader {
	private int rows = 20;
	private int cols = 20;
	
	public RandomSeedLoader(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}
	
	public void setDimensions(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	@Override
    public LoadedSeed loadSeed() {
    	List<List<Boolean>> cellRows = new ArrayList<>(rows);
    	Random random = new Random();
    	
         for (int r = 0; r < rows; r++) {
        	List<Boolean> cellCols = new ArrayList<>();
        	
            for (int c = 0; c < cols; c++) {
            	cellCols.add(random.nextBoolean());
            }
            
            cellRows.add(cellCols);
        }
         
         return new LoadedSeed(cellRows);
    }
}