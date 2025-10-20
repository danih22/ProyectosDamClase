public class Alumno {
    // Atributos
    private String nombre;
    private String apellido;
    private String dni;
    private double notaNum;
    private String notaLetra;


    // Constructor
    public Alumno(String nombre, String apellido, String dni, double notaNum, String notaLetra) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.notaNum = notaNum;
        this.notaLetra = notaLetra;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getNotaNum() {
        return notaNum;
    }

    public void setNotaNum(double notaNum) {
        this.notaNum = notaNum;
    }

    public String getNotaLetra() {
        return notaLetra;
    }

    public void setNotaLetra(String notaLetra) {
        this.notaLetra = notaLetra;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", notaNum=" + notaNum +
                ", notaLetra='" + notaLetra + '\'' +
                '}';
    }
}