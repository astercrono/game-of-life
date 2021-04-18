package net.astercrono.gameoflife.seed.model;

import java.util.ArrayList;
import java.util.List;

public class LoadedSeed {
	private List<List<Boolean>> cells = new ArrayList<>();
	private Exception error;
	
	public LoadedSeed(List<List<Boolean>> cells) {
		this.cells = cells;
	}
	
	public LoadedSeed(Exception error) {
		this.error = error;
	}
	
	public boolean hasError() {
		return error != null;
	}
	
	public List<List<Boolean>> getCells() {
		return cells;
	}
}
