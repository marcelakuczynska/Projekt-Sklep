package Relokacja.RelokacjaWzgledemPopytu;

import sklep.Polka;
import statystyka.Statystyka;

import java.util.Map;

public class Metoda2 implements RelokacjaWzgledemPopytu{
//  Kolejnosc: srodek, dol, gora
    @Override
    public void sposobRelokacjiWzgledemPopytu(Statystyka statystka, Polka[][] polka) {

        Map<Polka, Integer> wynikSprzedazyProduktu = statystka.generujRanking();

    }
}
