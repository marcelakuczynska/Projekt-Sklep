package statystyka;

import sklep.PodmiotStatystyki;
import sklep.Polka;

import java.io.Serializable;
import java.util.*;

public class StatystykaTygodniowa implements ObserwatorStatystyki, Serializable {
    //przechowujemy w hashmapie polke i ilosc sprzedanych produktow dla niej
    private Map<Polka, WartosciSprzedazy> wynikSprzedazyProduktu;

    public StatystykaTygodniowa(PodmiotStatystyki produkty, Polka[][] regal) {
        produkty.zarejestrujObserwatora(this);
        this.wynikSprzedazyProduktu = inicjalizacjaHashMapyProduktamiCalegoRegalu(regal);
    }

    @Override
    public void aktualizacja(Polka polka, double cena) {
        //dodajemy plus 1 do sprzedazy dla danej polki
        WartosciSprzedazy odczytaneWartosci = wynikSprzedazyProduktu.get(polka);
        //modyfikacja wartosci
        odczytaneWartosci.iloscSprzedanych++;
        odczytaneWartosci.zysk += cena;;

        wynikSprzedazyProduktu.put(polka, wynikSprzedazyProduktu.getOrDefault(polka, odczytaneWartosci) );
    }


    public void wyswietl() {
        System.out.println("Ilosc sprzedanych produktow:");
        for (Map.Entry<Polka, WartosciSprzedazy> entry : wynikSprzedazyProduktu.entrySet()) {
            Polka polka = entry.getKey();
            WartosciSprzedazy wartosciSprzedazy = entry.getValue();
            System.out.println(polka.getTypProduktu() + " " + polka.getProducent() + ": " + wartosciSprzedazy.getZysk() + "zl, " + wartosciSprzedazy.getIloscSprzedanych());
        }
    }

    public Map<Polka, Integer> generujRanking() {
        //TODO: podzielic ta metode bo troche za duza ale poki nie wiadomo co robimy to zostaje
        Map<Polka, Integer> rankingZysku = new HashMap<>();

        //w metodzie generujemy ranking dla kazdego "produktu" czyli polki z produktami o tej samej nazwie, firmie i cenie


        for (Map.Entry<Polka, WartosciSprzedazy> entry : wynikSprzedazyProduktu.entrySet()) {
            Polka polka = entry.getKey();
            String nazwa = polka.getTypProduktu();
            WartosciSprzedazy wartosciSprzedazy = entry.getValue();

            // dostanie sie do zysku dla nazwy, sumowanie dla wszystkich firm
            List<Double> zyskValues = new ArrayList<>();
            for (Map.Entry<Polka, WartosciSprzedazy> innerEntry : wynikSprzedazyProduktu.entrySet()) {
                if (innerEntry.getKey().getTypProduktu().equals(nazwa)) {
                    double zysk = innerEntry.getValue().getZysk();
                    zyskValues.add(zysk);
                }
            }
            zyskValues.sort(Collections.reverseOrder());

            // Przydzielamy ranking na podstawie zysku, 1 dla najlepiej sprzedajacych, 2 dla srodkowych, 3 dla najgorzej
            // W przypadku takiego samego zysku dostaja ten sam wynik - to  mozemy poprawic w razie czego ale nie mam pomyslu jak
            int rank;
            double zysk = wartosciSprzedazy.getZysk();

            if (zysk == zyskValues.get(0)) {
                rank = 1;
            } else if (zysk == zyskValues.get(1)) {
                rank = 2;
            } else {
                rank = 3;
            }

            rankingZysku.put(polka, rank);
        }

        return rankingZysku;
    }
    public Map<Polka, WartosciSprzedazy> inicjalizacjaHashMapyProduktamiCalegoRegalu(Polka[][] keyArray) {
        Map<Polka, WartosciSprzedazy> hashMap = new HashMap<>();

        for (Polka[] rzad : keyArray) {
            for (Polka key : rzad) {
                // kazda polka zostaje kluczem w hashmapie i otrzymuje bazowa wartosc 0
                hashMap.put(key, new WartosciSprzedazy());
            }
        }
        return hashMap;
    }

    public Map<Polka, WartosciSprzedazy> getWynikSprzedazyProduktu() {
        return wynikSprzedazyProduktu;
    }

    public void setWynikSprzedazyProduktu(Map<Polka, WartosciSprzedazy> wynikSprzedazyProduktu) {
        this.wynikSprzedazyProduktu = wynikSprzedazyProduktu;
    }

    public class WartosciSprzedazy implements Serializable {
        private int iloscSprzedanych;
        private double zysk;

        public WartosciSprzedazy(int iloscSprzedanych, double zysk){
            this.iloscSprzedanych = iloscSprzedanych;
            this.zysk = zysk;
        }
        public WartosciSprzedazy(){
            this.iloscSprzedanych = 0;
            this.zysk = 0;
        }

        public int getIloscSprzedanych() {return iloscSprzedanych;}

        public double getZysk() {return zysk;}
    }
}