import java.awt.*;
import javax.sound.sampled.*;
import java.io.*;
import java.awt.event.*;

public class ambienteOruga extends Canvas implements KeyListener, Runnable
{
    String Tecla = "";
    int Codigo = 0;
    int i, j, direccion, k, l, contador, aux, aux2, NMartillos, NHachas, NEscudos;
    int columas = 20, filas = 40;
    Graphics g;
    Thread hilo;
    boolean correrHilo=true;
    Archivos file=new Archivos();
    int Matriz[][] = new int[columas][filas];
    String [] initial;
    boolean yaGeneroBomba=false, perdio=false;
    Image comida, bomba, megaComida, oruga, cabeza, pared, panelF, arbol, vidas_add, vidas_menos, hacha, martillo, Escudo;
    public int puntuacion=0, manzana=0, hojas=0, vidas=2, nivel=0;

    public static final int NIVEL1=300;
    public static final int NIVEL2=250;
    public static final int NIVEL3=200;
    public static final int NIVEL_INTERMEDIO=150;
    public static final int NIVEL_AVANZADO=100;
    public static final int NIVEL_EXPERTO=50;
    public String escenarioActual="";

    public static int setNivel=NIVEL1;
    public static String newEscenario="";
    
	public boolean generando=true, generandoMegacomida=true;

	public ambienteOruga(String nombreEscenario)
	{
        this.setBackground(Color.black);
        this.setSize(700,600);
        addKeyListener(this);
        newEscenario=nombreEscenario;
        inicializar();
        hilo = new Thread(this);
        hilo.start();
	}

