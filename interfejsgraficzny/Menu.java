package interfejsgraficzny;

import sklep.Serializacja;
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
        JMenu algorytm = new JMenu("Wybor algorytmu");

        wyswietl.add(createMenuItem("Wyświetlenie stanu sklepu", new ReakcjaWywStanSkl()));
        wyswietl.add(createMenuItem("Wyświetlenie statystyk tygodniowych", new ReakcjaWysStatTyg()));
        wyswietl.add(createMenuItem("Wyświetlenie statystyk ogolnych", new ReakcjaWysStatOgol()));
        algorytm.add(createMenuItem("Wybor algorytmu koszykowego", new ReakcjaNaWyborAlgorytmu()));

        plik.add(createMenuItem("Zapis stanu do pliku", new ReakcjaZapis()));
        plik.add(createMenuItem("Odczyt stanu z pliku", new ReakcjaOdczyt()));

        tydzien.add(createMenuItem("Przewin o tydzien", new ReakcjaTydzien()));

        add(wyswietl);
        add(plik);
        add(tydzien);
        add(algorytm);
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

        protected void wyswietlNaPanelu(){
            searchResult.setCaretPosition(0);
        }
    }

    public class ReakcjaWysStatOgol extends ReakcjaNaWyswietlanie {

        @Override
        protected void wyswietlNaPanelu() {
            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Wyswietlanie statystyk ogolnych calorocznych"));
            ramka.add(panelTytulu, BorderLayout.NORTH);

            JButton chartButton = new JButton("Show Chart");
            chartButton.addActionListener(e -> {
                WykresyStare.wykresSprzedazyCalorocznej(sklep);
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(chartButton);
            ramka.add(buttonPanel, BorderLayout.SOUTH);

            searchResult.setText(WyswietlanieTymczasowo.wyswietlStatystykeCalorocznaSklepuString(sklep));
            super.wyswietlNaPanelu();
        }
    }

    public class ReakcjaWysStatTyg extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Wyswietlanie statystyk tygodniowych"));
            ramka.add(panelTytulu, BorderLayout.NORTH);

            searchResult.setText(WyswietlanieTymczasowo.wyswietlStatystykeTygodniowaSklepuString(sklep));
            super.wyswietlNaPanelu();
        }
    }

    public class ReakcjaWywStanSkl extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Wyswietl stan sklepu"));
            ramka.add(panelTytulu, BorderLayout.NORTH);

            searchResult.setText(WyswietlanieTymczasowo.wyswietlSklepString(sklep));
            super.wyswietlNaPanelu();
        }
    }

    public class ReakcjaTydzien implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sklep.uplywCzasu();

            ramka.getContentPane().removeAll();
            ramka.setLayout(new BorderLayout());

            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Tydzien " + sklep.getKtoryTydzien()));
            ramka.add(panelTytulu, BorderLayout.CENTER);
            ramka.revalidate();
        }
    }

    public class ReakcjaOdczyt implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ramka.getContentPane().removeAll();
            ramka.setLayout(new BorderLayout());

            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Wczytano sklep z pliku"));
            ramka.add(panelTytulu, BorderLayout.CENTER);
            Serializacja s = new Serializacja();
            sklep = s.wczytajZPlikuSklep("Sklep.ser");
            ramka.revalidate();
        }
    }

    public class ReakcjaZapis implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sklep.zapiszDoPliku();

            ramka.getContentPane().removeAll();
            ramka.setLayout(new BorderLayout());

            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Plik zapisano do pliku"));

            ramka.add(panelTytulu, BorderLayout.CENTER);
            ramka.revalidate();
        }
    }

    public class ReakcjaNaWyborAlgorytmu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ramka.getContentPane().removeAll();
            ramka.setLayout(new BorderLayout());
            String[] metody = {"Metoda 1", "Metoda 2", "Metoda 3"};

            JPanel panel = new JPanel();
            JComboBox comboBox = new JComboBox<>(metody);
            JLabel selectedMethodLabel = new JLabel("Wybrana metoda: ");

            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String wybranaMetoda = (String) comboBox.getSelectedItem();
                    selectedMethodLabel.setText("Wybrana metoda: " + wybranaMetoda);
                    sklep.wybierzRelokacjePopytowa(wybranaMetoda);
                }
            });

            panel.add(comboBox);
            panel.add(selectedMethodLabel);
            ramka.add(panel);
            ramka.revalidate();
        }
    }


    public class ReakcjaWystawStanPromocji extends ReakcjaNaWyswietlanie {
        @Override
        protected void wyswietlNaPanelu() {
            JPanel panelTytulu = new JPanel();
            panelTytulu.add(new JLabel("Wyswietl promocje sklepu"));
            ramka.add(panelTytulu, BorderLayout.NORTH);

            searchResult.setText(WyswietlanieTymczasowo.wyswietlProduktyNaPromocji(sklep));
            super.wyswietlNaPanelu();
        }
    }
}