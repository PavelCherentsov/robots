package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import gui.menu.MenuBarItem;
import gui.menu.MenuBar;
import gui.menu.Menu;
import gui.serialization.WindowSerializable;
import log.Logger;


public class MainApplicationFrame extends JFrame {
    public final JDesktopPane desktopPane = new JDesktopPane();
    private final int inset = 50;
    public HashMap<String, WindowSerializable> windows_s = new HashMap<>();


    public MainApplicationFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        setContentPane(desktopPane);

        CloseWindow.addClose(this);

        LogWindow logWindow = createLogWindow();
        addWindow("logWindow", logWindow);


        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(600, 400);
        addWindow("gameWindow", gameWindow);

        RobotInfoWindow robotInfoWindow = new RobotInfoWindow();
        robotInfoWindow.setSize(200,200);
        addWindow("robotInfoWindow", robotInfoWindow);

        //RobotsProgram.load(this);

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10, 10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void addWindow(String name, JInternalFrame frame) {
        windows_s.put(name, (WindowSerializable) frame);
        frame.setName(name);
        desktopPane.add(frame);
        frame.setVisible(true);
        CloseWindow.addClose(frame);

    }

    private ArrayList<MenuBar> menuStruct = new Menu(this).menu;

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu;
        for (MenuBar e : menuStruct) {
            menu = createMenu(e.name, e.key, e.info);
            for (MenuBarItem i : e.items) {
                menu.add(createMenuItem(i.name, i.action));
            }
            menuBar.add(menu);
        }
        return menuBar;
    }

    private JMenu createMenu(String name, int key, String info) {
        JMenu menu = new JMenu(name);
        menu.setMnemonic(key);
        menu.getAccessibleContext().setAccessibleDescription(info);
        return menu;
    }


    private JMenuItem createMenuItem(String name, ActionListener action) {
        JMenuItem item = new JMenuItem(name, KeyEvent.VK_S);
        item.addActionListener(action);
        return item;
    }

    public void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }

}
