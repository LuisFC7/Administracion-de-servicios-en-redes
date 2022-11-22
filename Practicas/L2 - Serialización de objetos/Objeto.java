
//Objeto que se no ha serializado aún
import java.net.*;
import java.util.Scanner;
import javax.swing.text.html.ObjectView;
import java.io.*;

public class Objeto {
    public String color;
    public float capacidad;
    public Boolean tieneEtiqueta;
    public String marca;
    public float altura;
    public float ancho;

    // constructor
    public Objeto(String color, float capacidad, Boolean tieneEtiqueta, String marca, float altura, float ancho) {
        this.color = color;
        this.capacidad = capacidad;
        this.tieneEtiqueta = tieneEtiqueta;
        this.marca = marca;
        this.altura = altura;
        this.ancho = ancho;
    }

    public void serializar(String color, float capacidad, Boolean tieneEtiqueta, String marca, float altura,
            float ancho) {
        // Pasamos el objeto creado en el cliente a la clase serializar y aqui lo
        // escribimos
        Serializar objeto2 = new Serializar(color, capacidad, tieneEtiqueta, marca, altura, ancho);
        // Aqui ya se escribe en el txt
        try {
            ObjectOutputStream serializado = new ObjectOutputStream(new FileOutputStream("datos.txt"));
            serializado.writeObject(objeto2);
            serializado.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
