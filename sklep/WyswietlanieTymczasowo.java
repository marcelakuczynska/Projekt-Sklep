package sklep;

import org.jfree.base.modules.SubSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WyswietlanieTymczasowo {

    // to tymczasowo zeby sprawdzic czy dziala w interfejsie graficznym, pozniej to jakos uporzadkuje
    public static String wyswietlStatystykeCalorocznaSklepuString(Sklep sklep) {
        StringBuilder result = new StringBuilder("\n");

        for (int i = 0; i < sklep.getRegalyWSklepie().length; i++) {
            result.append("Numer regalu: ").append(i).append("\n");
            result.append(sklep.getRegalyWSklepie()[i].getStatystykaCaloroczna().wyswietlString()).append("\n\n");
        }

        return result.toString();
    }


    public static String wyswietlStatystykeTygodniowaSklepuString(Sklep sklep) {
        StringBuilder result = new StringBuilder("\n");

        for (int i = 0; i < sklep.getRegalyWSklepie().length; i++) {
            if (sklep.getRegalyWSklepie()[i].getStatystykaTygodniowa() != null) {
                result.append("Numer regalu: ").append(i).append("\n");
                result.append(sklep.getRegalyWSklepie()[i].getStatystykaTygodniowa().wyswietlStatystykeTygodniowaString()).append("\n\n");
            } else {
                result.append("Statystyka regalu ").append(i).append(" nie istnieje\n");
            }
        }

        return result.toString();
    }

    public static String wyswietlSklepString(Sklep sklep) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sklep.getRegalyWSklepie().length; i++) {
            result.append("Numer regalu: ").append(i).append("\n");

            for (int k = 0; k < sklep.getRegalyWSklepie()[i].getPolkiWRegale()[0].length; k++) {
                for (int j = 0; j < sklep.getRegalyWSklepie()[i].getPolkiWRegale().length; j++) {
                    result.append("Lokalizacja polki (").append(j).append(",").append(k).append(")\n");

                    for (int l = 0; l < sklep.getRegalyWSklepie()[i].getPolkiWRegale()[j][k].getProdukty1D().length; l++) {
                        result.append(sklep.getRegalyWSklepie()[i].getPolkiWRegale()[j][k].getProdukty1D()[l]).append("\n");
                    }

                    result.append("\n");
                }
            }
        }

        return result.toString();
    }

    public static String wyswietlProduktyNaPromocji(Sklep sklep){
        JTable table = new JTable(sklep.getTabelaZDanymiPromocyjnymi());
        ArrayList<ArrayList<Produkt>> promocja = sklep.getProduktyNaPromocji();

        System.out.println(table);
        if(promocja.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nie ma danych", "Sklep dane", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            JOptionPane.showMessageDialog(null, panel, "Sklep dane", JOptionPane.INFORMATION_MESSAGE);

        }
        return null;
    }
}
