package programglowny;

import sklep.Polka;
import sklep.Regal;
import sklep.Sklep;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Sklep test = new Sklep();
        test.wczytajPierwszaDostawa();
        System.out.print(test);
//		test.wyswietlSklep();



        test.uplywCzasu();
        test.wyswietlSklepCzytelniejsze();


        //ta czesc by wygodnie dzialalo dla relokacji
        Map<Polka, Integer> finalRanking = test.getRegalyWSklepie()[0].getStatystykaTygodniowa().generujRanking();

        Polka polka = test.getRegalyWSklepie()[0].getPolkiWRegale()[1][1];
        int rank = finalRanking.get(polka);

//		//to na szybko do wyswietlenia rankingu, normalnie mogloby byc metoda w statystykach xD
        for (Map.Entry<Polka, Integer> entry : finalRanking.entrySet()) {
            System.out.println(entry.getKey().getTypProduktu() + " " + entry.getKey().getProducent() + " | pozycja: " + entry.getValue());
        }




        for(Regal regal : test.getRegalyWSklepie()){
            regal.relokacjaWzgledemPopytu();
        }


        /* TEST SERIALIZACJI

        test.zapiszDoPliku();
        test.ZrelokujSezonowo(68, test);
        test.wczytajZPliku().wyswietlSklep();

         */
    }
}