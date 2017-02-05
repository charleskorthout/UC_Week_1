# UC_Week_1
Assignments week 1 UC

## Opdracht 1

Doel: Kennismaken met een verzameling, het begrip interface en deze kunnen toepassen, deze verzameling kunnen sorteren door gebruik te maken van zijn interface en Comparator klassen. Tevens herstart programmeren in JAVA.

### Inleiding.
Er moet een applicatie gemaakt worden waarmee een foto verzameling beheerd kan worden. Om de applicatie niet te ingewikkeld te maken gebruiken we een klasse Foto die als attributen heeft een titel, hoogte en breedte.
Deze foto’s worden opgeslagen in een klasse VerzamelingLijst.java, deze klasse gaan jullie ook maken.
De foto’s in de verzameling moeten gesorteerd kunnen worden op naam, hoogte of breedte. In de user interface is hier al rekening mee gehouden.

Er is al een Netbeans project aangemaakt dat je kunt vinden op sharepoint.
Dit project bevat een drietal packages, namelijk:
* viewer, bevat de user interface, hier hoeft niets aan veranderd te worden. 
* verzameling, bevat de klassen die te maken hebben met de verzameling die jullie gaan maken, 
* fotos, bevat de klassen die te maken hebben met de objecten die opgeslagen gaan worden in de verzameling en zichtbaar gemaakt in de viewer klassen, dus onder andere de klasse Foto.java.

In de package verzameling zit de interface IFotoVerzameling.java, deze interface mag niet veranderd worden. De klasse FotoFrame.java maakt namelijk gebruik van deze interface, dus wordt IFotoVerzameling.java gewijzigd dan kan het zijn dat de applicatie niet meer werkt.

### Opdracht 1.1

Uitleveren van code zonder tests (zoals vaak soms gebeurt bij SWE), is natuurlijk niet professioneel (en heet in goed engels ‘programming by coincidence’[1]). Beter is om gedrag van je code aan te tonen via reproduceerbare tests.
Maak unittests om het gedrag van IFotoVerzameling aan te tonen.

De invulling van de unit tests is opgenomen in de broncode. Sommige testen zijn uitgebreid. hieronder staat een fragment van de testen.

``` java
protected boolean isSorted (List<Foto> fotos, Comparator comp)
    {
      for(int i = 0 ; i < fotos.size() -1; i++) {
        if( (comp.compare(fotos.get(i),fotos.get(i+1)) > 0 )) return false;
      }
      return true;
    }
    
/**
     * Test of sort method, of class IFotoVerzameling.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        Comparator comp = new NaamComparator();
        IFotoVerzameling instance = new VerzamelingLijst();
        instance.sort(comp);
        Boolean expResult = true; // empty list is sorted
        Boolean result = isSorted(instance.getReadOnlyList(),comp);
        assertEquals(expResult,result);
        instance.add(foto1); // The list with a single record is sorted
        result = isSorted(instance.getReadOnlyList(),comp);
        assertEquals(expResult,result);
        instance.add(foto2); // The list with two records is sorted
        result = isSorted(instance.getReadOnlyList(),comp);
        assertEquals(expResult,result);
    }    
```

## Opdracht 1.2
Maak in de package verzameling een klasse VerzamelingLijst.java die de interface IFotoVerzameling implementeert.

Van deze methoden moet je dus zelf een implementatie maken: De klasse implementeert de verzameling als een lijst, maak hierbij gebruik van een javaFX observableList implementatie (of een andere keus indien je dat beter vindt).  De verzameling moet nog gesorteerd kunnen worden. 
Bedenk op dit moment wanneer gebruik van een Comparable of Comparator nodig is in je applicatie. 
Je kunt in eerste instantie besluiten het sorteren later in te bouwen, dit vergemakkelijkt het testen in het begin. 
Voeg eventuele benodigde extra methoden zelf toe aan de klasse, en zorg voor specificatie en tests ervoor.

Voor de vergelijking heb ik een interface gemaakt, 

``` java
public interface ICompareStrategy<T> {
    /**
     * Compare 2 objects
`     * @param t1 The first object
     * @param t2 The second object
     * @return The compare result
     *          If t1 is equal to t2 then 0 is returned.
    *           If t1 is less than t2 then -1 is returned.
    *           If t1 is greater than t2 then 1 is returned.     
     */
    int compare(T t1, T t2);    
}
```

Deze interface wordt hierna als parameter opgenomen in de constructor van  FotoComparator

``` java
/**
 *
 * @author Charles Korthout
 * 
 * Compares photos based on the desired strategy
 */
public abstract class FotoComparator<T extends Foto> implements Comparator<T>{

    private ICompareStrategy strategy;
    public FotoComparator(ICompareStrategy strategy) {
        super();
        this.strategy = strategy;
    }
    
