package fotos.Comparison;

import fotos.Foto;
import verzameling.VerzamelingException;

/**
 *
 * @author Charles Korthout
 */
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