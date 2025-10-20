import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //1 Crear instancia curso
        Curso micurso = new Curso();
        int opcion;
        do {
            mostrarMenu();

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                switch (opcion) {
                    case 1:
                        implementarAlumno(micurso);
                        break;
                    case 2:
                        borrarAlumno(micurso);
                        break;
                    case 3:
                        modificarNota(micurso);
                        break;
                    case 4:
                        micurso.mostrarTodosLosDatos();
                        break;
                    case 5:
                        micurso.ActualizarNotasEnLetra( -1, ""); // El índice no es necesario aquí
                        break;
                    case 6:
                        micurso.mostrarEstadisticas();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("❌ Opción inválida. Por favor, seleccione una opción");
                }

            }else {
                System.out.println("❌ Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
                opcion = -1; // Forzar la repetición del menú
            }
        } while (opcion != 0);
        scanner.close();
    }

    //------------------- MOSTRAR MENÚ ------------------//
    private static void mostrarMenu() {
        System.out.println("\n==================================");
        System.out.println("       MENÚ DE GESTIÓN DE CURSO   ");
        System.out.println("==================================");
        System.out.println("1. Añadir nuevo alumno");
        System.out.println("2. Borrar alumno existente");
        System.out.println("3. Modificar nota de alumno");
        System.out.println("4. Mostrar todos los alumnos (con índice)");
        System.out.println("5. Actualizar notas en letra (Calificar)");
        System.out.println("6. Mostrar Estadísticas de notas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }


    //------------------- IMPLEMENTAR AÑADIR ALUMNO ------------------//
    public static void implementarAlumno(Curso curso) {

        System.out.println("\n--- AÑADIR NUEVO ALUMNO ---");

        // 1. Pedir y guardar datos de texto
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese los apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("Ingrese el DNI: ");
        String dni = scanner.nextLine();

        // 2. Pedir y validar la nota numérica
        double notaNum = -1.0;
        boolean notaValida = false;

        while (!notaValida) {
            System.out.print("Ingrese la Nota Numérica (0.0 a 10.0): ");
            if (scanner.hasNextDouble()) {
                notaNum = scanner.nextDouble();
                if (notaNum >= 0.0 && notaNum <= 10.0) {
                    notaValida = true; // La nota es válida
                } else {
                    System.out.println("❌ Error: La nota debe estar entre 0.0 y 10.0.");
                }
            } else {
                System.out.println("❌ Error: Por favor, ingrese un número decimal válido.");
            }
            // Limpiar el buffer de nueva línea después de nextDouble() o la entrada inválida (CRUCIAL)
            scanner.nextLine();
        }

        // 3. Determinar la nota en letra usando el método estático de la clase Curso
        String notaLetra = Curso.determinarNotaEnLetra(notaNum);

        // 4. Crear el objeto Alumno
        // (Asumiendo que el constructor es: Alumno(nombre, apellidos, dni, notaNumerica, notaEnLetra))
        Alumno nuevoAlumno = new Alumno(nombre, apellidos, dni, notaNum, notaLetra);

        // 5. Llamar al método de GESTIÓN de la clase Curso
        curso.AgregarAlumno(nuevoAlumno);

        System.out.println("\n✅ Alumno '" + nombre + " " + apellidos + "' añadido correctamente. Calificación inicial: " + notaLetra);
    }

    //------------------BORRAR ALUMNO ------------------//
    private static void borrarAlumno(Curso curso) {
        System.out.println("\n--- BORRAR ALUMNO ---");
        curso.mostrarTodosLosDatos(); // Mostrar la lista para que el usuario sepa el índice

        System.out.print("Ingrese el índice (posición) del alumno a borrar: ");

        if (scanner.hasNextInt()) {
            int indice = scanner.nextInt();
            scanner.nextLine();

            if (curso.BorrarAlumno(indice)) {
                System.out.println("✅ Alumno borrado con éxito de la posición " + indice);
            } else {
                System.out.println("❌ ERROR: El índice " + indice + " no es válido o está fuera de rango.");
            }
        } else {
            System.out.println("❌ Entrada inválida. Debe ingresar un número para el índice.");
            scanner.nextLine();
        }
    }

    //------------------ MODIFICAR NOTA ALUMNO ------------------//

    private static void modificarNota(Curso curso) {
        System.out.println("\n--- MODIFICAR NOTA DE ALUMNO ---");
        curso.mostrarTodosLosDatos(); // Mostrar la lista

        System.out.print("Ingrese el índice (posición) del alumno a modificar: ");

        if (scanner.hasNextInt()) {
            int indice = scanner.nextInt();
            scanner.nextLine();

            // Validación de rango de índice simple (el método de Curso lo valida mejor)
            if (indice < 0 || indice >= curso.alumnos.size()) {
                System.out.println("❌ ERROR: El índice " + indice + " no es válido.");
                return;
            }

            // Aquí se debería repetir la LÓGICA DE VALIDACIÓN DE NOTA (como en implementarAlumno)
            double nuevaNota = 8.5; // SUSTITUIR por la lógica de validación de Scanner

            if (curso.ModificarNotaAlumno(indice, nuevaNota)) {
                // Forzamos la actualización de la nota en letra después del cambio numérico
                curso.ActualizarNotasEnLetra(indice, Curso.determinarNotaEnLetra(nuevaNota));
                System.out.println("✅ Nota modificada con éxito para el alumno en posición " + indice);
            } else {
                // Este 'else' solo se ejecutaría si el método de Curso no valida bien,
                // ya que ya comprobamos el índice.
            }
        } else {
            System.out.println("❌ Entrada inválida. Debe ingresar un número para el índice.");
            scanner.nextLine();

        }

    }
}

