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
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;




public class Alien extends Application {
    
    //Variables fondo de pantalla
    final int ALTO_PANTALLA = 700;
    final int ANCHO_PANTALLA = 900;
    float posicionXFondo1 = 0;
    float posicionYFondo1 = 0;
    float posicionXFondo2 = 0;
    float posicionYFondo2 = -700;
    float velocidadFondo = 0.5f;
    //Variable para posición de nave
    int posicionNaveX = 450;
    int posicionNaveY = 550;  
    //Variable para velocidad de movimiento de la nave
    int velocidadNaveX = 0;
    int velocidadNaveY = 0;
    //Variable para posocion nave Titan
    int posicionYNaveTitan = -250;
    Random randomPosicionXNaveTitan = new Random();
        int posicionXNaveTitan = randomPosicionXNaveTitan.nextInt(ANCHO_PANTALLA-190);
    //Variable para posocion nave Blue
    int posicionYNaveBlue = -250;
    Random randomPosicionXNaveBlue = new Random();
        int posicionXNaveBlue = randomPosicionXNaveBlue.nextInt(ANCHO_PANTALLA-190);
    //Variable para posicion de disparo lazer
    int posicionXDisparoLazer;
    int posicionYDisparoLazer;
    //Variable para disparar
    boolean dispararLazer = false;
    int velocidadLazer = 0;
    //Variables y Constante para puntuación
    final int TAMAÑO_LETRAS = 24;
    Text textoPuntuacion;
    int vidas = 3;
    int puntos = 0;

   //audioclip

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
        //Zona de contacto nave
        Polygon zonaContactoNave = new Polygon(new double[]{
            0.0, 15.0,
            -75.0, 170.0,
            75.0, 170.0 
        }); 
        zonaContactoNave.setFill(Color.YELLOW);
        zonaContactoNave.setVisible(false);
        
        //Agrupar todos los objetos de la nave
        Group nave = new Group();
        nave.getChildren().add(zonaContactoNave);
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
        //nave.setLayoutX(0);
        //nave.setLayoutY(0);

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
        
        //Imagen nave Titan
        var naveTitan = new Image(getClass().getResourceAsStream("/images/Titan.png"));
        ImageView naveTitanView = new ImageView(naveTitan);
        
        //29-01-2021 Zona de contacto nave Titan
        Polygon zonaContactoTitan = new Polygon(new double[]{
            0.0, 0.0,
            -140.0, 300.0,
            140.0, 300.0 
        }); 
        zonaContactoTitan.setFill(Color.YELLOW);
        zonaContactoTitan.setVisible(false);
        zonaContactoTitan.setLayoutX(140);
        
        //Agrupar imagen y objetos de la nave Titan
        Group grupoNaveTitan = new Group();
        grupoNaveTitan.getChildren().add(zonaContactoTitan);
        grupoNaveTitan.getChildren().add(naveTitanView);
        
        //Escalar nave Titan
        grupoNaveTitan.setScaleX(0.3);
        grupoNaveTitan.setScaleY(0.3);
        //Rotar nave Titan
        grupoNaveTitan.setRotate(180);
        
        //Zona decontacto disparo lazer
        Circle ZonaContactoDisparoLazer = new Circle();
        ZonaContactoDisparoLazer.setCenterX(86);
        ZonaContactoDisparoLazer.setCenterY(115);
        ZonaContactoDisparoLazer.setRadius(20);
        ZonaContactoDisparoLazer.setFill(Color.YELLOW);
        ZonaContactoDisparoLazer.setVisible(false);       
        
        //Imagen nave Blue
        var naveBlue = new Image(getClass().getResourceAsStream("/images/Blue.png"));
        ImageView naveBlueView = new ImageView(naveBlue);
        
        //Zona de contacto nave Blue
        Polyline zonaContactoBlue = new Polyline();
        zonaContactoBlue.getPoints().addAll(new Double[]{
            0.0, 22.0,
            -35.0, 75.0,
            -40.0, 0.0 ,
            -70.0, 75.0,
            0.0, 110.0,
            70.0, 75.0,
            40.0, 0.0,
            35.0, 75.0
        });
        zonaContactoBlue.setFill(Color.YELLOW);
        zonaContactoBlue.setVisible(true);
        zonaContactoBlue.setLayoutX(75);
        zonaContactoBlue.setLayoutY(0);
        
