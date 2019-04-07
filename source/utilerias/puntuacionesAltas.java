package utilerias;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import ficheros.*;

public class puntuacionesAltas extends JFrame
{
	Archivos file=new Archivos();
	String[] Usuarios;
	int[] n;
	String filePts="a021pts.dll";

	JButton btnOk=new JButton("Aceptar");
    final JTable table;
	JScrollPane scrollPane;
	JLabel formLabel=new JLabel("Puntuaciones altas");

    String[] columnTitles = { "Nombre", "Puntos", "Tiempo"};
    Object[][] rowData;

	public puntuacionesAltas(String Titulo, int posX, int posY)
	{
		super(Titulo);
		this.setLayout(null);
		this.setSize(250,500);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLocation(posX,posY);
                this.setVisible(true);
                this.setLocationRelativeTo(null);
		Usuarios=file.leer(filePts);
		n=file.numPts(Usuarios);
		Ordenar();
		rowData=datos();

	    table = new JTable(rowData, columnTitles);
		table.setForeground(new Color(0,0,150));
		table.setCellSelectionEnabled(true);
		table.setGridColor(Color.lightGray);
		table.setSelectionBackground(new Color(200,200,255));
		table.setSelectionForeground(new Color(150,0,0));
		scrollPane=new JScrollPane(table);
		scrollPane.setBounds(10,40,230,410);
		btnOk.setBounds(new Rectangle(20,455,210,25));
		btnOk.setMnemonic('a');
		formLabel.setBounds(20,10,210,20);
		JLayeredPane lp = getLayeredPane();
		btnOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				ocultarVentana(true);				
        	}
		});
		lp.add(scrollPane, new Integer(1));
		lp.add(btnOk,new Integer(2));
		lp.add(formLabel, new Integer(2));
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent evt )
            {
                ocultarVentana(true);
            }
        } );
	}

	public void ocultarVentana(boolean ocultar){
		this.setVisible(!ocultar);
		this.dispose();
	}
	public String[][] datos()
	{
		StringTokenizer st;
		String dats[][]=new String[24][4];
		for(int i=0; i<24; i++)
		{
			if(Usuarios.length>i)
			{
				st=new StringTokenizer(Usuarios[i],",");
				for(int j=0;st.hasMoreTokens(); j++)
					dats[i][j]=st.nextToken();
			}
		}
		return dats;
	}
    public int[] Ordenar()
	{
        int temp;
        int t = n.length;
		String tempS;
        for (int i = 1; i < t; i++) 
		{
            for (int k = t- 1; k >= i; k--) 
			{
                if(n[k] > n[k-1])
				{
                    temp = n[k];
					tempS=Usuarios[k];

                    n[k] = n[k-1];
					Usuarios[k]=Usuarios[k-1];

                    n[k-1]=  temp;
					Usuarios[k-1]=tempS;
                }
            }
        }
        return n;
    }
	/*public static void main(String Ar[])
	{
		new puntuacionesAltas("Puntuaciones Altas",100,100);
	}*/
}
