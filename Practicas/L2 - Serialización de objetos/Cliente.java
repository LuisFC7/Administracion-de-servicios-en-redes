import java.net.*;
import java.util.Scanner;
import javax.swing.text.html.ObjectView;
import java.io.*;

public class Cliente {

    public static void main(String[] args) {
        // Se instance el objeto e impresión de objeto sin serializar
        Objeto botella = new Objeto("Azul", (float) 1.5, false, "Bonafont", 20, 8);
        System.out.println("Color: " + botella.color);
        System.out.println("Capacidad: " + botella.capacidad);
        System.out.println("Etiqueta: " + botella.tieneEtiqueta);
        System.out.println("Marca: " + botella.marca);
        System.out.println("Altura: " + botella.altura);
        System.out.println("Ancho: " + botella.ancho);
        String color = botella.color;
        float capacidad = botella.capacidad;
        Boolean tieneEtiqueta = botella.tieneEtiqueta;
        String marca = botella.marca;
        float altura = botella.altura;
        float ancho = botella.ancho;

        // serialización del objeto
        botella.serializar(color, capacidad, tieneEtiqueta, marca, altura, ancho);
        // mandar el objeto al servidor

        try {
            // establecer conexión con el cliente.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n\nEscriba la dirección del servidor: ");
            String host = br.readLine();
            System.out.println("\n\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());
            Socket cl = new Socket(host, pto);

            // extracción de fichero.
            File f = new File("datos.txt");
            String archivo = f.getAbsolutePath();
            String nombre = f.getName();
            long tam = f.length();

            DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
            DataInputStream dis = new DataInputStream(new FileInputStream(archivo));
            dos.writeUTF(nombre);
            dos.flush();
            dos.writeLong(tam);
            dos.flush();
            byte[] b = new byte[1024];
            long enviados = 0;
            int porcentaje, n;

            while (enviados < tam) {
                n = dis.read(b);
                dos.write(b, 0, n);
                dos.flush();
                enviados = enviados + n;
                porcentaje = (int) (enviados * 100 / tam);
                System.out.println("El archivo se esta enviando");
                System.out.println("Enviando: %/r");

            }
            System.out.println("\n\nArchivo enviado");

            // dos.close();
            dos.close();
            dis.close();
            cl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
