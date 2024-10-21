import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.util.TreeSet;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.Image;
import javax.swing.JOptionPane;

/**
pantalla entera
4x4
8 parejas
6 puntos
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuiCartas extends JFrame implements ActionListener
{
    private JButton boton;
    private JButton[] botones;
    private JPanel panel;
    private ArrayList<ImageIcon> fotos;
    private int parejaAct;
    private ArrayList<ImageIcon> indice;
    private ImageIcon img;
    private ArrayList<JButton> parejas;
    private JButton boton1;
    private JButton boton2;
    private JOptionPane ventana;
    private int contador;

    public GuiCartas(){
        setTitle("Carrtititicas"); 
        botones = new JButton[16];
        fotos = new ArrayList<>();
        parejas = new ArrayList<>();
        parejaAct=0;
        contador =6;
        iniComponents();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniComponents(){
        panel = new JPanel(new GridLayout(4,4));
        add(panel);

        for(int i=0; i<16; i++){
            boton = new JButton();
            boton.addActionListener(this);
            panel.add(boton);
            botones[i]=boton;
        }
        cargaImg();
        imgRandom();
    }

    private void cargaImg(){
        String[] rutas = {
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/uno.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/uno - uno.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/dos.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/dos - copia.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/tres.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/tres - copia.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/cuatro.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/cuatro - copia.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/cinco.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/cinco - copia.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/seis.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/seis - copia.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/siete.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/siete - copia.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/ocho.jpg",
                "//tsclient/D/Apuntes-20230219T184428Z-001/Temas/T10/fotos/ocho - copia.jpg"
        };
        String[] descripciones = {
                "uno", "uno", "dos", "dos", "tres", "tres", "cuatro", "cuatro",
                "cinco", "cinco", "seis", "seis", "siete", "siete", "ocho", "ocho"
        };
        int tamano=300;
        for (int i = 0; i < rutas.length; i++) {
            ImageIcon imagenO = new ImageIcon(rutas[i]);
            Image img = imagenO.getImage();
            Image imgRedi = img.getScaledInstance(tamano, tamano, Image.SCALE_SMOOTH);
            ImageIcon imagenFin = new ImageIcon(imgRedi);
            imagenFin.setDescription(descripciones[i]);
            fotos.add(imagenFin);
        }
    }

    private void imgRandom(){        
        Collections.shuffle(fotos);
        for(int i=0; i<16; i++){
            img= fotos.get(i);
            botones[i].putClientProperty("foto", img);
        }
    }

    public void actionPerformed (ActionEvent e){
        boton = (JButton) e.getSource();
        img= (ImageIcon) boton.getClientProperty("foto");

        if(img!=null && parejaAct<2){
            boton.setIcon(img);
            parejas.add(boton);
            parejaAct++;
            if(parejaAct==2){
                comprobarPareja();
            }

        }

    }

    private void comprobarPareja(){
        
        boton1 = parejas.get(0);
        boton2 = parejas.get(1);
        ImageIcon img1 = (ImageIcon) boton1.getClientProperty("foto");
        ImageIcon img2 = (ImageIcon) boton2.getClientProperty("foto");

        if (!img1.getDescription().equals(img2.getDescription())) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("Excepción de pausa");
            }

            boton1.setIcon(null);
            boton2.setIcon(null);
            contador--;
            ventana= new JOptionPane();
            ventana.showMessageDialog(null, "Puntos: " + contador, "Contador de Puntos", JOptionPane.INFORMATION_MESSAGE);
            //poner los puntos en el titulo
            if(contador==0){
            ventana.showMessageDialog(null, "Se ha acadado el juego", "FIN", JOptionPane.INFORMATION_MESSAGE);            
            System.exit(1);    
        }
            
        }

        parejas.clear();
        parejaAct = 0;

    }

    public static void main(String[] args){
        GuiCartas v= new GuiCartas();
        v.setSize(3000, 3000);
    }
}
