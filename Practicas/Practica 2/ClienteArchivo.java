//Flores Castro Luis Antonio 3CV13 
//Administración de Servicios en Red.
//2022 Agosto - Enero
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class ClienteArchivo {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba la dirección del servidor: ");
            String host = br.readLine();
            System.out.println("\n\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());
            Socket cl = new Socket(host, pto);

            JFileChooser jf = new JFileChooser();
            // usando propiedades de jf
            jf.setMultiSelectionEnabled(true);// activar la selección multiple
            jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// unicamente se permite seleccionar archivos
            jf.setFileHidingEnabled(false);// no permite ocultar archivos

            // proceso para abrir ventana
            int r = jf.showOpenDialog(null);// abre ventana
            if (r == JFileChooser.APPROVE_OPTION) {// Hasta aqui se abren los archivos

                File[] f = jf.getSelectedFiles();// selecciona el archivo
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());

                // se envía la cantidad de archivos seleccionados por el usuario
                dos.writeInt(f.length);
                dos.flush();

                for (int i = 0; i < f.length; i++) {

                    int mostrar = i + 1;
                    String archivo = f[i].getAbsolutePath();// raiz
                    String nombre = f[i].getName();// obtiene el nombre
                    long tam = f[i].length();

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
                        System.out.println("El archivo " + mostrar + " se esta enviando");
                        System.out.println("Enviando: " + porcentaje + "%/r");

                    }
                    System.out.println("\n\nArchivo " + mostrar + " enviado");

                    // dos.close();
                    dis.close();

                }
                System.out.println("\n\nTotal de archivos enviados: " + f.length);

                dos.close();
                cl.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


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
