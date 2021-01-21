/*PongFX

Juego parecido al clásico Space Invaders donde una nave tendrá
que destruir los marcianos y evitar chocar con los asteroides.

Diego Jesús Sánchez Del Corral
2021*/

package diegosanchez.alien;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class Alien extends Application {
    
   

    @Override
    public void start(Stage primeraEtapa) {
        //Crear el contenedor para poner los objetos
        StackPane root = new StackPane();
        //Crear la escena(ventana)
        Scene escena = new Scene(root, 800, 600, Color.BLACK);
        primeraEtapa.setTitle("Alien");
        primeraEtapa.setScene(escena);
        primeraEtapa.show();
    }

    public static void main(String[] args) {
        launch();
    }

}