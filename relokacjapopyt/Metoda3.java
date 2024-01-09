package relokacjapopyt;

import sklep.Polka;
import statystyka.StatystykaTygodniowa;

import java.io.Serializable;
import java.util.Map;

public class Metoda3 implements RelokacjaWzgledemPopytu, Serializable {
//  Kolejnosc: gora, dol, srodek
    @Override
    public void sposobRelokacjiWzgledemPopytu(StatystykaTygodniowa statystyka, Polka[][] tablicaPolek) {

        Map<Polka, Integer> wynikSprzedazyProduktu = statystyka.generujRanking();

        final int maxPopyt = 0, midPopyt = 2;

        Relokuj.relokuj(tablicaPolek, wynikSprzedazyProduktu, maxPopyt, midPopyt);

    }
}