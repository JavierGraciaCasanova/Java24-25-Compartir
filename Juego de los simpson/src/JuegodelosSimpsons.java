import java.util.Random;
import java.util.Scanner;

public class JuegodelosSimpsons {
    private static final int MAX_FILA_TABLERO = 10;
    private static final int MAX_COLUMNA_TABLERO = 10;
    private static char[][] tablero;
    private static int filaBart;
    private  static int columnaBard;

    private static void imprimrTablero(){
        for (int i = 0; i < MAX_FILA_TABLERO; i++) {
            for (int j = 0; j < MAX_COLUMNA_TABLERO; j++){
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
    }
    private static void asignarLibreACasilla(char caracter){
        //2º) Rellenar el tablero 'L'
        for (int i = 0; i < MAX_FILA_TABLERO; i++) {
            for (int j = 0; j < MAX_COLUMNA_TABLERO; j++) {
                //[0,0],[0,1],[0,2],[0,3],ETC
                tablero[i][j] = caracter;
            }
        }
    }
    private static void asignarPersonajesaCasillaLibre(char caracter, int numReoeticiones){
        //4º) Asignar a Bart
        Random aleatorio = new Random();
        int filaAleatorio = -1;
        int columnaAleatorio = -1;
        for(int i = 0; i<numReoeticiones; i++){
            do {
                filaAleatorio = aleatorio.nextInt(MAX_FILA_TABLERO);
                columnaAleatorio = aleatorio.nextInt(MAX_COLUMNA_TABLERO);
            }while (tablero[filaAleatorio][columnaAleatorio]!='L');
            tablero[filaAleatorio][columnaAleatorio] = caracter;
        }
        if(caracter == 'B') {
            filaBart = filaAleatorio;
            columnaBard = columnaAleatorio;
        }
    }
    /*public static void main(String[] args) {
        // 1º) Inicualizo mi matriz tablero
        tablero = new char[MAX_FILA_TABLERO][MAX_COLUMNA_TABLERO];

        //  2º) Rellenar tablero de caracter l
        for (int i = 0; i < MAX_FILA_TABLERO; i++){
            for (int j = 0; j < MAX_COLUMNA_TABLERO; j++){
                tablero[i][j] = 'L';
            }
        }
        asignarCaracterCasillasLibres('L');
        // 3º) Imprimir tablero
        imprimrTablero();
        asignarPersonajesaCasillaLibre('B',1);
        //4º) Asignar a Bart
        Random aleatorio = new Random();
        int filaAleatorio = aleatorio.nextInt(MAX_FILA_TABLERO);//0-9
        int columnaAleatorio = aleatorio.nextInt(MAX_COLUMNA_TABLERO);//0-9

        tablero[filaAleatorio][columnaAleatorio] = 'B';

        // 5º) Imprimir tablero por pantalla
        imprimrTablero();
        asignarPersonajesaCasillaLibre('H',10);

        // 6º) Repartir 10 Homers dentro del tablero
        int filaAleatorioHomer;
        int columnaAleatorioHomer;
        for (int i = 0; i < 10; i++) {
            do{
                filaAleatorioHomer = aleatorio.nextInt(MAX_FILA_TABLERO);//0-9
                columnaAleatorioHomer = aleatorio.nextInt(MAX_COLUMNA_TABLERO);//0-9
            }while (tablero[filaAleatorioHomer][columnaAleatorioHomer] !='L' );
            // Cuando   (tablero[filaAleatorioHomer][columnaAleatorioHomer] !='l' );
            tablero[filaAleatorioHomer][columnaAleatorioHomer] = 'H';
        }
        // 7º) Imprimir tablero
        asignarPersonajesaCasillaLibre('M',10);
        imprimrTablero();
        int filaAleatorioMuro;
        int columnaAleatorioMuro;
        for (int i = 0; i < 10; i++) {
            do{
                filaAleatorioMuro = aleatorio.nextInt(MAX_FILA_TABLERO);//0-9
                columnaAleatorioMuro = aleatorio.nextInt(MAX_COLUMNA_TABLERO);//0-9
            }while (tablero[filaAleatorioMuro][columnaAleatorioMuro] !='L' );
            // Cuando   (tablero[filaAleatorioHomer][columnaAleatorioHomer] !='l' );
            tablero[filaAleatorioMuro][columnaAleatorioMuro] = 'H';
        }
        imprimrTablero();
        tablero[MAX_FILA_TABLERO-1][MAX_COLUMNA_TABLERO-1] = 'F';
        imprimrTablero();
        */
    public static void main(String[] args) {
        // 1º) Inicializo mi matriz tablero
        tablero = new char[MAX_FILA_TABLERO][MAX_COLUMNA_TABLERO];
        asignarLibreACasilla('L');
        //3º) Imprimir tablero
        imprimrTablero();
        asignarPersonajesaCasillaLibre('B', 1);
        //5º) Imprimir tablero
        imprimrTablero();
        asignarPersonajesaCasillaLibre('H',10);
        //7º) Imprimir tablero
        imprimrTablero();
        asignarPersonajesaCasillaLibre('M',10);
        imprimrTablero();
        tablero[MAX_FILA_TABLERO-1][MAX_COLUMNA_TABLERO-1] = 'F';
        imprimrTablero();


        /*Desplazamiento de bart*/
        /*A--> izquierda*/
        /*S--> Abajo*/
        /*D--> Derecha*/
        /*w--> Arriba*/
        Scanner lector = new Scanner(System.in);
        /*---------------------------------------------------------------------------------*/
        //Jugar
        /*---------------------------------------------------------------------------------*/
        int vidas = 10;
        do {
            System.out.println("Dime el desplazamiento que quieres realizar");
            System.out.println("a--> Izquierda, s--> Abajo, d--> Derecha, w--> Arriba");
            String desplazamiento = lector.nextLine();
            System.out.println("Desplazamiento=" + desplazamiento);
            switch (desplazamiento) {
                case "a":// Izquerda
                    if ((columnaBard - 1) >= 0) {
                        columnaBard = columnaBard - 1;
                        switch (tablero[filaBart][columnaBard]){
                            case 'H':
                                vidas = vidas - 1;
                                tablero[filaBart][columnaBard] = 'B';
                                tablero[filaBart][columnaBard + 1] = 'L';
                                System.out.println("Te quedan " + vidas + " vidas");
                                break;
                            case 'L':
                                tablero[filaBart][columnaBard] = 'B';
                                tablero[filaBart][columnaBard + 1] = 'L';
                                break;
                            case 'M':
                                System.out.println("El muro te ha bloqueado");
                                columnaBard = columnaBard + 1;
                                break;
                        }
                    } else {
                        System.out.println("Desplazamiento prohibido. Linmite de tablero.");
                    }
                    break;
                case "s":
                    break;
                case "d":
                    break;
                case "w":
                    break;
                default:
                    break;
            }
            imprimrTablero();
        }while(vidas > 0);
    }
}
