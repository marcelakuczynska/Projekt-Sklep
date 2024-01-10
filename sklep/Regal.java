package sklep;

import relokacjawzgledempopytu.Metoda1;
import relokacjawzgledempopytu.RelokacjaWzgledemPopytu;
import statystyka.ObserwatorStatystyki;
import statystyka.StatystykaCaloroczna;
import statystyka.StatystykaTygodniowa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Regal implements ObserwatorTygodnia, PodmiotStatystyki, Serializable {
    private final int szerokosc = 5;
    private final int wysokosc = 3;
    private Polka[][] polkiWRegale;
    private ArrayList<ObserwatorStatystyki> listaObserwatorow;
    private StatystykaTygodniowa statystykaTygodniowa;
    private StatystykaCaloroczna statystykaCaloroczna;
    private RelokacjaWzgledemPopytu relokacjaWzgledemPopytu;

    public Regal(PodmiotTydzien sklep) {
        polkiWRegale = new Polka[wysokosc][szerokosc];
        relokacjaWzgledemPopytu = new Metoda1();

        for (int i = 0; i < polkiWRegale.length; i++) {
            for (int j = 0; j < polkiWRegale[i].length; j++) {
                polkiWRegale[i][j] = new Polka();
            }
        }
        //zwiazane z obserwatorem tygodnia
        sklep.zarejestrujObserwatora(this);

        //zwiazane z obserwatorem statystyki sprzedazy
        listaObserwatorow = new ArrayList<>();

        //tworzenie statystyki ogolnej ktora trwa caly rok
        statystykaCaloroczna = new StatystykaCaloroczna(this);
    }

    @Override
    public void aktualizacja(int ktoryTydzien) {
        //potencjalnie przekazuje ktorytydzien dla relokacji sezonowej?
        // //ale chyba nie bedzie potrzebne bo relokacja sezonowa ma przesuwac w sklepie regaly?
        zmianaDaty();
        //wraz ze zmiana tygodnia robimy nowe statystyki tygodniowe potrzebne do relokacji
        statystykaTygodniowa = new StatystykaTygodniowa(this, polkiWRegale);
        symulacjaSprzedazy();
        statystykaTygodniowa.generujRanking();
        relokacjaWzgledemPopytu.sposobRelokacjiWzgledemPopytu(statystykaTygodniowa, polkiWRegale);
    }

    public void zmianaDaty () { // odpane przez za pomocą Obserwatora po zmianie tygodnia!!!
        Produkt[] getProdukty1D;
        for (int i = 0; i < polkiWRegale.length; i++) {
            for (int j = 0; j < polkiWRegale[i].length; j++) {
                getProdukty1D = polkiWRegale[i][j].getProdukty1D();
                if (getProdukty1D != null) {
                    for (int k = 0; k < getProdukty1D.length; k++) {
                        if (getProdukty1D[k] != null)
                            getProdukty1D[k].zmianaDatyWaznosci();
                        if (getProdukty1D[k].getDataWaznosci() == 1)
                            getProdukty1D[k].zrobPromocje(0.5);
                    }
                }
            }
        }
    }

    public void symulacjaSprzedazy () {    //losowa sprzedaż produktów + też się odpala obserwatorem
        Random gen = new Random();
        Produkt[] produktyNaPolce;
        for(int i = 0; i < polkiWRegale.length; i++) {
            for (int j = 0; j < polkiWRegale[i].length; j++) {
                //dostajemy sie do klasy polki i tam bedziemy iterowac po glebokosci
                produktyNaPolce = polkiWRegale[i][j].getProdukty1D();
                if (produktyNaPolce != null) {
                    for (int k = 0; k < produktyNaPolce.length; k++) {
                        if (produktyNaPolce[k] != null) {
                            if (produktyNaPolce[k].getCzyPromocja()) {
                                //powiadamia obserwatorow i przekazuje im polke na ktorej lezy usuwany produkt oraz jego cene
                                powiadomObserwatorow(polkiWRegale[i][j], produktyNaPolce[k].getCena());

                                produktyNaPolce[k] = null;
                            } else if (gen.nextInt(3) + 1 == 1) {//od 1 do 3
                                powiadomObserwatorow(polkiWRegale[i][j], produktyNaPolce[k].getCena());

                                produktyNaPolce[k] = null;
                            }
                        }
                    }
                }
//               Dosuwanie produktow do poczatku polki
                polkiWRegale[i][j].dosunProdukty();
            }
        }
    }


    public void ustawProdukt(Produkt produkt, int x1, int y1, int z1) {  //ustawia produkt w danym miejscu
        if (polkiWRegale[x1][y1].getProdukty1D()[z1] != null) {
            polkiWRegale[x1][y1].getProdukty1D()[z1] = produkt;

        } else System.out.println("Na tym miejscu juz cos stoi");
    }

    public void usunProdukt(int x1,int y1,int z1) {
        polkiWRegale[x1][y1].getProdukty1D()[z1] = null;
    }

    public void relokacjaWzgledemPopytu() {
        relokacjaWzgledemPopytu.sposobRelokacjiWzgledemPopytu(statystykaTygodniowa,polkiWRegale);
    }


    @Override
    public void zarejestrujObserwatora(ObserwatorStatystyki obserwatorStatystyki) {
        listaObserwatorow.add(obserwatorStatystyki);
    }

    @Override
    public void usunObserwatora(ObserwatorStatystyki obserwatorStatystyki) {
        listaObserwatorow.remove(obserwatorStatystyki);
    }

    @Override
    public void powiadomObserwatorow(Polka polka, double cenaProduktu) {
        for (ObserwatorStatystyki obserwator : listaObserwatorow){
            obserwator.aktualizacja(polka, cenaProduktu);
        }
    }


    //GETTERY

    public int getSzerokosc() {
        return szerokosc;
    }

    public int getWysokosc() {
        return wysokosc;
    }
    public Polka[][] getPolkiWRegale() {
        return polkiWRegale;
    }

    public StatystykaTygodniowa getStatystykaTygodniowa() {
        return statystykaTygodniowa;
    }

    public StatystykaCaloroczna getStatystykaCaloroczna() {
        return statystykaCaloroczna;
    }

    //SETTERY
    public void setPolkiWRegale(Polka[][] polkiWRegale) {
        this.polkiWRegale = polkiWRegale;
    }

    public void setRelokacjaWzgledemPopytu(RelokacjaWzgledemPopytu relokacjaWzgledemPopytu) {
        this.relokacjaWzgledemPopytu = relokacjaWzgledemPopytu;
    }
}