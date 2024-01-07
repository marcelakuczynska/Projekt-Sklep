package RelokacjaSezonowa;

import sklep.Regal;
import sklep.Sklep;

import java.io.Serializable;

//owoceWarzywa, napoje, nabial, mięso-->napoje,owoceWarzywa,nabial,mieso
public class Lato implements RelokacjaSezonowa, Serializable {
    public void SposobRelokacjiSezonowej(Sklep sklep) {
        Regal[] Regaly = sklep.getRegalyWSklepie();
        Regal[] kopia = Regaly.clone();
        Regaly[0] = kopia[1];
        Regaly[1] = kopia[0];
    }
}