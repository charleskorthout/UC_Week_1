/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import fotos.Foto;
import fotos.Comparison.*;
import verzameling.IFotoVerzameling;
import verzameling.VerzamelingLijst;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author tijmen
 */
public class FotoFrameController implements Initializable {
    
    @FXML private Button btInlezen;
    @FXML private Button btNieuweFoto;
    @FXML private Button btSortHoogte;
    @FXML private Button btSortBreedte;
    @FXML private Button btSortNaam;
    @FXML private ListView<Foto> lvFotos;
    @FXML private TextField tfSelectedNaam;
    @FXML private TextField tfSelectedHoogte;
    @FXML private TextField tfSelectedBreedte;
    @FXML private TextField tfInputNaam;
    @FXML private TextField tfInputHoogte;
    @FXML private TextField tfInputBreedte;
    
    private IFotoVerzameling fotos;
    private Comparator<Foto> currentComparator;
    
    private final ChangeListener<String> integerOnly =
            (observable, oldValue, newValue) -> {
                if (newValue != null && !newValue.equals("") && !newValue.matches("\\d{1,5}"))
                    ((StringProperty) observable).setValue(oldValue);
            };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fotos = new VerzamelingLijst();
        lvFotos.setItems(fotos.getReadOnlyList());
        lvFotos.getSelectionModel()
                .selectedItemProperty()
                .addListener(this::selectedFotoChanged);
        
        // Hoogte/Breedte fields should only accept integers
        tfInputHoogte.textProperty().addListener(integerOnly);
        tfInputBreedte.textProperty().addListener(integerOnly);
    }    

    @FXML private void handleButtonInlezen(ActionEvent event) {
        fotos.add(new Foto(100,50,"zebra2"));
        fotos.add(new Foto(100,505,"paard"));
        fotos.add(new Foto(1000,50,"beer"));
        fotos.add(new Foto(10,50,"stinkdier"));
        fotos.add(new Foto(200,50,"foto5"));
        fotos.add(new Foto(100,150,"foto6"));
        fotos.add(new Foto(100,250,"foto7"));
        fotos.add(new Foto(100,650,"foto8"));
        fotos.add(new Foto(1100,50,"foto9"));
        fotos.add(new Foto(1800,50,"foto10"));
        fotos.add(new Foto(100,50,"foto11"));
        fotos.add(new Foto(100,50,"foto12"));
        fotos.add(new Foto(100,50,"foto13"));
        fotos.add(new Foto(100,50,"foto14"));
        fotos.add(new Foto(100,50,"foto15"));
        fotos.add(new Foto(100,50,"foto16"));
        fotos.add(new Foto(100,50,"foto17"));
        fotos.add(new Foto(100,50,"foto18"));
        fotos.add(new Foto(100,50,"foto19"));
        fotos.add(new Foto(100,50,"foto20"));
        fotos.add(new Foto(100,50,"foto21"));
        fotos.add(new Foto(100,50,"foto22"));
        fotos.add(new Foto(100,50,"foto23"));
        fotos.add(new Foto(100,50,"foto24"));
        fotos.add(new Foto(100,50,"foto25"));
        fotos.add(new Foto(100,50,"foto26"));
        fotos.add(new Foto(100,50,"foto27"));
        fotos.add(new Foto(100,50,"foto28"));
        lvFotos.setItems(fotos.getReadOnlyList());
        //TODO
    }

    @FXML private void handleButtonSortNaam(ActionEvent e) {
        currentComparator = new NaamComparator();
        fotos.sort(currentComparator);
        lvFotos.setItems(fotos.getReadOnlyList());
    }

    @FXML private void handleButtonSortHoogte(ActionEvent e) {
        currentComparator = new HoogteComparator();
        fotos.sort(currentComparator);
        lvFotos.setItems(fotos.getReadOnlyList());
    }

    @FXML private void handleButtonSortBreedte(ActionEvent e) {
        currentComparator = new BreedteComparator();
        fotos.sort(currentComparator);
        lvFotos.setItems(fotos.getReadOnlyList());
    }

    @FXML private void handleButtonNieuweFoto(ActionEvent e) {
        Foto foto = new Foto(Integer.parseInt(tfInputBreedte.getText()), Integer.parseInt(tfInputHoogte.getText()), tfInputNaam.getText() );
        fotos.add(foto);
        lvFotos.setItems(fotos.getReadOnlyList());        
    }
    
          private void selectedFotoChanged(ObservableValue<? extends Foto> ov,
            Foto oldFoto, Foto newFoto) {
              if (oldFoto != null) {
                tfSelectedNaam.setText(oldFoto.getName());
                tfSelectedHoogte.setText("" + oldFoto.getHeight());
                tfSelectedBreedte.setText("" + oldFoto.getWidth()); 
              }
              else if (newFoto != null) {
                tfSelectedNaam.setText(newFoto.getName());
                tfSelectedHoogte.setText("" + newFoto.getHeight());
                tfSelectedBreedte.setText("" + ov.getValue().getWidth()); 
              } 
   
    }
 
}
