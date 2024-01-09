package statystyka;

import sklep.Polka;

public interface ObserwatorStatystyki {
    void aktualizacja(Polka polka, double cena);
}