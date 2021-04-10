package net.astercrono.gameoflife;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.astercrono.colorgrid.ColorGrid;

public class LifeRenderer {
    private static final Color COLOR_ALIVE = Color.ORANGE;
    private static final Color COLOR_DEAD = Color.DARK_GRAY;
    private ColorGrid grid;
    private boolean paused = true;

    public LifeRenderer(ColorGrid grid) {
        this.grid = grid;
    }

    public void render(Life life) {
        while (life.isActive()) {
            if (!paused) {
                drawLife(life);
                delay();
                life.update();
            }
            grid.repaint();
        }
    }

    public void togglePause() {
        if (paused) {
            paused = false;
        }
        else {
            paused = true;
        }
    }

    private void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void drawLife(Life life) {
        Iterator<List<Boolean>> it = life.iterator();
        int rowCount = 0;
        while (it.hasNext()) {
            List<Boolean> stateRow = it.next();

            int colCount = 0;
            for (Boolean b : stateRow) {
                grid.setCellColor(rowCount, colCount, stateToColor(b));
                colCount++;
            }

            rowCount++;
            colCount = 0;
        }
    }

    private Color stateToColor(Boolean b) {
        return b? COLOR_ALIVE : COLOR_DEAD;
    }
}
