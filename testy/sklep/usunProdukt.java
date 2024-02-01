package sklep;

import org.junit.jupiter.api.Test;
import sklep.Polka;
import sklep.Produkt;

import static org.junit.jupiter.api.Assertions.*;

public class usunProdukt {

    @Test
    void usunProdukt(){
        Produkt produkt1= new Produkt();

        Produkt[] produkty1d= {
                produkt1, null, produkt1, null, produkt1
        };

        Produkt[] usuniete= {
                produkt1, null, produkt1, null,null
        };

        Regal regal= new Regal(produkty1d);

        regal.usunProdukt(0,0,4);

        assertArrayEquals(produkty1d, usuniete);
    }

}
