package sklep;

import java.util.Objects;

public class Polka {
    private String typProduktu;
    private String producent;
    private double cena;
    private final int glebokoscPolki = 10; //powinno byc 10 normalnie
    private int dataWaznosci;
    
    private Produkt[] produkty1D;
    
    public Polka() {
        this.produkty1D = new Produkt[glebokoscPolki];
    }

    public void wczytajNazwy(){
        this.typProduktu = produkty1D[0].getTypProduktu();
        this.producent = produkty1D[0].getProducent();
        this.cena = produkty1D[0].getCena();
        this.dataWaznosci=produkty1D[0].getDataWaznosci();
    }
    //GETTERY
    public String getTypProduktu() {
        return typProduktu;
    }

    public String getProducent() {
        return producent;
    }

    public double getCena() {
        return cena;
    }

    public Produkt[] getProdukty1D() {
        return produkty1D;
    }
    
    public int getDataWaznosci() {
    	return dataWaznosci;
    }
    
    //SETTERY
    public void setTypProduktu(String typProduktu) {
        this.typProduktu = typProduktu;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setProdukty1D(Produkt[] produkty1D) {
        this.produkty1D = produkty1D;
        this.typProduktu = produkty1D[0].getTypProduktu();
        this.producent = produkty1D[0].getProducent();
        this.cena = produkty1D[0].getCena();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polka polka = (Polka) o;
        return cena == polka.cena && Objects.equals(typProduktu, polka.typProduktu) && Objects.equals(producent, polka.producent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typProduktu, producent, cena);
    }
}
