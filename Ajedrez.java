import java.util.Scanner;


/**
 * Escriviu aquí una descripcìó de la classe Ajedrez
 * 
 * @author (el vostre nom) 
 * @version (un número de versió o la data)
 */
public class Ajedrez {
    public static void main(String[] args){
        Scanner sc;
        int fi;
        int col;
        int r1;
        int r2;
        int[][] tablero= new int[8][8];
        System.out.println(" 1 2 3 4 5 6 7 8");
        for (int i = 8; i >= 1; i--) {
            for (int j = 1; j <= 8; j++) {
                if (j == 1) {
                    System.out.print(i);
                }
                System.out.print("x ");
            }
            System.out.println();
        }
        System.out.println("Dime una posición del tablero por fila");
        sc = new Scanner(System.in);
        fi= sc.nextInt();
        System.out.println("Dime una posición del tablero por columna");
        sc = new Scanner(System.in);
        col = sc.nextInt();
        System.out.println("Movimientos posibles del alfil en la posición (" + fi + "," + col + "):");
        for(int i=1; i<=8; i++){
            if(fi+i<=8&& col+i<=8){
                r1=fi+i;
                r2=col+i;
                System.out.println(r1+","+r2);
            }
            if(fi+i<=8&&col-i>0){
                r1=fi+i;
                r2=col-i;
                System.out.println(r1+","+r2);
            }
            if(fi-i>0&&col+i<=8){
                r1=fi-i;
                r2=col+i;
                System.out.println(r1+","+r2);
            }
            if(fi-i>0&&col-i>0){
                r1=fi-i;
                r2=col-i;
                System.out.println(r1+","+r2);
            }
        }
    }
    
}
