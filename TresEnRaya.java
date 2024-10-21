import java.util.Scanner;

/**
 * Escriviu aquí una descripcìó de la classe ArrayBiDiez
 * 
 * @author (el vostre nom) 
 * @version (un número de versió o la data)
 */
import java.util.Scanner;

public class TresEnRaya {
    private char[][] tablero;
    private char jugadorActual;

    public TresEnRaya() {
        tablero = new char[3][3];
        jugadorActual = 'X';
        inicializarTablero();
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        int fila, columna;
        boolean jugadaValida = false;

        while (!hayGanador()) {
            dibujarTablero();
            System.out.println("Turno del jugador " + jugadorActual);
            
            // Pedir la fila y columna para la jugada
            do {
                System.out.print("Ingrese la fila (0-2): ");
                fila = scanner.nextInt();
                System.out.print("Ingrese la columna (0-2): ");
                columna = scanner.nextInt();

                if (tablero[fila][columna] == ' ') {
                    jugadaValida = true;
                } else {
                    System.out.println("La posición ya está ocupada, por favor elige otra.");
                }
            } while (!jugadaValida);

            // Realizar la jugada
            tablero[fila][columna] = jugadorActual;

            // Cambiar al siguiente jugador
            jugadorActual = jugadorActual == 'X' ? 'O' : 'X';
        }

        dibujarTablero();

        // Mostrar el resultado del juego
        if (hayGanador()) {
            System.out.println("¡Jugador " + jugadorActual + " ha ganado!");
        } else {
            System.out.println("¡Empate!");
        }

        scanner.close();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    private void dibujarTablero() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean hayGanador() {
        // Revisar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugadorActual && tablero[i][1] == jugadorActual && tablero[i][2] == jugadorActual) {
                return true;
            }
        }

        // Revisar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] == jugadorActual && tablero[1][j] == jugadorActual && tablero[2][j] == jugadorActual) {
                return true;
            }
        }

        // Revisar diagonales
        if (tablero[0][0] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][2] == jugadorActual) {
            return true;
        }
        if (tablero[0][2] == jugadorActual && tablero[1][1] == jugadorActual && tablero[2][0] == jugadorActual) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args){
        TresEnRaya tresi = new TresEnRaya();
        tresi.jugar();
    }
    
    
}
