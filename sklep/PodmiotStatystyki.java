package sklep;

import statystyka.ObserwatorStatystyki;

public interface PodmiotStatystyki {
    void zarejestrujObserwatora(ObserwatorStatystyki obserwatorStatystyki);
    void usunObserwatora(ObserwatorStatystyki obserwatorStatystyki);
    void powiadomObserwatorow(Polka polka);
}
