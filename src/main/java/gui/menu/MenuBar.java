package gui.menu;

import java.util.ArrayList;

public class MenuBar {

    public final String name;
    public final int key;
    public final String info;
    public ArrayList<MenuBarItem> items = new ArrayList<>();

    public MenuBar(String name, int key, String info)
    {
        this.name = name;
        this.key = key;
        this.info = info;
    }
}
