/*PongFX

Juego parecido al clásico Space Invaders donde una nave tendrá
que destruir los marcianos y evitar chocar con los asteroides.

Diego Jesús Sánchez Del Corral
2021*/

package diegosanchez.alien;

import java.net.URISyntaxException;
import java.net.URL;
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
import javafx.scene.media.AudioClip;
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
    int VelocidadNaveIzquierda = -5;
    int VelocidadNaveDerecha = 5;
    int VelocidadNaveArriba = -5;
    int VelocidadNaveAbajo = 5;
    
    //Variables Nave
    Group nave;
    Polygon zonaContactoNave;
    
    //Variable para posocion Nave Titan
    int posicionYNaveTitan = -250;
    Random randomPosicionXNaveTitan = new Random();
        int posicionXNaveTitan = randomPosicionXNaveTitan.nextInt(ANCHO_PANTALLA-190);
    Group grupoNaveTitan;
        
    //Variable Velocidad Nave Titan
    float velocidadNaveTitan = 1;
    
    //Variables nave Titán
    Polygon zonaContactoTitan;
    
    //Variable para posocion Nave Blue
    int posicionYNaveBlue = -250;
    Random randomPosicionXNaveBlue = new Random();
        int posicionXNaveBlue = randomPosicionXNaveBlue.nextInt(ANCHO_PANTALLA-190);
    Group grupoNaveBlue;
        
    //Variable Velocidad Nave Blue
    float velocidadNaveBlue = 2;
    
    //Variable Nave Blue
    Polyline zonaContactoBlue;
    
    //Variable para posocion Nave Green
    int posicionYNaveGreen = -350;
    Random randomPosicionXNaveGreen = new Random();
        int posicionXNaveGreen = randomPosicionXNaveGreen.nextInt(ANCHO_PANTALLA)-150;
    Group grupoNaveGreen;
    
    //Variable Nave Green
    Polyline zonaContactoGreen;
        
    //Variable Velocidad Nave Green
    float velocidadNaveGreen = 1.5f;
    
    //Variable para posocion Asteroide1
    int posicionYAsteroide1 = -250;
    Random randomPosicionXAsteroide1 = new Random();
        int posicionXAsteroide1 = randomPosicionXAsteroide1.nextInt(ANCHO_PANTALLA);
    Group grupoAsteroide1;
    Polyline zonaContactoAsteroide1;
    
    //Variable para posocion Asteroide2
    int posicionXAsteroide2 = -700;
    Random randomPosicionYAsteroide2 = new Random();
        int posicionYAsteroide2 = randomPosicionYAsteroide2.nextInt(300)*-1;
    Group grupoAsteroide2;
    Polyline zonaContactoAsteroide2;
        
    //Variable Velocidad Asteroide1
    int velocidadXAsteroide1 = 1;
    int velocidadYAsteroide1 = 3;
    
    //Variable Velocidad Asteroide2
    int velocidadXAsteroide2 = -4;
    int velocidadYAsteroide2 = 2;
       
    //Variable para posicion de disparo lazer
    int posicionXDisparoLazer;
    int posicionYDisparoLazer;
    
    //Variable para disparar
    boolean dispararLazer = false;
    int estadoEspacio = 0;
    int ultimoEstadoEspacio = 0;
    int estadoDisparo;
    int velocidadLazer = 0;
    boolean retornoLazer = true;
    boolean destruyeNave;
    Group grupoDisparoLazer;
    Circle ZonaContactoDisparoLazer;


    //Variables y Constante para puntuación
    final int TAMAÑO_LETRAS = 24;
    Text textoPuntuacion;
    Text textoPuntuacionMax;
    int vidas = 3;
    int puntos = 0;
    int puntoMaximos = 0;
    Text textoNumeroVidas;
    boolean incrementoVidas;
    HBox panePuntuacion;

    //Variable para sonidos
    AudioClip sonidoLazer;
    AudioClip sonidoExplosionNaves;
    AudioClip sonidoExplosionAsteroides;
    AudioClip sonidoExplosionGameOver;
    AudioClip sonidoChoqueNave;
    
    //Variable para el Timeline
    Timeline movimiento;
    
    //Variable para la pantalla
    Pane root;
    
    
    //Variables para las imagenes
    ImageView fondoPantallaView1;
    ImageView fondoPantallaView2;
    ImageView imagenExplosionGameOverView;
    ImageView imagenGameOverView;
    ImageView imagenExplosionGameOver;

    @Override
    public void start(Stage primeraEtapa) {
        
        //Crear el contenedor para poner los objetos
        root = new Pane();
        
        //Crear la escena(ventana)
        Scene escena = new Scene(root, ANCHO_PANTALLA, ALTO_PANTALLA, Color.BLACK);
        primeraEtapa.setResizable(false);
        primeraEtapa.setTitle("Alien");
        primeraEtapa.setScene(escena);
        primeraEtapa.show();
        
        //Llamada al método para crear nave
        crearNave();
        
        //Llamada al método para el fondo
        crearFondo();
        
        //Llamada al método para nave Titán
        crearNaveTitan();   
        
        //Llamada al método para nave Titán
        crearNaveBlue(); 
        
        //Llamada método para nave Green
        crearNaveGreen();
        
        //Lamada al método para Asteroide1
        crearAsteroide1();
        
        //Llamada al método para Asteroide2
        crearAsteroide2();
        
        //Llamada al método para Imagen Explsión Game Over
        crearImagenGameOver();
        
        //Llamada al método para crear y sonido de Lazer
        crearLazer();
        
        //Llamada al metodo sonido explosión naves
        explosionNaves();
        
        //Llamada al método sonido explosión Asteroides
        explosionAsteroides();
        
        //Llamada al método sonido explosión Game Over
        explosionGameOver();
        
        //Llamada al método soonido explosion Choque Nave
        explosionChoqueNave();
        
        //Llamada al método para crear marcadores
        marcadores();
        
        
        //Imagen Game Over 
        var imagenGameOver = new Image(getClass().getResourceAsStream("/images/GameOver.png"));
        imagenGameOverView = new ImageView(imagenGameOver);
        
        //Posicionar imagen Game Over
        imagenGameOverView.setLayoutX(ANCHO_PANTALLA/2 - 258);
        imagenGameOverView.setLayoutY(ALTO_PANTALLA/2 - 62);
        
        //Añadir al contenedor
        root.getChildren().add(fondoPantallaView1);
        root.getChildren().add(fondoPantallaView2);
        root.getChildren().add(grupoNaveTitan);
        root.getChildren().add(grupoNaveBlue);
        root.getChildren().add(grupoNaveGreen);
        root.getChildren().add(grupoAsteroide1);
        root.getChildren().add(grupoAsteroide2);
        root.getChildren().add(grupoDisparoLazer);
        root.getChildren().add(nave);
        root.getChildren().add(panePuntuacion);
        
        
        //Animación
        movimiento = new Timeline(
            new KeyFrame(Duration.seconds(0.017),(var ae) -> {
                //mostrar variables
                //System.out.println("Velocidad Titán: " + velocidadNaveTitan);
                //Movimiento del fondo comprobando que ha llegado al final de la pantalla
                fondoPantallaView1.setLayoutY(posicionYFondo1);
                posicionYFondo1+= velocidadFondo;
                fondoPantallaView2.setLayoutY(posicionYFondo2);
                posicionYFondo2+= velocidadFondo;
                if (posicionYFondo1 >= ALTO_PANTALLA) {
                    posicionYFondo1 = -ALTO_PANTALLA;
                }
                if (posicionYFondo2 >= ALTO_PANTALLA) {
                    posicionYFondo2 = -ALTO_PANTALLA;
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
                //Movimiento nave Titan
                grupoNaveTitan.setLayoutY(posicionYNaveTitan);
                posicionYNaveTitan+= velocidadNaveTitan;
                grupoNaveTitan.setLayoutX(posicionXNaveTitan);
                //Posición aleatoria nave Titan
                if (posicionYNaveTitan > ALTO_PANTALLA) {
                    posicionXNaveTitan = randomPosicionXNaveTitan.nextInt(ANCHO_PANTALLA-190);
                    posicionYNaveTitan = -250;
                    puntos --;
                    textoPuntuacion.setText(String.valueOf(puntos));
                }
                
                //Movimiento nave Blue
                grupoNaveBlue.setLayoutY(posicionYNaveBlue);
                posicionYNaveBlue+= velocidadNaveBlue;
                grupoNaveBlue.setLayoutX(posicionXNaveBlue);
                
                //Posición aleatoria nave Blue
                if (posicionYNaveBlue > ALTO_PANTALLA) {
                    posicionXNaveBlue = randomPosicionXNaveBlue.nextInt(ANCHO_PANTALLA-190);
                    posicionYNaveBlue = -250;
                    puntos --;
                    textoPuntuacion.setText(String.valueOf(puntos));
                }
                
                //Movimiento nave Green
                grupoNaveGreen.setLayoutY(posicionYNaveGreen);
                posicionYNaveGreen+= velocidadNaveGreen;
                grupoNaveGreen.setLayoutX(posicionXNaveGreen);
                
                //Posición aleatoria nave Green
                if (posicionYNaveGreen > ALTO_PANTALLA) {
                    posicionXNaveGreen = randomPosicionXNaveGreen.nextInt(ANCHO_PANTALLA)- 150;
                    System.out.println(posicionXNaveGreen);
                    posicionYNaveGreen = -350;
                    puntos --;
                    textoPuntuacion.setText(String.valueOf(puntos));
                }
                
                //Movimiento Asteroide1
                grupoAsteroide1.setLayoutY(posicionYAsteroide1);
                posicionYAsteroide1 += velocidadYAsteroide1;
                grupoAsteroide1.setLayoutX(posicionXAsteroide1);
                posicionXAsteroide1 -= velocidadXAsteroide1;
                
                //Posición aleatoria Asteroide1
                if (posicionYAsteroide1 > ALTO_PANTALLA) {
                    posicionXAsteroide1 = randomPosicionXAsteroide1.nextInt(ANCHO_PANTALLA + 200);
                    posicionYAsteroide1 = -250;
                }
                
                //Movimiento Asteroide2
                grupoAsteroide2.setLayoutY(posicionYAsteroide2);
                posicionYAsteroide2 += velocidadYAsteroide2;
                grupoAsteroide2.setLayoutX(posicionXAsteroide2);
                posicionXAsteroide2 -= velocidadXAsteroide2;
                
                //Posición aleatoria Asteroide2
                if (posicionXAsteroide2 > ANCHO_PANTALLA) {
                    posicionYAsteroide2 = randomPosicionYAsteroide2.nextInt(300)*-1;
                    posicionXAsteroide2 = -700;
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
                    posicionYNaveTitan = -250;
                    if (vidas != 0){
                    sonidoChoqueNave.play();
                    }
                }
                
                //Sentencia para comprobar si hay colición entre nave y nave Blue
                Shape shapeColisionNaveGrupoNaveBlue = Shape.intersect(zonaContactoNave, zonaContactoBlue);
                //Variable para saber si hay colición
                boolean colisionNaveGrupoNaveBlue = shapeColisionNaveGrupoNaveBlue.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y la nave Blue
                if (colisionNaveGrupoNaveBlue == false) {
                    //Perdida de vidas
                    vidas--;
                    textoNumeroVidas.setText(String.valueOf(vidas));
                    posicionXNaveBlue = randomPosicionXNaveBlue.nextInt(ANCHO_PANTALLA-190);
                    posicionYNaveBlue = -250;  
                    if (vidas != 0){
                    sonidoChoqueNave.play();
                    }
                }
                
                //Sentencia para comprobar si hay colición entre nave y nave Green
                Shape shapeColisionNaveGrupoNaveGreen = Shape.intersect(zonaContactoNave, zonaContactoGreen);
                //Variable para saber si hay colición
                boolean colisionNaveGrupoNaveGreen = shapeColisionNaveGrupoNaveGreen.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y la nave Green
                if (colisionNaveGrupoNaveGreen == false) {
                    //Perdida de vidas
                    vidas--;
                    textoNumeroVidas.setText(String.valueOf(vidas));
                    posicionXNaveGreen = randomPosicionXNaveGreen.nextInt(ANCHO_PANTALLA)- 150;
                    System.out.println(posicionXNaveGreen);
                    posicionYNaveGreen = -350;  
                    if (vidas != 0){
                    sonidoChoqueNave.play();
                    }
                }
                
                //Sentencia para comprobar si hay colición entre nave y Asteroide1
                Shape shapeColisionNaveGrupoAsteroide1 = Shape.intersect(zonaContactoNave, zonaContactoAsteroide1);
                //Variable para saber si hay colición
                boolean colisionNaveGrupoAsteroide1 = shapeColisionNaveGrupoAsteroide1.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y Asteroide1
                if (colisionNaveGrupoAsteroide1 == false) {
                    //Perdida de vidas
                    vidas--;
                    textoNumeroVidas.setText(String.valueOf(vidas));
                    posicionXAsteroide1 = randomPosicionXAsteroide1.nextInt(ANCHO_PANTALLA + 150);
                    posicionYAsteroide1 = -250;
                    if (vidas != 0){
                    sonidoChoqueNave.play();
                    } 
                }
                
                //Sentencia para comprobar si hay colición entre nave y Asteroide2
                Shape shapeColisionNaveGrupoAsteroide2 = Shape.intersect(zonaContactoNave, zonaContactoAsteroide2);
                //Variable para saber si hay colición
                boolean colisionNaveGrupoAsteroide2 = shapeColisionNaveGrupoAsteroide2.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y Asteroide2
                if (colisionNaveGrupoAsteroide2 == false) {
                    //Perdida de vidas
                    vidas--;
                    textoNumeroVidas.setText(String.valueOf(vidas));
                    posicionYAsteroide2 = randomPosicionYAsteroide2.nextInt(300)*-1;
                    posicionXAsteroide2 = -700;
                    if (vidas != 0){
                    sonidoChoqueNave.play();
                    }
                }
                
                //Sentencia para comprobar si hay colición entre disparo lazer y nave Titan
                Shape shapeColisionGrupoDisparoLazerGrupoNaveTitan = Shape.intersect(ZonaContactoDisparoLazer, zonaContactoTitan);
                //Variable para saber si hay colición
                boolean colisionGrupoDisparoLazerGrupoNaveTitan = shapeColisionGrupoDisparoLazerGrupoNaveTitan.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y la nave Titan
                if ((colisionGrupoDisparoLazerGrupoNaveTitan == false) && (colisionNaveGrupoNaveTitan == true)) {
                    //Perdida de vidas 
                    retornoLazer = true;
                    puntos ++;
                    incrementoVidas = false;
                    textoPuntuacion.setText(String.valueOf(puntos));
                    posicionXNaveTitan = randomPosicionXNaveTitan.nextInt(ANCHO_PANTALLA-190);
                    posicionYNaveTitan = -250;
                    destruyeNave = true;
                    sonidoExplosionNaves.play();
                }
                
                //Sentencia para comprobar si hay colición entre disparo lazer y nave Blue
                Shape shapeColisionGrupoDisparoLazerGrupoNaveBlue = Shape.intersect(ZonaContactoDisparoLazer, zonaContactoBlue);
                //Variable para saber si hay colición
                boolean colisionGrupoDisparoLazerGrupoNaveBlue = shapeColisionGrupoDisparoLazerGrupoNaveBlue.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y la nave Blue
                if ((colisionGrupoDisparoLazerGrupoNaveBlue == false) && (colisionNaveGrupoNaveBlue == true)) {
                    //Perdida de vidas 
                    retornoLazer = true;
                    puntos ++;
                    incrementoVidas = false;
                    textoPuntuacion.setText(String.valueOf(puntos));
                    posicionXNaveBlue = randomPosicionXNaveBlue.nextInt(ANCHO_PANTALLA-190);
                    posicionYNaveBlue = -250;
                    destruyeNave = true;
                    sonidoExplosionNaves.play();
                }
                
                //Sentencia para comprobar si hay colición entre disparo lazer y nave Green
                Shape shapeColisionGrupoDisparoLazerGrupoNaveGreen = Shape.intersect(ZonaContactoDisparoLazer, zonaContactoGreen);
                //Variable para saber si hay colición
                boolean colisionGrupoDisparoLazerGrupoNaveGreen = shapeColisionGrupoDisparoLazerGrupoNaveGreen.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan la nave y la nave Green
                if ((colisionGrupoDisparoLazerGrupoNaveGreen == false) && (colisionNaveGrupoNaveGreen == true)) {
                    //Perdida de vidas 
                    retornoLazer = true;
                    puntos ++;
                    incrementoVidas = false;
                    textoPuntuacion.setText(String.valueOf(puntos));
                    posicionXNaveGreen = randomPosicionXNaveGreen.nextInt(ANCHO_PANTALLA)- 150;
                    System.out.println(posicionXNaveGreen);
                    posicionYNaveGreen = -350; 
                    destruyeNave = true;
                    sonidoExplosionNaves.play();
                }
                
                //Sentencia para comprobar si hay colición entre disparo lazer y Asteroide1
                Shape shapeColisionGrupoDisparoLazerGrupoAsteroide1 = Shape.intersect(ZonaContactoDisparoLazer, zonaContactoAsteroide1);
                //Variable para saber si hay colición
                boolean colisionGrupoDisparoLazerGrupoAsteroide1 = shapeColisionGrupoDisparoLazerGrupoAsteroide1.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan lazer y Asteroide1
                if ((colisionGrupoDisparoLazerGrupoAsteroide1 == false) && (colisionNaveGrupoAsteroide1 == true)) {
                    sonidoExplosionAsteroides.play();
                    retornoLazer = true;
                }
                
                //Sentencia para comprobar si hay colición entre disparo lazer y Asteroide2
                Shape shapeColisionGrupoDisparoLazerGrupoAsteroide2 = Shape.intersect(ZonaContactoDisparoLazer, zonaContactoAsteroide2);
                //Variable para saber si hay colición
                boolean colisionGrupoDisparoLazerGrupoAsteroide2 = shapeColisionGrupoDisparoLazerGrupoAsteroide2.getBoundsInLocal().isEmpty();
                //Sentencia para saber si colicionan lazer y Asteroide1
                if ((colisionGrupoDisparoLazerGrupoAsteroide2 == false) && (colisionNaveGrupoAsteroide2 == true)) {
                    sonidoExplosionAsteroides.play();
                    retornoLazer = true;
                }
                
                //Posición disparo lazer
                grupoDisparoLazer.setLayoutX(posicionXDisparoLazer);                
                grupoDisparoLazer.setLayoutY(posicionYDisparoLazer);
                
                //Disparar con tecla espacio
                if (dispararLazer == true) {                
                    estadoEspacio = 1;
                    sonidoLazer.play();
                }
                
                if ((estadoEspacio == 1) && (ultimoEstadoEspacio == 0)){
                    estadoDisparo = 1 - estadoDisparo;
                }
                ultimoEstadoEspacio = estadoEspacio;
                if (estadoDisparo == 1){ 
                    posicionYDisparoLazer -= velocidadLazer;
                    velocidadLazer = 8;
                    dispararLazer = false;
                }
                 else {
                    posicionXDisparoLazer = posicionNaveX - 171;
                    posicionYDisparoLazer = posicionNaveY - 108;
                }
                System.out.println("pulsar espacio = " + estadoEspacio);
                System.out.println("disparando = " + estadoDisparo);
                if ((posicionYDisparoLazer < -180) || (retornoLazer == true)){
                    posicionXDisparoLazer = posicionNaveX - 171;
                    posicionYDisparoLazer = posicionNaveY - 108;
                    estadoDisparo = 0;
                    velocidadLazer = 0;
                    retornoLazer = false;
                }
                
               //Aumento de velocidad por marcador             
                
                if (puntos == 5){                   
                    velocidadNaveTitan = 1.5f;
                    velocidadNaveBlue = 2.5f;
                    velocidadNaveGreen = 2.0f; 
                }             
                if (puntos == 15) {
                    velocidadXAsteroide1 = 2;
                    velocidadYAsteroide1 = 4;
                    VelocidadNaveIzquierda = -7;
                    VelocidadNaveDerecha = 7;
                    VelocidadNaveArriba = -7;
                    VelocidadNaveAbajo = 7;
                }
                if (puntos == 20) {
                    velocidadNaveTitan = 2f;
                    velocidadNaveBlue = 3f;
                    velocidadNaveGreen = 2.5f;
                    velocidadXAsteroide2 = -5;
                    velocidadYAsteroide2 = 3;
                }
                if (puntos == 25) {
                    velocidadXAsteroide1 = 3;
                    velocidadYAsteroide1 = 5;
                    VelocidadNaveIzquierda = -9;
                    VelocidadNaveDerecha = 9;
                    VelocidadNaveArriba = -9;
                    VelocidadNaveAbajo = 9;
                }
                if (puntos == 30) {
                    velocidadNaveTitan = 2.5f;
                    velocidadNaveBlue = 3.5f;
                    velocidadNaveGreen = 3f;
                }
                if (puntos == 35) {
                    velocidadXAsteroide1 = 3;
                    velocidadYAsteroide1 = 5;
                    VelocidadNaveIzquierda = -10;
                    VelocidadNaveDerecha = 10;
                    VelocidadNaveArriba = -10;
                    VelocidadNaveAbajo = 10;
                    velocidadXAsteroide2 = -6;
                    velocidadYAsteroide2 = 4;
                }
                if (puntos == 40) {
                    velocidadNaveTitan = 3f;
                    velocidadNaveBlue = 4f;
                    velocidadNaveGreen = 3.5f;
                }
                if (puntos == 45) {
                    velocidadXAsteroide1 = 4;
                    velocidadYAsteroide1 = 5;
                    VelocidadNaveIzquierda = -11;
                    VelocidadNaveDerecha = 11;
                    VelocidadNaveArriba = -11;
                    VelocidadNaveAbajo = 11;
                    
                }
                if ((puntos == 50) && (incrementoVidas == false)){
                    incrementoVidas = true;
                    velocidadNaveTitan = 3.5f;
                    velocidadNaveBlue = 4.5f;
                    velocidadNaveGreen = 4f;
                    velocidadXAsteroide2 = -7;
                    velocidadYAsteroide2 = 4;
                    vidas ++;
                    textoNumeroVidas.setText(String.valueOf(vidas));
                }
                if (puntos == 55) {                   
                    velocidadXAsteroide1 = 5;
                    velocidadYAsteroide1 = 6;
                    VelocidadNaveIzquierda = -12;
                    VelocidadNaveDerecha = 12;
                    VelocidadNaveArriba = -12;
                    VelocidadNaveAbajo = 12;
                    velocidadNaveTitan = 6f;
                    velocidadNaveBlue = 8f;
                }
                if (puntos == 60) {
                    velocidadNaveTitan = 4f;
                    velocidadNaveBlue = 5f;
                    velocidadNaveGreen = 4.5f;
                }
                
                //Actualizacion de máximo de puntos
                if (puntos > puntoMaximos) {
                        //Cambiar la nueva puntuación
                         puntoMaximos = puntos;
                        textoPuntuacionMax.setText(String.valueOf(puntoMaximos));
                }
                //Parar juego si pierdes las vidas
                if ((vidas <=0) || (puntos < 0)){
                    imagenExplosionGameOverView.setLayoutX(posicionNaveX - 77);                
                    imagenExplosionGameOverView.setLayoutY(posicionNaveY + 40);
                    root.getChildren().add(imagenExplosionGameOverView);
                    root.getChildren().remove(nave);
                    sonidoExplosionGameOver.play();
                    root.getChildren().add(imagenGameOverView);
                    movimiento.stop();
                }                
            })
        ); //Final timeLine 
        movimiento.setCycleCount (Timeline.INDEFINITE);
        movimiento.play();
        
        
        //Detección de las pulsaciones de las teclas
        escena.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()) {
                case LEFT:
                    //Pulsada tecla izquierda
                    velocidadNaveX = VelocidadNaveIzquierda;
                    break;
                case RIGHT:
                    //Pulsada tecla derecha
                    velocidadNaveX = VelocidadNaveDerecha;
                    break;
                case UP:
                    //Pulsada tecla arriba
                    velocidadNaveY = VelocidadNaveArriba;
                    break;
                case DOWN:
                    //Pulsada tecla abajo
                    velocidadNaveY = VelocidadNaveAbajo;
                    break;
                case SPACE:
                    //Pulsada tecla espacio                    
                    dispararLazer = true;                    
                    break;
                case ENTER:
                    //Pulsada tecla enter                    
                    resetGame();                   
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
                    //dispararLazer = false; 
                    ultimoEstadoEspacio = 0;
                    estadoEspacio = 0;
                    break;
            }
        });
        
    }
    
    //Método para crear nave
    private void crearNave(){
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
        zonaContactoNave = new Polygon(new double[]{
            0.0, 15.0,
            -75.0, 170.0,
            75.0, 170.0 
        }); 
        zonaContactoNave.setFill(Color.YELLOW);
        zonaContactoNave.setVisible(false);
        
        //Agrupar todos los objetos de la nave
        nave = new Group();
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
        
        //Escalar la nave
        nave.setScaleX(0.4);
        nave.setScaleY(0.4);
    }
    
    //Método para crear el fondo
    private void crearFondo(){
        //Imegen 1 de pantalla
        var fondoPantalla1 = new Image(getClass().getResourceAsStream("/images/FondoEstrellas1.png"));
        fondoPantallaView1 = new ImageView(fondoPantalla1);
        
        //Imegen 2 de pantalla
        var fondoPantalla2 = new Image(getClass().getResourceAsStream("/images/FondoEstrellas2.png"));
        fondoPantallaView2 = new ImageView(fondoPantalla2);
        
        //Posicionar imagen 1
        fondoPantallaView1.setLayoutX(posicionXFondo1);
        fondoPantallaView1.setLayoutY(posicionYFondo1);
        
        //Posicionar imagen 2
        fondoPantallaView2.setLayoutX(posicionXFondo2);
        fondoPantallaView2.setLayoutY(posicionYFondo2);
    }
    
    //Método para crear nave Titán
    private void crearNaveTitan(){
        //Imagen nave Titan
        var naveTitan = new Image(getClass().getResourceAsStream("/images/Titan.png"));
        ImageView naveTitanView = new ImageView(naveTitan);
        
        //Zona de contacto nave Titán
        zonaContactoTitan = new Polygon(new double[]{
            0.0, 0.0,
            -140.0, 300.0,
            140.0, 300.0 
        }); 
        zonaContactoTitan.setFill(Color.YELLOW);
        zonaContactoTitan.setVisible(false);
        zonaContactoTitan.setLayoutX(140);
        
        //Agrupar imagen y objetos de la nave Titán
        grupoNaveTitan = new Group();
        grupoNaveTitan.getChildren().add(zonaContactoTitan);
        grupoNaveTitan.getChildren().add(naveTitanView);
        
        //Escalar nave Titán
        grupoNaveTitan.setScaleX(0.3);
        grupoNaveTitan.setScaleY(0.3);
        
        //Rotar nave Titán
        grupoNaveTitan.setRotate(180); 
    }
    
    //Método para crear nave Blue
    private void crearNaveBlue(){
        //Imagen nave Blue
        var naveBlue = new Image(getClass().getResourceAsStream("/images/Blue.png"));
        ImageView naveBlueView = new ImageView(naveBlue);
        
        //Zona de contacto nave Blue
        zonaContactoBlue = new Polyline();
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
        zonaContactoBlue.setVisible(false);
        zonaContactoBlue.setLayoutX(75);
        zonaContactoBlue.setLayoutY(0);
        
        //Agrupar imagen y objetos de la nave Blue
        grupoNaveBlue = new Group();
        grupoNaveBlue.getChildren().add(zonaContactoBlue);
        grupoNaveBlue.getChildren().add(naveBlueView);
        
        //Escalar nave Blue
        grupoNaveBlue.setScaleX(0.6);
        grupoNaveBlue.setScaleY(0.6);
        
        //Rotar nave Blue
        grupoNaveBlue.setRotate(180);
    }
    
    //Método para crear nave Green
    private void crearNaveGreen(){
        //Imagen nave Green
        var naveGreen = new Image(getClass().getResourceAsStream("/images/NaveGreen.png"));
        ImageView naveGreenView = new ImageView(naveGreen);
        
        //Zona de contacto nave Green
        zonaContactoGreen = new Polyline();
        zonaContactoGreen.getPoints().addAll(new Double[]{
            0.0, 0.0,
            -65.0, 100.0,
            -55.0, 170.0 ,
            -183.0, 280.0,
            -183.0, 305.0,
            0.0, 230.0,
            183.0, 305.0,
            183.0, 280.0,
            55.0, 170.0,
            65.0, 100.0
        });
        zonaContactoGreen.setFill(Color.YELLOW);
        zonaContactoGreen.setVisible(false);
        zonaContactoGreen.setLayoutX(183);
        zonaContactoGreen.setLayoutY(0);
        
        //Agrupar imagen y objetos de la nave Blue
        grupoNaveGreen = new Group();
        grupoNaveGreen.getChildren().add(zonaContactoGreen);
        grupoNaveGreen.getChildren().add(naveGreenView);
        
        //Escalar nave Blue
        grupoNaveGreen.setScaleX(0.25);
        grupoNaveGreen.setScaleY(0.25);
        
        //Rotar nave Blue
        grupoNaveGreen.setRotate(180);
    }
     
    //Método para crear Asteroide1
    private void crearAsteroide1(){
        //Imagen nave Asteroide1
        var asteroide1 = new Image(getClass().getResourceAsStream("/images/Asteroide_1.png"));
        ImageView Asteroide1View = new ImageView(asteroide1);
        
        //Zona de contacto Asteroide1
        zonaContactoAsteroide1 = new Polyline();
        zonaContactoAsteroide1.getPoints().addAll(new Double[]{
            0.0, 0.0,
            -150.0, 20.0,
            -300.0, 250.0 ,
            -180.0, 380.0,
            0.0, 455.0,
            235.0, 260.0,
            140.0, 75.0,
            0.0, 0.0,
        });
        zonaContactoAsteroide1.setFill(Color.YELLOW);
        zonaContactoAsteroide1.setVisible(false);
        zonaContactoAsteroide1.setLayoutX(300);
        zonaContactoAsteroide1.setLayoutY(0);
        
        //Agrupar imagen y objetos de Asteroide1
        grupoAsteroide1 = new Group();
        grupoAsteroide1.getChildren().add(zonaContactoAsteroide1);
        grupoAsteroide1.getChildren().add(Asteroide1View);
        
        //Escalar Asteroide 1
        grupoAsteroide1.setScaleX(0.15);
        grupoAsteroide1.setScaleY(0.15);
    }
    
    //Método para crear Asteroide2
    private void crearAsteroide2(){
        //Imagen nave Asteroide2
        var asteroide2 = new Image(getClass().getResourceAsStream("/images/Asteroide_2.png"));
        ImageView Asteroide2View = new ImageView(asteroide2);
        
        //Zona de contacto Asteroide2
        zonaContactoAsteroide2 = new Polyline();
        zonaContactoAsteroide2.getPoints().addAll(new Double[]{
            0.0, 0.0,
            -150.0, 53.0,
            -240.0, 270.0 ,
            -160.0,440.0,
            -5.0, 475.0,
            120.0, 460.0,
            260.0, 290.0,
            190.0, 120.0,
            110.0, 95.0,
            60.0, 5.0,
            0.0, 0.0,
        });
        zonaContactoAsteroide2.setFill(Color.YELLOW);
        zonaContactoAsteroide2.setVisible(false);
        zonaContactoAsteroide2.setLayoutX(240);
        zonaContactoAsteroide2.setLayoutY(0);
        
        //Agrupar imagen y objetos de Asteroide2
        grupoAsteroide2 = new Group();
        grupoAsteroide2.getChildren().add(zonaContactoAsteroide2);
        grupoAsteroide2.getChildren().add(Asteroide2View);
        
        //Escalar Asteroide 2
        grupoAsteroide2.setScaleX(0.15);
        grupoAsteroide2.setScaleY(0.15);
    }
    
    //Método para crear imagen Explosión Game Over
    private void crearImagenGameOver(){
    //Imagen explosion game over
        var imagenExplosionGameOver = new Image(getClass().getResourceAsStream("/images/explosionGameOver.png"));
        imagenExplosionGameOverView = new ImageView(imagenExplosionGameOver);
    }
    
    //Método para crear lazer
    private void crearLazer(){
        //Imagen disparo lazer
        var disparoLazer = new Image(getClass().getResourceAsStream("/images/DisparoLaser.png"));
        ImageView disparoLazerView = new ImageView(disparoLazer);
        
        //Zona decontacto disparo lazer
        ZonaContactoDisparoLazer = new Circle();
        ZonaContactoDisparoLazer.setCenterX(86);
        ZonaContactoDisparoLazer.setCenterY(115);
        ZonaContactoDisparoLazer.setRadius(20);
        ZonaContactoDisparoLazer.setFill(Color.YELLOW);
        ZonaContactoDisparoLazer.setVisible(false);  
                
        //Agrupar imagen y objetos de disparo lazer
        grupoDisparoLazer = new Group();
        grupoDisparoLazer.getChildren().add(disparoLazerView);
        grupoDisparoLazer.getChildren().add(ZonaContactoDisparoLazer);
        
        //Escalar disparo lazer
        grupoDisparoLazer.setScaleX(0.25);
        grupoDisparoLazer.setScaleY(0.25); 
        
        //Sonido disparo lazer //   
        URL urlAudio = getClass().getResource("/sonidos/SHOOT013.mp3");
        if(urlAudio != null) {
            try {
                sonidoLazer = new AudioClip(urlAudio.toURI().toString());
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
        System.out.println("No se ha encontrado el archivo de audio");
        }
    }
    
    //Método para sonido ecplosion Naves
    private void explosionNaves(){
        //Sonido de explosión naves enemigas //   
        URL urlExplosionNaves = getClass().getResource("/sonidos/explosion_Naves.mp3");
        if(urlExplosionNaves != null) {
            try {
                sonidoExplosionNaves = new AudioClip(urlExplosionNaves.toURI().toString());
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
        System.out.println("No se ha encontrado el archivo de audio");
        }
    }
    
    //Método para sonido explosión Asteroides
    private void explosionAsteroides(){
        //Sonido de explosión asteroides //   
        URL urlExplosionAsteroides = getClass().getResource("/sonidos/explosion_asteroides.mp3");
        if(urlExplosionAsteroides != null) {
            try {
                sonidoExplosionAsteroides = new AudioClip(urlExplosionAsteroides.toURI().toString());
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
        System.out.println("No se ha encontrado el archivo de audio");
        }
    }
    
    //Método para sonido explosión Game Over
    private void explosionGameOver(){
        //Sonido de explosión game over //   
        URL urlExplosionGameOver = getClass().getResource("/sonidos/explosion1GameOver.mp3");
        if(urlExplosionGameOver != null) {
            try {
                sonidoExplosionGameOver = new AudioClip(urlExplosionGameOver.toURI().toString());
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
        System.out.println("No se ha encontrado el archivo de audio");
        }
    }
    
    //Método ara sonido explosion Choque nave
    private void explosionChoqueNave(){
        //Sonido de choque de la nave   
        URL urlChoqueNave = getClass().getResource("/sonidos/ChoqueNave.mp3");
        if(urlChoqueNave != null) {
            try {
                sonidoChoqueNave = new AudioClip(urlChoqueNave.toURI().toString());
            } catch (URISyntaxException ex) {
                System.out.println("Error en el formato de ruta de archivo de audio");
            }            
        } else {
        System.out.println("No se ha encontrado el archivo de audio");
        }
    }
    
    //Método para crear marcadores
    private void marcadores(){
        //Crear los marcadores con Layout
        //Layout principal
        panePuntuacion = new HBox();
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
        textoPuntuacionMax = new Text("0");
        textoPuntuacionMax.setFont(Font.font(TAMAÑO_LETRAS));
        textoPuntuacionMax.setFill(Color.WHITE);
        
        //Texto de etiqueta para Vida
        Text textoVidas = new Text("Vidas:");
        textoVidas.setFont(Font.font(TAMAÑO_LETRAS));
        textoVidas.setFill(Color.RED);
        
        //Texto para Vida
        textoNumeroVidas = new Text("3");
        textoNumeroVidas.setFont(Font.font(TAMAÑO_LETRAS));
        textoNumeroVidas.setFill(Color.WHITE);
        
        //Añadir los textos a los Layout reservados para ello
        panePuntuacionActual.getChildren().add(textoPuntos);
        panePuntuacionActual.getChildren().add(textoPuntuacion);
        panePuntuacionMax.getChildren().add(textoPuntosMax);
        panePuntuacionMax.getChildren().add(textoPuntuacionMax);
        paneVidas.getChildren().add(textoVidas);
        paneVidas.getChildren().add(textoNumeroVidas);
    }
    //Método para reiniciar el juego
    private void resetGame() {
        //Reiniciar Timeline
        movimiento.play();
        //Reinicio puntos
        puntos = 0;
        textoPuntuacion.setText(String.valueOf(puntos));
        //Reinicio Vidas
        vidas = 3;
        textoNumeroVidas.setText(String.valueOf(vidas));
        //Reinicio posicion  y  velocidad Nave
        posicionNaveX = 450;
        posicionNaveY = 550;
        VelocidadNaveIzquierda = -5;
        VelocidadNaveDerecha = 5;
        VelocidadNaveArriba = -5;
        VelocidadNaveAbajo = 5; 
        //Reinicio velocidades Titan, Blue, Green, Asteroide1, Asteroide2 y Lazer
        velocidadNaveTitan = 1;
        velocidadNaveBlue = 2;
        velocidadNaveGreen = 1.5f;
        velocidadXAsteroide1 = 1;
        velocidadYAsteroide1 = 3;
        velocidadXAsteroide2 = -4;
        velocidadYAsteroide2 = 2;
        velocidadLazer = 0;
        //Reinicio posicion Titan, Blue, Green, Asteroide1, Asteroide2 y Lazer
        posicionXNaveTitan = randomPosicionXNaveTitan.nextInt(ANCHO_PANTALLA-190);
        posicionYNaveTitan = -250;
        posicionXNaveBlue = randomPosicionXNaveBlue.nextInt(ANCHO_PANTALLA-190);
        posicionYNaveBlue = -250;    
        posicionXNaveGreen = randomPosicionXNaveGreen.nextInt(ANCHO_PANTALLA)- 150;
        posicionYNaveGreen = -350;
        posicionXAsteroide1 = randomPosicionXAsteroide1.nextInt(ANCHO_PANTALLA);  
        posicionYAsteroide1 = -250;
        posicionXAsteroide2 = -700;
        posicionYAsteroide2 = randomPosicionYAsteroide2.nextInt(300)*-1;
        posicionXDisparoLazer = posicionNaveX - 171;
        posicionYDisparoLazer = posicionNaveY - 108;
        //Remover imágenes
        root.getChildren().remove(imagenExplosionGameOverView);
        root.getChildren().add(nave);
        root.getChildren().remove(imagenGameOverView);
    }
    
    public static void main(String[] args) {
        launch();
    }

}