package statystyka;

import sklep.PodmiotStatystyki;
import sklep.Polka;

import java.io.Serializable;
import java.util.*;

public class StatystykaTygodniowa implements ObserwatorStatystyki, Serializable {
    private Map<Polka, WartosciSprzedazy> wynikSprzedazyProduktu;
    private Map<Polka, Integer> rankingZysku;

    public StatystykaTygodniowa(PodmiotStatystyki produkty, Polka[][] regal) {
        produkty.zarejestrujObserwatora(this);
        this.rankingZysku = new HashMap<>();
        this.wynikSprzedazyProduktu = inicjalizacjaHashMapyProduktamiCalegoRegalu(regal);
    }

    @Override
    public void aktualizacja(Polka polka, double cena) {
        //dodajemy plus 1 do sprzedazy dla danej polki
        WartosciSprzedazy odczytaneWartosci = wynikSprzedazyProduktu.get(polka);
        //modyfikacja wartosci
        odczytaneWartosci.iloscSprzedanych++;
        odczytaneWartosci.zysk += cena;;

        wynikSprzedazyProduktu.put(polka, wynikSprzedazyProduktu.getOrDefault(polka, odczytaneWartosci));
    }

    public void wyswietlStatystykeTygodniowa() {
        if (rankingZysku != null) {
            System.out.println("Statystyka tygodniowa:");
            for (Map.Entry<Polka, WartosciSprzedazy> entry : wynikSprzedazyProduktu.entrySet()) {
                Polka polka = entry.getKey();
                WartosciSprzedazy wartosciSprzedazy = entry.getValue();
                int rank = rankingZysku.get(polka);

                String sformatowane = String.format("%-20s %-20s  Zysk: %-10.2f  Ilość sprzedanych: %-5d  Ranking: %d",
                        polka.getTypProduktu(), polka.getProducent(), wartosciSprzedazy.getZysk(),
                        wartosciSprzedazy.getIloscSprzedanych(), rank);

                System.out.println(sformatowane);
            }
        }
    }

    public String wyswietlStatystykeTygodniowaString() {
        StringBuilder result = new StringBuilder();
        TreeMap<Polka, WartosciSprzedazy> wynikSprzedazy = new TreeMap<>(Comparator
                .<Polka, String>comparing(Polka::getTypProduktu)
                .thenComparing(Comparator.comparingDouble(polka -> wynikSprzedazyProduktu.get(polka).getZysk()).reversed()));

        wynikSprzedazy.putAll(wynikSprzedazyProduktu);

        if (rankingZysku != null) {
            result.append("Statystyka tygodniowa:\n");

            for (Map.Entry<Polka, WartosciSprzedazy> entry : wynikSprzedazy.entrySet()) {
                Polka polka = entry.getKey();
                WartosciSprzedazy wartosciSprzedazy = entry.getValue();
                int rank = rankingZysku.get(polka);

                String sformatowane = String.format("%-20s %-20s  Zysk: %-10.2f  Ilość sprzedanych: %-5d  Ranking: %d\n",
                        polka.getTypProduktu(), polka.getProducent(), wartosciSprzedazy.getZysk(),
                        wartosciSprzedazy.getIloscSprzedanych(), rank);

                result.append(sformatowane);
            }
        }

        return result.toString();
    }


    public void generujRanking() {
        //TODO: podzielic ta metode bo troche za duza ale poki nie wiadomo co robimy to zostaje

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
            // W przypadku takiego samego zysku dostaja ten sam wynik
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

    public Map<Polka, Integer> getRankingZysku() {
        return rankingZysku;
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