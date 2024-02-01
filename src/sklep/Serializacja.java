package sklep;

import java.io.*;

public class Serializacja {

    // SERIALIZACJA
    public <T extends Serializable> void zapiszDoPlikuSklep(T obj, String nazwaPliku) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            outputStream.writeObject(obj);
            System.out.println("Stan zapisany do pliku.\n");
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }

    public <T extends Serializable> T wczytajZPlikuSklep(String nazwaPliku) {
        T obj = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            obj = (T) inputStream.readObject();
            System.out.println("Stan wczytany z pliku.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Błąd podczas wczytywania z pliku: " + e.getMessage());
        }
        return obj;
    }
}