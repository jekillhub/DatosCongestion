import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Cuadra> cuadrasHor = CSV.deserializarCSV("CSVOriginales/Calles Horizontales.csv");
        ArrayList<String> muestrasHor = new ArrayList<String>();
        ArrayList<String> promediosHor = new ArrayList<String>();

        ArrayList<Cuadra> cuadrasVer = CSV.deserializarCSV("CSVOriginales/Calles Verticales.csv");
        ArrayList<String> muestrasVer = new ArrayList<String>();
        ArrayList<String> promediosVer = new ArrayList<String>();

        String encabezadoSerializados = "latA,longA,latB,longB,descripcion,hora,tasaFlujo,cantidadVehiculos";
        String encabezadoPromedios = "latA,longA,latB,longB,descripcion,tasaFlujo7,tasaFlujo13,tasaFlujo18";

        escribirCSV(cuadrasHor, muestrasHor, promediosHor, "DatosGenerados/Muestras_Horizontales.csv", "DatosGenerados/Prom_Horizontales.csv", encabezadoSerializados, encabezadoPromedios);
        escribirCSV(cuadrasVer, muestrasVer, promediosVer, "DatosGenerados/Muestras_Verticales.csv", "DatosGenerados/Prom_Verticales.csv", encabezadoSerializados, encabezadoPromedios);

        System.out.println("Checkea en /DatosGenerados");
    }

    public static void escribirCSV(ArrayList<Cuadra> cuadras, ArrayList<String> muestras, ArrayList<String> promedios, String rutaSerializados, String rutaPromedios, String encabezadoSerializados, String encabezadoPromedios) throws IOException {

        for (int i = 0; i < cuadras.size(); i++) {

            Cuadra c1 = cuadras.get(i);
            Cuadra c2 = cuadras.get(i);
            Cuadra c3 = cuadras.get(i);
            double media07 = 0.0;
            double media13 = 0.0;
            double media18 = 0.0;

            boolean soloColectivos = soloColectivo(cuadras.get(i).descripcion);

            for (int j = 0; j < 58; j = (j + 3)) {
                c1.setHora(setearHora(07, j));
                c1.generarCantidadVehiculos();

                if(soloColectivos){
                    c1.setTasaFlujo(-1);
                    media07 += (-1);
                }
                else {
                    c1.calcularTasaFlujo();
                    media07 += c1.tasaFlujo;
                }
                muestras.add(c1.toStringSerializado());
            }

            for (int j = 0; j < 58; j = (j + 3)) {
                c2.setHora(setearHora(13, j));
                c2.generarCantidadVehiculos();
                if(soloColectivos){
                    c2.setTasaFlujo(-1);
                    media13 += (-1);
                }
                else {
                    c2.calcularTasaFlujo();
                    media13 += c2.tasaFlujo;
                }
                muestras.add(c2.toStringSerializado());
            }

            for (int j = 0; j < 58; j = (j + 3)) {
                c3.setHora(setearHora(18, j));
                c3.generarCantidadVehiculos();
                if(soloColectivos){
                    c3.setTasaFlujo(-1);
                    media18 += (-1);
                }
                else {
                    c3.calcularTasaFlujo();
                    media18 += c3.tasaFlujo;
                }
                muestras.add(c3.toStringSerializado());
            }

            Cuadra proms = cuadras.get(i);
            media07 = media07 / 60;
            media13 = media13 / 60;
            media18 = media18 / 60;
            promedios.add(proms.toStringProm() + "," + formatearDouble(media07) + "," + formatearDouble(media13) + "," + formatearDouble(media18));
        }
        CSV.serializarCSV(muestras, encabezadoSerializados, rutaSerializados);
        CSV.serializarCSV(promedios, encabezadoPromedios, rutaPromedios);
    }

    public static Calendar setearHora(int hora, int minutos) {
        Calendar horario = Calendar.getInstance();
        horario.set(Calendar.HOUR_OF_DAY, hora);
        horario.set(Calendar.MINUTE, minutos);
        horario.set(Calendar.SECOND, 0);
        return horario;
    }

    public static double formatearDouble(double x) {
        return (double) Math.round(x * 100000d) / 100000d;
    }

    public static boolean soloColectivo(String descripcion){

        String[] descripcionSeparada = descripcion.split(" ");

        if(descripcionSeparada[0].equals("Portales") || descripcionSeparada[0].equals("Rodriguez")){
            return true;
        }
        return false;
    }
}
