package escenarios;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Clase que permite eliminar un escenario creado en el juego, ademas de eliminarlo de la lista de escenarios
 * @author gateway
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import ficheros.*;

public class eliminaEscenario extends JFrame implements ItemListener
{
    JButton btnOk=new JButton("Aceptar");
    JButton btnCancel=new JButton("Cancelar");
    JLabel titForm=new JLabel("Seleccion el escenario");
    Archivos file=new Archivos();
    String escenarios[];
    JComboBox listEscenario;
    StringTokenizer st;
    String Caracteristicas[][];
    /**
     * Constructor principal de la clase, la cual se incializa y se muestra
     * @param titulo titulo de la ventana
     * @param x posicion x sobre la pantalla
     * @param y posicion y sobre la pantalla
     */
    public eliminaEscenario(String titulo, int x, int y){
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
    }/**
      * Inicializa la lista de controles, necesarios la interaccion de la ventana con el usuario, ademas de posicionarlos sobre la misma
     * @param lp Panel que permite aderir los controles sobre capas
     */
    public void inicializaContenedor(JLayeredPane lp)
    {
        listEscenario=new JComboBox();
        btnOk.setMnemonic('a');
        btnCancel.setMnemonic('c');
        btnOk.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
            eliminarFicheros();
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
    /**
     * Oculta la ventana es decir el boton de salir o aceptar
     * @param ocultar True ocultarla, False Mantenerla
     */
     public void ocultarVentana(boolean ocultar){
	this.setVisible(!ocultar);
	this.dispose();
    }
     /**
      * Obtiene la seleccion e invoca la clase que maneja archivos elimina y actualiza el fichero de configuracion del juego
      */
     public void eliminarFicheros()
    {
         int delEscenario=listEscenario.getSelectedIndex();
         try{
            String titFile=Caracteristicas[delEscenario][1];
            String titFileInit=Caracteristicas[delEscenario][2];
            File esc=new File("escenarios/"+titFile);
            File init=new File("escenarios/"+titFileInit);
            escenarios[delEscenario]="";
            file.rescribirEscenarios(escenarios, "a021esc.dll");
            if(esc.delete())
                if(init.delete())
                    JOptionPane.showMessageDialog(this, "Escenario eliminado");
        }catch(Exception e){}
     }
     /**
      * Eventos de la lista de seleccion
      * @param e Evento sobre una opcion del ListBox
      */
    public void itemStateChanged(ItemEvent e) {
        
    }

}
