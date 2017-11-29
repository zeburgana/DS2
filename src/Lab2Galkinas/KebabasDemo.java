package Lab2Galkinas;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import studijosKTU.Ks;
import studijosKTU.ListKTU;

public class KebabasDemo {

    public KebabasDemo() {

    }

    public void demo() {
//        test01();
        test02();
//        test03();
    }

    private void test01() {
        Kebabas in1 = new Kebabas("MazasMix", 150, 150, 0, 0, 50,true,5);
        Kebabas in2 = new Kebabas("Liuksas", 250, 200, 0, 0, 100,true,7);
        Kebabas in3 = new Kebabas("Lekstutis", 150, 150, 0, 0, 50,false,5);
        Kebabas in4 = new Kebabas();
        Kebabas in5 = new Kebabas();
        Kebabas in6 = new Kebabas();

        in4.parse("Lekstutis 150 100 0 50 100 7 false");
        in5.parse("Falafeliuks 0 150 200 150 100 5 true");
        in6.parse("Liuksas 150 0 200 150 100 5 true");

        Ks.oun(in1);
        Ks.oun(in2);
        Ks.oun(in3);
        Ks.oun(in4);
        Ks.oun(in5);
        Ks.oun(in6);

        int sum = in1.getgMeat() + in2.getgMeat() + in3.getgMeat()
                + in4.getgMeat() + in5.getgMeat() + in6.getgMeat();
        Ks.oun("Visų Kebabu mesos kiekis: " + sum);

    }

    private void test02() {
        ListKTU<Kebabas> kebai = Kebabas.generateKebabas(6);
        for(Kebabas Kebabas : kebai) {
            Ks.oun(Kebabas);
        }

        Map<String, Integer> frequency = new HashMap<>();
        int max = 0;
        for(Kebabas Kebas : kebai) {
            if(frequency.get(Kebas.getName()) == null) {
                frequency.put(Kebas.getName(), 1);
            }
            else {
                frequency.put(Kebas.getName(), frequency.get(Kebas.getName()) + 1);
                max = frequency.get(Kebas.getName()) > max ? frequency.get(Kebas.getName()) : max;
            }
        }

        ListKTU<String> mostPopularNames = new ListKTU<>();
        for(String name : frequency.keySet()) {
            if(frequency.get(name) == max) {
                mostPopularNames.add(name);
            }
        }

        if(mostPopularNames.size() == 1) {
            Ks.oun("Populiariausias kebabo pavadinimas: " + mostPopularNames.get(0));
            kebai.removeWithName(kebai.get(3));
            for(Kebabas Kebabas : kebai) {
                Ks.oun(Kebabas);
            }
        }
        else {
            Ks.oun("Populiariausi kebabu pavadinimai: ");
            for(String name : mostPopularNames) {
                Ks.oun(name);
            }
        }

    }

    private void test03() {
        ListKTU<Kebabas> Kebai = Kebabas.generateKebabas(10);
        for(Kebabas Kebas : Kebai) {
            Ks.oun(Kebas);
        }

        int count = 0;
        for(Kebabas Kebabas : Kebai) {
            if(Kebabas.getgFalafels() == 0) {
                count++;
            }
        }
        Ks.oun("Kebabu kiekis, kurie neturi falafeliu " + count);
    }

    public static void main(String... args) {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)

        Locale.setDefault(new Locale("LT"));
        new KebabasDemo().demo();
    }

}