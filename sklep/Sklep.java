package sklep;

import relokacjasezonowa.*;
import relokacjawzgledempopytu.Metoda1;
import relokacjawzgledempopytu.Metoda2;
import relokacjawzgledempopytu.Metoda3;
import relokacjawzgledempopytu.RelokacjaWzgledemPopytu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Sklep implements PodmiotTydzien, Serializable {
    ArrayList<ObserwatorTygodnia> listaObserwatorow;
    private Regal[] regalyWSklepie; // tablica z regałami
    private int ktoryTydzien; //który jest tydzień symulacji zaczynamy 1
    private int iloscRegalow; //ilość regałów na ten moment 4
    private RelokacjaSezonowa relokacjaSezonowa;

    public Sklep() {		//domyślny jego używamy
        ktoryTydzien = 1;
        iloscRegalow = 4;
        listaObserwatorow = new ArrayList<>();

        regalyWSklepie = new Regal[iloscRegalow];

        for (int i = 0; i < iloscRegalow; i++) {
            regalyWSklepie[i] = new Regal(this);
        }
        wczytajPierwszaDostawa();
    }

    public void uplywCzasu() {
        ktoryTydzien++;
        powiadomObserwatorow();
        kolejneDostawy();
        zrelokujSezonowo();
        zapiszDoPliku();
    }

    public void wyswietlSklep(){
        for (int i = 0; i < regalyWSklepie.length; i++) {// ktory regal
            System.out.println("Numer regalu: " + i);
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

    public void wyswietlStatystykeTygodniowaSklepu(){
        System.out.println();
        for (int i = 0; i < regalyWSklepie.length; i++) {// ktory regal
            if (regalyWSklepie[i].getStatystykaTygodniowa() != null) {
                System.out.println("Numer regalu: " + i);
                regalyWSklepie[i].getStatystykaTygodniowa().wyswietlStatystykeTygodniowa();
                System.out.println();
            } else System.out.println("Statystyka regalu " + i + " nie istnieje");
        }
    }

    public void wyswietlStatystykeCalorocznaSklepu(){
        System.out.println();
        for (int i = 0; i < regalyWSklepie.length; i++) {// ktory regal
            System.out.println("Numer regalu: " + i);
            regalyWSklepie[i].getStatystykaCaloroczna().wyswietl();
            System.out.println();
        }
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
                    regalyWSklepie[i].getPolkiWRegale()[j][k].wczytajWartosciBazowePolki();
                    x++;
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
                    dostawa[x][3]=regalyWSklepie[i].getPolkiWRegale()[j][k].getCenaBazowa();
                    dostawa[x][4]=String.format("%s%04d", (String)dostawa[x][1], ktoryTydzien);

                    for (int l = 0; l < regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D().length; l++) { // glebokosc regalu
                        if(regalyWSklepie[i].getPolkiWRegale()[j][k].getProdukty1D()[l] == null) {
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
        this.relokacjaSezonowa = strategia;
    }

    public void zrelokujSezonowo() {
        int tydzienRoku = ktoryTydzien%52+1;

        if (tydzienRoku >= 10 && tydzienRoku <= 22) { //Wiosna
           ustawRelokacjeSezonowa(new Wiosna());
           relokacjaSezonowa.SposobRelokacjiSezonowej(this);
        }
        else if (tydzienRoku >= 23 && tydzienRoku <= 35) { //Lato
            ustawRelokacjeSezonowa(new Lato());
            relokacjaSezonowa.SposobRelokacjiSezonowej(this);
        }
        else if (tydzienRoku >= 36 && tydzienRoku <= 48) { //Jesien
            ustawRelokacjeSezonowa(new Jesien());
            relokacjaSezonowa.SposobRelokacjiSezonowej(this);
        }
        else if ((tydzienRoku >= 1 && tydzienRoku <= 9) || (tydzienRoku >= 49)) { //Zima
           ustawRelokacjeSezonowa(new Zima());
           relokacjaSezonowa.SposobRelokacjiSezonowej(this);
        } else {
            System.out.println("Wprowadz poprawny numer tygodnia!");
        }
    }

    //SERIALIZACJA
    public void zapiszDoPliku() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Sklep.ser"))) {
            outputStream.writeObject(this);
            System.out.println("Stan sklepu zapisany do pliku.\n");
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }

    public Sklep wczytajZPliku() {
        Sklep sklep = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Sklep.ser"))) {
            sklep = (Sklep) inputStream.readObject();
            System.out.println("Stan sklepu wczytany z pliku.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Błąd podczas wczytywania z pliku: " + e.getMessage());
        }
        return sklep;
    }

    @Override
    public void zarejestrujObserwatora(ObserwatorTygodnia o) {
        listaObserwatorow.add(o);
    }

    @Override
    public void usunObserwatora(ObserwatorTygodnia o) {
        listaObserwatorow.remove(o);
    }

    @Override
    public void powiadomObserwatorow() {
        for (int i = 0; i < listaObserwatorow.size(); i++) {
            listaObserwatorow.get(i).aktualizacja(ktoryTydzien);
        }
    }

    public void wybierzRelokacjePopytowa(String metoda) {
        switch (metoda) {
            case "Metoda 1":
                ustawRelokacjePopytowa(new Metoda1());
                break;
            case "Metoda 2":
                ustawRelokacjePopytowa(new Metoda2());
                break;
            case "Metoda 3":
                ustawRelokacjePopytowa(new Metoda3());
                break;
        }
    }
    private void ustawRelokacjePopytowa(RelokacjaWzgledemPopytu relokacja) {
        for (Regal regal : regalyWSklepie){
            regal.setRelokacjaWzgledemPopytu(relokacja);
        }
    }


    @Override
    public String toString() {
        return "Sklep{" +
                "listaObs=" + listaObserwatorow +
                ", regalyWSklepie=" + Arrays.toString(regalyWSklepie) +
                ", ktoryTydzien=" + ktoryTydzien +
                ", iloscRegalow=" + iloscRegalow +
                '}';
    }

    //GETTERY
    public Regal[] getRegalyWSklepie() {
        return regalyWSklepie;
    }

    public int getIloscRegalow() {
        return iloscRegalow;
    }

    public int getKtoryTydzien() {
        return ktoryTydzien;
    }

    public ArrayList<ObserwatorTygodnia> getListaObserwatorow() {
        return listaObserwatorow;
    }
}