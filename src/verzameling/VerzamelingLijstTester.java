package verzameling;

import fotos.Comparison.NaamComparator;
import fotos.Foto;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by charles korthout on 2/5/2017.
 */
public class VerzamelingLijstTester {

    private int nameLength;
    private int minWidth;
    private int maxWidth;
    private int minLength;
    private int maxLength;
    private Random random;

    /**
     * Constructor
     * @param namelength The length of the randomly generated names
     * @param length The average length
     * @param width The average width
     * @param margin the upper and lower bounds for both length and width
     */
    public VerzamelingLijstTester(int namelength, int length, int width, int margin) {
        random = new Random();
        this.nameLength = namelength;
        minWidth = width - margin;
        maxWidth = width + margin;
        minLength = length - margin;
        maxLength = length + margin;
    }

    /**
     * Generates a random name with a predefined length
     * @return a random name with he specified number of characters
     */
    private String randomName() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nameLength; i++) {
            char c = (char)(random.nextInt((int)(Character.MAX_VALUE)));
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Creates a random foto based on the settings in the constructor
     * @return a random foto
     */
    private Foto createRandomFoto() {
        int width = ThreadLocalRandom.current().nextInt(minWidth, maxWidth + 1);
        int length = ThreadLocalRandom.current().nextInt(minLength, maxLength + 1);
        String name = randomName();
        return new Foto(width, length, name);
    }

    /**
     * Creates a list of indexes to retrieve randomly values from a list
     * @param size the size of the collection to randomly pick values from
     * @return a shuffled list of indexes;
     */
    private List<Integer> randomPicks(int size) {
        ArrayList<Integer> list = new ArrayList();
        for (int index = 0; index < size - 1; index++) {
            list.add(index);
        }
        Collections.shuffle(list,random);
        return list;
    }

    /**
     * Convert the elapsedTime as nano seconds to seconds using TimeUnit
     * @param elapsedTime The elapsed time in nano seconds
     * @return the elapsed time in seconds.
     */
    private double asSeconds(long elapsedTime) {
        return TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
    }

    /**
     * Perform some benchmarking in the IFotoVerzameling collection
     * Tests are being done for:
     * - insert
     * - sort
     * - get
     * - remove
     * @param size The size of the list
     * @param fotos the collection implementation
     */
    public void benchmark(int size, IFotoVerzameling fotos) {
        System.out.println("Adding " + size + " elements to the collection");
        long startTime = System.nanoTime();
        fotos.add(this.createRandomFoto());
        long elapsedTimeNanos = System.nanoTime()- startTime;
        System.out.println("Adding " + size + " elements to the collection took " + elapsedTimeNanos + " nano seconds");
        System.out.println("Sorting the collection");
        startTime = System.nanoTime();
        Comparator comp = new NaamComparator();
        fotos.sort(comp);
        elapsedTimeNanos = System.nanoTime() - startTime;
        System.out.println("Sorting the elements of the collection took " + elapsedTimeNanos + " nano seconds");
        List<Integer> randomPicks = this.randomPicks(fotos.getSize());
        System.out.println("Randomly picking " + size + " elements from the collection");
        startTime = System.nanoTime();
        randomPicks.forEach(x  -> {
                    try {
                        fotos.get(x);
                    } catch (VerzamelingException ve) {
                    }
                });
        elapsedTimeNanos = System.nanoTime() - startTime;
        System.out.println("Randomly picking " + size + " elements from the collection took " + elapsedTimeNanos + " nano seconds");
        List<Foto> lijst = fotos.getReadOnlyList();
        System.out.println("Deleting  " + size + " elements from the collection");
        startTime = System.nanoTime();
        lijst.forEach(x  -> fotos.remove(x));
        elapsedTimeNanos = System.nanoTime() - startTime;
        System.out.println("Deleting " + size + " elements from the collection took " + elapsedTimeNanos + " nano seconds");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VerzamelingLijstTester tester = new VerzamelingLijstTester(20, 200,200, 20);
        VerzamelingLijst lijst = new VerzamelingLijst();
        tester.benchmark(Integer.MAX_VALUE, lijst);
    }
}
