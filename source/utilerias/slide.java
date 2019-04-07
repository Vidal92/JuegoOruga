package utilerias;

import java.awt.*;
import javax.swing.*;

public class slide extends JPanel implements Runnable
{
	Thread reloj;
	public JLabel mTime=new JLabel("00:00:00");
	public JLabel vidas=new JLabel("2");
	
	public JLabel manzanas=new JLabel("0");
	public JLabel hojas=new JLabel("0");
	public JLabel bombas=new JLabel("0");
        public JLabel martillos=new JLabel("0");
        public JLabel escudos=new JLabel("0");
        public JLabel hachas=new JLabel("0");
	public JLabel ntotal=new JLabel("Total:");
	public JLabel totales=new JLabel("0");
	public JLabel Fondo=new JLabel();
	int horas=0, minutos=0, segundos=0;
	String Shr="", Smin="", Sseg="";
	int puntuacionTotal=0, mzn=0, hjas=0, lifes=0, esc=0, mart=0, hach=0;

	public slide(int width, int height)
	{
		reloj=new Thread(this);
		this.setLayout(null);
		this.setBackground(new Color(0,0,50));
		Fondo.setBounds(new Rectangle(0,0,width,height));
		Fondo.setText("Panel de puntuaciones");
		disenioControl();
		cargarIconos();
		cargarControles();
		reloj.start();
	}
    public void setTime()
    {
        horas=0;
        minutos=0;
        segundos=0;
    }
	public void setHojas(int inputHoja)
	{
		hjas=inputHoja;
		puntuacionTotal=hjas*25 + mzn*50;
	}
	public void setManzana(int inputManzana)
	{
		mzn=inputManzana;
		puntuacionTotal=hjas*25 + mzn*50;
	}
	public void setPuntuacion(int inputPuntuacion)
	{
		puntuacionTotal=inputPuntuacion;
	}
	public void setLifes(int inputVidas)
	{
		lifes=inputVidas;
	}
        public void setMartillo(int inputMartillo){
            mart=inputMartillo;
        }
        public void setHacha(int inputHacha){
            hach=inputHacha;
        }
        public void setEscudo(int inputEscudo){
            esc=inputEscudo;
        }
	public void disenioControl()
	{
		hojas.setBounds(new Rectangle(0,3,100,30));
		hojas.setForeground(Color.white);
		hojas.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

		manzanas.setBounds(new Rectangle(0,36,100,30));
		manzanas.setForeground(Color.white);
		manzanas.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

		bombas.setBounds(new Rectangle(0,69,100,30));
		bombas.setForeground(Color.white);		
		bombas.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

		vidas.setBounds(new Rectangle(100,3,100,30));
		vidas.setForeground(Color.white);
		vidas.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

		escudos.setBounds(new Rectangle(200,3,100,30));
		escudos.setForeground(Color.white);
		escudos.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

		martillos.setBounds(new Rectangle(300,3,100,30));
		martillos.setForeground(Color.white);
		martillos.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

        	hachas.setBounds(new Rectangle(400,3,100,30));
		hachas.setForeground(Color.white);
		hachas.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));

		ntotal.setBounds(new Rectangle(100,40,150,50));
		ntotal.setForeground(Color.white);
		ntotal.setFont(new Font(Font.SANS_SERIF, Font.BOLD,40));

		totales.setBounds(new Rectangle(250,40,180,50));
		totales.setForeground(Color.red);
		totales.setFont(new Font(Font.SANS_SERIF, Font.BOLD,40));

		mTime.setBounds(new Rectangle(400,40,250,60));
		mTime.setForeground(Color.orange);
		mTime.setFont(new Font(Font.SANS_SERIF, Font.BOLD,40));
	}
	public void cargarControles()
	{
		//this.add(Fondo);		
		this.add(manzanas);		
		this.add(hojas);		
		this.add(bombas);		
		this.add(vidas);		
		this.add(escudos);
		this.add(martillos);
		this.add(hachas);
		this.add(ntotal);		
		this.add(totales);		
		this.add(mTime);		
	}
	public void cargarIconos()
	{
			Fondo.setIcon(new ImageIcon("hdu.jpg"));
			manzanas.setIcon(new ImageIcon("manzana.png"));
			hojas.setIcon(new ImageIcon("comida.png"));
			bombas.setIcon(new ImageIcon("bomba.gif"));
			vidas.setIcon(new ImageIcon("vida.png"));
                        escudos.setIcon(new ImageIcon("escudo.jpg"));
                        martillos.setIcon(new ImageIcon("martillo.png"));
                        hachas.setIcon(new ImageIcon("hacha.jpg"));
	}
    public Image[] getImages()
    {
        Image[] icono=new Image[12];
        icono[0]=(new ImageIcon("pared.jpg")).getImage();
        icono[1]=(new ImageIcon("manzana.png")).getImage();
        icono[2]=(new ImageIcon("comida.png")).getImage();
        icono[3]=(new ImageIcon("bomba.gif")).getImage();
        icono[4]=(new ImageIcon("cabeza.png")).getImage();
        icono[5]=(new ImageIcon("oruga.gif")).getImage();
        icono[6]=(new ImageIcon("arbol.png")).getImage();
        icono[7]=(new ImageIcon("vida.png")).getImage();
        icono[8]=(new ImageIcon("vidamenos.png")).getImage();
        icono[9]=(new ImageIcon("escudo.jpg")).getImage();
        icono[10]=(new ImageIcon("martillo.png")).getImage();
        icono[11]=(new ImageIcon("hacha.jpg")).getImage();

        return icono;
    }
	public void run()
	{
		while(true)
		{
			if(segundos>=60){
				segundos=0;
				minutos++;
			}
			if(minutos>=60){
				minutos=0;
				horas++;
			}
			Shr=(horas<10)?"0"+horas:""+horas;
			Smin=(minutos<10)?"0"+minutos:""+minutos;
			Sseg=(segundos<10)?"0"+segundos:""+segundos;
			mTime.setText(Shr+":"+Smin+":"+Sseg);
			manzanas.setText(""+mzn);
			hojas.setText(""+hjas);
                        martillos.setText(""+mart);
                        hachas.setText(""+hach);
                        escudos.setText(""+esc);
			totales.setText(""+puntuacionTotal);
			vidas.setText(""+lifes);
			segundos++;
			try{
				reloj.sleep(1000);
			}
			catch(Exception e){}
		}
	}
}
