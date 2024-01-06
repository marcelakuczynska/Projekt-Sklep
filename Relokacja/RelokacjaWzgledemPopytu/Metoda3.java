package Relokacja.RelokacjaWzgledemPopytu;

import sklep.Polka;
import statystyka.Statystyka;

import java.util.Map;

public class Metoda3 implements RelokacjaWzgledemPopytu{
//  Kolejnosc: gora, dol, srodek
    @Override
    public void sposobRelokacjiWzgledemPopytu(Statystyka statystyka, Polka[][] polka) {

        Map<Polka, Integer> wynikSprzedazyProduktu = statystyka.generujRanking();


    }
}
