
package org.example;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;


public class TextoPlanoFruta {

    private static Path RUTA = Paths.get("frutas.txt");
    private static List<Fruta> frutas = new ArrayList<>();


    public static void main(String[] args) {
        //Creamso lista de frutas

        frutas = new ArrayList<>(List.of(
                new Fruta(1, "Manzana Fuji", 2.95, 120),
                new Fruta(2, "Plátano de Canarias", 3.30, 80),
                new Fruta(3, "Pera Conferencia", 2.10, 60)
        ));

        //Creamos el Scanner para leer el archivo

        Scanner sn = new Scanner(System.in);
        int opcion = 0;

        //Creamos el bucle para el menu
        do {
            mostarMenu();
            opcion = Integer.parseInt(sn.nextLine());
try {
    switch (opcion) {

        case 1:

            // Añadir Frutas

            System.out.println(" \nHas elegido la opción 1: Añadir frutas");
            añadirFruta(sn, frutas);
            break;


        case 2:
            // Listar Frutas
            System.out.println("Has elegido la opción 2: Listar frutas");
            if (frutas.isEmpty()) {
                System.out.println("No hay frutas en la lista.");
            } else {
                System.out.println("Frutas disponibles:");
                for (Fruta f : frutas) {
                    System.out.println(f);
                }
            }
            break;

        case 3:
            // Exportar a TXT
            System.out.println("Has elegido la opción 3: Exportar a TXT");

            try (
                    //CREAMOS BUFFEREDWRITER PARA PODER ESCRIBIR EN NUESTRO ARCHIVO .TXT CON UTF8
                    BufferedWriter bw = Files.newBufferedWriter(RUTA, StandardCharsets.UTF_8);
            ) {
                //RECORREMOS LA LISTA DE FRUTAS Y ESCRIBIMOS EN EL ARCHIVO
                for (Fruta f : frutas) {
                    bw.write(f.getId() + ";" + f.getNombre() + ";" + f.getPrecioKg() + ";" + f.getStockKg());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case 4:
            // Importar a TXT
            System.out.println("Has elegido la opción 4: Importar a TXT");
            List<Fruta> leidos = new ArrayList<>();
            try (
                    //CREAMOS UN BUFFEREDREADER PARA LEER EL ARCHIVO
                    BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8);

            ) {
                //LEEMOS EL ARCHIVO LINEA A LINEA
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";", -1);
                    if (partes.length != 4) {
                        System.err.println("Línea con formato incorrecto: " + linea);
                        continue;
                    }
                    try {
                        int id = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        double precioKg = Double.parseDouble(partes[2]);
                        int stockKg = Integer.parseInt(partes[3]);

                        leidos.add(new Fruta(id, nombre, precioKg, stockKg));

                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir número en línea: " + linea);
                    } catch (Exception e) {
                        System.err.println("Error inesperado en línea: " + linea);
                    }
                }

                //MOSTRAMOS POR PANTALLA LO LEIDO
                System.out.println("Leídos:");
                leidos.forEach(System.out::println);

            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case 5:
            // Salir
            System.out.println("Has elegido la opción 5: Salir");
            break;
        default:
            System.out.println("Opción no válida, por favor elige otra opción del 1 al 5.");
            break;
    }
}catch (NumberFormatException e){
    System.out.println("Entrada no válida. Por favor, introduce un número del 1 al 5.");
    opcion = 0;
}
            System.out.println("-----------------------------------");

        } while (opcion != 5);{
            System.out.println("Saliendo del programa...");
            sn.close();
        }

    }

    //CONSTANTES
    private static final Map<Integer, String> ID_TO_FRUTA = Map.of(
            1, "Manzana Fuji",
            2, "Plátano de Canarias",
            3, "Pera Conferencia"
    );

    //METODOS
    public static void mostarMenu() {
        System.out.println("-----MENU-----");
        System.out.println("1. Añadir frutas");
        System.out.println("2. Listar fruta");
        System.out.println("3. Exportar a TXT");
        System.out.println("4. Importar a TXT");
        System.out.println("5. Salir");
        System.out.println("---------------");
        System.out.println("Elige una opción: ");
    }



    public static void añadirFruta(Scanner sn, List<Fruta> frutas ) {
        try {
            System.out.println("Introduce el nombre de la fruta: ");
            String nombre = sn.nextLine();
            // Validación del nombre tiene que tener al menos 2 caracteres
            if (nombre.trim().length() < 2) {

                System.out.println("El nombre debe tener al menos 2 caracteres.");
                return;
            }
            System.out.println("Introduce el precio por kg de la fruta: ");
            double precioKg = Double.parseDouble(sn.nextLine());

            // Validación del precio
            if (precioKg <= 0) {
                System.out.println("El precio debe ser un número positivo.");
                return;
            }

            System.out.println("Introduce el stock en kg de la fruta: ");
            int stockKg = Integer.parseInt(sn.nextLine());
            // Validación del stock
            if (stockKg < 0) {
                System.out.println("El stock no puede ser negativo.");
                return;
            }

            // Generar un ID único
            int id = frutas.stream().mapToInt(Fruta::getId).max().orElse(0) + 1;


            Fruta fruta = new Fruta(id, nombre, precioKg, stockKg);
            frutas.add(fruta);
            System.out.println("Fruta añadida: " + fruta);
        }catch (NumberFormatException e){
            System.out.println("Entrada no válida. Por favor, introduce los datos correctamente.");
        }
        // Añadir la fruta a la lista


    }


}