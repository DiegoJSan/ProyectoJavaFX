/*PongFX

Juego parecido al clásico Space Invaders donde una nave tendrá
que destruir los marcianos y evitar chocar con los asteroides.

Diego Jesús Sánchez Del Corral
2021*/

package diegosanchez.alien;

import java.util.Random;
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
    float posicionXFondo1 = 0;
    float posicionYFondo1 = 0;
    float posicionXFondo2 = 0;
    float posicionYFondo2 = -700;
    float velocidadFondo = 0.5f;
    int posicionNaveX = 450;
    int posicionNaveY = 550;  
    //Variable para velocidad de movimiento de la nave
    int velocidadNaveX = 0;
    int velocidadNaveY = 0;
    
    int posicionYNaveTitan = 0;
    

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
            0.0, 0.0,
            -20.0, 50.0,
            20.0, 50.0 
        });
        picoNave.setFill(Color.WHITE);
        //Cuerpo nave
        Rectangle cuerpoNave = new Rectangle (-20,50,40,100);
        cuerpoNave.setFill(Color.CRIMSON);
        //Ala izquierda nave
        Polygon alaIzqNave = new Polygon(new double[]{
            -20.0, 70.0,
            -80.0, 180.0,
            -20.0, 150.0 
        });
        alaIzqNave.setFill(Color.WHITE);
        //Ala derecha nave
        Polygon alaDchaNave = new Polygon(new double[]{
            20.0, 70.0,
            20.0, 150.0,
            80.0, 180.0 
        });
        alaDchaNave.setFill(Color.WHITE);
        //Fuego nave 1
        Polygon fuegoNave1 = new Polygon(new double[]{
            0.0, 180.0,
            -10.0, 150.0,
            10.0, 150.0 
        });
        fuegoNave1.setFill(Color.YELLOW);
        //Fuego nave 2
        Polygon fuegoNave2 = new Polygon(new double[]{
            0.0, 170.0,
            -5.0, 150.0,
            5.0, 150.0 
        });
        fuegoNave2.setFill(Color.RED);
        //Ventana nave
        Ellipse ventanaNave = new Ellipse(); {
        ventanaNave.setCenterX(0);
        ventanaNave.setCenterY(50);
        ventanaNave.setRadiusX(8);
        ventanaNave.setRadiusY(18);
        }
        ventanaNave.setFill(Color.CORNFLOWERBLUE);
        //Linea nave1
        Line lineaNave1 = new Line (-3, 75, -3, 145);
        lineaNave1.setStroke(Color.WHITE);
        lineaNave1.setStrokeWidth(2);
        lineaNave1.setFill(Color.WHITE);
        //Linea nave
        Line lineaNave2 = new Line (3, 75, 3, 145);
        lineaNave2.setStroke(Color.WHITE);
        lineaNave2.setStrokeWidth(2);
        lineaNave2.setFill(Color.WHITE);
        //Punta ala izaquierda
        Polygon puntaAlaIzq = new Polygon(new double[]{
            -75, 140,
            -80, 180,
            -70, 175
        });
        puntaAlaIzq.setFill(Color.CRIMSON);
        //Punta ala derecha
        Polygon puntaAlaDcha = new Polygon(new double[]{
            75, 140,
            70, 175,
            80, 180
        });
        puntaAlaDcha.setFill(Color.CRIMSON);
        //Circulo ala iqz
        Circle circuloAlaIqz = new Circle();
        circuloAlaIqz.setCenterX(-38);
        circuloAlaIqz.setCenterY(137);
        circuloAlaIqz.setRadius(6);
        circuloAlaIqz.setFill(Color.CRIMSON);
        //Circulo ala derecha
        Circle circuloAlaDcha = new Circle();
        circuloAlaDcha.setCenterX(38);
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
//        nave.setLayoutX(0);
//        nave.setLayoutY(0);
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
        
        //Imagen nave enemiga
        var naveTitan = new Image(getClass().getResourceAsStream("/images/Titan.png"));
        ImageView naveTitanView = new ImageView(naveTitan);
        
        //Escalar nave Titan
        naveTitanView.setScaleX(0.3);
        naveTitanView.setScaleY(0.3);
        //Rotar nave Titan
        naveTitanView.setRotate(180);
                
        //Añadir al contenedor
        root.getChildren().add(fondoPantallaView1);
        root.getChildren().add(fondoPantallaView2);
        root.getChildren().add(nave);
        root.getChildren().add(naveTitanView);
        
        
        
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
                if (posicionNaveX < 50) {
                    posicionNaveX = 50;
                }else{
                    //Comprobar que no se sale por la derecha
                    if (posicionNaveX > ANCHO_PANTALLA -50) {
                        posicionNaveX = ANCHO_PANTALLA -50;
                    }
                }
                
                //Comprobar si se sale la nave por arriba
                if (posicionNaveY < 0) {
                    posicionNaveY = 0;
                }else{
                    //Comprobar que no se sale por abajo
                    if (posicionNaveY > ALTO_PANTALLA -150) {
                        posicionNaveY = ALTO_PANTALLA -150;
                    }
                }
                //Movimiento naveTitan
                naveTitanView.setLayoutY(posicionYNaveTitan);
                posicionYNaveTitan+= 1;
                //Posicion aleatoria nave Titan
                if (posicionYNaveTitan > ALTO_PANTALLA) {
                    posicionYNaveTitan = -200;
                   

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