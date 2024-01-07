package RelokacjaSezonowa;

import sklep.Regal;
import sklep.Sklep;

//mieso,owoceWarzywa,nabial,napoje-->nabial,mieso,napoje,owoceWarzywa
public class Zima implements RelokacjaSezonowa{
    public void SposobRelokacjiSezonowej(Sklep sklep) {
        Regal[] Regaly = sklep.getRegalyWSklepie();
        Regal[] kopia = Regaly.clone();
        Regaly[0]= kopia[2];
        Regaly[1]=kopia[0];
        Regaly[2]=kopia[3];
        Regaly[3]=kopia[1];
    }
}
