package sklep;

import org.junit.jupiter.api.Test;
import sklep.Polka;
import sklep.Produkt;

import static org.junit.jupiter.api.Assertions.*;

public class dosunProduktTest {

    @Test
    void dosunProdukty(){
        Produkt produkt1= new Produkt();

        Produkt[] produkty1d= {
                produkt1, null, produkt1, null, produkt1
        };

        Produkt[] dosuniete= {
                produkt1, produkt1, produkt1, null,null
        };

        Polka polka=new Polka(produkty1d);

        polka.dosunProdukty();

        assertArrayEquals(produkty1d, dosuniete);
    }

}
