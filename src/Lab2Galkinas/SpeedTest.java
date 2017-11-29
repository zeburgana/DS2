package Lab2Galkinas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import studijosKTU.Ks;
import studijosKTU.Timekeeper;

public class SpeedTest {

    Random random = new Random();  // atsitiktinių generatorius
    final int MAX_NUMBER = 1_000_000;
    int[] numbersCount = {8_000, 16_000, 32_000, 64_000, 128_000};

    public SpeedTest() {

    }

    public List<Integer> generateIntegers(List<Integer> list, int count) {
        for(int i = 0; i < count; i++) {
            list.add(1 + random.nextInt(MAX_NUMBER));
        }
        return list;
    }

    private void demo() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);

        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Šaknies traukimas Math.pow(x, 0.5)");
        Ks.oun("4 - Šaknies traukimas Math.sqrt(x)");
        Ks.oun("5 - lastIndexOf(Object o) su ArrayList<Integer>");
        Ks.oun("6 - lastIndexOf(Object o) su LinkedList<Integer>");

        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        for(int count : numbersCount) {
            test(count);
        }
        systemicTest();
    }

    private void test(int count) {
        long t0 = System.nanoTime();

        // Duomenu generavimas

        List<Integer> list = new ArrayList<>();
        list = generateIntegers(list, count);
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int x : list) {
            linkedList.add(x);
        }

        List<Integer> o = new ArrayList<>();
        o = generateIntegers(o, 1000);

        long t1 = System.nanoTime();

        // Siuksliu surinkimas
        System.gc(); System.gc(); System.gc();
        long t2 = System.nanoTime();

        // Math.pow(x, 0.5)
        for(int x : list) {
            Math.pow(x, 0.5);
        }
        long t3 = System.nanoTime();

        // Math.sqrt(x);
        for(int x : list) {
            Math.sqrt(x);
        }
        long t4 = System.nanoTime();

        // lastIndexOf su ArrayList
        for(int x : o) {
            list.lastIndexOf(x);
        }
        long t5 = System.nanoTime();

        // lastIndexOf su LinkedList
        for(int x : o) {
            linkedList.lastIndexOf(x);
        }
        long t6 = System.nanoTime();

        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", count,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );
    }

    private void systemicTest() {
        Timekeeper tk = new Timekeeper(numbersCount);
        for(int count : numbersCount) {
            List<Integer> list = new ArrayList<>();
            list = generateIntegers(list, count);
            LinkedList<Integer> linkedList = new LinkedList<>();
            for(int x : list) {
                linkedList.add(x);
            }

            List<Integer> o = new ArrayList<>();
            o = generateIntegers(o, 1000);

            tk.start();
            for(int x : list) {
                Math.pow(x, 0.5);
            }
            tk.finish("pow");

            for(int x : list) {
                Math.sqrt(x);
            }
            tk.finish("sqrt");

            for(int x : o) {
                list.lastIndexOf(x);
            }
            tk.finish("Array");

            for(int x : o) {
                linkedList.lastIndexOf(x);
            }
            tk.finish("Linked");
            tk.seriesFinish();
        }
    }

    public static void main(String... args){
        Locale.setDefault(new Locale("LT"));
        new SpeedTest().demo();
    }

}

