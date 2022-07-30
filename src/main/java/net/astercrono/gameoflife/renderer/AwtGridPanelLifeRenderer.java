package net.astercrono.gameoflife.renderer;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import net.astercrono.gameoflife.life.Life;
import net.astercrono.gameoflife.graphics.grid.GridPanel;

public class AwtGridPanelLifeRenderer extends LifeRenderer {
    private static final Color COLOR_ALIVE = Color.ORANGE;
    private static final Color COLOR_DEAD = Color.DARK_GRAY;
    private GridPanel grid;

    public AwtGridPanelLifeRenderer(GridPanel grid) {
        this.grid = grid;
    }

    @Override
    public void render(Life life) {
        drawLife(life);
        grid.redraw();
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
        return b ? COLOR_ALIVE : COLOR_DEAD;
    }
}
