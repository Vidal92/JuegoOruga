package escenarios;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gateway
 * clase que se usa para la creacion de escenarios
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ficheros.*;

public class Escenario extends JFrame implements ItemListener{

    panelEditable cEdita[][];
    Archivos file=new Archivos();
    public int nren, ncol;
    public int clave=0;
    ButtonGroup grupo;
    ButtonGroup direccion;
    JButton btnGuardar=new JButton("Guardar Escenario");
    JButton btnCancelar=new JButton("Cancelar");
    JRadioButton pared=new JRadioButton();
    JRadioButton arbol=new JRadioButton();
    JRadioButton bomba=new JRadioButton();
    JRadioButton vidas=new JRadioButton("Vidas");
    JRadioButton vidasMenos=new JRadioButton("Vidas falsas");
    JRadioButton escudo=new JRadioButton("Escudos");
    JRadioButton martillo=new JRadioButton("Martillos");
    JRadioButton hacha=new JRadioButton("Hachas");
    JRadioButton borrar=new JRadioButton("Borrar Obtaculos");
    JRadioButton inicio=new JRadioButton("Inicio del Snake");

    JRadioButton left=new JRadioButton("Izquierda");
    JRadioButton right=new JRadioButton("Derecha");
    JRadioButton up=new JRadioButton("Arriba");
    JRadioButton down=new JRadioButton("Abajo");

