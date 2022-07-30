package net.astercrono.gameoflife.graphics;

import javax.swing.JComponent;

public interface ComponentWrapper<T extends JComponent> {
    T getComponent();
}
