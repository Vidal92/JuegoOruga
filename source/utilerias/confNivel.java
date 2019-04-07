package utilerias;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gateway
 */
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;
import ficheros.*;

public class confNivel extends JFrame implements ChangeListener{

    JButton btnOk=new JButton("Aceptar");
    JLabel titForm=new JLabel("Establecer nivel");
    public int nivel=1;
    Archivos file=new Archivos();
    JSlider niveles;
    int nivs[]={0, 6, 5, 4 ,3 ,2, 1};
    
    public confNivel(String titulo, int x, int y){
	super(titulo);
	this.setLayout(null);
	this.setSize(250,150);
	this.setUndecorated(true);
	this.setResizable(false);
	this.setVisible(true);
	this.setLocation(x,y);
        setLocationRelativeTo(null);
	JLayeredPane lp = getLayeredPane();
        niveles = new JSlider();
        btnOk.setMnemonic('a');
	btnOk.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
            ocultarVentana(true);
       	}
	});
	titForm.setBounds(new Rectangle(10,10,230,20));

	niveles.setBounds(new Rectangle(10,35,230,50));
	niveles.setFocusable(true);
        niveles.setMaximum(6);
        niveles.setMinimum(1);
        niveles.setMajorTickSpacing(1);
        niveles.setValue(1);
        niveles.setPaintTicks(true);
        niveles.setPaintTrack(true);
        niveles.setPaintLabels(true);
        niveles.addChangeListener(this);
        verNivel();
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt )
            {
                ocultarVentana(true);
            }
        } );
        
	btnOk.setBounds(new Rectangle(10,niveles.getY()+niveles.getHeight()+20,230,25));
	lp.add(titForm, new Integer(1));
	lp.add(btnOk,new Integer(1));
	lp.add(niveles,new Integer(1));
    }
    public void ocultarVentana(boolean ocultar){
	this.setVisible(!ocultar);
	this.dispose();
    }
    public void verNivel()
    {
        switch(nivs[nivel]*50)
        {
            case ambienteOruga.NIVEL1:
                titForm.setText("Nivel: NIVEL1");
            break;
            case ambienteOruga.NIVEL2:
                titForm.setText("Nivel: USUARIO PRINCIPIANTE");
            break;
            case ambienteOruga.NIVEL3:
                titForm.setText("Nive: USUARIO BASICO");
            break;
            case ambienteOruga.NIVEL_INTERMEDIO:
                titForm.setText("Nivel: USUARIO INTERMEDIO");
            break;
            case ambienteOruga.NIVEL_AVANZADO:
                titForm.setText("Nivel: USUARIO AVANZADO");
            break;
            case ambienteOruga.NIVEL_EXPERTO:
                titForm.setText("Nivel: USUARIO EXPERTO");
            break;
        }
    }
public void stateChanged(ChangeEvent ce){
      nivel = niveles.getValue();
      ambienteOruga.setValueNivel(nivs[nivel]*50);
      verNivel();
  }
}
