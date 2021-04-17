package net.astercrono.gameoflife.graphics.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MenuActionListener extends AbstractAction {
    private static final long serialVersionUID = -4935509206340525998L;
    private MenuActionType actionType;
    private MenuHandler actionHandler;

    public MenuActionListener(MenuActionType actionType, MenuHandler handler) {
        this.actionType = actionType;
        this.actionHandler = handler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (actionHandler != null) {
            actionHandler.handleMenuAction(actionType);
        }
    }
}
