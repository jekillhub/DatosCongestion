import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CSV {

    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public static ArrayList<Cuadra> deserializarCSV(String ruta) throws FileNotFoundException {

        String latA = "";
        String longA = "";
        String latB = "";
        String longB = "";
        String descripcion = "";

        Cuadra cuadra = null;

        ArrayList<Cuadra> arrayList = new ArrayList<Cuadra>();

        BufferedReader br = null;
        String linea = "";

        String separador = ",";

        try {
            br = new BufferedReader(new FileReader(ruta));
            while ((linea = br.readLine()) != null) {

                String[] atr = linea.split(separador);
                latA = atr[0];
                longA = atr[1];
                latB = atr[2];
                longB = atr[3];
                descripcion = atr[4];
                cuadra = new Cuadra(Double.parseDouble(latA), Double.parseDouble(longA), Double.parseDouble(latB), Double.parseDouble(longB), descripcion);

                arrayList.add(cuadra);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return arrayList;
    }

    public static void serializarCSV(ArrayList<String> arrayList, String encabezado, String ruta) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(ruta));
        StringBuilder sb = new StringBuilder();

        sb.append(encabezado + "\n");

        for (String s : arrayList) {
            sb.append(s + "\n");
        }

        pw.write(sb.toString());
        pw.close();
    }
}