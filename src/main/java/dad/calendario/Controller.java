package dad.calendario;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {
	
	// model
	
	private IntegerProperty yearProperty = new SimpleIntegerProperty(LocalDateTime.now().getYear());
	
	// view
	
	@FXML
    private Button anteriorButton;
	
    @FXML
    private Label mesLabel;

    @FXML
    private Label mesLabel1;

    @FXML
    private Label mesLabel10;

    @FXML
    private Label mesLabel11;

    @FXML
    private Label mesLabel2;

    @FXML
    private Label mesLabel3;

    @FXML
    private Label mesLabel4;

    @FXML
    private Label mesLabel5;

    @FXML
    private Label mesLabel6;

    @FXML
    private Label mesLabel7;

    @FXML
    private Label mesLabel8;

    @FXML
    private Label mesLabel9;

    @FXML
    private Button siguienteButton;

    @FXML
    private GridPane view;

    @FXML
    private GridPane view1;

    @FXML
    private GridPane view10;

    @FXML
    private GridPane view11;

    @FXML
    private GridPane view12;

    @FXML
    private GridPane view2;

    @FXML
    private GridPane view3;

    @FXML
    private GridPane view4;

    @FXML
    private GridPane view5;

    @FXML
    private GridPane view6;

    @FXML
    private GridPane view7;

    @FXML
    private GridPane view8;

    @FXML
    private GridPane view9;

    @FXML
    private Label yearLabel;
    
    public Controller() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
			loader.setController(this);
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
		yearLabel.textProperty().bind(yearProperty.asString());
		
		mesLabel.setText("enero");
		mesLabel1.setText("febrero");
		mesLabel2.setText("marzo");
		mesLabel3.setText("abril");
		mesLabel4.setText("mayo");
		mesLabel5.setText("junio");
		mesLabel6.setText("julio");
		mesLabel7.setText("agosto");
		mesLabel8.setText("septiembre");
		mesLabel9.setText("octubre");
		mesLabel10.setText("noviembre");
		mesLabel11.setText("diciembre");
		
		generarDias();		
		
	}
	
	public GridPane getView() {
		return view;
	}
	
	private void generarDias(){
		
		for (int i = 0; i < 12; i++) {
			List<GridPane> months = view.getChildren().stream()
					.filter(nodo -> nodo instanceof GridPane)
					.filter(nodo -> "monthView".equals(nodo.getId()))
					.map(nodo -> (GridPane) nodo)
					.collect(Collectors.toList());
			
			int dias = 1;
			List<Label> labelsDias = months.get(i).getChildren().stream()
					.filter(nodo -> nodo instanceof Label)
					.filter(nodo -> "diaLabel".equals(nodo.getId()))
					.map(nodo -> (Label) nodo)
					.collect(Collectors.toList());
			for (int j =0 ; j < 42; j++) {
			
				if(j >= posicionDia(i) && j <= diasMes(i+1)+posicionDia(i)-1) {
					labelsDias.get(j).setText(String.valueOf(dias));
					dias++;
				} else {
					labelsDias.get(j).setText(null);
				}
				
				
			}
			
		}
		
		
	}
	
	private int posicionDia(int month) {
		
		Calendar c = Calendar.getInstance();
		c.set(getYearProperty(), month, 1);
		int n = c.get(Calendar.DAY_OF_WEEK);
		
		if(n == 1 ) {
			n = 6;
		} else{
			n -= 2;
		}

		return n;
	}
	
	private int diasMes(int mes) {
		int dias;
		
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			dias = 31;
		} else if(mes ==2) {
			if(esBisiesto()) {
				dias = 29;
			} else {
				dias = 28;
			}
			
		} else {
			dias = 30;
		}
		
		return dias;
	} 
	
	private boolean esBisiesto() {
		boolean esBisiesto;
		if(yearProperty.get() % 4 == 0) {
			if(yearProperty.get() % 100 == 0) {
				if(yearProperty.get() % 400 == 0) {
					esBisiesto = true;
				} else {
					esBisiesto = false;
				}
			} else {
				esBisiesto = true;
			}
		} else {
			esBisiesto = false;
		}
		
		return esBisiesto;
	}
	
	@FXML
    void onAnteriorAction(ActionEvent event) {

		setYearProperty(getYearProperty()-1);
		generarDias();
		
    }

    @FXML
    void onSiguienteAction(ActionEvent event) {

    	setYearProperty(getYearProperty()+1);
    	generarDias();
    	
    }

	public final IntegerProperty yearProperty() {
		return this.yearProperty;
	}
	

	public final int getYearProperty() {
		return this.yearProperty().get();
	}
	

	public final void setYearProperty(final int yearProperty) {
		this.yearProperty().set(yearProperty);
	}
	
}
