package fotos;

import java.util.Comparator;

/**
 * <p>Title: Opdracht 1</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Fontys ICT</p>
 *
 * @author okp
 * @version 1.0
 * 
 * @author erik
 * @version 2.0
 * JBuilder zaken verwijderd
 */
public class Foto {
    
    public Foto() {
    }

    private int breedte;
    private int hoogte;
    private String naam;
    
    public Foto(int breedte, int hoogte, String naam) {
        this.breedte = breedte;
        this.hoogte= hoogte;
        this.naam = naam;
    }

   public int getWidth() {
       return breedte;       
   }
   
   public int getHeight() {
       return hoogte;
   }
   
   public String getName() {
       return naam;
   }
   
   
    @Override
    public String toString() {
        return "Foto{" + "breedte=" + breedte + ", hoogte=" + hoogte + ", naam=" + naam + '}';
    }
}


