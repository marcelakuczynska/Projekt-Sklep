package programglowny;

import sklep.Sklep;

public class Main {
    public static void main(String[] args) {
        Sklep test = new Sklep();

        test.wyswietlSklep();
        test.uplywCzasu();
        test.uplywCzasu();

        test.wyswietlStatystykeTygodniowaSklepu();
        test.wyswietlStatystykeCalorocznaSklepu();

        /* //TEST SERIALIZACJI

        test.zapiszDoPliku();
        test.zrelokujSezonowo();
        test.wczytajZPliku().wyswietlSklep();

        test.wyswietlStatystykeCalorocznaSklepu();

         */
    }
}