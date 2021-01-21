/*PongFX

Juego parecido al clásico Space Invaders donde una nave tendrá
que destruir los marcianos y evitar chocar con los asteroides.

Diego Jesús Sánchez Del Corral
2021*/

package diegosanchez.alien;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class Alien extends Application {
    
   

    @Override
    public void start(Stage primeraEtapa) {
        //Crear el contenedor para poner los objetos
        Pane root = new Pane();
        //Crear la escena(ventana)
        Scene escena = new Scene(root, 900, 700, Color.BLACK);
        primeraEtapa.setTitle("Alien");
        primeraEtapa.setScene(escena);
        primeraEtapa.show();
        //Crear nave espacial
        //Pico nave
        Polygon picoNave = new Polygon(new double[]{
            100.0, 0.0,
            80.0, 50.0,
            120.0, 50.0 });
        picoNave.setFill(Color.WHITE);
        //Cuerpo nave
        Rectangle cuerpoNave = new Rectangle (80,50,40,100);
        cuerpoNave.setFill(Color.WHITE);
        //Ala izquierda nave
        Polygon alaIzqNave = new Polygon(new double[]{
            80.0, 70.0,
            20.0, 180.0,
            80.0, 150.0 });
        alaIzqNave.setFill(Color.WHITE);
        //Ala derecha nave
        Polygon alaDrchNave = new Polygon(new double[]{
            120.0, 70.0,
            120.0, 150.0,
            180.0, 180.0 });
        alaDrchNave.setFill(Color.WHITE);
        //Agregar partes de la nave al contenedor
        root.getChildren().add(picoNave);
        root.getChildren().add(cuerpoNave);
        root.getChildren().add(alaIzqNave);
        root.getChildren().add(alaDrchNave);
    }

    public static void main(String[] args) {
        launch();
    }

}