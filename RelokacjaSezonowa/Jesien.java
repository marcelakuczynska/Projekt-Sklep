package RelokacjaSezonowa;

import sklep.Regal;
import sklep.Sklep;

//napoje,owoceWarzywa,nabial,mieso-->mieso,owoceWarzywa,nabial,napoje
public class Jesien implements RelokacjaSezonowa{
    public void SposobRelokacjiSezonowej(Sklep sklep) {
        Regal[] Regaly = sklep.getRegalyWSklepie();
        Regal[] kopia = Regaly.clone();
        Regaly[0]= kopia[3];
        Regaly[3]=kopia[0];
    }
}
