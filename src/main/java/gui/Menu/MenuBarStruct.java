package gui.Menu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuBarStruct {

    public String name;
    public int key;
    public String info;
    public  ArrayList<MenuBarItemStruct> items = new ArrayList<>();

    public MenuBarStruct(String name, int key, String info)
    {
        this.name = name;
        this.key = key;
        this.info = info;
    }
}
