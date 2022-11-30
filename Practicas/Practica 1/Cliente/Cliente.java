import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cliente {
    public static void main(String[] argsStrings) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n\nEscriba la dirección del servidor: ");
            String host = br.readLine();
            System.out.println("\n\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());
            Socket cl = new Socket(host, pto);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
