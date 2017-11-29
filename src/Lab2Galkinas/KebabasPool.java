package Lab2Galkinas;

import java.util.Locale;
import studijosKTU.Ks;
import studijosKTU.ListKTU;

public class KebabasPool {

    public KebabasPool() {

    }

    private void demo() {
        ListKTU<Kebabas> kebai = Kebabas.generateKebabas(10);
        for(Kebabas Kebas : kebai) {
            Ks.oun(Kebas);
        }

        ListKTU<Kebabas> found = findByCookTime(kebai, 5);
        //ListKTU<Intern> found = findByNames(kebai, "Liuksas");
        for(Kebabas kebas : found) {
            Ks.oun(kebas);
        }

        //kebai.sortBuble(Kebabas.byCookTime);
        kebai.sortBuble(Kebabas.bygVegetables);
        Ks.oun("Surikiuoti darzoviu kiek:");
        for(Kebabas kebas : kebai) {
            Ks.oun(kebas);
        }
    }

    public ListKTU<Kebabas> findByCookTime(ListKTU<Kebabas> kebai, int minCookTime) {
        ListKTU<Kebabas> found = new ListKTU<>();
        for(Kebabas kebas : kebai) {
            if(kebas.getCookTime() >= minCookTime) {
                found.add(kebas);
            }
        }
        return found;
    }

    public ListKTU<Kebabas> findByNames(ListKTU<Kebabas> kebabai, String name) {
        ListKTU<Kebabas> found = new ListKTU<>();
        for(Kebabas kebas : kebabai) {
            if(kebas.getName().equalsIgnoreCase(name)) {
                found.add(kebas);
            }
        }
        return found;
    }

    public static void main(String... args){
        Locale.setDefault(new Locale("LT"));
        new KebabasPool().demo();

    }
}
