package interfaceGraficzny;

import sklep.Dostawa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReakcjaWysDos implements ActionListener {

    JTextArea searchResult;

    @Override
    public void actionPerformed(ActionEvent e) {

        Ramka ramka = new Ramka();

        ramka.getContentPane().removeAll();
        ramka.setLayout(new BorderLayout());

        //panel wyswietlanie
        JPanel wyswietlanie = new JPanel(new BorderLayout());

        searchResult = new JTextArea();
        searchResult.setEditable(false);

        wyswietlanie.add(new JScrollPane(searchResult));
        wyswietlNaPanelu();

        //popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        searchResult.add(popupMenu);

        //dodanie paneli do ramki
        ramka.add(wyswietlanie, BorderLayout.CENTER);

        ramka.revalidate();
    }

    protected void wyswietlNaPanelu() {
        Dostawa dostawa = new Dostawa();
        searchResult.setCaretPosition(0);
        searchResult.setText(dostawa.toString());
    }
}

