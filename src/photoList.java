import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class photoList extends ListCell<Photo>{
/*************************************************
* Agregara a ListView propiedades para contener *
* imagenes y metodo para agregar nuevos item    *
* con estas propiedades.                        *
* @method updateItem                            *
*************************************************/

public void updateItem(Photo photo, boolean bo) {
								super.updateItem(photo, bo);

								if (photo != null) {
																HBox hbox = new HBox();

																Image image = new Image(photo.getPathFile());

																ImageView imageView = new ImageView();
																imageView.setImage(image);
																imageView.setFitWidth(200);
																imageView.setFitHeight(140);
																imageView.setPreserveRatio(true);
																imageView.setSmooth(true);
																imageView.setCache(true);

																hbox.getChildren().add(imageView);

																setGraphic(hbox);
								}
}
}
