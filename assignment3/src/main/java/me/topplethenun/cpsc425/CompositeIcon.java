package me.topplethenun.cpsc425;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CompositeIcon implements Icon {

    private final Map<Icon, Point> iconMap;

    public CompositeIcon() {
        this.iconMap = new HashMap<>();
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        System.out.println(x + " : " + y);
        System.out.println(getIconWidth() + " : " + getIconHeight());
        for (Map.Entry<Icon, Point> entry : iconMap.entrySet()) {
            entry.getKey().paintIcon(c, g, x + entry.getValue().x, y + entry.getValue().y);
        }
    }

    @Override
    public int getIconWidth() {
        int maxX = Integer.MIN_VALUE;
        for (Map.Entry<Icon, Point> entry : iconMap.entrySet()) {
            int px = entry.getValue().x + entry.getKey().getIconWidth();
            if (px > maxX) {
                maxX = px;
            }
        }
        return maxX;
    }

    @Override
    public int getIconHeight() {
        int maxY = Integer.MIN_VALUE;
        for (Map.Entry<Icon, Point> entry : iconMap.entrySet()) {
            int py = entry.getValue().y + entry.getKey().getIconHeight();
            if (py > maxY) {
                maxY = py;
            }
        }
        return maxY;
    }

    public void addIcon(Icon icon, int x, int y) {
        iconMap.put(icon, new Point(x, y));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Container panel = frame.getContentPane();

        panel.setLayout(new BorderLayout());

        CompositeIcon icon = new CompositeIcon();

        try {
            icon.addIcon(new ImageIcon(new URL("http://th02.deviantart.net/fs71/150/f/2013/103/2/7/java_dock_icon_by_excurse-d61mi0t.png")), 10, 10);

            icon.addIcon(new ImageIcon(new URL("http://fc03.deviantart.net/fs20/f/2007/274/9/8/3D_Java_icon_by_BrightKnight.png")), 200, 200);

            icon.addIcon(new ImageIcon(new URL("http://www.bravegnu.org/blog/icons/java.png")), 5, 370);

        } catch (MalformedURLException e) {

            System.err.println("Apparently, somebody cannot type a URL");

        }

        panel.add(new JLabel(icon));

        frame.pack();

        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