    public int[][] MatrizEscenario;
    /**
     * Contructor principal de la clase, la cual invoca la mayoria de los metodos que incializan la ventan
     * @param titulo Titulo de la ventana
     * @param posX posicion horizontal de la ventana sobre la pantalla
     * @param posY posicion vertical sobre la pantalla
     * @param ren numero de filas de la matriz para crear el escenario
     * @param col numero de columnas, de la matiz para crear el escenario
     */
    public Escenario(String titulo, int posX, int posY, int ren, int col)
    {
        super(titulo);
        this.setSize(710, 530);
        this.setLocation(posX, posY);
        this.setBackground(Color.red);
        this.setResizable(false);
        //this.setUndecorated(true);
        this.nren=ren;
        this.ncol=col;
        this.MatrizEscenario=new int[ren][col];
        grupo = new ButtonGroup();
        direccion=new ButtonGroup();
        JLayeredPane lp = getLayeredPane();
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt )
            {
                ocultarVentana(true);
            }
        } );
        inicializaPaneles(ren,col, lp);
        inicializaBotones(lp);
        this.setVisible(true);
    }
    /**
     * Este metodo inicializa todos los elemnetos controles que se usan en la ventana, los posiciona, los adiere a la misma
     * @param lp JLeyeredPane usado para aderir los controles en la ventana ppr medio de capas, para evitar mal uso de los controles
     */
    public void inicializaBotones(JLayeredPane lp)
    {
        btnGuardar.setBounds(new Rectangle(100,460,250,25));
        btnCancelar.setBounds(new Rectangle(400,460,250,25));
        pared.setText("Crear ladrillos");
        arbol.setText("Crear arboles");
        bomba.setText("Crear flamas");

        pared.setBounds(5,5,100,20);
        bomba.setBounds(110,5,100,20);
        arbol.setBounds(225,5,100,20);
        vidas.setBounds(330,5,60,20);
        vidasMenos.setBounds(395,5,100,20);
        escudo.setBounds(500,5,100,20);
        martillo.setBounds(600,5,100,20);
        hacha.setBounds(5,30,100,20);
        borrar.setBounds(110,30,120,20);
        
        inicio.setBounds(230,30,120,20);
        left.setBounds(new Rectangle(350,30,100,20));
        right.setBounds(new Rectangle(450,30,100,20));
        up.setBounds(new Rectangle(550,30,100,20));
        down.setBounds(new Rectangle(650,30,100,20));

        arbol.addItemListener(this);
        pared.addItemListener(this);
        bomba.addItemListener(this);
        vidas.addItemListener(this);
        vidasMenos.addItemListener(this);
        escudo.addItemListener(this);
        martillo.addItemListener(this);
        hacha.addItemListener(this);
        borrar.addItemListener(this);

        inicio.addItemListener(this);
        left.addItemListener(this);
        right.addItemListener(this);
        up.addItemListener(this);
        down.addItemListener(this);

	vidas.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	vidasMenos.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	escudo.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	pared.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	arbol.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	bomba.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	martillo.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	hacha.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	borrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	inicio.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	left.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	right.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
	up.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
        down.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));

        lp.add(btnGuardar, new Integer(2));
        lp.add(btnCancelar, new Integer(2));
        grupo.add(pared);
        grupo.add(arbol);
        grupo.add(bomba);
        grupo.add(vidas);
        grupo.add(vidasMenos);
        grupo.add(escudo);
        grupo.add(martillo);
        grupo.add(hacha);
        grupo.add(borrar);
        grupo.add(inicio);

        direccion.add(left);
        direccion.add(right);
        direccion.add(up);
        direccion.add(down);
        
        lp.add(pared,new Integer(2));
        lp.add(arbol,new Integer(2));
        lp.add(bomba,new Integer(2));
        lp.add(vidas,new Integer(2));
        lp.add(vidasMenos,new Integer(2));
        lp.add(escudo,new Integer(2));
        lp.add(martillo,new Integer(2));
        lp.add(hacha,new Integer(2));
        lp.add(borrar,new Integer(2));
        lp.add(inicio,new Integer(2));
        lp.add(left,new Integer(2));
        lp.add(right,new Integer(2));
        lp.add(up,new Integer(2));
        lp.add(down,new Integer(2));
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
			guardarEscenario();
        	}
		});
         btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				ocultarVentana(true);
        	}
		});
    }
    /**
     * Guarda el estado de los paneles, es decir que identificador tiene cada panel,
     * y lo asigna al elemento correspondiente e la matriz
     */
    public void ObtenerMatrizEscenario()
    {
        for(int i=0; i<MatrizEscenario.length; i++)
            for(int j=0; j<MatrizEscenario[0].length; j++)
                MatrizEscenario[i][j]=cEdita[i][j].getValue();
    }
    /**
     * Este metodo guarda el estado de cada uno de los paneles sobre una Matriz de enteros, para posteriormente
     * enviar la peticion para guardarla como un fichero de texto
     */
    public void guardarEscenario()
    {
        String nombreEscenario="", nombreArchivoEscenario="";
        nombreEscenario=JOptionPane.showInputDialog("Nombre del Escenario: ");
        nombreArchivoEscenario=nombreEscenario.replaceAll(" ","");
        ObtenerMatrizEscenario();
        file.guardarNameEscenario(nombreEscenario+","+nombreArchivoEscenario+".esc"+","+nombreArchivoEscenario+"initial.dll","a021esc.dll");
        file.guardaEstadoInicial(panelEditable.Direccion+","+panelEditable.posI+","+panelEditable.posJ, nombreArchivoEscenario+"initial.dll");
        file.guardarEscenario(MatrizEscenario, nombreEscenario, nombreArchivoEscenario);
    }
    /**
     * Metodo para ocultar la ventana, y liberar los recursos
     * @param ocultar estado de la ventana, True oculta la ventana, False la mantiene visible
     */
	public void ocultarVentana(boolean ocultar){
		this.setVisible(!ocultar);
		this.dispose();
	}
        /**
         * Este metodo inicializa un vector de 2 dimensiones de paneles, con los cuales permite crear el escenario
         * @param ren Numero de filas del escenario vector de paneles
         * @param col Numero de columnas del escenario, vector de paneles
         * @param lp
         */
     public void inicializaPaneles(int ren, int col, JLayeredPane lp)
    {
        cEdita=new panelEditable[ren][col];
        for(int i=0; i<this.nren; i++)
        {
            for(int j=0; j<this.ncol; j++)
            {
                cEdita[i][j]=new panelEditable();
                cEdita[i][j].setBounds(new Rectangle((j*20),(i*20)+50,20,20));
                cEdita[i][j].setI(i);
                cEdita[i][j].setJ(j);
                cEdita[i][j].estableceClave(1000);
                lp.add(cEdita[i][j],new Integer(1));
            }
        }
    }
    /**
     * Metodo necesario para capturar los eventos de los botones de radio
     * @param e
     */
    public void itemStateChanged(ItemEvent e) {
        if(e.getItemSelectable()==pared)
            cEdita[0][0].estableceClave(panelEditable.PARED_VALUE);
        else if(e.getItemSelectable()==arbol)
            cEdita[0][0].estableceClave(panelEditable.ARBOL_VALUE);
        else if(e.getItemSelectable()==bomba)
            cEdita[0][0].estableceClave(panelEditable.BOMBA_VALUE);
        else if(e.getItemSelectable()==vidas)
            cEdita[0][0].estableceClave(panelEditable.VIDAS_VALUE);
        else if(e.getItemSelectable()==vidasMenos)
            cEdita[0][0].estableceClave(panelEditable.VIDAS_MENOS_VALUE);
        else if(e.getItemSelectable()==escudo)
            cEdita[0][0].estableceClave(panelEditable.ESCUDO_VALUE);
        else if(e.getItemSelectable()==martillo)
            cEdita[0][0].estableceClave(panelEditable.MARTILLO_VALUE);
        else if(e.getItemSelectable()==hacha)
            cEdita[0][0].estableceClave(panelEditable.HACHA_VALUE);
        else if(e.getItemSelectable()==borrar)
            cEdita[0][0].estableceClave(0);
        else if(e.getItemSelectable()==inicio)
            cEdita[0][0].seleciconarInicio();
        else if(e.getItemSelectable()==left)
            cEdita[0][0].setDireccion(3);
        else if(e.getItemSelectable()==right)
            cEdita[0][0].setDireccion(4);
        else if(e.getItemSelectable()==up)
            cEdita[0][0].setDireccion(1);
        else if(e.getItemSelectable()==down)
            cEdita[0][0].setDireccion(2);
    }
}
