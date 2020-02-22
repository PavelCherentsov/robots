package gui.Menu;

import gui.MainApplicationFrame;
import log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuStruct {
    public ArrayList<MenuBarStruct> menu = new ArrayList<>();

    public MenuStruct(MainApplicationFrame main)
    {
        MenuBarStruct menuBar;

        menuBar = new MenuBarStruct("Режим отображения",
                                                  KeyEvent.VK_V,
                                                  "Управление режимом отображения приложения");

        menuBar.items.add(new MenuBarItemStruct("Системная схема", (event) -> {
            main.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            main.invalidate();
        }));
        menuBar.items.add(new MenuBarItemStruct("Универсальная схема", (event) -> {
            main.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            main.invalidate();
        }));

        menu.add(menuBar);

        menuBar = new MenuBarStruct("Тесты",
                KeyEvent.VK_T,
                "Тестовые команды");

        menuBar.items.add(new MenuBarItemStruct("Сообщение в лог", (event) -> {
            Logger.debug("Новая строка");
        }));

        menu.add(menuBar);
    }


}
