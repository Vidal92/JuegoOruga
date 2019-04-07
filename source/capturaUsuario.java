import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class capturaUsuario extends JFrame implements KeyListener
{
	Archivos file=new Archivos();
	String filePts="a021pts.dll";

	JButton btnOk=new JButton("Aceptar");
	JTextField nUsuario=new JTextField("Administrador");
	JLabel titForm=new JLabel("Usuario destacado");
	JLabel Pts=new JLabel("Puntuacion: ");
	JLabel nPts=new JLabel("0");
	String tiempoJugado="00:00:00";
	Calendar c;

	public capturaUsuario(String Titulo, int posX, int posY, String pts, String tiempo)
	{
		super(Titulo);
		this.setLayout(null);
		this.setSize(250,150);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocation(posX,posY);
		c=new GregorianCalendar();

		JLayeredPane lp = getLayeredPane();
		nPts=new JLabel(pts);
		tiempoJugado=tiempo;
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt )
            {
                ocultarVentana(true);
            }
        } );
		btnOk.setMnemonic('a');
		btnOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				ocultarVentana(true);				
        	}
		});
		titForm.setBounds(new Rectangle(10,10,230,20));

		nUsuario.setBounds(new Rectangle(10,35,230,25));
		nUsuario.setFocusable(true);
		nUsuario.setSelectionColor(Color.blue);
		nUsuario.setSelectedTextColor(Color.white);
		nUsuario.addKeyListener(this);

		Pts.setBounds(new Rectangle(10,65,130,20));
		nPts.setBounds(new Rectangle(Pts.getX()+Pts.getWidth()+5,Pts.getY(),95,20));
		btnOk.setBounds(new Rectangle(10,Pts.getY()+Pts.getHeight()+10,230,25));
		lp.add(titForm, new Integer(1));
		lp.add(Pts,new Integer(1));
		lp.add(nPts, new Integer(1));
		lp.add(btnOk,new Integer(1));
		lp.add(nUsuario,new Integer(1));
		nUsuario.requestFocus();
		nUsuario.selectAll();
	}
	public void ocultarVentana(boolean ocultar){
		String dia = (c.get(Calendar.DATE)<10?"0"+c.get(Calendar.DATE):""+c.get(Calendar.DATE));
		String mes = ((c.get(Calendar.MONTH)+1)<10?"0"+(c.get(Calendar.MONTH)+1):""+c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String lineaNueva=nUsuario.getText()+","+nPts.getText()+","+tiempoJugado+","+dia+"-"+mes+"-"+annio;
		file.guardaRegistro(lineaNueva,filePts);
		this.setVisible(!ocultar);
		this.dispose();
	}
    public void keyPressed( KeyEvent evt ) {
		if(evt.getKeyCode()==KeyEvent.VK_ENTER)
			ocultarVentana(true);
    }
   
    public void keyReleased( KeyEvent evt ) {
    }

    public void keyTyped( KeyEvent evt ) {
    }
/*	public static void main(String A[])
	{
		new capturaUsuario("Usuario destacado",100,100,"10000","00:30:10");
	}*/
}
