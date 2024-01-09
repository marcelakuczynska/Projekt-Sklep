package sklep;

public class WyswietlanieTymczasowo {

    // to tymczasowo zeby sprawdzic czy dziala w interfejsie graficznym, pozniej to jakos uporzadkuje
    public static String wyswietlStatystykeCalorocznaSklepuString(Sklep sklep) {
        StringBuilder statystykaCaloroczna = new StringBuilder();

        statystykaCaloroczna.append("\n");

        for (int i = 0; i < sklep.getRegalyWSklepie().length; i++) {
            statystykaCaloroczna.append("Numer regalu: ").append(i).append("\n");
            statystykaCaloroczna.append(sklep.getRegalyWSklepie()[i].getStatystykaCaloroczna().toString()).append("\n\n");
        }

        return statystykaCaloroczna.toString();
    }


    public static String wyswietlStatystykeTygodniowaSklepuString(Sklep sklep) {
        StringBuilder result = new StringBuilder();

        result.append("\n");

        for (int i = 0; i < sklep.getRegalyWSklepie().length; i++) {
            if (sklep.getRegalyWSklepie()[i].getStatystykaTygodniowa() != null) {
                result.append("Numer regalu: ").append(i).append("\n");
                result.append(sklep.getRegalyWSklepie()[i].getStatystykaTygodniowa().toString()).append("\n\n");
            } else {
                result.append("Statystyka regalu ").append(i).append(" nie istnieje\n");
            }
        }

        return result.toString();
    }

    public static String wyswietlSklepString(Sklep sklep) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sklep.getRegalyWSklepie().length; i++) {
            result.append("Numer regalu: ").append(i).append("\n");

            for (int k = 0; k < sklep.getRegalyWSklepie()[i].getPolkiWRegale()[0].length; k++) {
                for (int j = 0; j < sklep.getRegalyWSklepie()[i].getPolkiWRegale().length; j++) {
                    result.append("Lokalizacja polki (").append(j).append(",").append(k).append(")\n");

                    for (int l = 0; l < sklep.getRegalyWSklepie()[i].getPolkiWRegale()[j][k].getProdukty1D().length; l++) {
                        result.append(sklep.getRegalyWSklepie()[i].getPolkiWRegale()[j][k].getProdukty1D()[l]).append("\n");
                    }

                    result.append("\n");
                }
            }
        }

        return result.toString();
    }

}
