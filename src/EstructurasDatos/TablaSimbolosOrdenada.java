package EstructurasDatos;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de una tabla de símbolos ORDENADA usando arreglos
 * paralelos para claves y valores. La búsqueda se realiza mediante
 * búsqueda binaria (rank).
 *
 * @param <Key> tipo de clave (debe implementar Comparable)
 * @param <Value> tipo de valor asociado
 */
public class TablaSimbolosOrdenada<Key extends Comparable<Key>, Value> {

    private static final int CAPACIDAD_INICIAL = 1000;

    private Key[] keys;
    private Value[] vals;
    private int n;

    /**
     * Construye una tabla vacía con capacidad inicial definida.
     */
    @SuppressWarnings("unchecked")
    public TablaSimbolosOrdenada() {
        keys = (Key[]) new Comparable[CAPACIDAD_INICIAL];
        vals = (Value[]) new Object[CAPACIDAD_INICIAL];
        n = 0;
    }

    /**
     * Devuelve la cantidad actual de pares almacenados.
     *
     * @return número de elementos
     */
    public int size() {
        return n;
    }

    /**
     * Duplica el tamaño de los arreglos internos.
     *
     * @param capacidad nueva capacidad
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacidad) {
        Key[] keys2 = (Key[]) new Comparable[capacidad];
        Value[] vals2 = (Value[]) new Object[capacidad];

        System.arraycopy(keys, 0, keys2, 0, n);
        System.arraycopy(vals, 0, vals2, 0, n);

        keys = keys2;
        vals = vals2;
    }

    /**
     * Realiza búsqueda binaria para encontrar la posición de una clave
     * o el punto donde debería insertarse.
     *
     * @param key clave a buscar
     * @return índice encontrado o índice de inserción
     */
    public int rank(Key key) {
        int lo = 0;
        int hi = n - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * Inserta una clave en orden, o si ya existe,
     * actualiza su valor.
     *
     * @param key clave a insertar
     * @param val valor asociado
     */
    public void put(Key key, Value val) {
        int i = rank(key);

        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        if (n == keys.length) {
            resize(2 * keys.length);
        }

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * Obtiene el valor asociado a una clave.
     *
     * @param key clave
     * @return valor o null si no existe
     */
    public Value get(Key key) {
        if (n == 0) {
            return null;
        }

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return vals[i];
        }

        return null;
    }

    /**
     * Devuelve todas las claves almacenadas en orden ascendente.
     *
     * @return colección iterable de claves
     */
    public Iterable<Key> keys() {
        List<Key> lista = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lista.add(keys[i]);
        }
        return lista;
    }
}
