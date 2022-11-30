import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.*;

public class Valor {
    public int obt() {
        // Aqui nada mas se obtiene el valor de num
        // es decir el numero de productos de la base de datos esto se pasa al valor a
        // la clase Cargar
        int cont = 0;
        String fir;
        try {
            String cadena;
            FileReader f = new FileReader("bd2.txt");
            BufferedReader b = new BufferedReader(f);
            // Aqui se copian los datos en arrays

            while ((cadena = b.readLine()) != null) {
                cont++;
            }
            b.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cont / 6;
    }
}
