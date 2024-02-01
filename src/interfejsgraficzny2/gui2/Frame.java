package interfejsgraficzny2.gui2;

import sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Frame extends JFrame {

    private Sklep sklep;
    private BufferedImage myPicture;
    private JPanel backgroundPanel;

    public Frame(Sklep sklep) {
        this.sklep = sklep;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setSize(1000, 650);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        backgroundPanel = new BackgroundPanel("https://github.com/poradajakub/Projekt-Sklep/blob/23111cd418c1cef6d2094122f147f781eeba78c6/interfejsgraficzny2/obrazki/regaltlo.jpg");
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight()-35);
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel);


        PanelGlowny panelGlowny= new PanelGlowny(this,sklep);

        UIManager.put("OptionPane.yesButtonText", "Tak");
        UIManager.put("OptionPane.noButtonText", "Nie");
        UIManager.put("OptionPane.cancelButtonText", "Anuluj");

        addWindowListener(new ConfirmCloseListener());
        setVisible(true);
    }


    private void zamknijRamke() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Czy chcesz zapisac stan sklepu przed wyjsciem?",
                "Zapisz zmiany",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            sklep.zapiszDoPliku();
            JOptionPane.showMessageDialog(
                    this,
                    "Zapisano stan sklepu!",
                    "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (result == JOptionPane.NO_OPTION) {
            dispose();
        }
    }

    private class ConfirmCloseListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            zamknijRamke();
        }
    }

    public void setBackgroundPanel(JPanel backgroundPanel) {
        this.backgroundPanel = backgroundPanel;
    }

    public void setMyPicture(BufferedImage myPicture) {
        this.myPicture = myPicture;
    }

    public JPanel getBackgroundPanel() {
        return backgroundPanel;
    }

public class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {

            ClassLoader classLoader = getClass().getClassLoader();
            java.net.URL imageURL = classLoader.getResource("interfejsgraficzny2/obrazki/regaltlo.jpg");
            backgroundImage = new ImageIcon(imageURL).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

}