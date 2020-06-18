package gui;

import com.google.gson.JsonSerializer;
import gui.game.Robot;
import gui.serialization.RobotInfoWindowSerializer;
import gui.serialization.SimpleWindowSerializer;
import gui.serialization.WindowSerializable;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class RobotInfoWindow extends JInternalFrame implements WindowSerializable, RobotChangeListener {
    public JsonSerializer getSerializer() {
        return new RobotInfoWindowSerializer();
    }

    private Robot robot = Robot.getRobot();

    public RobotInfoWindow() {
        super("Робот (информация)", true, true, true, true);
        robot.registerListener(this);
        JPanel panel = new JPanel(new GridLayout(3,2));
        getContentPane().add(panel);
        setMinimumSize(new Dimension(200,200));
        setResizable(false);
        setSize(200,100);
        onLogChanged();
    }

    @Override
    public void onLogChanged() {
        JPanel panel = new JPanel(new GridLayout(3,2));
        getContentPane().removeAll();
        getContentPane().add(panel);
        for (String key:robot.getInfo().keySet()) {
            panel.add(new JTextField(key));
            panel.add(new JTextField(""+robot.getInfo().get(key)));
        }
        pack();
    }


    @Override
    public void dispose() {
        robot.unregisterListener(this);
        super.dispose();
    }
}
