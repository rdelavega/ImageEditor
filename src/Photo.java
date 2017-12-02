/*******************************************
* Guarda la ubicacion de las fotografias  *
* a editar.                               *
*******************************************/

public class Photo {
private String pathFile;

public Photo(String pathFile) {
        this.pathFile = pathFile;
}

public String getPathFile() {
        return pathFile;
}
}
