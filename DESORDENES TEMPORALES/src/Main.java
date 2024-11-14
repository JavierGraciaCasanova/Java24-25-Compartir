import java.util.Scanner;

public class Main {

    public static void actualizarBIT(int[] bit, int index, int valor) {
        for (; index < bit.length; index += index & -index) {
            bit[index] += valor;
        }
    }

    public static int consultaBIT(int[] bit, int index) {
        int suma = 0;
        for (; index > 0; index -= index & -index) {
            suma += bit[index];
        }
        return suma;
    }

    public static int[] contarMenoresADerecha(int[] numeros) {
        int[] copia = numeros.clone();
        ordenarArreglo(copia);
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = busquedaBinaria(copia, numeros[i]) + 1;
        }
        int[] bit = new int[numeros.length + 1];
        int[] resultado = new int[numeros.length];
        for (int i = numeros.length - 1; i >= 0; i--) {
            resultado[i] = consultaBIT(bit, numeros[i] - 1);
            actualizarBIT(bit, numeros[i], 1);
        }
        return resultado;
    }

    public static void ordenarArreglo(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = i + 1; j < arreglo.length; j++) {
                if (arreglo[i] > arreglo[j]) {
                    int temp = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = temp;
                }
            }
        }
    }

    public static int busquedaBinaria(int[] arreglo, int clave) {
        int izquierda = 0, derecha = arreglo.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            if (arreglo[medio] == clave) {
                return medio;
            } else if (arreglo[medio] < clave) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int caso = scanner.nextInt();
        while (caso != 0) {
            int[] entrada = new int[caso];
            for (int i = 0; i < caso; i++) {
                entrada[i] = scanner.nextInt();
            }
            long total = 0;
            int[] menoresADerecha = contarMenoresADerecha(entrada);
            for (int i = 0; i < menoresADerecha.length; i++) {
                total += menoresADerecha[i];
            }
            System.out.println(total);
            caso = scanner.nextInt();
        }
        scanner.close();
    }
}