    /**
     * Exercise 1.4: compares 2 photos based on the provided strategy
     * @param t1 The first photo
     * @param t2 The second photo
     * @return The compare of the two names
     */
    @Override
    public int compare(T t1, T t2) {
        return strategy.compare(t1, t2);
    }
}
```

De individuele vergelijkingen overerven deze abstracte class en vullen de juiste strategy in

``` java
/**
 *
 * @author Charles Korthout
 */
public class NaamComparator <T extends Foto> extends FotoComparator<T> {
    
    public NaamComparator() {
        super(new FotoNaamCompare());
    }
}

```
De verschillende vergelijkingen zijn nu als speciale classes te maken.

``` java
public class FotoNaamCompare<T extends Foto> implements ICompareStrategy<T> {

    /**
     * Compares the photos based on name first. If the names are the same, the comparison is made
     * on height first and afterwards on width
     * @param t1 The first photo
     * @param t2 The second photo
     * @return the result of the the compare
     *          If t1 is equal to t2 then 0 is returned.
     *          If t1 is less than t2 then -1 is returned.
     *          If t1 is greater than t2 then 1 is returned.     
     */
    @Override
    public int compare(T t1, T t2) {
        if (CompareTools.isNull(t1,t2)) return CompareTools.nullsFirst(t1,t2);
        if (t1.getName() == t2.getName()) {
            if (t1.getHeight()== t2.getHeight()) 
                return Integer.compare(t1.getWidth(),t2.getWidth());
            else return Integer.compare(t1.getHeight(), t2.getHeight());
          }
          else return t1.getName().compareTo(t2.getName());
     }   
    
}
``` 

Alle vergelijkingen zijn in een aparte folder opgenomen; fotos.Comparison. Standaard wordt in de Verzamelinglijst constructor de naam vergelijking geladen.

``` java
/**
     * Default constructor
     */
    public VerzamelingLijst() {
        comp = new NaamComparator<Foto>();
        lijst = new ArrayList();        
    }
``` 

## Opdracht 1.3
De klasse Foto.java is al gegeven, deze moet nog wel aangepast worden. 
Foto objecten moeten vergeleken kunnen worden: Foto’s worden eerst op hoogte vergeleken, en pas als die gelijk is, op breedte. Je fotos dienen automatisch gesorteerd te worden op de natuurlijke ordening als je de foto's inleest in je applicatie. 

> Voor ordering zie opmerkingen 1.2. Voor de foto zijn alleen alle getters toegevoegd en de toString methode.

## Opdracht 1.4
In de fotos package moeten ook nog een drietal comparator klassen gemaakt worden.
* De klasse BreedteComparator.java die vergelijkt op breedte, daarna op hoogte, daarna op naam.
* De klasse HoogteComparator.java die vergelijkt op hoogte, daarna op breedte, daarna op naam.
* De klasse NaamComparator.java die vergelijkt op naam, daarna op breedte, daarna op hoogte.
De hierboven genoemde namen moeten exact aangehouden worden omdat die in FotoFrame.java gebruikt worden.
Je kunt de methode in deze klassen ook eventueel implementeren met een “return 0”, hierdoor kun je nog niet sorteren maar het testen van het programma wordt wel eenvoudiger. In latere instantie ga je deze methoden correct invullen en testen.

Stel jezelf na implementeren van 1 comparator de vraag: “hoe komt mijn code eruit te zien als er niet op 3, maar op 10 attributen gesorteerd moet worden? (bijv. hoogte, breedte, naam, kleurdiepte, pixelgrootte, gps coordinaten, meters boven zeeniveau, naam fotograaf, facebook account etc.)”. Indien dit een geneste structuur met IF statements wordt, is deze code niet beheerbaar.
Pas je code aan, zodat een dergelijke uitbreiding in de toekomst gemakkelijk te realiseren is (en je dus beheerbare code oplevert).

> Zie opdracht 1.2


## Opdracht 1.5
Exception handling: check welke exceptions er op kunnen treden in je implementatie van IVerzameling. Dit is afhankelijk van welke JAVA collection je gebruikt, dus lees de javadoc van je gebruikte collectie!
Pas je specificatie, testen en code aan, zodat alle relevante exceptions afgevangen worden.
Voor nu een exception handling is toegevoegd voor de get function “out of bounds. 
Bij de compare afhandeling is extra functionaliteit toegevoegd om op nulls te kunnen checken. 

``` java
/**
 *
 * @author Charles Korthout
 */
public class CompareTools {
/**
     * Check if any of the parameters is null
     * @param t1 The first parameters
     * @param t2 The second parameter
     * @return true if any of the parameters is null, false otherwise
     */
    public static boolean isNull(Object t1, Object t2) {
        return (t1 == null || t2 == null);
    }
    
