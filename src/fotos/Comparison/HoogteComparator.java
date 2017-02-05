package fotos.Comparison;

import fotos.Comparison.FotoComparator;
import fotos.Foto;

/**
 *
 * @author Charles Korthout
 */
public class HoogteComparator<T extends Foto> extends FotoComparator<T> {
    
    public HoogteComparator() {
        super(new FotoHoogteCompare());
    }
}
