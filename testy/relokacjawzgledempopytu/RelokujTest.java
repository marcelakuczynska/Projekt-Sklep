package relokacjawzgledempopytu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sklep.Polka;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RelokujTest {
//    jednak nie chyba bo to metoda statyczna
//    Relokuj ob;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        ob = new Relokuj();
//    }


    // dziala dla najprostszego przypadku ale przy wartosciach takiej samej sprzedazy robi fikolka

    @Test
    void relokuj() {
        Polka polka1 = new Polka("x");
        Polka polka2 = new Polka("y");
        Polka polka3 = new Polka("z");
        Polka polka4 = new Polka("a");
        Polka polka5 = new Polka("b");
        Polka polka6 = new Polka("c");
        Polka polka7 = new Polka("i");
        Polka polka8 = new Polka("j");
        Polka polka9 = new Polka("k");

        Polka[][] tablicaPolek = {
                {polka1, polka4, polka7},
                {polka2, polka5, polka8},
                {polka3, polka6, polka9}
        };

        Polka[][] tablicaZrelokowana = {
                {polka2, polka4, polka7},
                {polka1, polka5, polka9},
                {polka3, polka6, polka8}
        };

        HashMap<Polka, Integer> wynikSprzedazyProduktu = new HashMap<>();
        wynikSprzedazyProduktu.put(polka1, 1);
        wynikSprzedazyProduktu.put(polka2, 2);
        wynikSprzedazyProduktu.put(polka3, 3);

        wynikSprzedazyProduktu.put(polka5, 1);
        wynikSprzedazyProduktu.put(polka4, 2);
        wynikSprzedazyProduktu.put(polka6, 3);

        wynikSprzedazyProduktu.put(polka7, 2);
        wynikSprzedazyProduktu.put(polka8, 3);
        wynikSprzedazyProduktu.put(polka9, 1);

        int maxPopyt = 1;
        int minPopyt = 2;

        Relokuj.relokuj(tablicaPolek, wynikSprzedazyProduktu, maxPopyt, minPopyt);

        assertArrayEquals(tablicaZrelokowana, tablicaPolek);
    }
}