package gui;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;
import java.util.function.Function;


import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class CloseWindow {
    public static void addClose(JInternalFrame w) {
        w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        w.addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent event) {
                CloseWindow.openCloseWindow(event.getInternalFrame(), (a) -> {
                    event.getInternalFrame().setVisible(false);
                    w.dispose();
                });
            }
        });
    }

    public static void addClose(JFrame w){
        w.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        w.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                CloseWindow.openCloseWindow(event.getWindow(), (a) -> {
                    RobotsProgram.save();
                    System.exit(a);
                });
            }
        });

    }

    public static void openCloseWindow(Component window, Consumer<Integer> func) {
        Object[] options = {"Да", "Нет!"};
        int n = JOptionPane
                .showOptionDialog(window, "Закрыть окно?",
                        "Подтверждение", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
        if (n == 0) {
            func.accept(0);
        }
    }
}
