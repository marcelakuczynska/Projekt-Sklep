package interfejsgraficzny;

import sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ramka extends JFrame {

    private Sklep sklep;

    public Ramka(){;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        sklep = new Sklep();

        setSize(1000, 650);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);


        Menu menu = new Menu(this, sklep);
        setJMenuBar(menu);
        setVisible(true);

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
}
