package programglowny;

import sklep.Sklep;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Sklep test = new Sklep();

        test.wyswietlSklep();
        test.uplywCzasu();
        test.uplywCzasu();


        test.wyswietlStatystykeTygodniowaSklepu();
        test.wyswietlStatystykeCalorocznaSklepu();


        /* TEST SERIALIZACJI

        test.zapiszDoPliku();
        test.ZrelokujSezonowo(68, test);
        test.wczytajZPliku().wyswietlSklep();

         */
    }
}