/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gateway
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class panelEditable extends Canvas implements MouseListener
{

    public int i, j, figura;
    public Image Bomba, Pared, Arbol, Escudo, Hacha, Vidas, VidasMenos, Martillo;
    
    public static final int PARED_VALUE = 10500;
    public static final int ARBOL_VALUE = 20000;
    public static final int BOMBA_VALUE = 10000;
    public static final int HACHA_VALUE=20001;
    public static final int MARTILLO_VALUE=20002;
    public static final int VIDAS_VALUE=20003;
    public static final int VIDAS_MENOS_VALUE=20004;
    public static final int ESCUDO_VALUE=20005;
    public static boolean SELECCIONA_POS=false;

    public static int setClave=0;
    public static int posI=0, posJ=0, Direccion=1;
    public void posI(int pos_i){
        posI=pos_i;
    }
    public void posJ(int pos_j){
        posJ=pos_j;
    }
    public void posIJ(int pos_i, int pos_j){
        posI(pos_i);
        posJ(pos_j);
    }
    public void setDireccion(int direccion){
        Direccion=direccion;
    }
    public panelEditable(){
        super();
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.addMouseListener(this);
        Bomba=(new ImageIcon("bomba.gif")).getImage();
        Pared=(new ImageIcon("pared.jpg")).getImage();
        Arbol=(new ImageIcon("arbol.png")).getImage();
        Hacha=(new ImageIcon("hacha.jpg")).getImage();
        Martillo=(new ImageIcon("martillo.png")).getImage();
        Escudo=(new ImageIcon("escudo.jpg")).getImage();
        Vidas=(new ImageIcon("vida.png")).getImage();
        VidasMenos=(new ImageIcon("vidamenos.png")).getImage();
    }

    public void setI(int renglon){
        this.i=renglon;
    }
    public void setJ(int columna){
        this.j=columna;
    }
    public int getI(){
        return this.i;
    }
    public int getJ(){
        return this.j;
    }
    public void setValue(int clave){
        this.figura=clave;
    }
    public int getValue(){
        return this.figura;
    }
    public void estableceClave(int claveFigura)
    {
        SELECCIONA_POS=false;
        panelEditable.setClave=claveFigura;
    }
    public void pintaFigura(Graphics g, int fig)
    {
        switch(fig)
        {
            case PARED_VALUE:
                g.drawImage(Pared, 0,0, this);
            break;
            case ARBOL_VALUE:
                g.drawImage(Arbol, 0,0, this);
            break;
            case BOMBA_VALUE:
                g.drawImage(Bomba, 0,0, this);
            break;
            case HACHA_VALUE:
                g.drawImage(Hacha, 0,0, this);
            break;
            case MARTILLO_VALUE:
                g.drawImage(Martillo, 0,0, this);
            break;
            case ESCUDO_VALUE:
                g.drawImage(Escudo, 0,0, this);
            break;
            case VIDAS_VALUE:
                g.drawImage(Vidas, 0,0, this);
            break;
            case VIDAS_MENOS_VALUE:
                g.drawImage(VidasMenos, 0,0, this);
            break;
        }
    }
    public void seleciconarInicio(){
        SELECCIONA_POS=true;
    }
    public void soltarInicio(){
        SELECCIONA_POS=false;
    }
    public void paint(Graphics g)
    {
        pintaFigura(g,this.figura);
    }
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if(!SELECCIONA_POS)
        {
            this.setValue(panelEditable.setClave);
            repaint();
        }
        else{
            posIJ(getI(),getJ());
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
