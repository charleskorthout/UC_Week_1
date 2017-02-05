/**
 * Opdracht 1.2
 * Maak in de package verzameling een klasse VerzamelingLijst.java die de interface IFotoVerzameling implementeert.
 *
 * Van deze methoden moet je dus zelf een implementatie maken: De klasse implementeert de verzameling als een lijst, 
 * maak hierbij gebruik van een javaFX observableList implementatie (of een andere keus indien je dat beter vindt).  
 * De verzameling moet nog gesorteerd kunnen worden. 
 * Bedenk op dit moment wanneer gebruik van een Comparable of Comparator nodig is in je applicatie. 
 * Je kunt in eerste instantie besluiten het sorteren later in te bouwen, dit vergemakkelijkt het testen in het begin. 
 * Voeg eventuele benodigde extra methoden zelf toe aan de klasse, en zorg voor specificatie en tests ervoor.
 * 
 * -- Body ingevuld --
 * 
 * Opdracht 1.3
 * De klasse Foto.java is al gegeven, deze moet nog wel aangepast worden. 
 * Foto objecten moeten vergeleken kunnen worden: Foto’s worden eerst op hoogte vergeleken, en pas als die gelijk is, 
 * op breedte. Je fotos dienen automatisch gesorteerd te worden op de natuurlijke ordening als je de foto's 
 * inleest in je applicatie. 
 * 
 * --  Een default compare toegevoegd die sorteerd op foto naam. --
 * 
 * Opdracht 1.7 
 * Het expliciet sorteren door steeds de sort methode van IFotoVerzameling aan te roepen is niet efficient. 
 * Het aanroepen van Collections.sort is O(N*log(N)) waarbij N het aantal elementen in je collectie is.
 * 
 * Wat is dan de orde van het toevoegen van 1 item via IFotoVerzameling.add() ?   --> O(N*log(N)) (we would do a sort at every insert
 * Wat is dan de orde van het toevoegen van N items (fotos) aan je IFotoVerzameling? -> N * N * log(N)
 * 
 * Opdracht 1.8 (optioneel) 
 * Wat zou je moeten aanpassen aan je implementatie van IFotoVerzameling, zodat je collectie altijd gesorteerd is? 
 * 
 * Ik heb de verzameling geimplementeerd met de TreeSet (even aangenomen dat we geen dubbelen in de verzameling hebben, wat bij normale fotos
 * realistisch zou zijn, Zijn dubbelen wel mogelijk dan is een Bag structuur een mogelijke implementatie
 * 
 * Door de TreeSet wordt het invoegen O(N). Een nieuwe sort is (afhankelijk van de interne implementatie van TreeSet (N*log(N)).
 * De get op basis van index is nu inefficienter Bij get wordt eerst een lijst gemaakt (N*log(N) en dan de index berekent O(N). De efficientie wordt 
 * dan waarschijnlijk dus ook O(N*N*log(N))
 *
 */

package verzameling;

import fotos.Comparison.NaamComparator;
import fotos.Foto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Provides a list of {@link Foto} objects.
 * 
 * @Charles Korthout
 */

public class VerzamelingLijst implements IFotoVerzameling {
    private List<Foto> lijst;
    private Comparator comp;
    
    /**
     * Default constructor
     */
    public VerzamelingLijst() {
        comp = new NaamComparator<Foto>();
        lijst = new ArrayList();        
    }
    
    /**
     * Adds a photo to the list of Photos
     * @param object The photo to add
     * @return true if the photo could be added, false if the the photo is null or cannot be added.
     */
    public boolean add(Foto object) {
        if (object == null) return false;
        Boolean result = lijst.add(object);
        lijst.sort(comp);
        return result;
    }

    /**
     * Removes the first occurrence of the photo in the list
     * @param object The photo to remove
     * @return true if the photo could e removed, false otherwise
     */
    public boolean remove(Foto object) {
        return lijst.remove(object);
    }

    /**
     * Convert the current collection to a list
     * @return 
     */
    private List<Foto> toList() { 
            return lijst
                    .stream()
                    .collect(Collectors.toList());
    }
    
    /**
     * Tries to get the foto at the given index in the list
     * @param index The index in the collection
     * @return A photo object that was located at the index position in the list
     * @throws VerzamelingException An exception if the index is out of range
     */
    public Foto get(int index) throws VerzamelingException {
        Foto foto;
        try {
            foto = lijst.get(index);
            return foto;
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new VerzamelingException("The provided index is out of bounds");
        }
    }

    /**
     * Returns the number of photos currently in the collection
     * @return The number of photos in the collection
     */
    public int getSize() {
        return lijst.size();
    }

    /**
     * Sorts the collection based on the given compare strategy
     * @param comp The compare strategy
     */
    public void sort(Comparator comp) {
        if (comp != this.comp){ // we use a different comapare method and we have to rebuild
            this.comp = comp;
            lijst.sort(comp);
        }
    }

    /**
     * Retrieves the iterator for the collection
     * @return The collection iterator
     */
    public Iterator iterator() {
        return lijst.iterator();
    }

    /**
     * Clears all entries from the collection
     */
    public void clear() {
        lijst.clear();
    }

    /**
     * Returns the collection as a read-only list
     * @return The read-only collection
     */
    public ObservableList<Foto> getReadOnlyList() {
        return FXCollections.observableArrayList(lijst);
    }  
}

