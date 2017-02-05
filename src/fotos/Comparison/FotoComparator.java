package fotos.Comparison;

import fotos.Comparison.ICompareStrategy;
import fotos.Foto;
import java.util.Comparator;

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
