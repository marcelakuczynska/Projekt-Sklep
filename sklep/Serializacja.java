package sklep;

import java.io.*;

public class Serializacja {

    //SERIALIZACJA
    public void zapiszDoPlikuSklep(Sklep sklep, String nazwaPliku) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            outputStream.writeObject(sklep);
            System.out.println("Stan sklepu zapisany do pliku.\n");
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }

    public Sklep wczytajZPlikuSklep(String nazwaPliku) {
        Sklep sklep = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            sklep = (Sklep) inputStream.readObject();
            System.out.println("Stan sklepu wczytany z pliku.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Błąd podczas wczytywania z pliku: " + e.getMessage());
        }
        return sklep;
    }
}
