import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Cuadra {

    double latA;
    double longA;
    double latB;
    double longB;
    String descripcion;
    Calendar hora;
    double tasaFlujo;
    int cantidadVehiculos;

    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public Cuadra(double latA, double longA, double latB, double longB, String descripcion) {
        this.latA = latA;
        this.longA = longA;
        this.latB = latB;
        this.longB = longB;
        this.descripcion = descripcion;

        this.hora = new GregorianCalendar();
        this.tasaFlujo = 0.0;
        this.cantidadVehiculos = 0;
    }

    public void calcularTasaFlujo() {
        this.tasaFlujo = this.cantidadVehiculos / 3;
    }

    public void generarCantidadVehiculos() {
        this.cantidadVehiculos = (int) Math.round(Math.random() * (50 - 0));
    }

    public void setHora(Calendar hora) {
        this.hora = hora;
    }

    public void setTasaFlujo(double tasaFlujo) {
        this.tasaFlujo = tasaFlujo;
    }

    public String toString() {
        return "LatA: " + latA + "\n" +
                "LongA: " + longA + "\n" +
                "LatB: " + latB + "\n" +
                "LongB: " + longB + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Hora: " + sdf.format(hora.getTime()) + "\n" +
                "Tasa Flujo: " + tasaFlujo + "\n" +
                "Cantidad Vehiculos: " + cantidadVehiculos;
    }

    public String toStringSerializado() {
        return latA + "," +
                longA + "," +
                latB + "," +
                longB + "," +
                descripcion + "," +
                sdf.format(hora.getTime()) + "," +
                tasaFlujo + "," +
                cantidadVehiculos;
    }

    public String toStringProm() {
        return latA + "," +
                longA + "," +
                latB + "," +
                longB + "," +
                descripcion;
    }
}
