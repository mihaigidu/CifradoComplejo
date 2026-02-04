public class CifradoComplejo {

    /*
     * EXPLICACIÓN DE MI ALGORITMO:
     * Mi cifrado es mas seguro que el Cesar porque el desplazamiento no es fijo.
     * Tiene dos diferencias principales:
     * * 1. El salto cambia: En cada vuelta del bucle, el desplazamiento es (Clave + i).
     * Esto hace que letras iguales (como "LL") se cifren distinto.
     * * 2. Cambio de direccion (Zig-Zag):
     * - Si la posicion (i) es PAR: Sumo el desplazamiento (muevo a la derecha).
     * - Si la posicion (i) es IMPAR: Resto el desplazamiento (muevo a la izquierda).
     */

    private static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String procesarMensaje(String texto, int clave, boolean esCifrado) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caracterActual = texto.charAt(i);

            if (Character.isAlphabetic(caracterActual)) {
                boolean esMayuscula = Character.isUpperCase(caracterActual);
                char caracterBase = esMayuscula ? 'A' : 'a';

                int posOriginal = caracterActual - caracterBase;

                int desplazamiento = clave + i;

                int nuevaPos = 0;

                if (esCifrado) {
                    if (i % 2 == 0) {
                        // Posición PAR: Sumar (Derecha)
                        nuevaPos = (posOriginal + desplazamiento) % 26;
                    } else {
                        // Posición IMPAR: Restar (Izquierda)
                        nuevaPos = (posOriginal - desplazamiento) % 26;
                    }
                } else { // DESCIFRADO (Operación inversa)
                    if (i % 2 == 0) {
                        // Fue suma, ahora restamos
                        nuevaPos = (posOriginal - desplazamiento) % 26;
                    } else {
                        // Fue resta, ahora sumamos
                        nuevaPos = (posOriginal + desplazamiento) % 26;
                    }
                }

                if (nuevaPos < 0) {
                    nuevaPos += 26;
                }

                char nuevoCaracter = (char) (caracterBase + nuevaPos);
                resultado.append(nuevoCaracter);

            } else {
                resultado.append(caracterActual);
            }
        }

        return resultado.toString();
    }

    public static void main(String[] args) {

        String mensajeOriginal = "HOLA MUNDO";
        int claveSecreta = 5;

        System.out.println("Mensaje Original: " + mensajeOriginal);
        System.out.println("Clave Base: " + claveSecreta);

        // 1. Cifrar
        String mensajeCifrado = procesarMensaje(mensajeOriginal, claveSecreta, true);
        System.out.println("Mensaje Cifrado:  " + mensajeCifrado);

        // 2. Descifrar
        String mensajeDescifrado = procesarMensaje(mensajeCifrado, claveSecreta, false);
        System.out.println("Mensaje Descifrado: " + mensajeDescifrado);

    }
}