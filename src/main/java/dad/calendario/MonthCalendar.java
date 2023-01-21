package dad.calendario;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MonthCalendar extends GridPane implements Initializable {

	// model
	
	private StringProperty mes = new SimpleStringProperty();
	private List<Label> labelsDias;
	
	// view
	
    @FXML
    private Label mesLabel;

    @FXML
    private GridPane view;
    
    public MonthCalendar() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonthCalendar.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// bindings 
		
		mesLabel.textProperty().bind(mes);
		
		// cargar labels
		labelsDias = view.getChildren().stream()
				.filter(nodo -> nodo instanceof Label)
				.filter(nodo -> "diaLabel".equals(nodo.getId()))
				.map(nodo -> (Label) nodo)
				.collect(Collectors.toList());
		
	}
	
	public List<Label> getLabelsDias() {
		return labelsDias;
	}

	public void setLabelsDias(List<Label> labelsDias) {
		this.labelsDias = labelsDias;
	}

	public final StringProperty mesProperty() {
		return this.mes;
	}
	

	public final String getMes() {
		return this.mesProperty().get();
	}
	

	public final void setMes(final String mes) {
		this.mesProperty().set(mes);
	}
	

}
