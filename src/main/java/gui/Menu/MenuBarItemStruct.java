package gui.Menu;

import java.awt.event.ActionListener;

public class MenuBarItemStruct {
    public String name;
    public ActionListener action;

    public MenuBarItemStruct(String name, ActionListener action)
    {
        this.name = name;
        this.action = action;
    }
}
