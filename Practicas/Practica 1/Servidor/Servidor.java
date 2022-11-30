import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        Scanner ent = new Scanner(System.in);
        try {
            // Se establece la conexión con el cliente
            System.out.println("\n\nIngrese el puerto: ");
            int puerto = ent.nextInt();
            ServerSocket s = new ServerSocket(puerto);
            for (;;) {
                Socket cl = s.accept();
                System.out.println("Inicio de sesión exitoso");
                DataInputStream entrada = new DataInputStream(cl.getInputStream());

                // Aqui se empieza a leer el TXT
                Cargar bd = new Cargar();
                bd.cargar("bd2.txt");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
