import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Curso {

    // Atributo

    static List<Alumno> alumnos;

    // Constructor
    public Curso() {
        this.alumnos = new ArrayList<>();
    }


    // Métodos gestion

    // Agregar alumno
    public static void AgregarAlumno(Alumno nuevoAlumno) {
        alumnos.add(nuevoAlumno);
        //Añade el objeto Alumno a la lista alumnos
    }

    //Borrar alumno
    public boolean BorrarAlumno(int indice) {
        if (indice >= 0 && indice < alumnos.size()) {
            alumnos.remove(indice);
            return true;

        } else {
            System.out.println("Índice inválido.");
            return false;

        }

    }

    //Modificar nota numero de Alumnos
    public boolean ModificarNotaAlumno(int indice, double nuevaNotaNum) {
        if (indice >= 0 && indice < this.alumnos.size()) {
            Alumno alumnoAModificar = this.alumnos.get(indice);
            alumnoAModificar.setNotaNum(nuevaNotaNum);
            return true;
        } else {
            System.out.println("Índice inválido.");
            return false;
        }

    }

    //Modificar nota letra de Alumnos
    public void ActualizarNotasEnLetra(int indice, String nuevaNotaLetra) {
        for (Alumno alumno : this.alumnos) {
            String nuevaLetra = determinarNotaEnLetra(alumno.getNotaNum());
            alumno.setNotaLetra(nuevaLetra);
        }
        System.out.println("✅ Notas en letra actualizadas correctamente para todos los alumnos.");
    }

    public static String determinarNotaEnLetra(double nota) {
        if (nota == 10.0) {
            return "Matrícula de Honor";
        } else if (nota >= 9.0) {
            return "Sobresaliente";
        } else if (nota >= 7.0) {
            return "Notable";
        } else if (nota >= 5.0) {
            return "Aprobado";
        } else if (nota >= 0.0) {
            return "Suspenso";
        } else {
            return "No presentado"; // Para notas < 0.0 (según tu requisito)
        }
    }


    public void mostrarTodosLosDatos() {

        System.out.println("\n==================================");
        System.out.println("     LISTA COMPLETA DE ALUMNOS    ");
        System.out.println("==================================");

        // 1. Verificación: ¿Hay alumnos?
        if (this.alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados en el curso.");
            return;
        }

        // 2. Iteración y muestra: Recorremos la lista usando un bucle con índice
        for (int i = 0; i < this.alumnos.size(); i++) {

            // Muestra el índice [i] y luego la representación en cadena del Alumno (usando su toString())
            System.out.println("[" + i + "] " + this.alumnos.get(i).toString());
        }

        System.out.println("==================================");
    }

    public void mostrarEstadisticas() {

        if (this.alumnos.isEmpty()) {
            System.out.println("\nNo se pueden calcular estadísticas: No hay alumnos registrados.");
            return;
        }

        // Usamos un mapa para llevar el conteo de cada categoría
        Map<String, Integer> conteoNotas = new HashMap<>();

        // Inicializamos el mapa para asegurar que todas las categorías aparezcan, incluso con 0 alumnos
        conteoNotas.put("No presentado", 0);
        conteoNotas.put("Suspenso", 0);
        conteoNotas.put("Aprobado", 0);
        conteoNotas.put("Notable", 0);
        conteoNotas.put("Sobresaliente", 0);
        conteoNotas.put("Matrícula de Honor", 0);

        // 1. Recorrer la lista y contar las ocurrencias por nota en letra
        for (Alumno alumno : this.alumnos) {
            String notaLetra = alumno.getNotaLetra();
            // Usamos getOrDefault para asegurar que el conteo funciona si la nota no se inicializó arriba
            conteoNotas.put(notaLetra, conteoNotas.getOrDefault(notaLetra, 0) + 1);
        }

        int totalAlumnos = this.alumnos.size();
        int noPresentados = conteoNotas.getOrDefault("No presentado", 0);
        int totalPresentados = totalAlumnos - noPresentados;

        System.out.println("\n==================================");
        System.out.println("     ESTADÍSTICAS DEL CURSO       ");
        System.out.println(" Total Alumnos Registrados: " + totalAlumnos);
        System.out.println(" Total Alumnos Presentados: " + totalPresentados);
        System.out.println("==================================");

        // 2. Mostrar resultados y porcentajes
        for (Map.Entry<String, Integer> entry : conteoNotas.entrySet()) {
            String categoria = entry.getKey();
            int conteo = entry.getValue();

            // Calcular Porcentaje Total (respecto a todos los alumnos)
            double porcentajeTotal = (totalAlumnos > 0)
                    ? (double) conteo / totalAlumnos * 100
                    : 0.0;

            // Calcular Porcentaje Presentados (respecto a los que tienen >= 0.0)
            double porcentajePresentados = 0.0;
            if (!categoria.equals("No presentado") && totalPresentados > 0) {
                porcentajePresentados = (double) conteo / totalPresentados * 100;
            }

            System.out.printf(" %-20s: %2d alumnos (%.1f%% del total",
                    categoria, conteo, porcentajeTotal);

            if (!categoria.equals("No presentado")) {
                System.out.printf(", %.1f%% de presentados)\n", porcentajePresentados);
            } else {
                System.out.println(")");
            }
        }
        System.out.println("==================================");
    }



    //
}