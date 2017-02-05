package fotos.Comparison;

import fotos.Comparison.FotoComparator;
import fotos.Foto;

/**
 *
 * @author Charles Korthout
 */
public class BreedteComparator <T extends Foto> extends FotoComparator<T> {
    
    public BreedteComparator() {
        super(new FotoBreedteCompare());
    }

}
