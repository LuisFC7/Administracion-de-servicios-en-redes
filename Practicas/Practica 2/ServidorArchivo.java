
//Flores Castro Luis Antonio 3CV13 
//Administración de Servicios en Red.
//2022 Agosto - Enero
import java.net.*;
import java.io.*;

public class ServidorArchivo {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(7000);
            for (;;) {
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde" + cl.getInetAddress() + ":" + cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream());// Aqui se leen los archivos
                // obtenemos la longitud del array de la cantidad de archivos totales que envía
                // el cliente
                int lon = dis.readInt();

                for (int i = 0; i < lon; i++) {
                    int mostrar = i + 1;
                    String nombre = dis.readUTF();
                    System.out.println("Archivo numero: " + i + 1);
                    System.out.println("Recibimos el arhivo: " + nombre);// obtiene solo el nombre del archivo
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
                        System.out.println("Archivo " + mostrar + " esta siendo recibido");

                    }
                    dos.close();
                    System.out.println("\n\nTotal de archivos recibidos: " + lon + "\n\n");
                }

                dis.close();
                cl.close();
                // s.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
