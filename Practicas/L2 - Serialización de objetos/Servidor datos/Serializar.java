
import java.io.Serializable;

public class Serializar implements Serializable {
    public String color;
    public float capacidad;
    public transient Boolean tieneEtiqueta;
    public transient String marca;
    public transient float altura;
    public float ancho;

    // constructor
    public Serializar(String color, float capacidad, Boolean tieneEtiqueta, String marca, float altura, float ancho) {
        this.color = color;
        this.capacidad = capacidad;
        this.tieneEtiqueta = tieneEtiqueta;
        this.marca = marca;
        this.altura = altura;
        this.ancho = ancho;

        // Impresión de atributos de objeto serializado
        System.out.println("\n\nObjeto serializado");
        System.out.println(color);
        System.out.println(capacidad);
        System.out.println(ancho);
    }

}
