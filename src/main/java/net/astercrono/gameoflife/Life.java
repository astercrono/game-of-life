package net.astercrono.gameoflife;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Life {
    private List<List<Boolean>> state;
    private boolean active = true;

    public Life(List<List<Boolean>> seed) {
        state = seed;
    }

    public List<List<Boolean>> getState() {
        return state;
    }

    public void update() {
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
            }
            else {
                nextRow = null;
            }

            for (int i = 0; i < currRow.size(); i++) {
                List<Boolean> nearby = getNearbyCells(i, previousRow, currRow, nextRow);
                applyRules(i, originalCurrRow, nearby);
            }

            previousRow = currRow;
        }
    }

    public int getRowCount() {
        return state.size();
    }

    public int getColCount() {
        return state.get(0).size();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Iterator<List<Boolean>> iterator() {
        return state.iterator();
    }

    public Life snapshot() {
        List<List<Boolean>> snapshot = new ArrayList<>(state.size());

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
        }
        else if (!currentAlive && liveCount == 3) {
            row.set(index, true);
        }
        else {
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
            nearby.add(current.get(index+1));

            if (previous != null) {
                nearby.add(previous.get(index));
                nearby.add(previous.get(index+1));
            }

            if (next != null) {
                nearby.add(next.get(index));
                nearby.add(next.get(index+1));
            }
        }
        else if (index == (current.size() - 1)) {
            nearby.add(current.get(index - 1));

            if (previous != null) {
                nearby.add(previous.get(index));
                nearby.add(previous.get(index-1));
            }

            if (next != null) {
                nearby.add(next.get(index));
                nearby.add(next.get(index-1));
            }
        }
        else {
            nearby.add(current.get(index - 1));
            nearby.add(current.get(index + 1));

            if (previous != null) {
                nearby.add(previous.get(index));
                nearby.add(previous.get(index-1));
                nearby.add(previous.get(index+1));
            }

            if (next != null) {
                nearby.add(next.get(index));
                nearby.add(next.get(index-1));
                nearby.add(next.get(index+1));
            }
        }

        return nearby;
    }
}
