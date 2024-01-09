package statystyka;

import sklep.PodmiotStatystyki;
import sklep.Polka;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StatystykaCaloroczna implements ObserwatorStatystyki, Serializable {
    //statystyka ogolna podsumowuje zysk ze sprzedazy produktow na regale
    private Map<String, Double> statystykiOgolne; //<typProduktu, zysk>

    public StatystykaCaloroczna(PodmiotStatystyki podmiotStatystyki) {
        podmiotStatystyki.zarejestrujObserwatora(this);
        this.statystykiOgolne = new HashMap<>();
    }

    @Override
    public void aktualizacja(Polka polka, double cena) {
        statystykiOgolne.put(polka.getTypProduktu(), statystykiOgolne.getOrDefault(polka.getTypProduktu(), 0.0)+ cena);
    }

    public void wyswietl(){
        System.out.println("Statystyka ogólna:");

        for (Map.Entry<String, Double> entry : statystykiOgolne.entrySet()) {
            String nazwa = entry.getKey();
            double zysk = entry.getValue();

            String sformatowane = String.format("%-20s Zysk: %.2f zł", nazwa, zysk);
            System.out.println(sformatowane);
        }

    }
}