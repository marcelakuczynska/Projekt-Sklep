package sklep;

import RelokacjaSezonowa.RelokacjaSezonowa;
import RelokacjaSezonowa.Wiosna;
import RelokacjaSezonowa.Lato;
import RelokacjaSezonowa.Jesien;
import RelokacjaSezonowa.Zima;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Sklep implements PodmiotTydzien, Serializable {
    ArrayList<ObserwatorTygodnia> listaObs;
    private Regal[] regalyWSklepie; // tablica z regałami
    private int ktoryTydzien; //który jest tydzień symulacji zaczynamy 1
    private int iloscRegalow; //ilość regałów na ten moment 4
    private RelokacjaSezonowa RS;

    public Sklep() {		//domyślny jego używamy
        ktoryTydzien = 1;
        iloscRegalow = 4;
        listaObs = new ArrayList<>();

        regalyWSklepie = new Regal[iloscRegalow];

        for (int i = 0; i < iloscRegalow; i++)
            regalyWSklepie[i] = new Regal(this);
    }

    public Sklep(int ktoryTydzien, int iloscRegalow) {
        this.ktoryTydzien = ktoryTydzien;
        this.iloscRegalow = iloscRegalow;
        listaObs = new ArrayList<>();

        regalyWSklepie = new Regal[iloscRegalow];
        for(int i = 0; i < iloscRegalow; i++) {
            regalyWSklepie[i] = new Regal(this);
        }
    }

    public void uplywCzasu() {
        ktoryTydzien++;
        powiadomObserwatorow();
        zapiszDoPliku();
    }

    public void wczytajPierwszaDostawa() {
        Dostawa dostawa = new sklep.Dostawa(); // dostawa.getDostawa()[][];
        int x = 0;

        for (int i = 0; i < regalyWSklepie.length; i++) { // ktory regal
            for (int k = 0; k < regalyWSklepie[i].getPolkiWRegale()[0].length; k++) { // kolumny regalu
                for (int j = 0; j < regalyWSklepie[i].getPolkiWRegale().length; j++) { // wiersze regalu
                    for (int l = 0; l < regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D().length; l++) { // glebokosc regalu
                        regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D()[l] = new Produkt(
                                (String) dostawa.getDostawa()[x][0],
                                (String) dostawa.getDostawa()[x][1],
                                (int) dostawa.getDostawa()[x][2],
                                (double) dostawa.getDostawa()[x][3],
                                (String) dostawa.getDostawa()[x][4],
                                false,
                                1.0
                        );
                        if (x >= dostawa.getDostawa().length) {
                            System.out.println("Wszystkie produkty z dostawy zostały wczytane.");
                            return;
                        }
                    }
                    regalyWSklepie[i].getPolkiWRegale()[j][k].wczytajNazwy();
                    x++;
                }

            }
        }
    }

    public void wyswietlSklep() {
        for (int i = 0; i < regalyWSklepie.length; i++) { // ktory regal
            for (int k = 0; k < regalyWSklepie[i].getPolkiWRegale()[0].length; k++) { // kolumny regalu
                for (int j = 0; j < regalyWSklepie[i].getPolkiWRegale().length; j++) { // wiersze regalu
                    for (int l = 0; l < regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D().length; l++) { // glebokosc regalu
                        System.out.println(regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D()[l]);
                    }
                    System.out.println();
                }
            }
        }
    }

    public void wyswietlSklepCzytelniejsze(){
        for (int i = 0; i < regalyWSklepie.length; i++) {// ktory regal
            System.out.println("Numer regalu:" + i);
            for (int k = 0; k < regalyWSklepie[i].getPolkiWRegale()[0].length; k++) { // kolumny regalu
                for (int j = 0; j < regalyWSklepie[i].getPolkiWRegale().length; j++) { // wiersze regalu
                    System.out.println("Lokalizacja polki (" + j + "," + k + ")");
                    for (int l = 0; l < regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D().length; l++) { // glebokosc regalu
                        System.out.println(regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D()[l]);
                    }
                    System.out.println();
                }
            }
        }
    }

    public void kolejneDostawy(){
        Object[][] dostawa = new Object[60][6];
        int ileBrakuje;
        int x=0;

        for (int i = 0; i < regalyWSklepie.length; i++) { // ktory regal
            for (int k = 0; k < regalyWSklepie[i].getPolkiWRegale()[0].length; k++) { // kolumny regalu
                for (int j = 0; j < regalyWSklepie[i].getPolkiWRegale().length; j++) { // wiersze regalu
                    ileBrakuje=0;

                    dostawa[x][0]=regalyWSklepie[i].getPolkiWRegale()[j][k].getTypProduktu();
                    dostawa[x][1]=regalyWSklepie[i].getPolkiWRegale()[j][k].getProducent();
                    dostawa[x][2]=regalyWSklepie[i].getPolkiWRegale()[j][k].getDataWaznosci();
                    dostawa[x][3]=regalyWSklepie[i].getPolkiWRegale()[j][k].getCena();
                    dostawa[x][4]=String.format("%s%04d",(String)dostawa[x][1],ktoryTydzien);

                    for (int l = 0; l < regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D().length; l++) { // glebokosc regalu
                        if(regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D()[l]==null) {
                            ileBrakuje++;
                            regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D()[l] = new Produkt(
                                    (String) dostawa[x][0],
                                    (String) dostawa[x][1],
                                    (int) dostawa[x][2],
                                    (double) dostawa[x][3],
                                    (String) dostawa[x][4],
                                    false,
                                    1.0
                            );
                        }
                    }

                    dostawa[x][5]=ileBrakuje;
                    x++;
                }
            }
        }
//		for(int i=0;i<dostawa.length;i++)
//				System.out.println(dostawa[i][0]+" "+dostawa[i][1]+" "+dostawa[i][2]+" "+dostawa[i][3]+" "+dostawa[i][4]+" "+dostawa[i][5]+" ");
    }

    public void ustawRelokacjeSezonowa(RelokacjaSezonowa strategia) {
        this.RS= strategia;
    }

    public void ZrelokujSezonowo(int ktoryTydzien,Sklep sklep){
        if(ktoryTydzien>=10 && ktoryTydzien<=22){ //Wiosna
           ustawRelokacjeSezonowa(new Wiosna());
           RS.SposobRelokacjiSezonowej(sklep);
        }
        if(ktoryTydzien>=23 && ktoryTydzien<=35){ //Lato
            ustawRelokacjeSezonowa(new Lato());
            RS.SposobRelokacjiSezonowej(sklep);
        }
        if(ktoryTydzien>=36 && ktoryTydzien<=48){ //Jesien
            ustawRelokacjeSezonowa(new Jesien());
            RS.SposobRelokacjiSezonowej(sklep);
        }
        if((ktoryTydzien>=1 && ktoryTydzien<=9) || (ktoryTydzien>=49 && ktoryTydzien<=52)){ //Zima
           ustawRelokacjeSezonowa(new Zima());
           RS.SposobRelokacjiSezonowej(sklep);
        }else{
            System.out.println("Wprowadz poprawny numer tygodnia!");
            return;
        }
    }


    @Override
    public void zarejestrujObserwatora(ObserwatorTygodnia o) {
        listaObs.add(o);
    }

    @Override
    public void usunObserwatora(ObserwatorTygodnia o) {
        listaObs.remove(o);
    }

    @Override
    public void powiadomObserwatorow() {
        for (int i = 0; i < listaObs.size(); i++) {
            listaObs.get(i).aktualizacja(ktoryTydzien);
        }
    }

    public Regal[] getRegalyWSklepie() {
        return regalyWSklepie;
    }

    public int getIloscRegalow() {
        return iloscRegalow;
    }

    public int getKtoryTydzien() {
        return ktoryTydzien;
    }

    public ArrayList<ObserwatorTygodnia> getListaObs() {
        return listaObs;
    }

    @Override
    public String toString() {
        return "Sklep{" +
                "listaObs=" + listaObs +
                ", regalyWSklepie=" + Arrays.toString(regalyWSklepie) +
                ", ktoryTydzien=" + ktoryTydzien +
                ", iloscRegalow=" + iloscRegalow +
                '}';
    }


        public void zapiszDoPliku() {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Sklep.ser"))) {
                outputStream.writeObject(this);
                System.out.println("Obiekt zapisany do pliku: " + "Sklep.ser");
            } catch (IOException e) {
                System.err.println("Błąd podczas zapisywania do pliku: " + e.getMessage());
            }
        }

        public Sklep wczytajZPliku() {
            Sklep sklep = null;
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Sklep.ser"))) {
                sklep = (Sklep) inputStream.readObject();
                System.out.println("Obiekt wczytany z pliku: " + "Sklep.ser");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Błąd podczas wczytywania z pliku: " + e.getMessage());
            }
            return sklep;
        }


}