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

public final class instrucciones extends JFrame{
    JTextArea ta=new JTextArea();
    Archivos file=new Archivos();
    JButton btnOk=new JButton("Aceptar");
    String lineas[];
    JScrollPane pScroll;

    public instrucciones(String titulo, int x, int y){
	super(titulo);
	this.setLayout(null);
	this.setSize(400,600);
	this.setUndecorated(true);
	this.setResizable(false);
	this.setVisible(true);
	this.setLocation(x,y);
        setLocationRelativeTo(null);
	JLayeredPane lp = getLayeredPane();
        lineas=file.leer("documentacion/instrucciones.txt");
        ta.setBounds(new Rectangle(10,10,380,540));
        ta.setAutoscrolls(true);
        ta.setEditable(false);
        pScroll = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pScroll.setBounds(new Rectangle(10,10,380,540));
        btnOk.setBounds(new Rectangle(10,ta.getY()+ta.getHeight()+10,380,30));
        btnOk.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
            ocultarVentana(true);
       	}
	});
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt )
            {
                ocultarVentana(true);
            }
        } );
        ta.setText(obtenerInstructivo(lineas));
        lp.add(pScroll,new Integer(1));
        lp.add(btnOk, new Integer(1));
    }
    public void ocultarVentana(boolean ocultar){
	this.setVisible(!ocultar);
	this.dispose();
    }
    public String obtenerInstructivo(String[] lin)
    {
        String instr="";
        for(int i=0; i<lin.length; i++){
            instr+=lin[i]+"\n";
        }
        return instr;
    }
}
