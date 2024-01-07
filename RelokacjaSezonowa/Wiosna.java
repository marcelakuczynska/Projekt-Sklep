package RelokacjaSezonowa;
//owoceWarzywa, napoje, nabial, mięso
import sklep.Regal;
import sklep.Sklep;

import java.io.Serializable;

public class Wiosna implements RelokacjaSezonowa, Serializable {
    @Override                           ////nabial,mieso,napoje,owoceWarzywa
    // rok zaczynamy od zimy wiec na poczatku symulacji regaly muszą być w takiej kolejności
    public void SposobRelokacjiSezonowej(Sklep sklep) {
       Regal[] Regaly = sklep.getRegalyWSklepie();
       Regal[] kopia = Regaly.clone();
       Regaly[0]= kopia[3];
       Regaly[1]=kopia[2];
       Regaly[2]=kopia[0];
       Regaly[3]=kopia[1];
    }
}

