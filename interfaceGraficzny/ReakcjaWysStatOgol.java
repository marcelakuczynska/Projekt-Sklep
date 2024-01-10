package interfaceGraficzny;

import sklep.Dostawa;
import sklep.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReakcjaWysStatOgol  implements ActionListener {

    JTextArea obszarTekstowy = new JTextArea();
    JLabel label = new JLabel();


    @Override
    public void actionPerformed(ActionEvent e) {

        Ramka ramka = new Ramka();

        ramka.getContentPane().removeAll();
        ramka.setLayout(new BorderLayout());

        //panel wyswietlanie
        JPanel wyswietlanie = new JPanel(new BorderLayout());

        obszarTekstowy.setEditable(false);

        wyswietlanie.add(new JScrollPane(obszarTekstowy));
        wyswietlNaPanelu();



        //dodanie paneli do ramki
        ramka.add(wyswietlanie, BorderLayout.CENTER);

        ramka.revalidate();
    }

    protected void wyswietlNaPanelu() {
        Sklep sklep = new Sklep();
        obszarTekstowy.setText("Miau");
        label.setText("Miau");
    }
}
