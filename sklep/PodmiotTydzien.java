package sklep;

public interface PodmiotTydzien {
    void zarejestrujObserwatora(ObserwatorTygodnia o);
    void usunObserwatora(ObserwatorTygodnia o);
    void powiadomObserwatorow();
}