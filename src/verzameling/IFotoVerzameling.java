package verzameling;

import java.util.Comparator;
import java.util.Iterator;

import fotos.Foto;
import javafx.collections.ObservableList;

/**
 * Interface that, as it is now, provides no real useful stuff over List<Foto>,
 * but at this point only serves to complicate things.
 *
 * @author not attributable
 * @version 1.0
 */
public interface IFotoVerzameling {

    /**
     * add a Foto object
     * @param object a non-null Foto object
     * @return true if Foto object is added, false otherwise
     */
    public boolean add(Foto object);
    
    
    /**
     * removes the first occurence of the specified Foto
     * @param object
     * @return 
     */
    public boolean remove(Foto object);
    
    /**
     * returns a foto on a specific location
     * @param index 
     * @return Foto object on this position or null when this position doesn't 
     * contain an object.
     * @throws VerzamelingException when index is not valid
     */
    public Foto get(int index)throws VerzamelingException;
    
    /**
     * 
     * @return the amount of elements in the collection
     */
    public int getSize();
    
    /**
     * 
     * @param comp 
     */
    public void sort(Comparator comp);
    
    /**
     * returns an iterator on the collection
     * @return iterator which can iterate over all elements of the collection,
     * based on the index of the elements
     */
    public Iterator iterator();
    
    /**
     * empties the collection
     */
    public void clear();
    
    /** 
     * Added for convenience when using JavaFX Listview.
     * 
     * @return A readonly ObservableList view of all Foto's
     */
    public ObservableList<Foto> getReadOnlyList();
}
