
import utilerias.*;
import escenarios.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public final class juegoOruga extends JFrame implements Runnable
{
    JMenuBar menu=new JMenuBar();
    JMenuItem opcionJuego[]=new JMenuItem[6];
    JMenuItem opcionEscenario[]=new JMenuItem[4];
    JMenuItem opcionDocumentacion[]=new JMenuItem[2];
    JMenuItem opcionAyuda[]=new JMenuItem[2];
    JMenu opcionesPrincipales[]=new JMenu[4];

	slide panelPuntuacion;
        confNivel niv;
	ambienteOruga aO=new ambienteOruga("EscenarioNivel1");
	capturaUsuario cU;
        puntuacionesAltas pa;
        Escenario cE;
        eliminaEscenario ee;
        instrucciones inst;
        loadEscenario ambiente;
        
	int width=710, height=590;
	Thread colector;
	boolean captura=true;
        Image[] iconos;
        String mnemonicosJuego[]={"N","W","P","R","I","A"};
        String mnemonicosEscenario[]={"L","S","M","D"};
        String mnemonicosDoc[]={"F1","F2"};
        String mnemonicosAyuda[]={"F3","F4"};
        /**
         * constructor de la clase, con un paramtro para invocar a la superclase
         * @param titulo Titulo de la ventana
         */
	public juegoOruga(String titulo)
	{
            /**
             * Aqui se inicializan todas las variables y se instancian todas las clases necesarias para el funcionamiento del juego
             */
		super(titulo);
		colector=new Thread(this);
		this.setLayout(null);
        this.setBackground(Color.GREEN);
        this.setSize(width,height);
		this.setResizable(false);
                this.setLocationRelativeTo(null);
		this.setVisible(true);
		JLayeredPane lp = getLayeredPane();
		panelPuntuacion=new slide(width,100);
		panelPuntuacion.setBounds(new Rectangle(0,460,700,100));

		aO.setBounds(new Rectangle(0,25,width,400));
                asignarMenu();
                asignarEventosMenu();
		lp.add(panelPuntuacion,new Integer(2));
		lp.add(aO,new Integer(1));
                iconos=panelPuntuacion.getImages();
                aO.setImages(iconos[2], iconos[3], iconos[1], iconos[0], iconos[4], iconos[5], iconos[6], iconos[7], iconos[8], iconos[9], iconos[10], iconos[11]);
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt )
            {
                System.exit( 0 );
            }
        } );
		colector.start();
                aO.requestFocus();
                
	}

   
    
    public void asignarMenu()
    {
        opcionesPrincipales[0]=new JMenu("Juego");
        opcionesPrincipales[1]=new JMenu("Escenario");
        opcionesPrincipales[2]=new JMenu("Documentación");
        opcionesPrincipales[3]=new JMenu("Ayuda");
        for(int h=0;h<opcionesPrincipales.length;h++)
            menu.add(opcionesPrincipales[h]);

        opcionJuego[0]=new JMenuItem("Nuevo Juego");
        opcionJuego[1]=new JMenuItem("Nivel");
        opcionJuego[2]=new JMenuItem("Pausar");
        opcionJuego[3]=new JMenuItem("Reanudar");
        opcionJuego[4]=new JMenuItem("Instrucciones");
        opcionJuego[5]=new JMenuItem("Puntajes Altos");
        for(int h=0;h<opcionJuego.length;h++)
        {
            opcionJuego[h].setAccelerator(KeyStroke.getKeyStroke("control "+mnemonicosJuego[h]));
            opcionesPrincipales[0].add(opcionJuego[h]);
            if(h==3)
                opcionesPrincipales[0].addSeparator();
        }

        opcionEscenario[0]=new JMenuItem("Cargar Escenario");
        opcionEscenario[1]=new JMenuItem("Crear Escenario");
        opcionEscenario[2]=new JMenuItem("Editar Escenario");
        opcionEscenario[3]=new JMenuItem("Borrar Escenario");
        
        for(int h=0;h<opcionEscenario.length;h++)
        {
            opcionEscenario[h].setAccelerator(KeyStroke.getKeyStroke("control "+mnemonicosEscenario[h]));
            opcionesPrincipales[1].add(opcionEscenario[h]);
            if(h==0)
                opcionesPrincipales[1].addSeparator();
        }

        opcionDocumentacion[0]=new JMenuItem("Abrir documentación");
        opcionDocumentacion[1]=new JMenuItem("Manual de usuario");
        for(int h=0;h<opcionAyuda.length;h++)
        {
            opcionDocumentacion[h].setAccelerator(KeyStroke.getKeyStroke(mnemonicosDoc[h]));
            opcionesPrincipales[2].add(opcionDocumentacion[h]);
            if(h==0)
                opcionesPrincipales[2].addSeparator();
        }

        opcionAyuda[0]=new JMenuItem("Acerca del autor");
        opcionAyuda[1]=new JMenuItem("Acerca de ...");
        for(int h=0;h<opcionAyuda.length;h++)
        {
            opcionAyuda[h].setAccelerator(KeyStroke.getKeyStroke(mnemonicosAyuda[h]));
            opcionesPrincipales[3].add(opcionAyuda[h]);
        }
    }
    /**
     * Metodo que permite abrir una archivo pdf, en este caso la docuemntacion del juego
     */
    public void abrirDocumentacion()
    {
        try{
        File pdf = new File("documentacion/documentacion.jpg");
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(pdf);
        }
        }catch(Exception e){}
    }
    /**
     * En esta funcion se agregan los eventos a cada menuitem
     */
    public void asignarEventosMenu()
    {
        opcionAyuda[0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    abrirDocumentacion();
        	}
		});
        opcionJuego[0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    juegoNuevo();
        	}
		});
        opcionJuego[1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    nivelNuevo();
        	}
		});
        opcionJuego[2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    aO.paused();
        	}
		});
        opcionJuego[3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    aO.resume();
        	}
		});
        opcionJuego[4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    leerInstrucciones();
        	}
		});
        opcionJuego[5].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    abrePuntajes();
        	}
		});
        opcionEscenario[0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    cargaEscenario();
        	}
		});
        opcionEscenario[1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    creaEscenario();
        	}
		});
        opcionEscenario[3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    borrarEscenario();
        	}
		});
        this.setJMenuBar(menu);
    }
    /**
     * Crea la instancia para abrir la ventana que carga un nuevo escenario en el lienzo del juego
     */
    public void cargaEscenario(){
        ambiente=null;
        ambiente=new loadEscenario("Escenarios cargados",this.getWidth()/2-125,this.getHeight()/2-75);
    }
    /**
     * Crea la instancia para mostrar la ventana con las instrucciones del juego
     */
    public void leerInstrucciones(){
        inst=null;
        inst=new instrucciones("Escenarios cargados",this.getWidth()/2-125,this.getHeight()/2-75);
    }
    /**
     * Crea la instancia de la ventana que permite eliminar escenarios del juego
     */
   public void borrarEscenario(){
        ee=null;
        ee=new eliminaEscenario("Escenarios cargados",this.getWidth()/2-125,this.getHeight()/2-75);
    }
   /**
    * Crea una instancia sobre la clase que permite crear nuevos escenarios para posteriormente cargarlos al lienzo del juego
    */
    public void creaEscenario()
    {
        cE=null;
        cE=new Escenario("Edicion de escenarios",this.getWidth()/2-125,this.getHeight()/2-250,20,40);
    }
    /**
     * Crea la instancia sobre la clase que permite cambiar el nivel de complejidad del juego
     */
    public void nivelNuevo(){
        niv=null;
        niv=new confNivel("Niveles disponibles",this.getWidth()/2-125,this.getHeight()/2-75);
    }
    /**
     * Esta funcion invoca los metodos necesarios para crear un juego nuevo, es decir, el reinicializa el juego con nuuevas propedades o las mismas
     */
    public void juegoNuevo(){
        aO.juegoNuevo();
        panelPuntuacion.setTime();
    }
    /**
     * Esta funcion crea la instancia sobre la ventana que muestra las puntuaciones altas, y la muestra al mismo tiempo
     */
    public void abrePuntajes()
    {
        int x=this.getWidth()/2-125;
        int y=this.getHeight()/2-250;
        pa=new puntuacionesAltas("Puntajes Altos",x,y);
    }
    /**
     * Este metodo permite arrancar los hlos de animacion, es decir el juego empieza a correr, con las animaciones necesarias.
     */
	public void run(){
		while(true)
		{
                    aO.setImages(iconos[2], iconos[3], iconos[1], iconos[0], iconos[4], iconos[5], iconos[6], iconos[7], iconos[8], iconos[9], iconos[10], iconos[11]);
			//System.out.println(aO.hojas);
			panelPuntuacion.setManzana(aO.manzana);
			panelPuntuacion.setHojas(aO.hojas);
			panelPuntuacion.setPuntuacion(aO.puntuacion);
			panelPuntuacion.setLifes(aO.vidas);
                        panelPuntuacion.setEscudo(aO.NEscudos);
                        panelPuntuacion.setHacha(aO.NHachas);
                        panelPuntuacion.setMartillo(aO.NMartillos);
                        if(!aO.perdio && !captura) captura=true;
			if(aO.vidas<0 && captura && aO.perdio)
			{
				cU=new capturaUsuario("Usuario destacado",this.getX()+this.getWidth()/2,this.getY()+this.getHeight()/2,""+aO.puntuacion,panelPuntuacion.mTime.getText());
				captura=false;
			}
                        try{
                            colector.sleep(100);
                        }catch(Exception e){}
		}
	}
        /**
         * Metodo principal del juego necesario para ejecutar esta clase, sin este metodo no se puede arrancar el juego
         * @param A Argumentos insertados por consola.
         */
	public static void main(String A[])
	{
            new juegoOruga("Snake");
	}
}
