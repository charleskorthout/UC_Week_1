package fotos.Comparison;

/**
 *
 * @author Charles Korthout
 * 
 * Interface for comparing two objects
 */
public interface ICompareStrategy<T> {
    /**
     * Compare 2 objects
     * @param t1 The first object
     * @param t2 The second object
     * @return The compare result
     *          If t1 is equal to t2 then 0 is returned.
    *           If t1 is less than t2 then -1 is returned.
    *           If t1 is greater than t2 then 1 is returned.     
     */
    int compare(T t1, T t2);    
}
