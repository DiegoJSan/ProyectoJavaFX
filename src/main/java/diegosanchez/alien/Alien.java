/*PongFX

Juego parecido al clásico Space Invaders donde una nave tendrá
que destruir los marcianos y evitar chocar con los asteroides.

Diego Jesús Sánchez Del Corral
2021*/

package diegosanchez.alien;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Alien extends Application {
    
    //Variables
    final int ALTO_PANTALLA = 700;
    final int ANCHO_PANTALLA = 900;
    int posicionXFondo1 = 0;
    int posicionYFondo1 = 0;
    int posicionXFondo2 = 0;
    int posicionYFondo2 = -700;
    int velocidadFondo = 1;
    int posicionNaveX = 175;
    int posicionNaveY = 265;  
    //Variable para velocidad de movimiento de la nave
    int velocidadNaveX = 0;
    int velocidadNaveY = 0;
    

    @Override
    public void start(Stage primeraEtapa) {
        //Crear el contenedor para poner los objetos
        Pane root = new Pane();
        //Crear la escena(ventana)
        Scene escena = new Scene(root, ANCHO_PANTALLA, ALTO_PANTALLA, Color.BLACK);
        primeraEtapa.setResizable(false);
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
        Polygon alaDchaNave = new Polygon(new double[]{
            120.0, 70.0,
            120.0, 150.0,
            180.0, 180.0 
        });
        alaDchaNave.setFill(Color.WHITE);
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
        //Linea nave1
        Line lineaNave1 = new Line (97, 75, 97, 145);
        lineaNave1.setStroke(Color.WHITE);
        lineaNave1.setStrokeWidth(2);
        lineaNave1.setFill(Color.WHITE);
        //Linea nave
        Line lineaNave2 = new Line (103, 75, 103, 145);
        lineaNave2.setStroke(Color.WHITE);
        lineaNave2.setStrokeWidth(2);
        lineaNave2.setFill(Color.WHITE);
        //Punta ala izaquierda
        Polygon puntaAlaIzq = new Polygon(new double[]{
            25, 140,
            20, 180,
            30, 175
        });
        puntaAlaIzq.setFill(Color.CRIMSON);
        //Punta ala derecha
        Polygon puntaAlaDcha = new Polygon(new double[]{
            175, 140,
            170, 175,
            180, 180
        });
        puntaAlaDcha.setFill(Color.CRIMSON);
        //Circulo ala iqz
        Circle circuloAlaIqz = new Circle();
        circuloAlaIqz.setCenterX(62);
        circuloAlaIqz.setCenterY(137);
        circuloAlaIqz.setRadius(6);
        circuloAlaIqz.setFill(Color.CRIMSON);
        //Circulo ala derecha
        Circle circuloAlaDcha = new Circle();
        circuloAlaDcha.setCenterX(138);
        circuloAlaDcha.setCenterY(137);
        circuloAlaDcha.setRadius(6);
        circuloAlaDcha.setFill(Color.CRIMSON);
        
        //Agrupar todos los objetos de la nave
        Group nave = new Group();
        nave.getChildren().add(picoNave);
        nave.getChildren().add(cuerpoNave);
        nave.getChildren().add(alaIzqNave);
        nave.getChildren().add(alaDchaNave);
        nave.getChildren().add(fuegoNave1);
        nave.getChildren().add(fuegoNave2);
        nave.getChildren().add(ventanaNave);
        nave.getChildren().add(lineaNave1);
        nave.getChildren().add(lineaNave2);
        nave.getChildren().add(puntaAlaIzq);
        nave.getChildren().add(puntaAlaDcha);
        nave.getChildren().add(circuloAlaIqz);
        nave.getChildren().add(circuloAlaDcha);
        
        //Posicionar la nave
        nave.setLayoutX(posicionNaveX);
        nave.setLayoutY(posicionNaveY);
        //Escalar la nave
        nave.setScaleX(0.4);
        nave.setScaleY(0.4);
        
        //Imegen 1 de pantalla
        var fondoPantalla1 = new Image(getClass().getResourceAsStream("/images/FondoEstrellas1.png"));
        ImageView fondoPantallaView1 = new ImageView(fondoPantalla1);
        
        //Imegen 2 de pantalla
        var fondoPantalla2 = new Image(getClass().getResourceAsStream("/images/FondoEstrellas2.png"));
        ImageView fondoPantallaView2 = new ImageView(fondoPantalla2);
        
        //Posicionar imagen 1
        fondoPantallaView1.setLayoutX(posicionXFondo1);
        fondoPantallaView1.setLayoutY(posicionYFondo1);
        
        //Posicionar imagen 2
        fondoPantallaView2.setLayoutX(posicionXFondo2);
        fondoPantallaView2.setLayoutY(posicionYFondo2);
                
        //Añadir al contenedor
        root.getChildren().add(fondoPantallaView1);
        root.getChildren().add(fondoPantallaView2);
        root.getChildren().add(nave);
        
        //Movimiento del fondo
        Timeline movimiento = new Timeline(
            //Movimiento del fondo comprobando que ha llegado al final de la pantalla
            new KeyFrame(Duration.seconds(0.017),(var ae) -> {
                fondoPantallaView1.setLayoutY(posicionYFondo1);
                posicionYFondo1+= velocidadFondo;
                fondoPantallaView2.setLayoutY(posicionYFondo2);
                posicionYFondo2+= velocidadFondo;
                if (posicionYFondo1 >= ALTO_PANTALLA) {
                    posicionYFondo1 = -700;
                }
                if (posicionYFondo2 >= ALTO_PANTALLA) {
                    posicionYFondo2 = -700;
                }
                //Actualizar posición X de la nave
                posicionNaveX += velocidadNaveX;
                nave.setTranslateX(posicionNaveX);
                //Actualizar posición Y de la nave
                posicionNaveY += velocidadNaveY;
                nave.setTranslateY(posicionNaveY);
                
                //Comprobar si se sale la nave por la izquierda
                if (posicionNaveX < -230) {
                    posicionNaveX = -230;
                }else{
                    //Cpmprobar que no se sale por la derecha
                    if (posicionNaveX > 580) {
                        posicionNaveX = 580;
                    }
                }
            })
        );
        movimiento.setCycleCount (Timeline.INDEFINITE);
        movimiento.play();
        
        //Detección de las pulsaciones de las teclas
        escena.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()) {
                case LEFT:
                    //Pulsada tecla izquierda
                    velocidadNaveX = -5;
                    break;
                case RIGHT:
                    //Pulsada tecla derecha
                    velocidadNaveX = 5;
                    break;
                case UP:
                    //Pulsada tecla arriba
                    velocidadNaveY = -5;
                    break;
                case DOWN:
                    //Pulsada tecla abajo
                    velocidadNaveY = 5;
                    break;
            }
        });
        //Detección de dejar de pulsar las teclas
        escena.setOnKeyReleased((KeyEvent event) -> {
            switch(event.getCode()) {
                case LEFT:
                    //Dejar de pulsar tecla izquierda
                    velocidadNaveX = 0;
                    break;
                case RIGHT:
                    //Dejar de pulsar tecla derecha
                    velocidadNaveX = 0;
                    break;
                case UP:
                    //Dejar de pulsar tecla arriba
                    velocidadNaveY = 0;
                    break;
                case DOWN:
                    //Dejar de pulsar tecla abajo
                    velocidadNaveY = 0;
                    break;
            }
        });
        
    }
    public static void main(String[] args) {
        launch();
    }

}