    ambienteOruga() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
	public void run(){
            //4431972993 Cristopher
        while (true)
        {
            try
            {
                if(perdio && vidas<0)
                {
                    contador = 0;
                    correrHilo=false;
                }
                    hilo.sleep(nivel);
                //System.out.println(correrHilo+"==>"+direccion+" i==>"+i+" j==>"+j);
                if(correrHilo)
                {
                    if(setNivel!=nivel || newEscenario.compareTo(escenarioActual)!=0) juegoNuevo();
                    switch (direccion)
                    {
                        case 1:
                            Subiendo();
                            repaint();
                        break;
                        case 2:
                            Bajando();
                            repaint();
                        break;
                        case 3:
                            Izquierda();
                            repaint();
                        break;
                        case 4:
                            Derecha();
                            repaint();
                        break;
                    }
                }
                if (contador%15==0)
                {
                    if(!yaGeneroBomba)
                    {
                        generarMegaComida();
                        generarBomba();
                        generarBomba();
                        yaGeneroBomba=true;
                    }
                    //JOptionPane.showMessageDialog(this, "Felizidaz Ganaste !!");
                    //contador = 0;
                    //hilo.stop();
                }
                if (contador%15!=0 && yaGeneroBomba)
                {
                    yaGeneroBomba=false;
                }
                
            }//1000 es un segundo
            catch (Exception e) {}
        }
	}
    public void paused()
    {
        this.correrHilo=false;
        guardaEstado();
    }
    public void resume()
    {
        this.correrHilo=true;
        restableceEstado();
    }
    public void restableceEstado(){

    }
    public void guardaEstado(){
        
    }
    public void setImages(Image come, Image bmb, Image Mcomida, Image ladrillo, Image cabeza, Image Cuerpo, Image Arbol, Image vidas, Image vidasMenos, Image escudo, Image Martillo, Image Hacha)
    {
        comida = come;
        bomba = bmb;
        megaComida = Mcomida;
        pared = ladrillo;
        this.cabeza = cabeza;
        oruga = Cuerpo;
        arbol=Arbol;
        vidas_add=vidas;
        vidas_menos=vidasMenos;
        hacha=Hacha;
        martillo=Martillo;
        Escudo=escudo;
    }
    public void generarAlimento()
    {
		generando=true;
		int ii=0,jj=0;
		do
		{
			ii=(int) (Math.random() * (columas-2));
			jj=(int) (Math.random() * (filas-7));
			if(Matriz[1 + ii][1 + jj]<=contador)
			{
	        	Matriz[1 + ii][1 + jj] = 500;
				generando=false;
			}
		}while(generando);
    }
    public static void setLoadEscenario(String LoadEscenario){
        newEscenario=LoadEscenario;
    }
    public static void setValueNivel(int inputNivel){
        switch(inputNivel)
        {
            case NIVEL1:
                setNivel=NIVEL1;
            break;
            case NIVEL2:
                setNivel=NIVEL2;
            break;
            case NIVEL3:
                setNivel=NIVEL3;
            break;
            case NIVEL_INTERMEDIO:
                setNivel=NIVEL_INTERMEDIO;
            break;
            case NIVEL_AVANZADO:
                setNivel=NIVEL_AVANZADO;
            break;
            case NIVEL_EXPERTO:
                setNivel=NIVEL_EXPERTO;
            break;
            default: setNivel=NIVEL1;
        }
    }
    void inicializar()
    {
        correrHilo=true;
        vidas=2;
        i = 0; j = 0; direccion= 2; contador = 5; aux = 5; aux2 = 0;
        perdio=false;
        nivel=setNivel;
        escenarioActual=newEscenario;
        System.out.println(escenarioActual);
        Matriz=file.obtenerEscenario(escenarioActual.replaceAll(" ", "")+".esc");
        initial=file.obtenerEstadoInicial(escenarioActual.replaceAll(" ", "")+"initial.dll");
        if(initial.length>0)
        {
            direccion=Integer.parseInt(initial[0]);
            i=Integer.parseInt(initial[1]);
            j=Integer.parseInt(initial[2]);
        }
        Matriz[i][j] = aux;
        generarAlimento();
        NMartillos=2;
        NHachas=2;
        NEscudos=2;
    }
    public void juegoNuevo()
    {
        correrHilo=true;
        perdio=false;
        nivel=setNivel;
        escenarioActual=newEscenario;
        System.out.println(escenarioActual);
        i = 0; j = 0; direccion= 2; contador = 5; aux = 5; aux2 = 0; vidas=2; puntuacion=0;
        hojas=0;manzana=0;
        Matriz=file.obtenerEscenario(escenarioActual.replaceAll(" ", "")+".esc");
        initial=file.obtenerEstadoInicial(escenarioActual.replaceAll(" ", "")+"initial.dll");
        if(initial.length>0)
        {
            direccion=Integer.parseInt(initial[0]);
            i=Integer.parseInt(initial[1]);
            j=Integer.parseInt(initial[2]);
        }
        Matriz[i][j] = aux;
        generarAlimento();
        NMartillos=2;
        NHachas=2;
        NEscudos=2;
        repaint();
    }
    public void reiniciar()
    {
        correrHilo=true;
        i = 0; j = 0; direccion= 2; contador = 5; aux = 5; aux2 = 0;
        nivel=setNivel;
        escenarioActual=newEscenario;
        System.out.println(escenarioActual);
        initial=file.obtenerEstadoInicial(escenarioActual.replaceAll(" ", "")+"initial.dll");
        eliminarCola();
        if(initial.length>0)
        {
            direccion=Integer.parseInt(initial[0]);
            i=Integer.parseInt(initial[1]);
            j=Integer.parseInt(initial[2]);
        }
        NMartillos=2;
        NHachas=2;
        NEscudos=2;
        Matriz[i][j] = aux;
        generarAlimento();
    }
    public void eliminarCola(){
         for(int i=0; i<Matriz.length; i++)
            for(int j=0; j<Matriz[0].length; j++)
                if(Matriz[i][j]<=500)
                    Matriz[i][j]=0;
    }
    public void generarBomba()
    {
        Matriz[1 + (int) (Math.random() * (columas-2))][1 + (int) (Math.random() * (filas-6))] = 10000;
    }
    public void generarMegaComida()
    {
		generandoMegacomida=true;
        Matriz[1 + (int) (Math.random() * (columas-2))][1 + (int) (Math.random() * (filas-6))] = 1000;
    }
    void MuestraDatos(KeyEvent e)
    {
        Codigo = e.getID();//basico, puras letras		
        if (Codigo == KeyEvent.KEY_PRESSED) //keyPressedapretar y soltar
        {
            Codigo = e.getKeyCode();//teclado completo
            switch (Codigo)
            {
                case KeyEvent.VK_UP://Oruga subiendo
					if(direccion!=2)
	                    direccion = 1;
                break;
                case KeyEvent.VK_DOWN://Oruga bajando
					if(direccion!=1)
	                    direccion = 2;
                break;
                case KeyEvent.VK_LEFT://Oruga a la izquierda
					if(direccion!=4)
	                    direccion = 3;
                break;
                case KeyEvent.VK_RIGHT://Oruga a la derecha
					if(direccion!=3)
	                    direccion = 4;
                break;
            }
        }
    }
    public void Subiendo()
    {
        if (i == 0)
           i= columas;
        i = i - 1;
    }

    public void Bajando()
    {
        if (i == columas - 1)
            i=-1;

        i = i + 1;

    }

    public void Izquierda()
    {
        if (j == 0)
            j=filas - 5;
        j = j - 1;
    }

    public void Derecha()
    {
        if (j == filas - 6)
            j=-1;
        j = j + 1;
    }

    public void dibujaViborita()
    {
        g.drawImage(oruga, l * 20, k * 20, this);
        g.drawImage(cabeza, j * 20, i * 20, this);
    }

