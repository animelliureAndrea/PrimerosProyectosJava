 

import java.util.Scanner;
/**
 * Write a description of class Ahorcados here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ahorcados
{
    static Scanner sc = new Scanner(System.in);
    static String palabra = "ahorcado";
    static String oculta = "*".repeat(palabra.length());
    static String intentos = "";
    static int vidas = 6;

    public static void main(String[] args){
        System.out.println(palabra.length() == 1 ? "Adivina la palabra de " + palabra.length() + " letra:" : "Adivina la palabra de " + palabra.length() + " letras:");
        System.out.println(oculta);
        menu(vidas);
        do{
            validar(sc.nextLine().charAt(0));
            System.out.println("Caracteres ya introducidos: " + intentos);
        } while(vidas > 0 && oculta.indexOf('*') != -1);
        if(vidas<1){
            System.out.println("Has perdido!");
        } else {
            System.out.println("Victoria!");
        }
        sc.close();
    }
    
    public static void menu(int vidas){
        System.out.println("______________");
        System.out.println(oculta+" | "+"Vidas:"+vidas);
        System.out.println("______________");
        System.out.println(">>>>>Dame una letra");

    }


    private static void validar(char caracter){
        while (!((65 <= (int) caracter && (int) caracter <= 90) || (97 <= (int) caracter && (int) caracter <= 122)) ){
            System.out.println("El caracter no es valido");
            caracter = sc.nextLine().charAt(0);
        }
        if (intentos.indexOf(caracter) != -1){
            System.out.println("El caracter " + caracter + " ya ha sido introducido");
        } else{
            intento2(caracter);
        }
    }
    
    private static void intento2(char caracter){
        intentos += caracter;
        if(palabra.indexOf(caracter) == -1){
            vidas--;
            System.out.println(dibujar(vidas));
        }
        for(int posicion = 0; posicion < palabra.length(); posicion++){
            if (palabra.charAt(posicion) == caracter){
                String substring1, substring2;
                if(posicion == 0){
                    substring1 = "";
                    substring2 = oculta.substring(posicion+1, palabra.length());
                } else if (posicion == palabra.length()-1){
                    substring1 = oculta.substring(0, posicion);
                    substring2 = "";
                } else {
                    substring1 = oculta.substring(0, posicion);
                    substring2 = oculta.substring(posicion+1, palabra.length());
                }
                oculta = substring1 + caracter + substring2;
            }
        }
        System.out.println(oculta);
    }
    
    public static String dibujar(int vidas){
        String monigote="";
        String separador=" ".repeat(1);
        switch(vidas){
            case 5:
                monigote=separador + "O\n";
                break;
            case 4:
                monigote=separador + "O\n" + separador + "|\n";
                break;
            case 3:
                monigote=separador + "O\n" + separador + "-|\n";
                break;
            case 2:
                monigote=separador + "O\n" + separador + "-|-\n";
                break;
            case 1:
                monigote=separador + "O\n" + separador + "-|-\n" + separador + "/\n";
                break;
            case 0:
                monigote=separador + "O\n" + separador + "-|-\n" + separador + "/ \\";
                break;
        }
        return monigote;
    }

}
