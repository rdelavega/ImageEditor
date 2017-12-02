//////////////////////////////
// Controlador de Funciones //
//////////////////////////////

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.print.PrinterJob;

public class MainPage implements Initializable {

public static Stage main_stage;// Stage actual

private ObservableList<Photo> photos = FXCollections.observableArrayList();// Contenido
@FXML
private ListView<Photo> photoList;// Lista de fotos

//////////////////////////
// Contenedor Principal //
//////////////////////////

@FXML
private ImageView squareImg;

@FXML
private Slider saturationEdit;
@FXML
private Slider brightnessEdit;
@FXML
private Slider contrastEdit;
@FXML
private Slider hueEdit;

///////////////////////////
// Propiedades de imagen //
///////////////////////////

private DoubleProperty brightness = new SimpleDoubleProperty();
private DoubleProperty saturation = new SimpleDoubleProperty();
private DoubleProperty hue = new SimpleDoubleProperty();
private DoubleProperty contrast = new SimpleDoubleProperty();

public void initialize(URL location, ResourceBundle resources) {
								// ListView agregara dinamicamente celdas a la vista
								photoList.setItems(photos);
								// Inicializara un ListView con las propiedades requeridas
								photoList.setCellFactory(new Callback<ListView<Photo>, ListCell<Photo> >() {
																								public ListCell<Photo> call(ListView<Photo> list) {
																																return new photoList();
																								}
																});

								// Agregar fotos precargadas
								photos.add(new Photo("girl.jpg"));
								photos.add(new Photo("stone.jpg"));
								photos.add(new Photo("dry.jpg"));
								photos.add(new Photo("waves.jpg"));
								photos.add(new Photo("galaxy.jpg"));
								photos.add(new Photo("universe.jpg"));
								photos.add(new Photo("night.jpg"));
								// photos.add(new Photo("M.png"));

								photoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);// Establecer selccion unica
								photoList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Photo>() {
																								/*******************************************************************
																								* Sobreescribe el metodo changed, para que cuando se seleccione   *
																								* una opcion de ListView se muestre la imagen en cuadro principal *
																								* @method changed                                                 *
																								*******************************************************************/
																								public void changed(ObservableValue <? extends Photo> obsValue, Photo last, Photo showPhoto) {
																																if(showPhoto != null ) {
																																								squareImg.setImage(new Image(showPhoto.getPathFile()));
																																								hueEdit.adjustValue(0);
																																								contrastEdit.adjustValue(0);
																																								saturationEdit.adjustValue(0);
																																								brightnessEdit.adjustValue(0);
																																								ColorAdjust colorAdjust = new ColorAdjust();
																																								colorAdjust.setBrightness(0);
																																								colorAdjust.setSaturation(0);
																																								colorAdjust.setContrast(0);
																																								colorAdjust.setHue(0);
																																								squareImg.setEffect(colorAdjust);
																																}
																								}
																});

								brightnessEdit.valueProperty().bindBidirectional(brightness);// Asigna propiedad a un Slider
								brightness.addListener(new ChangeListener<Number>() {
																								/***********************************************************
																								* Si cambia el slider, cambiara la propiedad de la imagen *
																								* @method changed                                         *
																								***********************************************************/

																								public void changed(ObservableValue<? extends Number> obsValue, Number number, Number sliderNum) {
																																setColorEffect();
																								}
																});

								saturationEdit.valueProperty().bindBidirectional(saturation);
								saturation.addListener(new ChangeListener<Number>() {
																								public void changed(ObservableValue<? extends Number> obsValue, Number number, Number sliderNum) {
																																setColorEffect();
																								}
																});

								hueEdit.valueProperty().bindBidirectional(hue);
								hue.addListener(new ChangeListener<Number>() {
																								public void changed(ObservableValue<? extends Number> obsValue, Number number, Number sliderNum) {
																																setColorEffect();
																								}
																});

								contrastEdit.valueProperty().bindBidirectional(contrast);
								contrast.addListener(new ChangeListener<Number>() {
																								public void changed(ObservableValue<? extends Number> obsValue, Number number, Number sliderNum) {
																																setColorEffect();
																								}
																});

}

/*********************************************************
* Asigna las propiedades delimitadas por los sliders a  *
* la imagen que aparece como principal.                 *
* @method setColorEffect                                *
*********************************************************/

public void setColorEffect() {
								ColorAdjust colorAdjust = new ColorAdjust();
								colorAdjust.setBrightness(brightness.get());
								colorAdjust.setSaturation(saturation.get());
								colorAdjust.setContrast(contrast.get());
								colorAdjust.setHue(hue.get());
								squareImg.setEffect(colorAdjust);
}

/*************************************************
* Aplica un efecto sobre la imagen, modificando *
* sus propiedades y los sliders que la regulan  *
* @method oneEffect                             *
*************************************************/

@FXML
void oneEffect(ActionEvent event) {
								brightnessEdit.adjustValue(0);
								saturationEdit.adjustValue(-1);
								contrastEdit.adjustValue(1);
								hueEdit.adjustValue(0);
								ColorAdjust colorAdjust = new ColorAdjust();
								colorAdjust.setBrightness(0);
								colorAdjust.setSaturation(-1);
								colorAdjust.setContrast(1);
								colorAdjust.setHue(0);
								squareImg.setEffect(colorAdjust);
}

