package Relokacja.RelokacjaWzgledemPopytu;

import sklep.Polka;
import statystyka.Statystyka;

import java.util.Map;

public class Metoda1 implements RelokacjaWzgledemPopytu{
//  Kolejnosc: srodek, gora, dol
    @Override
    public void sposobRelokacjiWzgledemPopytu(Statystyka statystka, Polka [][] tablicaPolek) {

        Map<Polka, Integer> wynikSprzedazyProduktu = statystka.generujRanking();

        final int maxPopyt = 1, midPopyt = 0;

        Relokuj.relokuj(tablicaPolek, wynikSprzedazyProduktu, maxPopyt, midPopyt);

    }
}
