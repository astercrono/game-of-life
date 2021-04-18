package net.astercrono.gameoflife.seed;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import net.astercrono.gameoflife.seed.model.LoadedSeed;

public class FileSeedReader implements SeedLoader {
	private File file;
	
	public FileSeedReader(File file) {
		this.file = file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	@Override
    public LoadedSeed loadSeed() {
        try {
        	List<String> lines = Files.readAllLines(file.toPath());
            List<List<Boolean>> cells = new ArrayList<>();

            for (String l : lines) {
            	cells.add(lineToByteArray(l));
            }

            return new LoadedSeed(cells);
        }
        catch (Exception ex) {
        	return new LoadedSeed(ex);
        }
    }

    private List<Boolean> lineToByteArray(String line) {
        List<Boolean> bools = new ArrayList<>(line.length());

        for (int i = 0; i < line.length(); i++) {
            String s = String.valueOf(line.charAt(i));
            bools.add(s.equals("1"));
        }

        return bools;
    }
}