        //Agrupar imagen y objetos de la nave Blue
        Group grupoNaveBlue = new Group();
        grupoNaveBlue.getChildren().add(zonaContactoBlue);
        grupoNaveBlue.getChildren().add(naveBlueView);
        
        //Escalar nave Blue
        grupoNaveBlue.setScaleX(0.6);
        grupoNaveBlue.setScaleY(0.6);
        
        //Rotar nave Blue
        grupoNaveBlue.setRotate(180);
                
        //Imagen disparo lazer
        var disparoLazer = new Image(getClass().getResourceAsStream("/images/DisparoLaser.png"));
        ImageView disparoLazerView = new ImageView(disparoLazer);
                
        //Agrupar imagen y objetos de la nave Titan
        Group grupoDisparoLazer = new Group();
        grupoDisparoLazer.getChildren().add(disparoLazerView);
        grupoDisparoLazer.getChildren().add(ZonaContactoDisparoLazer);
        
        //Escalar disparo lazer
        grupoDisparoLazer.setScaleX(0.25);
        grupoDisparoLazer.setScaleY(0.25); 
        
        //Sonido disparo lazer    
        //AudioClip sonidoLazer = new AudioClip(getClass().getResourceAsStream("/sonidos/SHOOT013.mp3").toString());
        //AudioClip sonidoLazer = new AudioClip("\"D:\DAW\PROGRAMACIÓN\Sonidos\Sound Effects Shooting sounds 002\SHOOT013.mp3\"");
        
        //Crear los marcadores con Layout
        //Layout principal
        HBox panePuntuacion = new HBox();
        panePuntuacion.setTranslateY(20);
        panePuntuacion.setMinWidth(ANCHO_PANTALLA);
        panePuntuacion.setAlignment(Pos.CENTER);
        panePuntuacion.setSpacing(100);
        
        //Layout para puntuación actual
        HBox panePuntuacionActual = new HBox();
        panePuntuacionActual.setSpacing(10);
        panePuntuacion.getChildren().add(panePuntuacionActual);
        
        //Layout para puntuación máxima
        HBox panePuntuacionMax = new HBox();
        panePuntuacionMax.setSpacing(10);
        panePuntuacion.getChildren().add(panePuntuacionMax);
        
        //Layout para vidas
        HBox paneVidas = new HBox();
        paneVidas.setSpacing(10);
        panePuntuacion.getChildren().add(paneVidas);
        
        //Texo de etiqueta para la puntuación
        Text textoPuntos = new Text ("Puntos:");
        textoPuntos.setFont(Font.font(TAMAÑO_LETRAS));
        textoPuntos.setFill(Color.GREEN);
        
        //Texo para la puntuación
        textoPuntuacion = new Text ("0");
        textoPuntuacion.setFont(Font.font(TAMAÑO_LETRAS));
        textoPuntuacion.setFill(Color.WHITE);
        
        //Texto de etiqueta para la puntuación máxima
        Text textoPuntosMax = new Text("Max.Puntuación:");
        textoPuntosMax.setFont(Font.font(TAMAÑO_LETRAS));
        textoPuntosMax.setFill(Color.BLUE);
        
        //Texto para la puntuación máxima
        Text textoPuntuacionMax = new Text("0");
        textoPuntuacionMax.setFont(Font.font(TAMAÑO_LETRAS));
        textoPuntuacionMax.setFill(Color.WHITE);
        
        //Texto de etiqueta para Vida
        Text textoVidas = new Text("Vidas:");
        textoVidas.setFont(Font.font(TAMAÑO_LETRAS));
        textoVidas.setFill(Color.RED);
        
        //Texto para Vida
        Text textoNumeroVidas = new Text("3");
        textoNumeroVidas.setFont(Font.font(TAMAÑO_LETRAS));
        textoNumeroVidas.setFill(Color.WHITE);
        
