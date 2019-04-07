package escenarios;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gateway
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import ficheros.*;
import utilerias.*;

public class loadEscenario extends JFrame implements ItemListener{
    JButton btnOk=new JButton("Aceptar");
    JButton btnCancel=new JButton("Cancelar");
    JLabel titForm=new JLabel("Seleccion el escenario");
    Archivos file=new Archivos();
    String escenarios[];
    JComboBox listEscenario;
    StringTokenizer st;
    String Caracteristicas[][];
    public loadEscenario(String titulo, int x, int y){
	super(titulo);
	this.setLayout(null);
	this.setSize(250,150);
	this.setUndecorated(true);
	this.setResizable(false);
	this.setVisible(true);
	this.setLocation(x,y);
        setLocationRelativeTo(null);
	JLayeredPane lp = getLayeredPane();
        inicializaContenedor(lp);
    }
    public void inicializaContenedor(JLayeredPane lp)
    {
        listEscenario=new JComboBox();
        btnOk.setMnemonic('a');
        btnCancel.setMnemonic('c');
        btnOk.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
            cargarEscenario();
            ocultarVentana(true);
       	}
	});
        btnCancel.addActionListener(new ActionListener() {
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
        titForm.setBounds(new Rectangle(10,10,230,20));

	listEscenario.setBounds(new Rectangle(10,35,230,50));
        escenarios=file.leer("escenarios/a021esc.dll");
        Caracteristicas=new String[escenarios.length][3];
        for(int i=0; i<escenarios.length; i++)
        {
            st=new StringTokenizer(escenarios[i],",");
            for(int j=0;st.hasMoreTokens();j++)
                Caracteristicas[i][j]=st.nextToken();
            listEscenario.addItem(Caracteristicas[i][0]);
        }
        listEscenario.addItemListener(this);
	btnOk.setBounds(new Rectangle(10,listEscenario.getY()+listEscenario.getHeight()+20,110,25));
	btnCancel.setBounds(new Rectangle(130,listEscenario.getY()+listEscenario.getHeight()+20,110,25));
        lp.add(titForm, new Integer(1));
	lp.add(btnOk,new Integer(1));
        lp.add(btnCancel,new Integer(1));
	lp.add(listEscenario,new Integer(1));
    }
     public void ocultarVentana(boolean ocultar){
	this.setVisible(!ocultar);
	this.dispose();
    }
     public void cargarEscenario()
    {
         int delEscenario=listEscenario.getSelectedIndex();
         try{
            String titFile=Caracteristicas[delEscenario][0];
/*            String titFileInit=Caracteristicas[delEscenario][2];
            File esc=new File("escenarios/"+titFile);
            File init=new File("escenarios/"+titFileInit);*/
            ambienteOruga.setLoadEscenario(titFile);
            //file.rescribirEscenarios(escenarios, "a021esc.dll");
            //if(esc.delete())
              //  if(init.delete())
            JOptionPane.showMessageDialog(this, "Escenario cargado completamente");
        }catch(Exception e){}
     }
    public void itemStateChanged(ItemEvent e) {

    }

}

