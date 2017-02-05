/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fotos.Comparison;

import fotos.Foto;
import static javafx.scene.input.KeyCode.T;

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
     * @param t1 The first parameter
     * @param t2 The second parameter
     * @return the compare value, see above
     */
    public static int nullsFirst(Object t1, Object t2)  {
        if (t1 == null) 
            if (t2 == null) return 0;
            else return 1;
        else return -1;
    }
}
