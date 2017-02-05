/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verzameling;

import fotos.Comparison.NaamComparator;
import fotos.Foto;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import javafx.collections.ObservableList;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.internal.matchers.TypeSafeMatcher;


/**
 *
 * @author erik
 */
public class IFotoVerzamelingTest {
    private Foto foto1;
    private Foto foto2;

    public IFotoVerzamelingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.foto1 = new Foto(200,100,"zeezicht");
        this.foto2 = new Foto(200,100,"bergen");
    }
    
    @After
    public void tearDown() {
    }

    protected boolean isSorted (List<Foto> fotos, Comparator comp)
    {
      for(int i = 0 ; i < fotos.size() -1; i++) {
        if( (comp.compare(fotos.get(i),fotos.get(i+1)) > 0 )) return false;
      }
      return true;
    }
    
        
    /**
     * Test of add method, of class IFotoVerzameling.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        
        // add null Foto object, expect false
        Foto object = null;
        IFotoVerzameling instance = new VerzamelingLijst();
        boolean expResult = false;
        boolean result = instance.add(object);
        assertEquals(expResult, result);
        // add non null Foto object, expect true
        object = foto1;
        expResult = true;
        result = instance.add(object);
        assertEquals(expResult, result);
        // add non null non Foto object, expect false 
        // ? cannot would give compile error
        // String fotodesc = "this is a foto";
        // expResult = false;
        // result = instance.add(fotodesc);    
        // try to add a null object, expect false
        expResult = false;
        result = instance.add(null);   
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class IFotoVerzameling.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Foto object = null;
        IFotoVerzameling instance = new VerzamelingLijst();
        boolean expResult = false;
        boolean result = instance.remove(object);
        assertEquals(expResult, result);
        // add a foto and remove the foto afterwards result must be true;
        result = instance.add(foto1); // ignore result for now
        result = instance.remove(foto1);
        expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class IFotoVerzameling.
     */
    @Test(expected = VerzamelingException.class) 
    public void testGet() throws Exception {
        System.out.println("get");
        int index = 0;
        IFotoVerzameling instance = new VerzamelingLijst();
        Foto expResult = null;
        Foto result = instance.get(index);
        assertEquals(expResult, result);
        // add a foto and retrieve it afterwards, must return true
        expResult = foto1;
        Boolean ignore = instance.add(foto1);
        result = instance.get(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSize method, of class IFotoVerzameling.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        IFotoVerzameling instance = new VerzamelingLijst();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // add a foto and the size must equal 1
        expResult = 1;
        Boolean ignore = instance.add(foto1);
        result = instance.getSize();
        assertEquals(expResult, result);
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

    /**
     * Test of iterator method, of class IFotoVerzameling.
     * this does not make sense here. The iterator is a call to the Oracle iterator
     * If tests have to be defined following could be a consideration (http://stackoverflow.com/questions/14920164/how-to-junit-test-a-iterator) :
     * The few tests I can think of are:
     * - test hasNext on an empty collection (returns false)
     * - test next() on an empty collection (throws exception)
     * - test hasNext on a collection with one item (returns true, several times)
     * - test hasNext/next on a collection with one item: hasNext returns true, next returns the item, hasNext returns false, twice
     * - test remove on that collection: check size is 0 after
     * - test remove again: exception
     * - final test with a collection with several items, make sure the iterator goes through each item, in the correct order (if there is one)
     * - remove all elements from the collection: collection is now empty
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        IFotoVerzameling instance = new VerzamelingLijst();
        Boolean expResult = false; // an empty list has no next values.
        Boolean result = instance.iterator().hasNext();
        assertEquals(expResult, result);        
    }

    /**
     * Test of clear method, of class IFotoVerzameling.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        IFotoVerzameling instance = new VerzamelingLijst();
        instance.clear();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // add a photo
        instance.add(foto1); // The list with a single record 
        instance.clear();
        result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of getReadOnlyList method, of class IFotoVerzameling.
     */
    @Test
    public void testGetReadOnlyList() {
        System.out.println("getReadOnlyList");
        IFotoVerzameling instance = new VerzamelingLijst();
        int expResult = 0;
        int result = instance.getReadOnlyList().size();
        assertEquals(expResult, result);
        // TODO add additional tests
        
    }

}
