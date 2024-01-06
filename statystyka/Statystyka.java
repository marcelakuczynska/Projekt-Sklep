package statystyka;

import sklep.PodmiotStatystyki;
import sklep.Polka;

import java.util.*;

public class Statystyka implements ObserwatorStatystyki {
    //przechowujemy w hashmapie polke i ilosc sprzedanych produktow dla niej
    private Map<Polka, Integer> wynikSprzedazyProduktu;


    public Statystyka(PodmiotStatystyki produkty, Polka[][] regal) {
        produkty.zarejestrujObserwatora(this);
        this.wynikSprzedazyProduktu = inicjalizacjaHashMapyProduktamiCalegoRegalu(regal);
    }

    @Override
    public void aktualizacja(Polka polka) {
        //dodajemy plus 1 do sprzedazy dla danej polki
        wynikSprzedazyProduktu.put(polka, wynikSprzedazyProduktu.getOrDefault(polka, 0)+1);
    }


    public void wyswietl() {
        System.out.println("Ilosc sprzedanych produktow:");
        for (Map.Entry<Polka, Integer> entry : wynikSprzedazyProduktu.entrySet()) {
            Polka regal3D = entry.getKey();
            int iloscProduktu = entry.getValue();
            double zysk = iloscProduktu * regal3D.getCena();
            System.out.println(regal3D.getTypProduktu() + " " + regal3D.getProducent() + ": " + zysk + "zl, " + iloscProduktu);
        }
    }

    public Map<Polka, Integer> generujRanking() {
        //TODO: podzielic ta metode bo troche za duza ale poki nie wiadomo co robimy to zostaje
        Map<Polka, Integer> rankingZysku = new HashMap<>();

        //w metodzie generujemy ranking dla kazdego "produktu" czyli polki z produktami o tej samej nazwie, firmie i cenie


        for (Map.Entry<Polka, Integer> entry : wynikSprzedazyProduktu.entrySet()) {
            Polka polka = entry.getKey();
            int sprzedaneNaPolce = entry.getValue();

            String nazwa = polka.getTypProduktu();

            // dostanie sie do zysku dla nazwy, sumowanie dla wszystkich firm
            List<Double> zyskValues = new ArrayList<>();
            for (Map.Entry<Polka, Integer> innerEntry : wynikSprzedazyProduktu.entrySet()) {
                if (innerEntry.getKey().getTypProduktu().equals(nazwa)) {
                    double cena = innerEntry.getKey().getCena();
                    double zysk = innerEntry.getValue() * cena;
                    zyskValues.add(zysk);
                }
            }
            zyskValues.sort(Collections.reverseOrder());

            // Przydzielamy ranking na podstawie zysku, 1 dla najlepiej sprzedajacych, 2 dla srodkowych, 3 dla najgorzej
            // W przypadku takiego samego zysku dostaja ten sam wynik - to  mozemy poprawic w razie czego ale nie mam pomyslu jak
            int rank;
            double zysk = polka.getCena() * sprzedaneNaPolce;

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
    public Map<Polka, Integer> inicjalizacjaHashMapyProduktamiCalegoRegalu(Polka[][] keyArray) {
        Map<Polka, Integer> hashMap = new HashMap<>();

        for (Polka[] rzad : keyArray) {
            for (Polka key : rzad) {
                // kazda polka zostaje kluczem w hashmapie i otrzymuje bazowa wartosc 0
                hashMap.put(key, 0);
            }
        }
        return hashMap;
    }

    public Map<Polka, Integer> getWynikSprzedazyProduktu() {
        return wynikSprzedazyProduktu;
    }

    public void setWynikSprzedazyProduktu(Map<Polka, Integer> wynikSprzedazyProduktu) {
        this.wynikSprzedazyProduktu = wynikSprzedazyProduktu;
    }

}