package statystyka;

import sklep.PodmiotStatystyki;
import sklep.Polka;

import java.util.HashMap;
import java.util.Map;

public class StatystykaOgolna implements ObserwatorStatystyki {
    //TODO: na razie statystyka ogolna dziala tylko na laczny zysk, moge zmienic jesli chcemy tez miec ilosc produktow

    private Map<String, Double> statystykiOgolne;
    public StatystykaOgolna(PodmiotStatystyki podmiotStatystyki) {
        podmiotStatystyki.zarejestrujObserwatora(this);
        this.statystykiOgolne = new HashMap<>();
    }

    @Override
    public void aktualizacja(Polka polka) {
        statystykiOgolne.put(polka.getTypProduktu(), statystykiOgolne.getOrDefault(polka.getTypProduktu(), 0.0)+ polka.getCena());
    }

    public void wyswietl(){
        System.out.println("Statystyka ogolna:");
        for (Map.Entry<String, Double> entry : statystykiOgolne.entrySet()) {
            String nazwa = entry.getKey();
            double zysk = entry.getValue();
            System.out.println(nazwa + " " + zysk + " zl");
        }
    }
}
