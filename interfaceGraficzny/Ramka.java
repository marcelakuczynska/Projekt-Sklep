package interfaceGraficzny;

import sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ramka extends JFrame {

    private Sklep sklep;

    public Ramka(){;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sklep = new Sklep();

        setSize(600, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);


        Menu menu = new Menu(this, sklep);
        setJMenuBar(menu);
        setVisible(true);


        addWindowListener(new ConfirmCloseListener());
        setVisible(true);
    }


    private void zamknijRamke() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Czy chcesz zapisac zmiany przed wyjsciem?",
                "Zapisz zmiany",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            //Sklep.zapiszDoPliku();
            JOptionPane.showMessageDialog(
                    this,
                    "Zapisano zmiany!",
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
}
