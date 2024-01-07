package Relokacja.RelokacjaWzgledemPopytu;

import sklep.Polka;

import java.io.Serializable;
import java.util.Map;

public class Relokuj implements Serializable {

    static void relokuj(Polka[][] tablicaPolek, Map<Polka, Integer> wynikSprzedazyProduktu, int maxPopyt, int midPopyt) {
        for(int i=0;i< tablicaPolek.length;i++){
            for(int j=0;j<tablicaPolek[i].length;j++){
                for(Map.Entry<Polka, Integer> entry : wynikSprzedazyProduktu.entrySet()){
                    Polka klucz = entry.getKey();
                    int wartosc = entry.getValue()-1;

                    if(tablicaPolek[i][j].equals(klucz)){
                        if(wartosc == 0){

                            Polka pom = tablicaPolek[maxPopyt][j];
                            tablicaPolek[maxPopyt][j] = tablicaPolek[i][j];
                            tablicaPolek[i][j] = pom;
                        }
                        else if(wartosc == 1){
                            Polka pom = tablicaPolek[midPopyt][j];
                            tablicaPolek[midPopyt][j] = tablicaPolek[i][j];
                            tablicaPolek[i][j] = pom;
                        }
                    }
                }
            }
        }
    }
}