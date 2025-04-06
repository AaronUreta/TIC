import java.util.Scanner;
import java.util.Random;

public class GeneradorContrasena {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Paso 1: Pedir un dígito del 0 al 9
        System.out.print("Ingresa un dígito del 0 al 9: ");
        int digito = obtenerDigito(scanner);

        // Paso 2: Pedir nombre o apodo del usuario
        System.out.print("Ingresa tu nombre o apodo: ");
        String nombre = scanner.next();

        // Paso 3: Pedir un carácter especial
        System.out.print("Ingresa un carácter especial (ej: @, #, $, etc): ");
        char caracterEspecial = scanner.next().charAt(0);

        // Paso 4: Preguntar si quiere agregar más cosas
        scanner.nextLine(); // limpiar buffer
        System.out.print("¿Deseas agregar algo más a la contraseña? (s/n): ");
        String respuestaExtra = scanner.nextLine();
        String extra = "";

        if (respuestaExtra.equalsIgnoreCase("s")) {
            System.out.print("Escribe lo que deseas agregar: ");
            extra = scanner.nextLine();
        }

        // Paso 5: Pedir el número de dígitos deseado (entre 9 y 50)
        int longitud = 0;
        while (longitud < 9 || longitud > 50) {
            System.out.print("¿Cuántos caracteres debe tener la contraseña? (mínimo 9, máximo 50): ");
            longitud = scanner.nextInt();
        }

        // Generar la contraseña combinando los elementos dados
        String base = nombre + digito + caracterEspecial + extra;

        // Generar caracteres aleatorios hasta completar la longitud deseada
        String caracteresDisponibles = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder contrasena = new StringBuilder();

        // Agregamos la base al principio
        contrasena.append(base);

        // Luego completamos hasta llegar a la longitud deseada
        while (contrasena.length() < longitud) {
            int index = random.nextInt(caracteresDisponibles.length());
            contrasena.append(caracteresDisponibles.charAt(index));
        }

        // Mezclamos los caracteres para que no quede predecible
        String finalPassword = mezclarCadena(contrasena.toString(), random);

        System.out.println("\nTu contraseña generada es: " + finalPassword);
    }

    // Función para asegurar que el usuario ingrese un solo dígito del 0 al 9
    private static int obtenerDigito(Scanner scanner) {
        int digito;
        while (true) {
            if (scanner.hasNextInt()) {
                digito = scanner.nextInt();
                if (digito >= 0 && digito <= 9) {
                    return digito;
                } else {
                    System.out.print("Por favor ingresa un número del 0 al 9: ");
                }
            } else {
                System.out.print("Entrada inválida. Intenta de nuevo: ");
                scanner.next(); // limpiar entrada inválida
            }
        }
    }

    // Función para mezclar aleatoriamente los caracteres de la contraseña
    private static String mezclarCadena(String input, Random random) {
        char[] caracteres = input.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            int j = random.nextInt(caracteres.length);
            // intercambiar caracteres[i] con caracteres[j]
            char temp = caracteres[i];
            caracteres[i] = caracteres[j];
            caracteres[j] = temp;
        }
        return new String(caracteres);
    }
}
