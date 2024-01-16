package interfejsgraficzny2.gui2;

import javax.swing.*;
import java.awt.*;

public class PanelGlowny {
    private JPanel panelTytulowy;
    private PanelPlanSklepu panelPlanSklepu;
    private PanelMenu panelMenu;
    private JPanel panelWspolny;
    private JProgressBar progressBar;
    private Frame frame;


    public PanelGlowny(Frame frame) {
        frame.setLayout(new BorderLayout());
        this.frame = frame;

        panelTytulowy = new JPanel();
        panelTytulowy.setLayout(new BorderLayout());

        panelPlanSklepu = new PanelPlanSklepu();
        panelMenu = new PanelMenu(frame);

        //panel tytulowy
        JPanel panelProgres = new JPanel();
        panelProgres.setOpaque(false);
        progressBar = new JProgressBar(1, 365);
        progressBar.setPreferredSize(new Dimension(200, 20));
        progressBar.setStringPainted(true);
        progressBar.setString("1/365");
        panelProgres.add(progressBar);

        JLabel label = new JLabel("SUPERMARKET", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 30));

        panelTytulowy.add(Box.createVerticalStrut(20));
        panelTytulowy.add(label, BorderLayout.CENTER);
        panelTytulowy.add(panelProgres, BorderLayout.PAGE_END);
        panelTytulowy.setBackground(new Color(255, 255, 255, 179));

        //panel wspolny menu i planu sklepu
        panelWspolny = new JPanel();
        panelWspolny.setLayout(new BoxLayout(panelWspolny, BoxLayout.X_AXIS));
        panelWspolny.add(Box.createHorizontalStrut(70));
        panelWspolny.add(panelMenu);
        panelWspolny.add(Box.createHorizontalStrut(70));
        panelWspolny.add(panelPlanSklepu);
        panelWspolny.add(Box.createHorizontalStrut(70));
        panelWspolny.setOpaque(false);

        panelTytulowy.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelWspolny.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));

        frame.getBackgroundPanel().add(panelTytulowy, BorderLayout.NORTH);
        frame.getBackgroundPanel().add(panelWspolny, BorderLayout.CENTER);
        frame.getBackgroundPanel().add(new JSeparator(JSeparator.VERTICAL), BorderLayout.SOUTH);


        panelMenu.getButton1().addActionListener(e -> {

            int newProgressValue = progressBar.getValue() + 1;
            if (newProgressValue <= progressBar.getMaximum()) {
                progressBar.setValue(newProgressValue);
                progressBar.setString(Integer.toString(newProgressValue)+ "/365");

            }
        });

    }
}