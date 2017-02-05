package fotos.Comparison;

import fotos.Comparison.FotoComparator;
import fotos.Foto;

/**
 *
 * @author Charles Korthout
 */
public class NaamComparator <T extends Foto> extends FotoComparator<T> {
    
    public NaamComparator() {
        super(new FotoNaamCompare());
    }
}
