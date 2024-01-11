package relokacjawzgledempopytu;

import sklep.Polka;
import statystyka.StatystykaTygodniowa;

import java.io.Serializable;
import java.util.Map;

public class Metoda1 implements RelokacjaWzgledemPopytu, Serializable {
//  Kolejnosc: srodek, gora, dol
    @Override
    public void sposobRelokacjiWzgledemPopytu(StatystykaTygodniowa statystka, Polka [][] tablicaPolek) {

        Map<Polka, Integer> wynikSprzedazyProduktu = statystka.getRankingZysku();

        final int maxPopyt = 1, minPopyt = 2;

        Relokuj.relokuj(tablicaPolek, wynikSprzedazyProduktu, maxPopyt, minPopyt);

    }
}