@FXML
void twoEffect(ActionEvent event) {
								hueEdit.adjustValue(0.65);
								contrastEdit.adjustValue(-0.30);
								saturationEdit.adjustValue(-0.60);
								brightnessEdit.adjustValue(0);
								ColorAdjust colorAdjust = new ColorAdjust();
								colorAdjust.setBrightness(0);
								colorAdjust.setSaturation(-0.60);
								colorAdjust.setContrast(-0.30);
								colorAdjust.setHue(0.65);
								squareImg.setEffect(colorAdjust);
}

@FXML
void threeEffect(ActionEvent event) {
								hueEdit.adjustValue(0);
								contrastEdit.adjustValue(0);
								saturationEdit.adjustValue(-1);
								brightnessEdit.adjustValue(0);
								ColorAdjust colorAdjust = new ColorAdjust();
								colorAdjust.setBrightness(0);
								colorAdjust.setSaturation(-1);
								colorAdjust.setContrast(0);
								colorAdjust.setHue(0);
								squareImg.setEffect(colorAdjust);
}

@FXML
void fourEffect(ActionEvent event) {
								hueEdit.adjustValue(0);
								contrastEdit.adjustValue(0.5);
								saturationEdit.adjustValue(-0.60);
								brightnessEdit.adjustValue(-0.2);
								ColorAdjust colorAdjust = new ColorAdjust();
								colorAdjust.setBrightness(-0.20);
								colorAdjust.setSaturation(-0.60);
								colorAdjust.setContrast(0.50);
								colorAdjust.setHue(0);
								squareImg.setEffect(colorAdjust);
}

@FXML
void fiveEffect(ActionEvent event) {
								hueEdit.adjustValue(0.10);
								contrastEdit.adjustValue(0.10);
								saturationEdit.adjustValue(-0.60);
								brightnessEdit.adjustValue(0.10);
								ColorAdjust colorAdjust = new ColorAdjust();
								colorAdjust.setBrightness(0.10);
								colorAdjust.setSaturation(-0.60);
								colorAdjust.setContrast(0.10);
								colorAdjust.setHue(0.10);
								squareImg.setEffect(colorAdjust);
}


/******************************************************************
* Perimite al usuarios visualizar una seleccionador de archivos, *
* de el cual puede elegir una imagen, tambien valida el formato  *
* (.jpg, .png, .jpeg)                                            *
* @method loadImage                                              *
* @param  ActionEvent event click en boton                       *
******************************************************************/

@FXML
void loadImage(ActionEvent event) {
								try {
																File file = new FileChooser().showOpenDialog(main_stage);

																if (file.isFile() && file.getName().contains(".jpg") || file.getName().contains(".png")  || file.getName().contains(".jpeg")) {
																								try {
																																String pathFile = file.toURI().toURL().toString();
																																photos.add(new Photo(pathFile)); // Agregar a lista de imagenes
																																squareImg.setImage(new Image(pathFile)); // Poner como imagen principal
																																hueEdit.adjustValue(0);
																																contrastEdit.adjustValue(0);
																																saturationEdit.adjustValue(0);
																																brightnessEdit.adjustValue(0);
																																ColorAdjust colorAdjust = new ColorAdjust();
																																colorAdjust.setBrightness(0);
																																colorAdjust.setSaturation(0);
																																colorAdjust.setContrast(0);
																																colorAdjust.setHue(0);
																																squareImg.setEffect(colorAdjust);
																								} catch(MalformedURLException ex) {
																																System.out.println(ex.toString());
																								}
																}
								} catch (Exception e) {
																System.out.println(e.toString());
								}
}

/********************************************
* Guarda la imagen con formato .png        *
* @method saveImgPNG                       *
* @param  ActionEvent event click en boton *
********************************************/

@FXML
void saveImgPNG(ActionEvent event) {
								FileChooser fileChooser = new FileChooser();
								File file = fileChooser.showSaveDialog(main_stage);
								if (file != null) {
																try {
																								if (!file.getPath().endsWith(".png")) {
																																file = new File(file.getPath() + ".png");
																								}
																								ImageIO.write(SwingFXUtils.fromFXImage(squareImg.getImage(), null), "PNG", file);
																} catch (IOException ex) {
																								System.out.println(ex.toString());
																}
								}
}

/********************************************
* Guarda la imagen con formato .jpg        *
* @method saveImgPNG                       *
* @param  ActionEvent event click en boton *
********************************************/

@FXML
void saveImgJPG(ActionEvent event) {
								FileChooser fileChooser = new FileChooser();
								File file = fileChooser.showSaveDialog(main_stage);
								if (file != null) {
																try {
																								if (!file.getPath().endsWith(".jpg")) {
																																file = new File(file.getPath() + ".jpg");
																								}
																								ImageIO.write(SwingFXUtils.fromFXImage(squareImg.getImage(), null), "JPG", file);
																} catch (IOException ex) {
																								System.out.println(ex.toString());
																}
								}
}

/******************************************************************
* Muestra al usuario un dialogo de impresion, para posteriormete *
* conectar con la impresora e imprimir la imagen deseada.        *
* @method printImage                                             *
* @param  ActionEvent event click en boton                       *
******************************************************************/

@FXML
void printImage(ActionEvent event) {
								PrinterJob job = PrinterJob.createPrinterJob();
								if (job == null) {
																return;
								}
								boolean proceed = job.showPageSetupDialog(main_stage);
								if (proceed) {
																boolean printed = job.printPage(squareImg);
																if (printed) {
																								job.endJob();
																}
								}
}

}
