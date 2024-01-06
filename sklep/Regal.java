package sklep;

import Relokacja.RelokacjaWzgledemPopytu.RelokacjaWzgledemPopytu;
import statystyka.ObserwatorStatystyki;
import statystyka.Statystyka;
import statystyka.StatystykaOgolna;

import java.util.ArrayList;
import java.util.Random;

public class Regal implements ObserwatorTygodnia, PodmiotStatystyki {
    private final int szerokosc = 5;
    private final int wysokosc = 3;
    private Polka[][] polkiWRegale;
    private ArrayList<ObserwatorStatystyki> listaObserwatorow;
    private Statystyka statystyka; //statystyka do relokacji, zmieniana co tydzien?
    private StatystykaOgolna statystykaOgolna;
    private RelokacjaWzgledemPopytu relokacjaWzgledemPopytu;

     public Regal(PodmiotTydzien sklep) {
         polkiWRegale = new Polka[wysokosc][szerokosc];

         for (int i = 0; i < polkiWRegale.length; i++) {
             for (int j = 0; j < polkiWRegale[i].length; j++) {
                 polkiWRegale[i][j] = new Polka();
             }
         }
        //zwiazane z obserwatorem tygodnia
         sklep.zarejestrujObserwatora(this);

         //zwiazane z obserwatorem statystyki
         listaObserwatorow = new ArrayList<>();

         //tworzenie statystyki ogolnej ktora trwa caly rok
         statystykaOgolna = new StatystykaOgolna(this);
    }

    public void ustawProdukt(Produkt produkt, int x1, int y1, int z1) {  //ustawia produkt w danym miejscu
    	if (polkiWRegale[x1][y1].getProdukty1D()[z1] != null) {
            polkiWRegale[x1][y1].getProdukty1D()[z1] = produkt;

            // tu cos nowego wlasnie NIE WIEM JAK TO ZADZIALA, JAK DZIALA ZLE TO SIE COFAMY DO TABLICY NA RAZIE - jak szare to nie dziala xD
            /* produktInfoMap.computeIfAbsent(x1, HashMap::new)
                    .computeIfAbsent(y1, HashMap::new)
                    .put(z1, produkt); */
        } else System.out.println("Na tm miejscu juz cos stoi");
    }
    
    public void usunProdukt(int x1,int y1,int z1) {
    	polkiWRegale[x1][y1].getProdukty1D()[z1] = null;
    }

    @Override
    public void aktualizacja(int ktoryTydzien) {
        //potencjalnie przekazuje ktorytydzien dla relokacji sezonowej?
        // //ale chyba nie bedzie potrzebne bo relokacja sezonowa ma przesuwac w sklepie regaly?
        zmianaDaty();
        //wraz ze zmiana tygodnia robimy nowe statystyki tygodniowe potrzebne do relokacji
        statystyka = new Statystyka(this, polkiWRegale);
        symulacjaSprzedazy();
    }
    
    public void zmianaDaty () { // odpane przez za pomocą Obserwatora po zmianie tygodnia!!!
        // bardzo wazne zeby nigdzie nie bylo dwoch takich samych produktow
        Produkt[] getProdukty1D;
        for (int i = 0; i < polkiWRegale.length; i++) {
            for (int j = 0; j < polkiWRegale[i].length; j++) {
                getProdukty1D = polkiWRegale[i][j].getProdukty1D();
                if (getProdukty1D != null) {
                    for (int k = 0; k < getProdukty1D.length; k++) {
                        if (getProdukty1D[k] != null) 
                            getProdukty1D[k].zmianaDatyWaznosci();
                        if(getProdukty1D[k].getDataWaznosci()==1)
                        	getProdukty1D[k].zrobPromocje(0.5);
                    }
                }
            }
        }
    }

    public void symulacjaSprzedazy () {    //losowa sprzedaż produktów + też się odpala obserwatorem
    	Random gen = new Random();
        Produkt[] getProdukty1D;
    	for(int i = 0; i < polkiWRegale.length; i++) {
            for (int j = 0; j < polkiWRegale[i].length; j++) {
                //dostajemy sie do klasy polki i tam bedziemy iterowac po glebokosci
                getProdukty1D = polkiWRegale[i][j].getProdukty1D();
                if (getProdukty1D != null) {
                    for (int k = 0; k < getProdukty1D.length; k++) {
                        if (getProdukty1D[k] != null) {
                            if (getProdukty1D[k].getCzyPromocja()) {
                                powiadomObserwatorow(polkiWRegale[i][j]);

                                getProdukty1D[k] = null;
                                //powiadamia obserwatorow i przekazuje im CALA POLKE
                            } else if (gen.nextInt(3) + 1 == 1) {//od 1 do 3
                                powiadomObserwatorow(polkiWRegale[i][j]);

                                getProdukty1D[k] = null;
                            }
                        }
                    }
                }
            }
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

    public Statystyka getStatystyka() {
        return statystyka;
    }

    public StatystykaOgolna getStatystykaOgolna() {
        return statystykaOgolna;
    }

    //SETTERY
    public void setPolkiWRegale(Polka[][] polkiWRegale) {
        this.polkiWRegale = polkiWRegale;
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
    public void powiadomObserwatorow(Polka polka) {
        for (ObserwatorStatystyki obserwator : listaObserwatorow){
            obserwator.aktualizacja(polka);
        }
    }

    public void setRelokacjaWzgledemPopytu(RelokacjaWzgledemPopytu relokacjaWzgledemPopytu) {
        this.relokacjaWzgledemPopytu = relokacjaWzgledemPopytu;
    }

    public void relokacjaWzgledemPopytu() {
        relokacjaWzgledemPopytu.sposobRelokacjiWzgledemPopytu(statystyka,polkiWRegale);
    }

}