import java.util.*;

public class Main {

    final static String ANSI_RED = "\u001B[31m";
    final static String ANSI_YELLOW = "\u001B[33m";
    final static String ANSI_GREEN = "\u001B[32m";
    final static String ANSI_RESET = "\u001B[0m";
    private static char[] palabraActual;
    private static final int vidas = 10;
    private static final List<String> palabrasArray = new ArrayList<>();
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        final String menu = """
                1. Añadir Palabra
                2. Jugar
                4. Salir""";

        int opcio = llegirInt(menu, 1, 4);

        switch (opcio) {
            case 1 -> {
                anadirPalabras();
                menu(); // llamada recursiva
                System.out.println("Bienvenido");

            }
            case 2 -> {
                juegoAhorcado();
                menu(); // llamada recursiva
            }

            case 3 -> {
                System.out.println("Saliendo...");
                System.exit(0);
            }
        }
    }
    public static void juegoAhorcado() {
        elegirPalabra();
    }
    public static void elegirPalabra() {
        if (palabrasArray.size() == 0) {
            System.out.println("No hay palabras en la lista");
        } else {
            // mezcla el array de palabras
            Collections.shuffle(palabrasArray);
            String palabra = palabrasArray.get(0);
            // transforma la palabra (String) en un array de caracteres
            palabraActual = palabra.toCharArray();
            Arrays.fill(palabraActual, '_');
            System.out.println(palabraActual);
            jugar();
        }
    }
    public static void jugar() {
        int vidasRestantes = vidas;
        char letra;
        boolean acierto = false;
        do {
            letra = leerLetraValida();
            for (char c : palabraActual) {
                if (c == letra) {
                    System.out.println("Ya has introducido esa letra");
                    acierto = true;
                }
            }
            if (!acierto) {
                for (int i = 0; i < palabraActual.length; i++) {
                    if (palabraActual[i] == '_') {
                        if (palabrasArray.get(0).charAt(i) == letra) {
                            palabraActual[i] = letra;
                            acierto = true;
                        }
                    }
                }
                if (!acierto) {
                    vidasRestantes--;
                    if (vidasRestantes >= 6) {
                        System.out.println(ANSI_GREEN + "Te quedan " + vidasRestantes + " vidas" + ANSI_RESET);
                    }
                    if (vidasRestantes >= 3 && vidasRestantes <= 5) {
                        System.out.println(ANSI_YELLOW + "Te quedan " + vidasRestantes + " vidas" + ANSI_RESET);
                    }
                    if (vidasRestantes >= 0 && vidasRestantes <= 2) {
                        System.out.println(ANSI_RED + "Te quedan " + vidasRestantes + " vidas" + ANSI_RESET);
                    }
                }
            }
            System.out.println(palabraActual);
            acierto = false;
        } while (vidasRestantes > 0 && !Arrays.equals(palabraActual, palabrasArray.get(0).toCharArray()));
        if (vidasRestantes == 0) {
            System.out.println("Has perdido");
        } else {
            System.out.println("Has ganado");
        }
    }
    public static void anadirPalabras() {
        llegirString();

    }
    public static int llegirInt(String message, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        boolean valorCorrecte = false;
        do {
            System.out.println(message);
            valorCorrecte = scanner.hasNextInt();
            if (!valorCorrecte) {
                System.out.println("Valor no valido");
                scanner.nextLine();
            } else {
                x = scanner.nextInt();
                scanner.nextLine();
                if (x < min || x > max) {
                    System.out.println("Valor no valido");
                    valorCorrecte = false;
                }
            }
        } while (!valorCorrecte);
        return x;
    }
    public static void llegirString() {
        Scanner scanner = new Scanner(System.in);
        String palabra = null;
        boolean valorCorrecte = true;
        do {
            System.out.println("Introduce una palabra: ");
            valorCorrecte = scanner.hasNextInt();
            if (valorCorrecte) {
                System.out.println("Valor no valido");
                scanner.next();
            } else {
                palabra = scanner.nextLine().toLowerCase();
                palabrasArray.add(palabra);
            }
        } while (valorCorrecte);
    }
    public static char leerLetraValida() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Introduce una letra: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.length() != 1) {
                System.out.println("Error: la entrada debe ser una letra.");
            } else if (!Character.isLetter(input.charAt(0))) {
                System.out.println("Error: la entrada debe ser una letra.");
            } else {
                return input.charAt(0);
            }
        }
    }
}