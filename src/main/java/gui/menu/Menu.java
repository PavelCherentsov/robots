package gui.menu;

import gui.CloseWindow;
import gui.MainApplicationFrame;
import gui.RobotsProgram;
import log.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Menu {
    public ArrayList<MenuBar> menu = new ArrayList<>();

    public Menu(MainApplicationFrame main) {
        MenuBar menuBar;

        menuBar = new MenuBar("Режим отображения",
                KeyEvent.VK_V,
                "Управление режимом отображения приложения");

        menuBar.items.add(new MenuBarItem("Системная схема", (event) -> {
            main.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            main.invalidate();
        }));
        menuBar.items.add(new MenuBarItem("Универсальная схема", (event) -> {
            main.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            main.invalidate();
        }));

        menu.add(menuBar);

        menuBar = new MenuBar("Тесты",
                KeyEvent.VK_T,
                "Тестовые команды");

        menuBar.items.add(new MenuBarItem("Сообщение в лог", (event) -> {
            Logger.debug("Новая строка");
        }));

        menu.add(menuBar);

        menuBar = new MenuBar("Выход",
                KeyEvent.VK_END,
                "Выход из приложения");

        menuBar.items.add(new MenuBarItem("Выйти", (event) -> {
            CloseWindow.openCloseWindow(main, (a) -> {
                RobotsProgram.save();
                System.exit(a);
            });
        }));

        menu.add(menuBar);
    }


}
