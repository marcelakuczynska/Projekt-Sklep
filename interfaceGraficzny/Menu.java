package interfaceGraficzny;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {

    public Menu(){

    JMenu wyswietl = new JMenu("Wyswietl");
    JMenu plik = new JMenu("Plik");
    JMenu tydzien = new JMenu("Symulacja czasu");


        wyswietl.add(createMenuItem("Wyświetlenie stanu sklepu", new ReakcjaWywStanSkl()));
        wyswietl.add(createMenuItem("Wyświetlenie dostawy", new ReakcjaWysDos()));
        wyswietl.add(createMenuItem("Wyświetlenie statystyk tygodniowych", new ReakcjaWysStatTyg()));
        wyswietl.add(createMenuItem("Wyświetlenie statystyk ogolnych", new ReakcjaWysStatOgol()));

        plik.add(createMenuItem("Zapis stanu do pliku", new ReakcjaZapis()));
        plik.add(createMenuItem("Odczyt stanu z pliku", new ReakcjaOdczyt()));

        tydzien.add(createMenuItem("Przewin o tydzien", new ReakcjaTydzien()));


    add(wyswietl);
    add(plik);
    add(tydzien);


}

    private JMenuItem createMenuItem(String label, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(listener);
        return menuItem;
    }


}