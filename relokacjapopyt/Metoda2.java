package relokacjapopyt;

import sklep.Polka;
import statystyka.StatystykaTygodniowa;

import java.io.Serializable;
import java.util.Map;

public class Metoda2 implements RelokacjaWzgledemPopytu, Serializable {
//  Kolejnosc: srodek, dol, gora
    @Override
    public void sposobRelokacjiWzgledemPopytu(StatystykaTygodniowa statystka, Polka [][] tablicaPolek) {

        Map<Polka, Integer> wynikSprzedazyProduktu = statystka.generujRanking();

        final int maxPopyt = 1, midPopyt = 2;

        Relokuj.relokuj(tablicaPolek, wynikSprzedazyProduktu, maxPopyt, midPopyt);

    }
}