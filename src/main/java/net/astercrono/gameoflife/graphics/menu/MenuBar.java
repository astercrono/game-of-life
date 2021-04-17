package net.astercrono.gameoflife.graphics.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import net.astercrono.gameoflife.graphics.ComponentWrapper;

public class MenuBar implements ComponentWrapper<JMenuBar> {
	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem startItem = new JMenuItem("Start");
	private JMenuItem pauseItem = new JMenuItem("Pause");
	private MenuHandler actionHandler;

	public MenuBar(MenuHandler handler) {
		this.actionHandler = handler;

		JMenu[] menus = buildMenus();
		for (JMenu m : menus) {
			menuBar.add(m);
		}
	}

	@Override
	public JMenuBar getComponent() {
		return menuBar;
	}

	public void setMenuStart() {
		startItem.setText("Stop");
		pauseItem.setText("Pause");
		pauseItem.setEnabled(true);
		menuBar.repaint();
	}

	private JMenu[] buildMenus() {
		return new JMenu[] { buildFileMenu(), buildActionMenu() };
	}

	private JMenu buildFileMenu() {
		final JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new MenuActionListener(MenuActionType.SEED_OPEN, actionHandler));

		final JMenuItem randomItem = new JMenuItem("Random");
		randomItem.addActionListener(new MenuActionListener(MenuActionType.SEED_RANDOM, actionHandler));

		final JMenu fileMenu = new JMenu("Seed");
		fileMenu.add(openItem);
		fileMenu.add(randomItem);

		return fileMenu;
	}

	private JMenu buildActionMenu() {
		startItem.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String currentText = startItem.getText();
				if (currentText.equals("Start")) {
					startItem.setText("Stop");
					pauseItem.setEnabled(true);
					menuBar.repaint();
					actionHandler.handleMenuAction(MenuActionType.START);
				} else if (currentText.equals("Stop")) {
					startItem.setText("Start");
					pauseItem.setEnabled(false);
					menuBar.repaint();
					actionHandler.handleMenuAction(MenuActionType.STOP);
				}
			}
		});

		pauseItem.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String currentText = pauseItem.getText();
				if (currentText.equals("Pause")) {
					pauseItem.setText("Play");
					actionHandler.handleMenuAction(MenuActionType.TOGGLE_PAUSE);
				} else if (currentText.equals("Play")) {
					pauseItem.setText("Pause");
					actionHandler.handleMenuAction(MenuActionType.TOGGLE_PAUSE);
				}
			}
		});
		pauseItem.setEnabled(false);

		final JMenu actionMenu = new JMenu("Action");
		actionMenu.add(startItem);
		actionMenu.add(pauseItem);

		return actionMenu;
	}
}
