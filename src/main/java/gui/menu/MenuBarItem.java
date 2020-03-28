package gui.menu;

import java.awt.event.ActionListener;

public class MenuBarItem {
    public final String name;
    public final ActionListener action;

    public MenuBarItem(String name, ActionListener action) {
        this.name = name;
        this.action = action;
    }
}
