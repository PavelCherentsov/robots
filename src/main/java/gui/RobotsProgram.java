package gui;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import gui.serialization.WindowSerializable;

import java.awt.Frame;
import java.beans.PropertyVetoException;
import java.io.*;
import java.util.Arrays;

import javax.swing.*;

public class RobotsProgram {
    public static MainApplicationFrame frame;
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//          UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//          UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            frame = null;
            frame = new MainApplicationFrame();
            frame.pack();
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }



    public static void save() {
        JsonObject jsonObject = new JsonObject();
        for (String e: frame.windows_s.keySet()) {
            jsonObject.add(e, new JsonParser().parse(new GsonBuilder()
                    .registerTypeAdapter(frame.windows_s.get(e).getClass(),frame.windows_s.get(e).getSerializer())
                    .setPrettyPrinting()
                    .create()
                    .toJson(frame.windows_s.get(e))));

        }
        try(FileWriter writer = new FileWriter("save.txt", false))
        {
            writer.write(jsonObject.toString());
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void load(MainApplicationFrame mainFrame) {
        File file = new File("save.txt");
        if (!file.exists())
            return;
        try (FileReader reader = new FileReader(file)) {
            int c;
            String s_json = "";
            while ((c = reader.read()) != -1) {

                s_json += (char) c;
            }
            JsonObject json = new JsonParser().parse(s_json).getAsJsonObject();
            for (JInternalFrame frame : mainFrame.desktopPane.getAllFrames()) {
                String name = frame.getName();
                System.out.println( mainFrame.windows_s.get(name).getClass());
                WindowSerializable w = new GsonBuilder()
                        .registerTypeAdapter(mainFrame.windows_s.get(name).getClass(), mainFrame.windows_s.get(name).getSerializer())
                        .create()
                        .fromJson(json.get(name).getAsJsonObject(), mainFrame.windows_s.get(name).getClass());
                mainFrame.desktopPane.remove(frame);
                mainFrame.addWindow(name, (JInternalFrame) w);
            }

        } catch (IOException  ex) {
            System.out.println(Arrays.toString(ex.getStackTrace()));
        }
    }
}