    public void dibujaComida()
    {
        g.drawImage(comida, l * 20, k * 20, this);
    }
    public void dibujaBombas()
    {
        g.drawImage(bomba, l * 20, k * 20, this);
    }
    public void dibujaPared()
    {
        g.drawImage(pared, l * 20, k * 20, this);
    }
    public void dibujaMegaComida()
    {
        g.drawImage(megaComida, l * 20, k * 20, this);
    }
    public void dibujaArbol()
    {
        g.drawImage(arbol, l * 20, k * 20, this);
    }
    public void dibujaHacha(){
        g.drawImage(hacha, l * 20, k * 20, this);
    }
    public void dibujaMartillo(){
        g.drawImage(martillo, l * 20, k * 20, this);
    }
    public void dibujaVidas(){
        g.drawImage(vidas_add, l * 20, k * 20, this);
    }
    public void dibujaQuitaVidas(){
        g.drawImage(vidas_menos, l * 20, k * 20, this);
    }
    public void dibujaEscudo(){
        g.drawImage(Escudo, l * 20, k * 20, this);
    }
    public void keyPressed(KeyEvent e){MuestraDatos(e);}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public boolean obstaculos()
    {
        boolean obstaculo=false;
        
        if((Matriz[i][j]==10500 && NMartillos<=0) || (Matriz[i][j]==20000&& NHachas<=0) || Matriz[i][j]==20004 || (Matriz[i][j]==10000 && NEscudos<=0))
        {
             obstaculo = true;
             vidas--;
        }
        else obstaculo = false;
        if(Matriz[i][j]==10500) NMartillos--;
        if(Matriz[i][j]==20000) NHachas--;
        if(Matriz[i][j]==10000) NEscudos--;
        return obstaculo;
    }
    public void addHacha(){
        NHachas++;
    }
    public void addEscudo(){
        NEscudos++;
    }
    public void addMartillo(){
        NMartillos++;
    }
    public void addVida(){
        vidas++;
    }
    public void paint(Graphics Grafico)
    {
        g = Grafico;
        Grafico.clearRect(0, 0, 600, 400);
        if(Matriz[i][j]==1000)
        {
            aux = aux + 1;
            Matriz[i][j] = aux;
            contador++;
            manzana++;
            puntuacion+=50;
        }
        if(obstaculos()||(Matriz[i][j]>0 && Matriz[i][j]<aux))
        {
            //haPerdido();
            perdio=true;
            reiniciar();
        }
        if (Matriz[i][j] == 500)
        {
            aux = aux + 1;
            Matriz[i][j] = aux;
            contador++;
            hojas++;
            generarAlimento();
            puntuacion+=25;
        }

        switch(Matriz[i][j])
        {
            case panelEditable.ESCUDO_VALUE:
                addEscudo();
            break;
            case panelEditable.MARTILLO_VALUE:
                addMartillo();
            break;
            case panelEditable.HACHA_VALUE:
                addHacha();
            break;
            case panelEditable.VIDAS_VALUE:
                addVida();
            break;
        }
        //come
        for (k = 0; k < Matriz.length; k++)//columas - 1
        {
            for (l = 0; l < Matriz[0].length; l++)//filas - 1
            {
                if (Matriz[k][l] == 1000)
                {
                    dibujaMegaComida();
                    Matriz[i][j] = aux;
                }
                if (Matriz[k][l] == 10000)
                {
                    dibujaBombas();
                    Matriz[i][j] = aux;
                }
                if (Matriz[k][l] == 500)
                {
                    //Matriz[i][j] = Matriz[i][j] + 1;
                    Matriz[i][j] = aux;
                    dibujaComida();
                }
                if (Matriz[k][l] < 500 && Matriz[k][l] > 0 && Matriz[k][l]!=10000)
                {
                    dibujaViborita();
                    Matriz[k][l] = Matriz[k][l] - 1;
                }
                if (Matriz[k][l] == 10500)
                {
                    dibujaPared();
                    Matriz[i][j] = aux;
                }
                if (Matriz[k][l] == 20000)
                {
                    dibujaArbol();
                    Matriz[i][j] = aux;
                }
                if (Matriz[k][l] == panelEditable.VIDAS_VALUE)
                {
                    dibujaVidas();
                    Matriz[i][j] = aux;
                }
                if (Matriz[k][l] == panelEditable.VIDAS_MENOS_VALUE)
                {
                    dibujaQuitaVidas();
                    Matriz[i][j] = aux;
                }
                if (Matriz[k][l] == panelEditable.MARTILLO_VALUE)
                {
                    dibujaMartillo();
                    Matriz[i][j] = aux;
                }
                if (Matriz[k][l] == panelEditable.HACHA_VALUE)
                {
                    dibujaHacha();
                    Matriz[i][j] = aux;
                }
                if (Matriz[k][l] == panelEditable.ESCUDO_VALUE)
                {
                    dibujaEscudo();
                    Matriz[i][j] = aux;
                }
            }
        }
    }
}
