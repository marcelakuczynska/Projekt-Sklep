package interfaceGraficzny;

import sklep.Sklep;
import sklep.WyswietlanieTymczasowo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {
    private JFrame ramka;
    private Sklep sklep;

    public Menu(JFrame ramka, Sklep sklep) {
        this.sklep = sklep;
        this.ramka = ramka;

        JMenu wyswietl = new JMenu("Wyswietl");
        JMenu plik = new JMenu("Plik");
        JMenu tydzien = new JMenu("Symulacja czasu");

        wyswietl.add(createMenuItem("Wyświetlenie stanu sklepu", new ReakcjaWywStanSkl()));
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

    abstract class ReakcjaNaWyswietlanie implements ActionListener {
        protected JTextArea searchResult;

        @Override
        public void actionPerformed(ActionEvent e) {
            ramka.getContentPane().removeAll();
            ramka.setLayout(new BorderLayout());

            // Panel wyswietlanie
            JPanel wyswietlanie = new JPanel(new BorderLayout());

            searchResult = new JTextArea();
            searchResult.setEditable(false);

            wyswietlanie.add(new JScrollPane(searchResult));
            wyswietlNaPanelu();

            // Dodanie panelu do ramki
            ramka.add(wyswietlanie, BorderLayout.CENTER);

            ramka.revalidate();
        }

        protected void wyswietlNaPanelu() {

        }
    }

    public class ReakcjaWysStatOgol extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Wyswietlanie statystyk ogolnych calorocznych"));
            ramka.add(panelTytulu, BorderLayout.NORTH);

            searchResult.setText(WyswietlanieTymczasowo.wyswietlStatystykeCalorocznaSklepuString(sklep));
        }
    }

    public class ReakcjaWysStatTyg extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Wyswietlanie statystyk tygodniowych"));
            ramka.add(panelTytulu, BorderLayout.NORTH);

            searchResult.setText(WyswietlanieTymczasowo.wyswietlStatystykeTygodniowaSklepuString(sklep));
        }
    }

    public class ReakcjaWywStanSkl extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Wyswietl stan sklepu"));
            ramka.add(panelTytulu, BorderLayout.NORTH);

            searchResult.setText(WyswietlanieTymczasowo.wyswietlSklepString(sklep));
        }
    }

    public class ReakcjaTydzien extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            sklep.uplywCzasu();
        }
    }

    public class ReakcjaOdczyt extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            // Implementacja dla odczytu
        }
    }

    public class ReakcjaZapis extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            // Implementacja dla zapisu
        }
    }
}