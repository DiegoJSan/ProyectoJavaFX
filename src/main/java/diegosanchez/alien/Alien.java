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
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
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
            120.0, 50.0 
        });
        picoNave.setFill(Color.WHITE);
        //Cuerpo nave
        Rectangle cuerpoNave = new Rectangle (80,50,40,100);
        cuerpoNave.setFill(Color.CRIMSON);
        //Ala izquierda nave
        Polygon alaIzqNave = new Polygon(new double[]{
            80.0, 70.0,
            20.0, 180.0,
            80.0, 150.0 
        });
        alaIzqNave.setFill(Color.WHITE);
        //Ala derecha nave
        Polygon alaDrchNave = new Polygon(new double[]{
            120.0, 70.0,
            120.0, 150.0,
            180.0, 180.0 
        });
        alaDrchNave.setFill(Color.WHITE);
        //Fuego nave 1
        Polygon fuegoNave1 = new Polygon(new double[]{
            100.0, 180.0,
            90.0, 150.0,
            110.0, 150.0 
        });
        fuegoNave1.setFill(Color.YELLOW);
        //Fuego nave 2
        Polygon fuegoNave2 = new Polygon(new double[]{
            100.0, 170.0,
            95.0, 150.0,
            105.0, 150.0 
        });
        fuegoNave2.setFill(Color.RED);
        //Ventana nave
        Ellipse ventanaNave = new Ellipse(); {
        ventanaNave.setCenterX(100);
        ventanaNave.setCenterY(50);
        ventanaNave.setRadiusX(8);
        ventanaNave.setRadiusY(18);
        }
        ventanaNave.setFill(Color.CORNFLOWERBLUE);
        //Linea nave
        Line lineaNave = new Line (70, 102, 70, 102);
        lineaNave.setStroke(Color.WHITE);
        lineaNave.setStrokeWidth(4);
        //lineaNave.setFill(Color.WHITE);
        
        //Agregar partes de la nave al contenedor
        root.getChildren().add(picoNave);
        root.getChildren().add(cuerpoNave);
        root.getChildren().add(alaIzqNave);
        root.getChildren().add(alaDrchNave);
        root.getChildren().add(fuegoNave1);
        root.getChildren().add(fuegoNave2);
        root.getChildren().add(ventanaNave);
        root.getChildren().add(lineaNave);
    }

    public static void main(String[] args) {
        launch();
    }

}