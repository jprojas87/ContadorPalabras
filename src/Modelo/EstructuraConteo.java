package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import EstructurasDatos.Lista;
import EstructurasDatos.TablaSimbolosOrdenada;
import Util.EstructuraTexto;

/**
 * Clase encargada de realizar el análisis completo del texto.
 * Gestiona la lectura del archivo y usa una TablaSimbolosOrdenada
 * para almacenar cada palabra y su frecuencia.
 */
public class EstructuraConteo {

    private final TablaSimbolosOrdenada<String, Integer> tabla;
    private String primeraPalabra;
    private String ultimaPalabra;
    private int totalPalabras;
    private final int[] conteoIniciales = new int[26];

    /**
     * Crea una estructura de conteo usando una tabla
     * de símbolos ordenada.
     */
    public EstructuraConteo() {
        tabla = new TablaSimbolosOrdenada<>();
    }

    /**
     * Procesa un archivo .txt línea por línea, obtiene cada palabra
     * normalizada y la agrega a la tabla de símbolos.
     *
     * @param ruta ruta completa del archivo .txt
     */
    public void procesarArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            EstructuraTexto procesador = new EstructuraTexto();
            String linea;

            while ((linea = br.readLine()) != null) {
                Lista<String> palabras = procesador.obtenerPalabras(linea);
                for (String palabra : palabras) {
                    agregarPalabra(palabra);
                }
            }

        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Agrega una palabra a la tabla, actualiza la frecuencia,
     * registra primera/última palabra y cuenta letras iniciales.
     *
     * @param palabra palabra normalizada a agregar
     */
    private void agregarPalabra(String palabra) {
        if (palabra == null) {
            return;
        }

        palabra = palabra.trim();
        if (palabra.isEmpty()) {
            return;
        }

        totalPalabras++;

        if (primeraPalabra == null) {
            primeraPalabra = palabra;
        }
        ultimaPalabra = palabra;

        Integer actual = tabla.get(palabra);
        if (actual == null) {
            actual = 0;
        }
        tabla.put(palabra, actual + 1);

        char c = palabra.charAt(0);
        if (c >= 'a' && c <= 'z') {
            conteoIniciales[c - 'a']++;
        }
    }

    /**
     * Imprime estadísticas del análisis: totales, palabra más frecuente,
     * primeras y últimas apariciones, y conteo por letra inicial.
     */
    public void mostrarEstadisticas() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("             RESULTADOS");
        System.out.println("========================================");
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Palabras diferentes: " + tabla.size());

        int repetidas = 0;
        for (String palabra : tabla.keys()) {
            if (tabla.get(palabra) > 1) {
                repetidas++;
            }
        }
        System.out.println("Palabras repetidas: " + repetidas);
        System.out.println("Primera palabra: " + primeraPalabra);
        System.out.println("Ultima palabra: " + ultimaPalabra);

        String masFrecuente = null;
        int max = 0;

        for (String palabra : tabla.keys()) {
            int f = tabla.get(palabra);
            if (f > max) {
                max = f;
                masFrecuente = palabra;
            }
        }

        System.out.println("Palabra mas frecuente: " + masFrecuente + " (" + max + " veces)");
        System.out.println();
        System.out.println("Conteo por letra inicial:");

        for (int i = 0; i < 26; i++) {
            if (conteoIniciales[i] > 0) {
                System.out.printf("%c: %d%n", (char) ('A' + i), conteoIniciales[i]);
            }
        }
    }
}
