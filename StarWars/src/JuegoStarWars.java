import java.util.Random;
import java.util.Scanner;

public class JuegoStarWars {
    private static final int SIZE = 10; // Tamaño del tablero
    private static final char EMPTY = 'L'; // Celda vacía
    private static final char YODA = 'Y'; // Carácter para el jugador Yoda
    private static final char VADER = 'V'; // Carácter para el jugador Darth Vader
    private static final char DARTH_MAUL = 'D'; // Carácter para enemigo Darth Maul
    private static final char R2D2 = 'R'; // Carácter para enemigo R2-D2
    private static final char WALL = 'M'; // Carácter para muro
    private static final char FINAL = 'F'; // Carácter para la casilla final
    private static final int ENEMIES_COUNT = 5; // Cantidad de enemigos en cada tablero
    private static final int WALLS_COUNT = 5; // Cantidad de muros en cada tablero
    private static final int INITIAL_LIVES = 3; // Vidas iniciales de cada jugador

    private char[][] boardYoda = new char[SIZE][SIZE]; // Tablero de Yoda
    private char[][] boardVader = new char[SIZE][SIZE]; // Tablero de Vader
    private int yodaLives = INITIAL_LIVES; // Vidas de Yoda
    private int vaderLives = INITIAL_LIVES; // Vidas de Vader
    private int yodaX, yodaY, vaderX, vaderY; // Posiciones de Yoda y Vader

    // Constructor: inicializa los tableros de ambos jugadores
    public void StarWarsGame() {
        initializeBoard(boardYoda, YODA, DARTH_MAUL);
        initializeBoard(boardVader, VADER, R2D2);
    }

    // Inicializa un tablero con el jugador, enemigos, muros y la casilla final
    private void initializeBoard(char[][] board, char player, char enemy) {
        fillBoardWithEmptySpaces(board); // Llena el tablero con celdas vacías
        placeCharacter(board, player); // Coloca el personaje jugador
        placeEnemies(board, enemy); // Coloca los enemigos
        placeWalls(board); // Coloca los muros
        board[SIZE - 1][SIZE - 1] = FINAL; // Coloca la casilla final en [9,9]
    }

    // Llena el tablero con celdas vacías ('L')
    private void fillBoardWithEmptySpaces(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Coloca al jugador en una posición aleatoria del tablero
    private void placeCharacter(char[][] board, char player) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (board[x][y] != EMPTY);

        board[x][y] = player;
        if (player == YODA) {
            yodaX = x;
            yodaY = y;
        } else {
            vaderX = x;
            vaderY = y;
        }
    }

    // Coloca una cantidad de enemigos en posiciones aleatorias del tablero
    private void placeEnemies(char[][] board, char enemy) {
        Random random = new Random();
        int placed = 0;
        while (placed < ENEMIES_COUNT) {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);
            if (board[x][y] == EMPTY) {
                board[x][y] = enemy;
                placed++;
            }
        }
    }

    // Coloca una cantidad de muros en posiciones aleatorias del tablero
    private void placeWalls(char[][] board) {
        Random random = new Random();
        int placed = 0;
        while (placed < WALLS_COUNT) {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);
            if (board[x][y] == EMPTY) {
                board[x][y] = WALL;
                placed++;
            }
        }
    }

    // Muestra el tablero en la consola
    private void displayBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Mueve al jugador en la dirección y cantidad de pasos especificada
    private void movePlayer(char[][] board, char player, int[] playerPos, int steps, char direction) {
        int newX = playerPos[0];
        int newY = playerPos[1];

        // Determina las coordenadas de destino según la dirección
        switch (direction) {
            case 'W':
                newX -= steps;
                break; // Arriba
            case 'S':
                newX += steps;
                break; // Abajo
            case 'A':
                newY -= steps;
                break; // Izquierda
            case 'D':
                newY += steps;
                break; // Derecha
        }

        // Lógica de borde para tablero infinito
        newX = (newX + SIZE) % SIZE;
        newY = (newY + SIZE) % SIZE;

        // Verifica si la celda de destino es válida
        if (board[newX][newY] == EMPTY || board[newX][newY] == FINAL) {
            board[playerPos[0]][playerPos[1]] = EMPTY; // Limpia la posición anterior
            board[newX][newY] = player; // Mueve al jugador a la nueva posición
            playerPos[0] = newX;
            playerPos[1] = newY;
        } else if (board[newX][newY] == DARTH_MAUL || board[newX][newY] == R2D2) {
            // Si se mueve a una celda con enemigo, pierde una vida
            if (player == YODA) yodaLives--;
            else vaderLives--;
            System.out.println("¡Oh no! " + player + " ha perdido una vida.");
        } else if (board[newX][newY] == WALL) {
            System.out.println("Movimiento bloqueado por un muro.");
        }
    }

    // Controla el flujo del juego
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (yodaLives > 0 && vaderLives > 0) {
            System.out.println("Tablero de Yoda:");
            displayBoard(boardYoda);
            System.out.println("Tablero de Vader:");
            displayBoard(boardVader);

            System.out.println("Mover a Yoda: ingrese (tecla-direccion, pasos): ");
            char direction = scanner.next().charAt(0);
            int steps = scanner.nextInt();
            movePlayer(boardYoda, YODA, new int[]{yodaX, yodaY}, steps, direction);

            System.out.println("Mover a Vader: ingrese (tecla-direccion, pasos): ");
            direction = scanner.next().charAt(0);
            steps = scanner.nextInt();
            movePlayer(boardVader, VADER, new int[]{vaderX, vaderY}, steps, direction);

            if (boardYoda[yodaX][yodaY] == FINAL) {
                System.out.println("¡Yoda ha llegado al final y ha ganado el juego!");
                break;
            }
            if (boardVader[vaderX][vaderY] == FINAL) {
                System.out.println("¡Vader ha llegado al final y ha ganado el juego!");
                break;
            }
        }
    }
}
