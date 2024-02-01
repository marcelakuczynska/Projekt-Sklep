package sklep;

import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.*;

public class Produkt implements Serializable {
    private String typProduktu; //np. mleko, mąka, jajka
    private String producent; // nazwa firmy (rozróżnienie produktów tego samego typu)
    private int dataWaznosci; // ile tygodni do końca przydatności
    private double cena;
    private String nrSerii; // ("nazwa producenta"+ 4 cyfry)  0000, 0001, ... + 1
    private boolean czyPromocja; // 1- jest promocja, 0- nie ma promocji
    private double wartoscPromocji; // od 0,0-1,0 (mnożnik ceny)
    //private boolean czyDoLodowki;

    public Produkt(String typProduktu, String Producent, int dataWaznosci, double cena, String nrSerii, boolean czyPromocja, double wartoscPromocji) {
        this.typProduktu = typProduktu;
        this.dataWaznosci = dataWaznosci;
        this.cena = cena;
        this.czyPromocja = czyPromocja;
        this.nrSerii = nrSerii;
        this.producent = Producent;
        this.wartoscPromocji = wartoscPromocji;
    }

    public Produkt() {
        this.typProduktu = "a";
        this.dataWaznosci = 3;
        this.cena = 1.0;
        this.czyPromocja = false;
        this.nrSerii = "a";
        this.producent = "a";
        this.wartoscPromocji = 1.0;
    }

    public void zrobPromocje(double wartoscPromocji) { //ustalenie promocji
        czyPromocja = true;
        this.wartoscPromocji = wartoscPromocji;
    }

    public void zmianaDatyWaznosci() {
        this.dataWaznosci--;
    }

    public String toString() {
        return "sklep.Produkt{" +
                "typProduktu='" + typProduktu + '\'' +
                ", producent='" + producent + '\'' +
                ", dataWaznosci=" + dataWaznosci +
                ", cena=" + cena +
                ", nrSerii='" + nrSerii + '\'' +
                ", czyPromocja=" + czyPromocja +
                ", wartoscPromocji=" + wartoscPromocji +
                '}';
    }


    //GETTERY
    public String getTypProduktu() {
        return typProduktu;
    }

    public String getProducent() {
        return producent;
    }

    public int getDataWaznosci() {
        return dataWaznosci;
    }

    public double getCena() {
        return cena;
    }

    public String getnrSerii() {
        return nrSerii;
    }

    public boolean getCzyPromocja() {
        return czyPromocja;
    }

    public double getWartoscPromocji() {
        return wartoscPromocji;
    }

    //SETTERY
    public void setTypProduktu(String typProduktu) {
        this.typProduktu = typProduktu;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public void setDataWaznosci(int dataWaznosci) {
        this.dataWaznosci = dataWaznosci;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setnrSerii(String nrSerii) {
        this.nrSerii = nrSerii;
    }

    public void setCzyPromocja(boolean czyPromocja) {
        this.czyPromocja = czyPromocja;
    }

    public void setWartoscPromocji(double wartoscPromocji) {
        this.wartoscPromocji = wartoscPromocji;
    }
}