package arbolesavl;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ArbolesAVL extends JFrame implements ActionListener {

    public JButton botonCrear  = new JButton("Crear arbol");
    public JButton botonInsertar = new JButton("Insertar en el arbol");
    public JButton botonRetirar  = new JButton("Retirar del arbol");
    
    public JLabel label  = new JLabel("Indicador de balances (Colores)");
    public JLabel label2 = new JLabel(" 0 = Negro");
    public JLabel label3 = new JLabel("-1 = Azul");
    public JLabel label4 = new JLabel(" 1 = Rojo");
    public JLabel label5 = new JLabel("Ingresar numero(s) que desea añadir");
    public JLabel label6 = new JLabel("Recorrido Preorden: ");
    public JLabel label7 = new JLabel("Recorrido Inorden: ");
    public JLabel label8 = new JLabel("Recorrido Posorden: ");
    public JLabel label9 = new JLabel("");
    
    public JTextField tfIngreso = new JTextField("10,20,30,40,50,60,70,80,90,100");
    public JTextField tfRetiro = new JTextField("20,50,90");
    public JTextField preOrden = new JTextField();
    public JTextField inOrden = new JTextField();
    public JTextField posOrden = new JTextField();
    
    private Arbol tree = null; 
    private List <Nodo> listaArbol = new ArrayList();
    private List <JLabel> numeros= new ArrayList();
    private JPanel jpan;
    private String entrada;
    
    public static void main(String[] args) {
     
        ArbolesAVL arbolesAvl = new ArbolesAVL();
        arbolesAvl.setSize(1300, 700);
        arbolesAvl.setTitle("Arboles AVL");
        arbolesAvl.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        arbolesAvl.setVisible(true);
        
    }

    @SuppressWarnings("empty-statement")
    public ArbolesAVL(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(label);
        c.add(label2);
        c.add(label3);
        c.add(label4);
        c.add(label5);
        c.add(label6);
        c.add(label7);
        c.add(label8);
        c.add(label9);
        
        c.add(tfIngreso);
        c.add(tfRetiro);
        c.add(preOrden);
        c.add(inOrden);
        c.add(posOrden);
        
        c.add(botonInsertar);
        c.add(botonRetirar);
        c.add(botonCrear);
        
        jpan = new JPanel();       
        
        jpan.setBounds(0, 200, 1300, 500);
        jpan.setOpaque(false);
        jpan.setBackground(new Color(0,0,0,0));
        add(jpan);
        
        botonInsertar.addActionListener(this);
        botonRetirar.addActionListener(this);
        botonCrear.addActionListener(this);
        
        label.setBounds(20, 25, 200, 20);
        label2.setBounds(20, 50, 200, 20);
        label3.setBounds(20, 75, 200, 20);
        label3.setForeground(Color.BLUE);
        label4.setBounds(20, 100, 200, 20);
        label4.setForeground(Color.RED);
        label5.setBounds(900, 25, 300, 20);
        label6.setBounds(300, 50, 200, 20);
        label7.setBounds(300, 75, 200, 20);
        label8.setBounds(300, 100, 200, 20);
        label9.setBounds(900,100,370,20);
        
        tfIngreso.setBounds(900, 50, 210, 20);
        tfRetiro.setBounds(900, 75, 210, 20);
        preOrden.setBounds(450, 50, 350, 20);
        inOrden.setBounds(450, 75, 350, 20);
        posOrden.setBounds(450, 100, 350, 20);
        
        botonInsertar.setBounds(1125, 50, 145, 20);
        botonInsertar.setBackground(Color.green);
        botonRetirar.setBounds(1125, 75, 145, 20);
        botonRetirar.setBackground(Color.red);
        botonCrear.setBounds(1125, 25, 145, 20);
        botonCrear.setBackground(Color.blue);
    }
    
    private boolean todoNull(List list) {
        
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
    
    public void listarArbol(List<Nodo> nodos, int nivel, int profundidad){
        
        if (nodos.isEmpty() || todoNull(nodos)){
        
            return;

        }
       
        int ubicacion = profundidad - nivel;
        List<Nodo> newNodes = new ArrayList<>();
        
        for (Nodo node : nodos) {
            if (node != null) {
                
                newNodes.add(node.getIzquierdo());
                newNodes.add(node.getDerecho());
                listaArbol.add(node.getIzquierdo());
                listaArbol.add(node.getDerecho());
                
                
            } else {
                
                newNodes.add(null);
                newNodes.add(null);
                listaArbol.add(null);
                listaArbol.add(null);
            
            }
        }
            
            listarArbol(newNodes, nivel + 1, profundidad);
            
    
    }
    
    void pintarArbol(){
        
        int exponente = 0;
        int i=0;
        int j=0;
        int coorX = 630;
        int coorY = 30;
        
        JLabel numeros[]=new JLabel[listaArbol.size()];
        
        while(i<listaArbol.size()){
        
            if(j >= Math.pow(2, exponente)){           
                
                exponente=exponente+1;
                j=0;
                coorY = coorY + 50;
                coorX = (int) ((1260/(Math.pow(2, (exponente + 1)))));
                
            }
            
            if(j != 0){
                coorX = (int) ( coorX + (1260/(Math.pow(2, (exponente) ))) );
            }
        
            
            
            if(listaArbol.get(i) != null){
                
                numeros[i]= new JLabel(Integer.toString(listaArbol.get(i).getValor()));
                numeros[i].setBounds(coorX, coorY, 30, 30);
            
                JLabel img1 = new JLabel();
                
                int escala = (int) (280/((Math.pow(2, (exponente)))));
                
                if(listaArbol.get(i).getDerecho() != null){
                
                    
                    ImageIcon imgIcon = new ImageIcon(getClass().getResource("flecha.png"));
                    
                    Image imgEscalada = imgIcon.getImage().getScaledInstance(escala,30, Image.SCALE_SMOOTH);
                    Icon iconoEscalado = new ImageIcon(imgEscalada);
                    img1.setBounds(coorX+20 , coorY + 30, escala, 30);
                    img1.setIcon(iconoEscalado);
                    
                    jpan.add(img1);
            
                    
                } 
                
                img1 = new JLabel();
                
                if(listaArbol.get(i).getIzquierdo()!= null){
                
                    ImageIcon imgIcon = new ImageIcon(getClass().getResource("fder.png"));
            
                    Image imgEscalada = imgIcon.getImage().getScaledInstance(escala,30, Image.SCALE_SMOOTH);
                    Icon iconoEscalado = new ImageIcon(imgEscalada);
                    img1.setBounds(coorX-escala , coorY + 30, escala, 30);
                    img1.setIcon(iconoEscalado);
                    
                    jpan.add(img1);
                    
                }
                
            
                
                if(listaArbol.get(i).getBalance() == -1){

                    numeros[i].setForeground(Color.BLUE);

                } else if (listaArbol.get(i).getBalance() == 1){

                    numeros[i].setForeground(Color.red);

                } else {
                    
                    numeros[i].setForeground(Color.BLACK);

                }

                jpan.add(numeros[i]);
            
            
            } else {

                numeros[i]= new JLabel("");
                jpan.add(numeros[i]);

            }
            
            i++;
            j++;
        }
            
        jpan.repaint();
        
    }
    
    void dibujar(){
        
        tree.setCadena("");
        inOrden.removeAll();
        inOrden.setText(tree.printInorder(tree.getRaiz()));
        
        tree.setCadena2("");
        posOrden.removeAll();
        posOrden.setText(tree.printPostOrder(tree.getRaiz()));
        
        tree.setCadena3("");
        preOrden.removeAll();
        preOrden.setText(tree.printPreOrder(tree.getRaiz()));
        
        List <Nodo> lista = new ArrayList();
        lista.add(tree.getRaiz());
        listaArbol = new ArrayList();
        listaArbol.add(tree.getRaiz());
        listarArbol(lista, 0, tree.getMax());
        jpan.removeAll();

        pintarArbol();
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == botonCrear){            

            tree =  new Arbol(); 
        
            label9.setText("Arbol creado");
            
        } else if (e.getSource() == botonInsertar && tree != null){
            
            entrada = tfIngreso.getText() + " ";
            
            if(!entrada.equals(" ")){
            
                String temp="";

                for(int i=0;i<entrada.length();i++){

                    if(entrada.substring(i,i+1).equals(",")|| entrada.substring(i,i+1).equals(" ")){
                        
                        tree.setRaiz(tree.insertar(tree.getRaiz(), Integer.parseInt(temp)));
                        temp = "";

                    } else {

                        temp = temp + entrada.substring(i,i+1);

                    }

                }

                dibujar();
            
            } else {
            
                label9.setText("Por favor llene los campos");
                
            }
            
        } else if (e.getSource() == botonRetirar && tree != null){
            
            entrada = tfRetiro.getText() + " ";
            
            if(!entrada.equals(" ")){
            
                String temp="";

                for(int i=0;i<entrada.length();i++){

                    if(entrada.substring(i,i+1).equals(",")|| entrada.substring(i,i+1).equals(" ")){

                        tree.setRaiz(tree.retirar(tree.getRaiz(), Integer.parseInt(temp)));
                        temp = "";

                    } else {

                        temp = temp + entrada.substring(i,i+1);

                    }

                }

                dibujar();
                
                } else {
            
                    
                    label9.setText("Por favor llene bien los campos");
                
            }
            
        } else if (tree == null){
                
            label9.setText("El arbol no existe, por favor creelo"); 
                    
        } 
        
    }
    
}