        //Añadir los textos a los Layout reservados para ello
        panePuntuacionActual.getChildren().add(textoPuntos);
        panePuntuacionActual.getChildren().add(textoPuntuacion);
        panePuntuacionMax.getChildren().add(textoPuntosMax);
        panePuntuacionMax.getChildren().add(textoPuntuacionMax);
        paneVidas.getChildren().add(textoVidas);
        paneVidas.getChildren().add(textoNumeroVidas);
        
        
        //Añadir al contenedor
        root.getChildren().add(fondoPantallaView1);
        root.getChildren().add(fondoPantallaView2);
        root.getChildren().add(grupoNaveTitan);
        root.getChildren().add(grupoNaveBlue);
        root.getChildren().add(nave);
        root.getChildren().add(grupoDisparoLazer);
        //root.getChildren().add(sonidoLazer);
        root.getChildren().add(panePuntuacion);
        //root.getChildren().add(naveBlueView);
        
        
        
        
        //Animación
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
                if (posicionNaveY < -30) {
                    posicionNaveY = -30;
                }else{
                    //Comprobar que no se sale por abajo
                    if (posicionNaveY > ALTO_PANTALLA -150) {
                        posicionNaveY = ALTO_PANTALLA -150;
                    }
                }
                //Movimiento naveTitan
                grupoNaveTitan.setLayoutY(posicionYNaveTitan);
                posicionYNaveTitan+= 1;
                grupoNaveTitan.setLayoutX(posicionXNaveTitan);
                //Posición aleatoria nave Titan
                if (posicionYNaveTitan > ALTO_PANTALLA) {
                    posicionXNaveTitan = randomPosicionXNaveTitan.nextInt(ANCHO_PANTALLA-190);
                    System.out.println(posicionXNaveTitan);
                    posicionYNaveTitan = -250;
                }
                
                //Movimiento nave Blue
                grupoNaveBlue.setLayoutY(posicionYNaveBlue);
                posicionYNaveBlue+= 1;
                grupoNaveBlue.setLayoutX(posicionXNaveBlue);
                //Posición aleatoria nave Blue
                if (posicionYNaveBlue > ALTO_PANTALLA) {
                    posicionXNaveBlue = randomPosicionXNaveBlue.nextInt(ANCHO_PANTALLA-190);
                    System.out.println(posicionXNaveBlue);
                    posicionXNaveBlue = -250;
                }
                
                //Posición disparo lazer
                if (dispararLazer == false) {
                posicionXDisparoLazer = posicionNaveX - 171;
                posicionYDisparoLazer = posicionNaveY - 108;
                }
                //Posición disparo lazer
                grupoDisparoLazer.setLayoutX(posicionXDisparoLazer);                
                grupoDisparoLazer.setLayoutY(posicionYDisparoLazer);
                if (dispararLazer == true ) {                
                    posicionYDisparoLazer -= velocidadLazer;
                    velocidadLazer = 8;
                
                    /*while (posicionYDisparoLazer > 0) {
                        dispararLazer = true;
                    }*/
                    
                }
                
                //Sentencia para comprobar si hay colición entre nave y nave Titan
                Shape shapeColisionNaveGrupoNaveTitan = Shape.intersect(zonaContactoNave, zonaContactoTitan);
                //Variable para saber si hay colición
                boolean colisionNaveGrupoNaveTitan = shapeColisionNaveGrupoNaveTitan.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y la nave Titan
                if (colisionNaveGrupoNaveTitan == false) {
                    //Perdida de vidas
                    vidas--;
                    textoNumeroVidas.setText(String.valueOf(vidas));
                    posicionXNaveTitan = randomPosicionXNaveTitan.nextInt(ANCHO_PANTALLA-190);
                    System.out.println(posicionXNaveTitan);
                    posicionYNaveTitan = -250;
                    
                }
                
                //Sentencia para comprobar si hay colición entre disparo lazer y nave Titan
                Shape shapeColisionGrupoDisparoLazerGrupoNaveTitan = Shape.intersect(ZonaContactoDisparoLazer, zonaContactoTitan);
                //Variable para saber si hay colición
                boolean colisionGrupoDisparoLazerGrupoNaveTitan = shapeColisionGrupoDisparoLazerGrupoNaveTitan.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y la nave Titan
                if ((colisionGrupoDisparoLazerGrupoNaveTitan == false) && (colisionNaveGrupoNaveTitan == true)) {
                    //Perdida de vidas               
                    puntos ++;
                    textoPuntuacion.setText(String.valueOf(puntos));
                    posicionXNaveTitan = randomPosicionXNaveTitan.nextInt(ANCHO_PANTALLA-190);
                    System.out.println(posicionXNaveTitan);
                    posicionYNaveTitan = -250;
                    
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
                case SPACE:
                    //Pulsada tecla espacio
                    dispararLazer = true; 
                    //sonidoLazer.play();
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
                case SPACE:
                    //Dejar de pulsar tecla espacio
                    dispararLazer = false; 
                    break;
            }
        });
        
    }
    public static void main(String[] args) {
        launch();
    }

}