package sklep;

import java.io.Serializable;

public class Polka implements Serializable {
    private String typProduktu;
    private String producent;
    private double cenaBazowa;
    private int dataWaznosci; //data waznosci pierwszej serii produktu
    private final int glebokoscPolki = 10;
    private Produkt[] produkty1D;

    public Polka() {
        this.produkty1D = new Produkt[glebokoscPolki];
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
}