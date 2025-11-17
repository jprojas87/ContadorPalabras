package conteopalabraslibro;

import java.util.Scanner;
import Modelo.EstructuraConteo;

/**
 * Clase principal del proyecto.
 * Permite seleccionar un libro de la biblioteca disponible
 * y ejecutar el análisis de palabras usando la estructura de conteo.
 */
public class PruebaLibro {

    /**
     * Método principal (punto de entrada) del programa.
     *
     * @param args argumentos de línea de comandos (no usados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   CONTEO DE PALABRAS DE UN LIBRO");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Libros disponibles:");
        System.out.println("1. Alice Adventures in Wonderland");
        System.out.println("2. Moby Dick");
        System.out.println();

        System.out.print("Seleccione un libro (1 o 2): ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        String ruta = "";
        String nombreLibro = "";

        switch (opcion) {
            case 1:
                ruta = "librostxt/Alice Adventures in Wonderland.txt";
                nombreLibro = "Alice Adventures in Wonderland";
                break;
            case 2:
                ruta = "librostxt/Moby Dick.txt";
                nombreLibro = "Moby Dick";
                break;
            default:
                System.out.println("Opción no válida. Finalizando programa.");
                return;
        }

        System.out.println();
        System.out.println("Analizando: " + nombreLibro);
        System.out.println("Procesando archivo...");

        EstructuraConteo analizador = new EstructuraConteo();
        analizador.procesarArchivo(ruta);
        analizador.mostrarEstadisticas();
    }
}