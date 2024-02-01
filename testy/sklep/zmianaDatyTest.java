package sklep;

import org.junit.jupiter.api.Test;
import sklep.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class zmianaDatyTest {

    @Test
    void zmianaDaty() {
        Produkt produkt1 = new Produkt();
        Produkt produkt2 =new Produkt();
        Produkt produkt3 = new Produkt();

        Produkt[] produkty1d = {
                produkt1, produkt2, produkt3
        };
        Produkt produkt4 = new Produkt();
        Produkt produkt5 = new Produkt();
        Produkt produkt6 = new Produkt();


        produkt4.setCzyPromocja(true);
        produkt4.setDataWaznosci(2);
        produkt4.setWartoscPromocji(0.5);

        Produkt[] produkty1dPoZmianie = {
                produkt4, produkt4, produkt4
        };

        Regal regal= new Regal(produkty1d);
        //System.out.println(regal.getPolkiWRegale()[0][0].getProdukty1D()[0].getDataWaznosci());
        regal.zmianaDaty();
        //System.out.println(regal.getPolkiWRegale()[0][0].getProdukty1D()[0].getDataWaznosci());
        //System.out.println(regal.getPolkiWRegale()[0][0].getProdukty1D()[1].getDataWaznosci());
        for (int i = 0; i < produkty1d.length; i++) {
            if (regal.getPolkiWRegale()[0][0].getProdukty1D()[i]!= null && produkty1dPoZmianie[i] != null) {
                assertEquals(regal.getPolkiWRegale()[0][0].getProdukty1D()[i].getDataWaznosci(), produkty1dPoZmianie[i].getDataWaznosci());
                assertEquals(regal.getPolkiWRegale()[0][0].getProdukty1D()[i].getCzyPromocja(), produkty1dPoZmianie[i].getCzyPromocja());
                assertEquals(regal.getPolkiWRegale()[0][0].getProdukty1D()[i].getWartoscPromocji(), produkty1dPoZmianie[i].getWartoscPromocji());
            }
        }
    }
}
