package programglowny;

import sklep.Sklep;
import sklep.Serializacja;

public class Main {
    public static void main(String[] args) {
        Sklep test = new Sklep();
        Serializacja ser = new Serializacja();

        test.wyswietlSklep();
        test.uplywCzasu();
        test.uplywCzasu();

        test.wyswietlStatystykeTygodniowaSklepu();
        test.wyswietlStatystykeCalorocznaSklepu();



        //TEST SERIALIZACJI

        test.zapiszDoPliku();
        test.wczytajZPliku();
        test.uplywCzasu();
        test.zapiszDoPliku();
        test.wczytajZPliku().wyswietlSklep();

        test.wyswietlStatystykeCalorocznaSklepu();


    }
}