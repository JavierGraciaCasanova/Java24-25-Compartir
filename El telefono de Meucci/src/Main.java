import java.util.Scanner;

public class Main {
    static int tot=0;
    static String fr="";
    static String[] set1 = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        while(leer.hasNext()){
            tot = 0;
            fr=leer.next();
            if(!fr.contains("I")&&!fr.contains("V"))
                tot++;
            else
                buscar(fr);
            System.out.println(tot);
        }
    }

    private static void buscar(String f) {
        for(int i=1;i<5&&i<=f.length();i++){
            boolean encontrado=false;
            for(int j=0;j<10;j++){
                if(f.substring(0,i).equals(set1[j]))
                    encontrado=true;
            }
            if(encontrado){
                String res=f.substring(i,f.length());
                if(res.length()>0)
                    buscar(res);
                else if(res.length()==0){
                    tot++;
                    return;
                }
            }
        }
    }
}
