package interfejsgraficzny2.gui2;

import javax.swing.*;
import java.awt.*;


public class PanelPlanSklepu extends JPanel {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public PanelPlanSklepu() {
        setLayout(new GridLayout(0, 1));
        setBackground(new Color(255, 255, 255, 190));

        JLabel selectShelfLabel = new JLabel("WYBIERZ REGAL", SwingConstants.CENTER);
        selectShelfLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        button1 = createButton("Projekt-Sklep/interfejsgraficzny2/obrazki/regalyinne.jpg");
        button2 = createButton("Projekt-Sklep/interfejsgraficzny2/obrazki/regalyinne.jpg");;
        button3 = createButton("Projekt-Sklep/interfejsgraficzny2/obrazki/regalyinne.jpg");;
        button4 = createButton("Projekt-Sklep/interfejsgraficzny2/obrazki/regalyinne.jpg");;

        add(selectShelfLabel);
        addWithEmptyBorder(button1);
        addWithEmptyBorder(button2);
        addWithEmptyBorder(button3);
        addWithEmptyBorder(button4);
    }

    private JButton createButton(String imagePath) {
        JButton button = new JButton();

        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();

        int buttonWidth = 300;
        int buttonHeight = 100;

        Image scaledImage = originalImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        button.setSize(new Dimension(buttonWidth, buttonHeight));
        button.setIcon(scaledIcon);

        return button;
    }

    private void addWithEmptyBorder(JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(component, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 70, 5, 70));
        add(panel);
    }
}