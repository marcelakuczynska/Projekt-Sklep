package sklep;

public class Dostawa {
    private Object[][] dostawa = new Object[60][6];

    public Dostawa() {
        Object [][] dostawa = {
                {"Chleb", "Marfex", 3, 3.50, "Marfex0000", 10},
                {"Chleb", "Złoty Kłos", 4, 4.00, "Złoty Kłos0000", 10},
                {"Chleb", "Piekarnia Tradycyjna", 5, 3.80, "Piekarnia Tradycyjna0000", 10},
                {"Mleko", "Łaciate", 4, 2.50, "Łaciate0000", 10},
                {"Mleko", "Bakoma", 5, 2.80, "Bakoma0000", 10},
                {"Mleko", "Smak Natury", 3, 2.60, "Smak Natury0000", 10},
                {"Jogurt naturalny", "Danone", 4, 3.20, "Danone0000", 10},
                {"Jogurt naturalny", "Bakoma", 3, 2.90, "Bakoma0000", 10},
                {"Jogurt naturalny", "Rolnik", 4, 3.00, "Rolnik0000", 10},
                {"Ser żółty", "Hochland", 5, 5.50, "Hochland0000", 10},
                {"Ser żółty", "Turek", 3, 4.80, "Turek0000", 10},
                {"Ser żółty", "Krasnystaw", 5, 5.00, "Krasnystaw0000", 10},
                {"Jaja(10szt)", "Koko", 3, 10.2, "Koko0000", 10},
                {"Jaja(10szt)", "Kurka Wodna", 4, 9.1, "Kurka Wodna0000", 10},
                {"Jaja(10szt)", "Zielonogórskie", 5, 9.6, "Zielonogórskie0000", 10},
                {"Makaron", "Barilla", 4, 4.50, "Barilla0000", 10},
                {"Makaron", "De Cecco", 5, 4.80, "De Cecco0000", 10},
                {"Makaron", "Włoski Smak", 3, 4.20, "Włoski Smak0000", 10},
                {"Ryż", "Uncle Ben's", 4, 5.80, "Uncle Ben's0000", 10},
                {"Ryż", "Arborio", 3, 6.20, "Arborio0000", 10},
                {"Ryż", "Jasmin", 5, 5.50, "Jasmin0000", 10},
                {"Oliwa z oliwek", "Złota Kropka", 3, 12.00, "Złota Kropka0000", 10},
                {"Oliwa z oliwek", "Italia Bella", 5, 10.50, "Italia Bella0000", 10},
                {"Oliwa z oliwek", "Tradycja", 4, 11.80, "Tradycja0000", 10},
                {"Cukier", "Królewski", 3, 2.20, "Królewski0000", 10},
                {"Cukier", "Polski", 5, 2.50, "Polski0000", 10},
                {"Cukier", "Słodki", 4, 2.80, "Słodki0000", 10},
                {"Kawa ziarnista", "Lavazza", 5, 22.00, "Lavazza0000", 10},
                {"Kawa ziarnista", "Julius Meinl", 3, 20.50, "Julius Meinl0000", 10},
                {"Kawa ziarnista", "Pellini", 4, 21.80, "Pellini0000", 10},
                {"Herbata", "Twinings", 5, 8.00, "Twinings0000", 10},
                {"Herbata", "Ahmad Tea", 3, 7.50, "Ahmad Tea0000", 10},
                {"Herbata", "Loyd", 4, 8.20, "Loyd0000", 10},
                {"Miód", "Puszcza Białowieska", 3, 15.00, "Puszcza Białowieska0000", 10},
                {"Miód", "Akacjowy Raj", 5, 13.50, "Akacjowy Raj0000", 10},
                {"Miód", "Pszczeli Smak", 4, 14.80, "Pszczeli Smak0000", 10},
                {"Dżem", "St. Dalfour", 4, 9.50, "St. Dalfour0000", 10},
                {"Dżem", "Bonne Maman", 5, 8.80, "Bonne Maman0000", 10},
                {"Dżem", "Krakus", 3, 9.20, "Krakus0000", 10},
                {"Ketchup", "Heinz", 4, 7.00, "Heinz0000", 10},
                {"Ketchup", "Knorr", 3, 6.50, "Knorr0000", 10},
                {"Ketchup", "Pudliszki", 5, 6.80, "Pudliszki0000", 10},
                {"Sos pomidorowy", "Barilla", 5, 5.50, "Barilla0000", 10},
                {"Sos pomidorowy", "Mutti", 3, 5.80, "Mutti0000", 10},
                {"Sos pomidorowy", "Develey", 4, 5.20, "Develey0000", 10},
                {"Majonez", "Hellmann's", 3, 7.50, "Hellmann's0000", 10},
                {"Majonez", "Kielecki", 4, 6.80, "Kielecki0000", 10},
                {"Majonez", "Winiary", 5, 7.20, "Winiary0000", 10},
                {"Konserwy tuńczyka", "Rio Mare", 5, 10.00, "Rio Mare0000", 10},
                {"Konserwy tuńczyka", "Saupiquet", 4, 9.50, "Saupiquet0000", 10},
                {"Konserwy tuńczyka", "Pudliszki", 3, 9.80, "Pudliszki0000", 10},
                {"Kiełbasa(opakowanie)", "Krakus", 4, 13.00, "Krakus0000", 10},
                {"Kiełbasa(opakowanie)", "Kabanosy Kujawskie", 5, 15.00, "Kabanosy Kujawskie0000", 10},
                {"Kiełbasa(opakowanie)", "Wiejska Tradycja", 3, 14.00, "Wiejska Tradycja0000", 10},
                {"Mąka pszenna", "Młyny Polskie", 5, 3.80, "Młyny Polskie0000", 10},
                {"Mąka pszenna", "Kupiec", 4, 4.20, "Kupiec0000", 10},
                {"Mąka pszenna", "Szymanowski", 3, 4.50, "Szymanowski0000", 10},
                {"Mąka żytnia", "Kruszwica", 5, 3.50, "Kruszwica0000", 10},
                {"Mąka żytnia", "Kupiec", 4, 3.80, "Kupiec0000", 10},
                {"Mąka żytnia", "Młyny Polskie", 3, 4.00, "Młyny Polskie0000", 10},
                {"Mąka kukurydziana", "Mać Pariadka", 5, 5.00, "Mać Pariadka0000", 10},
                {"Mąka kukurydziana", "Hortex", 4, 4.80, "Hortex0000", 10},
                {"Mąka kukurydziana", "Biedronka", 3, 4.50, "Biedronka0000", 10},
                {"Woda mineralna", "Żywiec Zdrój", 4, 1.80, "Żywiec Zdrój0000", 10},
                {"Woda mineralna", "Cisowianka", 5, 1.50, "Cisowianka0000", 10},
                {"Woda mineralna", "Nałęczowianka", 3, 1.60, "Nałęczowianka0000", 10}
        };



        this.dostawa = dostawa;
    }

    public Object[][] getDostawa(){
        return dostawa;
    }
}
