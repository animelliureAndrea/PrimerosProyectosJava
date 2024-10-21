import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * un campo de texto
 * los numeros de 3 en 3
 * simbolos
 * frase
 * al pulsar numero frase del día 
 * no aparece en jtextfield aparece en joptionpanel
 * cada número un color
 *
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Calculadora extends JFrame implements ActionListener
{
    private JPanel panel;
    private JPanel panelBotones;
    private Container contenedor;
    private JButton boton;
    private JTextField txtResultado;
    private Color c;
    private JOptionPane ventana;
    private final String[] JTITBOTON ={"CE","0","1","+","2","3","4","-","5","6","7","*","8","9","=","/"};

    private  String operador;
    private double resultado =-1;
    private boolean inicioNumero = true;
    private Map<Integer,String> listi;
    public Calculadora(){
        setTitle("Calculadora");
        iniComponents();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniComponents(){
        c = new Color(250, 100, 150);
        contenedor = getContentPane();
        contenedor.setBackground(c);

        this.setLayout(new BorderLayout());
        txtResultado = new JTextField(80);
        this.add(txtResultado, BorderLayout.NORTH);
        panel = new JPanel(new GridLayout(4,4));
        int numBotones = JTITBOTON.length;
        rellenaMap();
        for(int pos = 0; pos<numBotones; pos++){
            if(JTITBOTON[pos].matches("[0-9]")){
                boton= new Boton(JTITBOTON[pos], listi.get(JTITBOTON[pos]));
            }else{
                boton = new JButton();
            }
            arcoirisEspecial(pos);
            boton.addActionListener(this);
            boton.setText(JTITBOTON[pos]);
            panel.add(boton);

        }
        add(panel, BorderLayout.CENTER);
        this.add(new JLabel("Copyright"), BorderLayout.SOUTH);

    }

    public void arcoirisEspecial(int pos){
        int rojo=(int)((Math.random())*(255-0)+0);
        int verde=(int)((Math.random())*(255-0)+0);
        int azul=(int)((Math.random())*(255-0)+0);
        if(pos<JTITBOTON.length){
            c = new Color(rojo,verde,azul);
            boton.setBackground(c);
        }
    }

    public void actionPerformed(ActionEvent e){
        String contenBoton = e.getActionCommand();

        switch(contenBoton){
            case "CE":
                txtResultado.setText("");
                resultado =-1;
                operador ="";
                inicioNumero = true;
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                operador = contenBoton;
                if(inicioNumero){
                    resultado = Double.parseDouble(txtResultado.getText());
                    inicioNumero = false;
                }else{
                    calcular(Double.parseDouble(txtResultado.getText()));
                }
                txtResultado.setText("");
                break;
            case "=":
                calcular(Double.parseDouble(txtResultado.getText()));
                txtResultado.setText(""+resultado);
                resultado =-1;
                operador ="";
                inicioNumero = true;
                break;
            default: 
                txtResultado.setText(txtResultado.getText()+contenBoton);
                if(contenBoton.matches("[0-9]")){
                    ventana.showMessageDialog(this, ((Boton)e.getSource()).getMensaje());
                }
                break;
        }
    }

    private void calcular(double segundoNum){
        switch(operador){
            case "+":
                resultado += segundoNum;
                break;
            case "-":
                resultado -= segundoNum;
                break;
            case "*":
                resultado *= segundoNum;
                break;
            case "/":
                resultado /= segundoNum;
                break;
            default:
                resultado = segundoNum;
                break;
        }
        inicioNumero=true;
    }

    public void rellenaMap(){
        listi=new HashMap();
        File f = new File("D:/Apuntes-20230219T184428Z-001/Temas/frasis.txt");
        try{

            BufferedReader b = new BufferedReader(new FileReader(f));
            int contador=0;
            String line=b.readLine();
            while(line!=null){
                listi.put(contador,line);
                contador++;
                line=b.readLine();
            }
            //System.out.println(listi);

        }catch(FileNotFoundException fnfe){
            System.out.println("Fichero no encontrado");
        }catch(IOException ioe){
            System.out.println("IOE");
        }

    }

    public static void main(String[] args){
        Calculadora ventana = new Calculadora();
        ventana.setVisible(true);
        ventana.iniComponents();
        ventana.setSize(900, 530);
    }
}
