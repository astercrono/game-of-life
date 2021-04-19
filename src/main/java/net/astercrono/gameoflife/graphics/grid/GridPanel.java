package net.astercrono.gameoflife.graphics.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.astercrono.gameoflife.graphics.ComponentWrapper;

public class GridPanel implements ComponentWrapper<JPanel> {
    private static final int BASE_BLOCK_SIZE = 10;
    private static final int DEFAULT_SCALE = 2;
    private static final Color DEFAULT_STROKE_COLOR = Color.LIGHT_GRAY;

    private int rows;
    private int columns;
    private List<List<GridBlock>> blocks;

    private int scale = DEFAULT_SCALE;
    private Color strokeColor = DEFAULT_STROKE_COLOR;

    private int blockSize;

    private JPanel panel = new JPanel() {
        private static final long serialVersionUID = 0L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBlocks((Graphics2D)g);
        }
    };

    public GridPanel() {
        init();
    }

    public GridPanel(int rows, int columns) {
        init();
    }

    public void setDimensions(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        configureBlocks();
    }

    public void setCellColor(int row, int col, Color color) {
        blocks.get(row).get(col).setColor(color);
    }

    public void updateScale(int scale) {
        this.scale = scale;
        this.blockSize = BASE_BLOCK_SIZE * this.scale;
    }

    @Override
    public JPanel getComponent() {
        return panel;
    }

    public void redraw() {
        panel.repaint();
    }

    public void clearBlocks() {
        if (blocks != null) {
            blocks.clear();
        }
        blocks = null;
    }

    private void init() {
        panel.setBackground(Color.DARK_GRAY);
        updateScale(1);
    }

    private void configureBlocks() {
    	int renderBlockRows = panel.getWidth() / blockSize;
    	int renderBlockColumns = panel.getHeight() / blockSize;
    	
    	if (rows > renderBlockRows) {
    		renderBlockRows = rows;
    	}
    	
    	if (columns > renderBlockColumns) {
    		renderBlockColumns = columns;
    	}
    	
        if (blocks == null) {
            blocks = new ArrayList<List<GridBlock>>(renderBlockRows);
        }

        int x = 0;
        int y = 0;

        for (int r = 0; r < renderBlockRows; r++) {
            List<GridBlock> blockRow = new ArrayList<>(renderBlockColumns);

            for (int c = 0; c < renderBlockColumns; c++) {
                GridBlock block = new GridBlock(blockSize, x, y);
                blockRow.add(block);

                x = x + blockSize;
            }

            x = 0;
            y = y + blockSize;
            blocks.add(blockRow);
        }
    }

    private void drawBlocks(Graphics2D g) {
    	if (blocks == null) {
    		return;
    	}
    	
        Rectangle bounds = g.getClipBounds();

        for (List<GridBlock> row : blocks) {
            for (GridBlock block : row) {
                int x = block.getX();
                int y = block.getY();
                int size = block.getSize();

                if (bounds.contains(x, y, size, size)) {
                    g.setColor(block.getColor());
                    g.fillRect(x, y, size, size);
                    g.setColor(strokeColor);
                    g.drawRect(x, y, size, size);
                }
            }
        }
    }
}