    /**
     * Compares the two parameters, if both are null they are equal and 0 is returned
     * If the first is null -1 is returned. Otherwise 1 is returned
r     * @param t1 The first parameter
     * @param t2 The second parameter
     * @return the compare value, see above
     */
    public static int nullsFirst(Object t1, Object t2)  {
        if (t1 == null) 
            if (t2 == null) return 0;
            else return 1;
        else return -1;
    }
e} 

``` 

## Opdracht 1.6
Functionele test van de applicatie. 
Als alle unit tests goed verlopen, is het tijd voor een functionele test via de GUI:
* Door op de knop “Inlezen” te drukken moeten er een aantal foto objecten aangemaakt worden. De foto objecten moeten zichtbaar worden in de lijst. Deze dienen gesorteerd te zijn via de natuurlijke ordening van Foto. Indien voor het inlezen al een keuze voor een sorteermethode gemaakt is, dient uiteraard volgens deze sorteermethode gesorteerd te worden.

* Door een item te selecteren in de lijst moeten de eigenschappen van het geselecteerde object onder in beeld getoond worden. 
i* Met de knoppen hoogte, breedte en naam wordt het sorteren getest, dus de werking van de methode sort(Comparator) en de drie Comparator klassen.
* Met de knop nieuwe foto wordt een nieuwe foto toegevoegd, dus is test van de aanroep van methode add in VerzamelingLijst. Voeg meerdere foto’s toe om het sorteren te controleren. De getoonde lijst dient altijd gesorteerd te zijn! De sortering is afhankelijk van welke sorteermethode in de GUI gekozen is.
   
## Opdracht 1.7 
Het expliciet sorteren door steeds de sort methode van IFotoVerzameling aan te roepen is niet efficient. Het aanroepen van Collections.sort is O(N*log(N)) waarbij N het aantal elementen in je collectie is.

Wat is dan de orde van het toevoegen van 1 item via IFotoVerzameling.add() ?

> Het toevoegen tot de lijst is O(N), de sorteerroutine is O(N*log(N)) dus de totale orde is O(N^2*log(N))

hWat is dan de orde van het toevoegen van N items (fotos) aan je IFotoVerzameling?

> Het toevoegen tot de lijst is O(N), de sorteerroutine is O(N*log(N)) dus de totale orde is O(N^2*log(N)). 
Het toevoegen van N elementen is dan O(N^3*log(N))

## Opdracht 1.8 (optioneel) 
Wat zou je moeten aanpassen aan je implementatie van IFotoVerzameling, zodat je collectie altijd gesorteerd is? 

Een mogelijkheid kan zijn om een gesorteerde datastructuur te kiezen, bijvoorbeeld TreeSet. 
Ik heb hiervan een verzameling gemaakt VerzamelingLijstTS. 
 Het nadeel hierbij is dat een set geen rekening houdt met dubbelen. Een datatype dat dat wel kan is eventueel een Bag of eventuele andere oplossing. Voorbeelden hiervan kunnen bijvoorbeeld zijn 
- PriorityQueue: trage update (remove(Object) + add(Object)), en boxing van sleutel velden
- Fibonacci heap: geheugen (?)
- TreeMap<Field_S, List<Value>>: geheugen en ook boxing van sleutelveld
- sorted list or array: trage insert en remove 
- TreeMultimap in de guava bib;liotheek: external afhankelijkheid, onbekend v.w.b. geheugengebruik

## Opdracht 1.9 (optioneel) 
Ga naar de site: [sorting](http://www.cs.ubc.ca/~harrison/Java/sorting-demo.html)
Deze site toonde java applets die het verschil in sorteersnelheid aantoonden tussen sorteermethoden. Applets zijn niet meer welkom in de meeste browsers, dus creeer een gui die het sorteren van 1 van de sorteringen laat zien analoog aan de website.

## Opdracht 1.10 (optioneel) 
Als 1.7, maar dan met meer dan 1 sortering.

Benchmark utility toegevoegd om verschillende implementaties van de IFotoVerzameling te kunnen vergelijken. De klasse maakt een random een aantal fotos aan en berekend dan de benodigde tijd voor invoer, sorteren, opvragen en verwijderen.

Om al te veel dubbelen te voorkomen is voor lengte en breedte een marge opgegeven. Hieronder is een fragment van de benchmark functie opgenomen. In dit geval is het de standaard lijst implementatie met de bijbehorende uitvoer.


``` java
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

```

* Adding 2147483647 elements to the collection
* Adding 2147483647 elements to the collection took 2929500 nano seconds
* Sorting the collection
* Sorting the elements of the collection took 10600 nano seconds
* Randomly picking 2147483647 elements from the collection
* Randomly picking 2147483647 elements from the collection took 124284000 nano seconds
* Deleting  2147483647 elements from the collection
* Deleting 2147483647 elements from the collection took 508800 nano seconds


