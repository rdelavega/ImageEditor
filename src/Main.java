/*********************************************************************************
* Editor de Imagenes.                                                           *
* @brief	Ofrece al usuario una interfaz que le permite cargar una imagen,      *
* editarla por medio de la propiedades (brillo, saturacion, matiz y contraste), *
* al igual que agregar efectos ya determinados, para posteriormente guardarla   *
* con el formato que el usuario decida o imprimirla.                            *
* @author	Ricardo de la Vega Barrón.                                            *
* @author	Angel Martin Rodriguez Mendoza.                                       *
* @author	Jorge Luis Vasquez Osorio.                                            *
*********************************************************************************/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

/*****************************************************************
* Carga la pagina principal y carga el archivo fxml asi como la *
* hoja de estilos.                                              *
* @method main                                                  *
*****************************************************************/

public static void main(String[] args) {
								Application.launch(args);
}


public void start(Stage primaryStage) throws Exception {
								BorderPane mainPane = (BorderPane) FXMLLoader.load(Main.class.getResource("FirstPage.fxml"));
								Scene scene = new Scene(mainPane);
								String css = Main.class.getResource("main.css").toExternalForm();
								scene.getStylesheets().add(css);
								primaryStage.setScene(scene);
								primaryStage.show();
}
}
