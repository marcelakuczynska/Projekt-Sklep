package interfejsgraficzny2.gui2;

import sklep.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelMenu extends JPanel {
    private JButton przesunTydzien;
    private JButton zapis;
    private JButton odczyt;
    private JButton statystyki;
    private JButton algorytm;
    private String[] metody;
    private JButton promocje;

    private int spacing = 1;

    private Sklep sklep;
    private Regal regal;


    public PanelMenu(Frame frame, Sklep sklep) {
        this.sklep = sklep;
        this.regal = regal;

        setLayout(new GridLayout(0, 1));
        setOpaque(false);


        przesunTydzien = new JButton("Przesun tydzien");
        zapis = new JButton("Zapis");
        odczyt = new JButton("Odczyt");
        statystyki = new JButton("Statystyki ogolne");
        algorytm = new JButton("Algorytm koszykowy");
        promocje = new JButton("Promocje");

        add(Box.createVerticalStrut(spacing));
        add(Box.createVerticalStrut(spacing));
        add(przesunTydzien);
        add(zapis);
        add(odczyt);
        add(statystyki);
        add(algorytm);
        add(promocje);


        zapis.addActionListener(e -> {
            //serializacja
            this.sklep.zapiszDoPliku();
            JOptionPane.showMessageDialog(frame, "Zapisano stan sklepu", "Serializacja", JOptionPane.INFORMATION_MESSAGE);
        });

        odczyt.addActionListener(e -> {
            //serializacja
            Serializacja s = new Serializacja();
            this.sklep = s.wczytajZPlikuSklep("Sklep.ser");

            new Frame(this.sklep);

            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof JFrame) {
                ((JFrame) window).dispose();
            }

            JOptionPane.showMessageDialog(frame, "Odczytano stan sklepu", "Deserializacja", JOptionPane.INFORMATION_MESSAGE);
        });

        statystyki.addActionListener(e ->{
            //statystyki ogolne

            SwingUtilities.invokeLater(() -> {
                Wykresy.pokazRamkeSprzedazyCalorocznej(sklep);
            });
        });

        algorytm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                metody = new String[]{"Metoda 1", "Metoda 2", "Metoda 3"};
                JComboBox<String> optionComboBox = new JComboBox<>(metody);
                JLabel opis1 = new JLabel("Metoda 1: srodek, gora, dol");
                JLabel opis2 = new JLabel("Metoda 2: srodek, dol, gora");
                JLabel opis3 = new JLabel("Metoda 3: gora, dol, srodek");
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(Box.createVerticalStrut(10));
                panel.add(opis1);
                panel.add(opis2);
                panel.add(opis3);
                panel.add(Box.createVerticalStrut(10));
                panel.add(optionComboBox);
                panel.add(Box.createVerticalStrut(10));

                int result = JOptionPane.showOptionDialog(frame,
                        panel,
                        "Wybierz algorytm koszykowy",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        null,
                        null);

                if (result == JOptionPane.OK_OPTION) {
                    String selectedOption = (String) optionComboBox.getSelectedItem();
                    sklep.wybierzRelokacjePopytowa(selectedOption);
                    JOptionPane.showMessageDialog(frame,
                            "Wybrano: " + selectedOption,
                            "Algorytm koszykowy", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        promocje.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

//                WyswietlanieTymczasowo w = new WyswietlanieTymczasowo();
//                w.wyswietlProduktyNaPromocji(sklep);

                JTable table = new JTable(sklep.getTabelaZDanymiPromocyjnymi());
                ArrayList<ArrayList<Produkt>> promocja = sklep.getProduktyNaPromocji();

                System.out.println(table);
                if (promocja.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nie ma danych", "Sklep dane", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.add(new JScrollPane(table), BorderLayout.CENTER);
                    JOptionPane.showMessageDialog(null, panel, "Sklep dane", JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });

    }



    public JButton getPrzesunTydzien() {
        return przesunTydzien;
    }
}