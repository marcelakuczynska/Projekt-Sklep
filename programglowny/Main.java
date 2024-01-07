package programglowny;

import Relokacja.RelokacjaWzgledemPopytu.*;
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
        test.kolejneDostawy();
        test.uplywCzasu();
        test.kolejneDostawy();
        test.uplywCzasu();
        test.kolejneDostawy();
        //to dla testu xD
//		for (int i = 0; i < test.getRegalyWSklepie().length; i++) { // ktory regal
//			for (int k = 0; k < test.getRegalyWSklepie()[i].getPolkiWRegale()[0].length; k++) { // kolumny regalu
//				for (int j = 0; j < test.getRegalyWSklepie()[i].getPolkiWRegale().length; j++) { // wiersze regalu
//					for (int l = 0; l < test.getRegalyWSklepie()[i].getPolkiWRegale()[j][k].getProdukty1D().length; l++) { // glebokosc regalu
//						if (test.getRegalyWSklepie()[i].getPolkiWRegale()[j][k].getProdukty1D()[l] != null){
//							System.out.println(test.getRegalyWSklepie()[i].getPolkiWRegale()[j][k].getProdukty1D()[l].toString() + "\n");
//						} else System.out.println("null");
//					}
//				}
//			}
//		}
//		System.out.println("--------------------------");
//		test.kolejneDostawy();

//		test.wyswietlSklep();
//		test.getRegalyWSklepie()[1].getStatystyka().wyswietl();
//		System.out.println();
//		test.getRegalyWSklepie()[1].getStatystykaOgolna().wyswietl();
//		System.out.println();

        //ta czesc by wygodnie dzialalo dla relokacji
        Map<Polka, Integer> finalRanking = test.getRegalyWSklepie()[0].getStatystyka().generujRanking();

        Polka polka = test.getRegalyWSklepie()[0].getPolkiWRegale()[1][1];
        int rank = finalRanking.get(polka);

        //prezentacja jak ladnie by dzialalo
//		String nazwaProduktu = polka.getTypProduktu();
//		String firma = polka.getProducent();
//		System.out.println(nazwaProduktu + " " + firma + " " + rank);
//		System.out.println();
        test.wyswietlSklep();
//		//to na szybko do wyswietlenia rankingu, normalnie mogloby byc metoda w statystykach xD
        for (Map.Entry<Polka, Integer> entry : finalRanking.entrySet()) {
            System.out.println(entry.getKey().getTypProduktu() + " " + entry.getKey().getProducent() + " | pozycja: " + entry.getValue());
        }




        for(Regal regal : test.getRegalyWSklepie()){
            regal.setRelokacjaWzgledemPopytu(new Metoda1());
            regal.relokacjaWzgledemPopytu();
        }


        test.ZrelokujSezonowo(48,test); // cos to robi bo printuje inaczej niz bez ale przy takiej ilosci danych cholera wie czy dobrze xdd
        test.wyswietlSklep();



    }
}