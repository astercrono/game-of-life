package net.astercrono.gameoflife;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;

import net.astercrono.colorgrid.ColorGrid;
import net.astercrono.colorgrid.ColorGridDimensions;

/**
 * Hello world!
 *
 */
public class App {
    private static final int SCALE = 7;

    public static void main(String[] args) {
        final List<List<Boolean>> seed = new SeedReader().read("seed.txt");
        final Life life = new Life(seed);

        final int width = life.getColCount() * SCALE;
        final int height = life.getRowCount() * SCALE;

        final ColorGridDimensions dimensions = new ColorGridDimensions();
        dimensions.setRows(life.getRowCount());
        dimensions.setColumns(life.getColCount());
        dimensions.setWidth(width);
        dimensions.setHeight(height);

        final ColorGrid grid = new ColorGrid(dimensions);
        final LifeRenderer renderer = new LifeRenderer(grid);

        final JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(width, height + 20));
        window.add(grid);
        window.setVisible(true);
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    life.setActive(false);
                }
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    renderer.togglePause();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        renderer.render(life);
        window.dispose();
    }
}
