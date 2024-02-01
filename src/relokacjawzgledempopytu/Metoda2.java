package relokacjawzgledempopytu;

import sklep.Polka;
import statystyka.StatystykaTygodniowa;

import java.io.Serializable;
import java.util.Map;

public class Metoda2 implements RelokacjaWzgledemPopytu, Serializable {
//  Kolejnosc: srodek, dol, gora
    @Override
    public void sposobRelokacjiWzgledemPopytu(StatystykaTygodniowa statystyka, Polka [][] tablicaPolek) {

        Map<Polka, Integer> wynikSprzedazyProduktu = statystyka.getRankingZysku();

        final int maxPopyt = 1, minPopyt = 0;

        Relokuj.relokuj(tablicaPolek, wynikSprzedazyProduktu, maxPopyt, minPopyt);

    }
}