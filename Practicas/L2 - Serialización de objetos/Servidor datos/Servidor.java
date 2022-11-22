import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(7000);
            for (;;) {
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde\n" + cl.getInetAddress() + ":" + cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream());// Aqui se leen los archivos
                // obtenemos la longitud del array de la cantidad de archivos totales que envía
                // el cliente

                String nombre = dis.readUTF();
                System.out.println("\nRecibimos el arhivo: " + nombre);// obtiene solo el nombre del archivo
                long tam = dis.readLong();

                DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));
                long recibidos = 0;
                int porcentaje, n;

                while (recibidos < tam) {
                    // aqui se obtiene el tamaño en bytes para cada archivo
                    int aux = (int) tam;
                    // Y lo casteamos para pasarlo a tamaño de bytes del array de byte
                    byte[] b = new byte[aux];
                    n = dis.read(b);
                    dos.write(b, 0, n);
                    dos.flush();
                    recibidos = recibidos + n;
                    porcentaje = (int) (recibidos * 100 / tam);
                    System.out.println("\nArchivo esta siendo recibido");

                }
                dos.close();
                dis.close();
                cl.close();

                // Se deserializa el objeto apartir del archivo recibido
                ObjectInputStream deserializado = new ObjectInputStream(new FileInputStream("datos.txt"));
                while (true) {
                    Serializar aux = (Serializar) deserializado.readObject();
                    System.out.println("\n\nObjeto deserializado");
                    System.out.println("Color: " + aux.color);
                    System.out.println("Capacidad: " + aux.capacidad);
                    System.out.println("Etiqueta: " + aux.tieneEtiqueta);
                    System.out.println("Marca: " + aux.marca);
                    System.out.println("Altura: " + aux.altura);
                    System.out.println("Ancho: " + aux.ancho);

                }

            }

        } catch (IOException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}