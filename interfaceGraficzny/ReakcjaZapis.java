package interfaceGraficzny;

import sklep.Sklep;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReakcjaZapis implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Ramka ramka = new Ramka();
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        panel.add(label);
        ramka.add(panel);
        Sklep sklep = new Sklep();
        label.setText("Zapisano do pliku");
        sklep.zapiszDoPliku();
    }
}
