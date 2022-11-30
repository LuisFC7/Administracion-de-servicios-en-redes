import java.io.BufferedReader;
import java.io.FileReader;
// import java.util.Arraylist;

public class Cargar {
    public static void cargar(String ubicacion) {

        try {
            String cadena;
            FileReader f = new FileReader(ubicacion);
            BufferedReader b = new BufferedReader(f);
            // Aqui se copian los datos en arrays
            Valor cantidad = new Valor();
            int num = cantidad.obt();
            System.out.println("********" + num);

            String id[] = new String[num];
            String nombre[] = new String[num];
            String precio[] = new String[num];
            String descripcion[] = new String[num];
            String stock[] = new String[num];
            int cont = 0;
            int cont2 = 0;
            int aux = 0;
            int aux2 = 0;
            int aux3 = 0;
            int aux4 = 0;
            while ((cadena = b.readLine()) != null) {
                if (cont < num) {
                    id[cont2] = cadena;
                    cont2++;
                } else if ((cont < num * 2) && (cont >= num - 1)) {
                    nombre[aux] = cadena;
                    aux++;
                } else if ((cont < num * 3) && (cont >= (num * 2) - 1)) {
                    precio[aux2] = cadena;
                    aux2++;
                } else if ((cont < num * 4) && (cont >= (num * 3) - 1)) {
                    descripcion[aux3] = cadena;
                    aux3++;
                } else if ((cont < num * 5) && (cont >= (num * 4) - 1)) {
                    stock[aux4] = cadena;
                    aux4++;
                }
                cont++;

            }

            // Aqui se muestra el catalogo de productos
            System.out.println("\n\nCatalogo de productos\n");
            System.out.println("ID  Producto  Precio  Descripcion  Stock");
            for (int i = 0; i < num; i++) {
                System.out.printf(
                        id[i] + " " + nombre[i] + "  $" + precio[i] + " " + descripcion[i] + " " + stock[i] + "\n");
            }
            b.close();
            System.out.println(id.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
