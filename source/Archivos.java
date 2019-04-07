import java.io.*;
import java.util.*;

public class Archivos
{
	public Archivos(){}

    public int lineas(String ruta)
    {
        int n=0;
        String linea;
      	try
      	{
            RandomAccessFile DIS = new RandomAccessFile(ruta, "r");
            while ( ( (linea = DIS.readLine()) != null) )
                n++;
        }
		catch (IOException e){}

        return n;
    }

    public String[] leer(String ruta)
    {
        String linea;
        int k=0;
        String[] objetos=new String[lineas(ruta)];
      	try
      	{
            RandomAccessFile DIS = new RandomAccessFile(ruta, "r");
            while ( ( (linea = DIS.readLine()) != null) )
            {
                objetos[k]=linea;
                k++;
            }
        }
		catch (IOException e)
      	{
            System.out.println("Error con el archivo...");
      	}
        return objetos;
    }
    /**
     * 
     * @param escenario
     * @param Nombre
     * @param Archivo
     */
    public void guardarEscenario(int[][] escenario, String Nombre, String Archivo)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        String linea="";
        try
        {
            fichero = new FileWriter("escenarios/"+Archivo+".esc");
            pw = new PrintWriter(fichero);
            for(int i=0; i<escenario.length; i++)
            {
                linea="";
                for(int j=0; j<escenario[0].length; j++)
                    linea+=escenario[i][j]+"\t";
                pw.println(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    public int[][] obtenerEscenario(String nombreArchivo)
    {
        int ren=lineas("escenarios/"+nombreArchivo);
        int col=0;
        String[] lineas=leer("escenarios/"+nombreArchivo);
        StringTokenizer st=new StringTokenizer(lineas[0],"\t");
        col=st.countTokens();
        int datos[][]=new int[ren][col];
        for(int i=0; i<ren; i++)
        {
            st=new StringTokenizer(lineas[i],"\t");
            for(int j=0; j<col; j++)
                datos[i][j]=Integer.parseInt(st.nextToken());
        }
        return datos;
    }
    /**
     * 
     * @param archivo
     * @return
     */
    public String[] obtenerEstadoInicial(String archivo)
    {
        String[] leido=leer("escenarios/"+archivo);
        String[] init;
        StringTokenizer st=new StringTokenizer(leido[0],",");
        init=new String[st.countTokens()];
        if(leido.length!=0)
        {
            for(int k=0; st.hasMoreTokens(); k++)
                init[k]=st.nextToken();
        }
        return init;
    }
    
    public void guardarNameEscenario(String Linea, String ruta)
    {
        guardaRegistro(Linea,"escenarios/"+ruta);
    }
    public void guardaRegistro(String Linea, String ruta)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta,true);
            pw = new PrintWriter(fichero);
            pw.println(Linea);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    /**
     *
     * @param Linea
     * @param ruta
     */
    public void guardaEstadoInicial(String Linea, String ruta)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("escenarios/"+ruta);
            pw = new PrintWriter(fichero);
            pw.println(Linea);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    public int[] numPts(String[] datos)
	{
		int n[]=new int[datos.length];
		StringTokenizer st;
		int k=0;
		for(int i=0; i<n.length; i++)
		{
			st=new StringTokenizer(datos[i],",");
			k=0;
			while(st.hasMoreTokens())
			{
				if(k==1) n[i]=Integer.parseInt(st.nextToken());
				st.nextToken();
				k++;
			}
		}
		return n;
	}
    /**
     * 
     * @param lineas
     * @param ruta
     */
    public void rescribirEscenarios(String lineas[], String ruta){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("escenarios/"+ruta);
            pw = new PrintWriter(fichero);
            for(int i=0; i<lineas.length; i++)
                if(!lineas[i].equals(""))
                    pw.println(lineas[i]);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}
