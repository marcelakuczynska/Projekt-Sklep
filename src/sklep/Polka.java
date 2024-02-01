package sklep;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Polka implements Serializable,Comparable<Polka> {
    private String typProduktu;
    private String producent;
    private double cenaBazowa;
    private int dataWaznosci; //data waznosci pierwszej serii produktu
    private final int glebokoscPolki = 10;
    private Produkt[] produkty1D;

    public Polka() {
        this.produkty1D = new Produkt[glebokoscPolki];
    }
    public Polka(String typProduktu) {
        this.produkty1D = new Produkt[glebokoscPolki];
        this.typProduktu = typProduktu;
    }

    public Polka(Produkt []produkty1d){
        this.produkty1D=produkty1d;
        this.typProduktu="x";

    }

    public void wczytajWartosciBazowePolki(){
        this.typProduktu = produkty1D[0].getTypProduktu();
        this.producent = produkty1D[0].getProducent();
        this.cenaBazowa = produkty1D[0].getCena();
        this.dataWaznosci = produkty1D[0].getDataWaznosci();
    }
    
    public void dosunProdukty() {
        for(int i = 0; i < produkty1D.length; i++) {
            if(produkty1D[i] != null) {
                for (int j = 0; j < produkty1D.length; j++) {
                    if (produkty1D[j] == null) {
                        produkty1D[j] = produkty1D[i];
                        produkty1D[i] = null;
                    }
                }
            }
        }
    }
    
    //GETTERY
    public String getTypProduktu() {
        return typProduktu;
    }

    public String getProducent() {
        return producent;
    }

    public double getCenaBazowa() {
        return cenaBazowa;
    }
    public int getDataWaznosci() {
        return dataWaznosci;
    }
    public Produkt[] getProdukty1D() {
        return produkty1D;
    }

    //SETTERY
    public void setTypProduktu(String typProduktu) {
        this.typProduktu = typProduktu;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    @Override
    public int compareTo(Polka polka){
        if(getTypProduktu().compareTo(polka.getTypProduktu())==0)
            return getProducent().compareTo(polka.getProducent());

        return getTypProduktu().compareTo(polka.getTypProduktu());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polka polka = (Polka) o;
        return Double.compare(cenaBazowa, polka.cenaBazowa) == 0 && dataWaznosci == polka.dataWaznosci && glebokoscPolki == polka.glebokoscPolki && Objects.equals(typProduktu, polka.typProduktu) && Objects.equals(producent, polka.producent);
    }

//    @Override
//    public String toString() {
//        return "Polka{" +
//                "typProduktu='" + typProduktu + '\'' +
//                ", producent='" + producent + '\'' +
//                ", cenaBazowa=" + cenaBazowa +
//                ", dataWaznosci=" + dataWaznosci +
//                ", glebokoscPolki=" + glebokoscPolki +
//                ", produkty1D=" + Arrays.toString(produkty1D) +
//                '}';
//    }

    public String toString(){
        String res="";
        for(int i=0;i< produkty1D.length;i++)
            res+=(produkty1D[i].toString()+"\n");
        return res;
    }
}