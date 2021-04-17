package net.astercrono.gameoflife.life;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Life {
    private List<List<Boolean>> cells;

    public Life(List<List<Boolean>> cells) {
        this.cells = cells;
    }
    
    public void setSeed(List<List<Boolean>> cells) {
    	this.cells = cells;
    }

    public List<List<Boolean>> getCells() {
        return cells;
    }

    public int getRowCount() {
        return cells.size();
    }

    public int getColCount() {
        return cells.get(0).size();
    }

    public Iterator<List<Boolean>> iterator() {
        return cells.iterator();
    }

    public void nextCycle() {
        Life snapshot = snapshot();

        List<Boolean> previousRow = null;
        List<Boolean> currRow = null;
        List<Boolean> originalCurrRow = null;
        List<Boolean> nextRow = null;

        Iterator<List<Boolean>> currIt = snapshot.iterator();
        Iterator<List<Boolean>> originalCurrIt = iterator();
        Iterator<List<Boolean>> nextIt = snapshot.iterator();

        if (nextIt.hasNext()) {
            nextIt.next();
        }

        while (currIt.hasNext()) {
            currRow = currIt.next();
            originalCurrRow = originalCurrIt.next();

            if (nextIt.hasNext()) {
                nextRow = nextIt.next();
            } else {
                nextRow = null;
            }

            for (int i = 0; i < currRow.size(); i++) {
                List<Boolean> nearby = getNearbyCells(i, previousRow, currRow, nextRow);
                applyRules(i, originalCurrRow, nearby);
            }

            previousRow = currRow;
        }
    }
    
    private Life snapshot() {
        List<List<Boolean>> snapshot = new ArrayList<>(cells.size());

        Iterator<List<Boolean>> it = iterator();
        while (it.hasNext()) {
            List<Boolean> row = it.next();
            List<Boolean> snapshotRow = new ArrayList<Boolean>();
            for (Boolean b : row) {
                snapshotRow.add(b && true);
            }
            snapshot.add(snapshotRow);
        }

        return new Life(snapshot);
    }

    private void applyRules(int index, List<Boolean> row, List<Boolean> nearby) {
        Boolean currentAlive = row.get(index);
        int liveCount = countLiveCells(nearby);

        if (currentAlive && (liveCount >= 2 && liveCount <= 3)) {
            // noop
        } else if (!currentAlive && liveCount == 3) {
            row.set(index, true);
        } else {
            row.set(index, false);
        }
    }

    private int countLiveCells(List<Boolean> cells) {
        int count = 0;

        for (Boolean b : cells) {
            if (b) {
                count++;
            }
        }

        return count;
    }

    private List<Boolean> getNearbyCells(int index, List<Boolean> previous, List<Boolean> current, List<Boolean> next) {
        List<Boolean> nearby = new ArrayList<>();

        if (index == 0) {
            nearby.add(current.get(index + 1));

            if (previous != null) {
                nearby.add(previous.get(index));
                nearby.add(previous.get(index + 1));
            }

            if (next != null) {
                nearby.add(next.get(index));
                nearby.add(next.get(index + 1));
            }
        } else if (index == (current.size() - 1)) {
            nearby.add(current.get(index - 1));

            if (previous != null) {
                nearby.add(previous.get(index));
                nearby.add(previous.get(index - 1));
            }

            if (next != null) {
                nearby.add(next.get(index));
                nearby.add(next.get(index - 1));
            }
        } else {
            nearby.add(current.get(index - 1));
            nearby.add(current.get(index + 1));

            if (previous != null) {
                nearby.add(previous.get(index));
                nearby.add(previous.get(index - 1));
                nearby.add(previous.get(index + 1));
            }

            if (next != null) {
                nearby.add(next.get(index));
                nearby.add(next.get(index - 1));
                nearby.add(next.get(index + 1));
            }
        }

        return nearby;
    }
}
