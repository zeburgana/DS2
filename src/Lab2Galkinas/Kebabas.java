package Lab2Galkinas;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import studijosKTU.KTUable;
import studijosKTU.Ks;
import studijosKTU.ListKTU;

/**
 *
 * @author Dominykas
 */
public class Kebabas implements KTUable<Kebabas> {

    final private static int MIN_g_Vegetables = 100;
    final private static int MIN_CookTime = 3;

    private static Random random = new Random();

    private String name;
    private int gMeat;
    private int gVegetables;
    private int gFalafels;
    private int gChips;
    private int cookTime;
    private int gSauce; //rawSauce, no ketchup
    private boolean wrap;

    public Kebabas() {

    }
    public Kebabas(String name){
        this.name = name;
    }

    public Kebabas(String name, int gMeat, int gVegetables, int gFalafels, int gChips, int gSauce, boolean wrap, int cookTime) {
        this.name = name;
        this.gMeat = gMeat;
        this.gVegetables = gVegetables;
        this.gFalafels = gFalafels;
        this.gChips = gChips;
        this.gSauce = gSauce;
        this.cookTime = cookTime;
        this.wrap = wrap;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getgMeat() {
        return gMeat;
    }
    public void setgMeat(int gMeat) {
        this.gMeat = gMeat;
    }

    public int getgVegetables() {
        return gVegetables;
    }
    public void setgVegetables(int gVegetables) {
        this.gVegetables = gVegetables;
    }

    public int getgFalafels() {
        return gFalafels;
    }
    public void setgFalafels(int gFalafels) {
        this.gFalafels = gFalafels;
    }

    public int getgChips() {
        return gChips;
    }
    public void setgChips(int gChips) {
        this.gChips = gChips;
    }

    public int getCookTime(){
        return cookTime;
    }
    public void setCookTime(int cookTime){
        this.cookTime = cookTime;
    }

    public int getgSauce(){
        return gSauce;
    }
    public void setgSauce(int gSauce){
        this.gSauce = gSauce;
    }

    public boolean getWrap(){
        return wrap;
    }
    public void setWrap(boolean wrap){
        this.wrap = wrap;
    }

    public String toString() {
        return String.format("%-24s %4d %4d %4d %4d %4d %4d %4d %5s %s",
                name, gMeat, gChips, gVegetables, gFalafels, gChips, cookTime, gSauce, wrap, validate());
    }

    @Override
    public Kebabas create(String dataString) {
        Kebabas Kebabas = new Kebabas();
        Kebabas.parse(dataString);
        return Kebabas;
    }

    @Override
    public String validate() {
        String error = "";
        if (gVegetables < MIN_g_Vegetables)
            error = "idek daugiau darzoviu, reikia bent [" +MIN_g_Vegetables+ "]";
        if (cookTime < MIN_CookTime)
            error += " Per mazai kepi, pakepk bent [" + MIN_CookTime + "]";
        return error;
    }

    @Override
    public void parse(String dataString) {
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);
            name = ed.next();
            gMeat = ed.nextInt();
            gVegetables = ed.nextInt();
            gFalafels = ed.nextInt();
            gChips = ed.nextInt();
            gSauce = ed.nextInt();
            cookTime = ed.nextInt();
            wrap = ed.nextBoolean();
        } catch (InputMismatchException  e) {
            Ks.ern("Blogas duomenų formatas apie Kebaba -> " + dataString);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie Kebaba -> " + dataString);
        }
    }

    @Override
    public int compareTo(Kebabas e) {
        if(gMeat > e.getgMeat()) return 1;
        if(gMeat < e.getgMeat()) return -1;
        return 0;
    }

    public final static Comparator bygVegetables = new Comparator() {
        // sarankiškai priderinkite prie generic interfeiso ir Lambda funkcijų
        @Override
        public int compare(Object o1, Object o2) {
            Kebabas i1 = (Kebabas) o1;
            Kebabas i2 = (Kebabas) o2;

            // by gVegetables, if they have the same amount of gVegetables, then by gChips
            if(i1.getgVegetables() < i2.getgVegetables()) return 1;
            if(i1.getgVegetables() > i2.getgVegetables()) return -1;
            if(i1.getgChips() < i2.getgChips()) return 1;
            if(i1.getgChips() > i2.getgChips()) return -1;
            return 0;
        }
    };

    public final static Comparator byCookTime = new Comparator() {
        // savarankiškai priderinkite prie generic interfeiso ir Lambda funkcijų
        @Override
        public int compare(Object o1, Object o2) {
            Kebabas i1 = (Kebabas) o1;
            Kebabas i2 = (Kebabas) o2;

            // by cookTime (from youngest to oldest)
            if(i1.getCookTime() < i2.getCookTime()) return 1;
            if(i1.getCookTime() > i2.getCookTime()) return -1;
            return 0;
        }
    };


    public boolean equals(Kebabas kebas) {
        return this.getName().equals(kebas.getName());
    }

    public static ListKTU<Kebabas> generateKebabas(int count) {
        String[] names =
                {
                        "Liuksas", "Skanukas", "Salocius",
                        "Meksikonas", "Niujerkeris", "Azijietiskas", "Bulvius"
                };
        ListKTU<Kebabas> Kebas = new ListKTU<>();
        while(count-- > 0) {
            Kebabas Kebabas = new Kebabas();
            Kebabas.setName(names[random.nextInt(names.length)]);
            Kebabas.setgMeat(random.nextInt(300));
            Kebabas.setgFalafels(random.nextInt(150));
            Kebabas.setgChips(random.nextInt(100));
            Kebabas.setgVegetables(random.nextInt(200));
            Kebabas.setgSauce(random.nextInt(50));
            Kebabas.setCookTime(random.nextInt(20));
            Kebabas.setWrap(random.nextBoolean());
            Kebas.add(Kebabas);
        }
        return Kebas;
    }

}