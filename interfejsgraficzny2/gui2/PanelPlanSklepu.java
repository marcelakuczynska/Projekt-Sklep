package interfejsgraficzny2.gui2;

import sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelPlanSklepu extends JPanel {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private Sklep sklep;

    public PanelPlanSklepu(Sklep sklep) {
        this.sklep = sklep;

        setLayout(new GridLayout(0, 1));
        setBackground(new Color(255, 255, 255, 190));

        JLabel selectShelfLabel = new JLabel("WYBIERZ REGAL", SwingConstants.CENTER);
        selectShelfLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        button1 = createButton("regalyinne.jpg");
        button2 = createButton("regalyinne.jpg");
        button3 = createButton("regalyinne.jpg");
        button4 = createButton("regalyinne.jpg");

        button1.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPlanSklepu.this);

			        Regal1 regal1 = new Regal1(sklep);

			        topFrame.dispose();

			        regal1.setVisible(true);
			}
        });
        
        
        button2.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPlanSklepu.this);

			        Regal2 regal2 = new Regal2(sklep);

			        topFrame.dispose();

			        regal2.setVisible(true);
			}
        });
        
        button3.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPlanSklepu.this);

			        Regal3 regal3 = new Regal3(sklep);

			        topFrame.dispose();

			        regal3.setVisible(true);
			}
        });
        
        button4.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPlanSklepu.this);

			        Regal4 regal4 = new Regal4(sklep);

			        topFrame.dispose();

			        regal4.setVisible(true);
			}
        });
        
        add(selectShelfLabel);
        addWithEmptyBorder(button1);
        addWithEmptyBorder(button2);
        addWithEmptyBorder(button3);
        addWithEmptyBorder(button4);
    }

    private JButton createButton(String imagePath) {
        JButton button = new JButton();



        ImageIcon originalIcon = loadImageIcon(imagePath);
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
    private ImageIcon loadImageIcon(String imageName) {
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL imageURL = classLoader.getResource("interfejsgraficzny2/obrazki/" + imageName);
        return new ImageIcon(imageURL);
    }
}