import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("¿Cuantos huevos quieres cocinar? y ¿Cuántos caben en la olla?");
        Scanner leer = new Scanner(System.in);

        short h, c;
        float t;

        h = leer.nextShort();
        c = leer.nextShort();

        while(h != 0 && c != 0){
            t = (float) h / c;

            if(t > (short)h/c){
                t = t + 1;
            }

            System.out.println("los minutos de cocción son:" + (short)t * 10);

            h = leer.nextShort();
            c = leer.nextShort();
        }
    }
}
