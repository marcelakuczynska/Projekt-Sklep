package Relokacja.RelokacjaWzgledemPopytu;

import sklep.Polka;
import statystyka.Statystyka;

import java.util.Map;

public class Metoda1 implements RelokacjaWzgledemPopytu{
//  Kolejnosc: srodek, gora, dol
    @Override
    public void sposobRelokacjiWzgledemPopytu(Statystyka statystka, Polka [][] tablicaPolek) {

        Map<Polka, Integer> wynikSprzedazyProduktu = statystka.generujRanking();

        for(int i=0;i< tablicaPolek.length;i++){
            for(int j=0;j<tablicaPolek[i].length;j++){
                for(Map.Entry<Polka, Integer> entry : wynikSprzedazyProduktu.entrySet()){
                    Polka klucz = entry.getKey();
                    int wartosc = entry.getValue()-1;

                    if(tablicaPolek[i][j].equals(klucz)){
                        if(wartosc == 0){
                            Polka pom = tablicaPolek[1][j];
                            tablicaPolek[1][j] = tablicaPolek[i][j];
                            tablicaPolek[i][j] = pom;
                        }
                        else if(wartosc == 1){
                            Polka pom = tablicaPolek[0][j];
                            tablicaPolek[0][j] = tablicaPolek[i][j];
                            tablicaPolek[i][j] = pom;
                        }
                    }
                }
            }
        }

    }
}
