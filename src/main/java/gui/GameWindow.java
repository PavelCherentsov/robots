package gui;

import com.google.gson.JsonSerializer;
import gui.game.Robot;
import gui.serialization.GameWindowSerializer;
import gui.serialization.WindowSerializable;

import java.awt.*;

import javax.swing.*;

public class GameWindow extends JInternalFrame implements WindowSerializable {
    private final GameVisualizer m_visualizer;
    public JsonSerializer getSerializer() {
        return new GameWindowSerializer();
    }

    public GameWindow() {
        super("Игровое поле", true, true, true, true);
        m_visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    public Robot getRobot(){
        return m_visualizer.getRobot();
    }

    public Point getTargetPosition(){
        return m_visualizer.getTargetPosition();
    }

    public void setTargetPosition(int x, int y){
        m_visualizer.setTargetPosition(new Point(x,y));

    }

    public void setRobot(double x, double y, double d){
        m_visualizer.setRobot(x,y,d);
    }
}
