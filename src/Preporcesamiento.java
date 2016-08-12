
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gorro
 */
public class Preporcesamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fichero = null;
        FileWriter fichero_sn = null, fichero_cn = null;
        PrintWriter pw = null; 
        PrintWriter cn = null, sn = null;
        String[] texto = {  "Articulo","Biografia","Canciones","Cuentos","Entrevista","Fabula","Frag Novela","Lecturas",
                            "Leyendas","Mitos","Noticia","Obrasde teatro","Otros","Poemas","Reportaje"};

        try {
            for (String texto1 : texto) {
                archivo = new File("/home/gorro/Escritorio/Textos/" + texto1 + "/" + texto1 + "_sineditar.txt");
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);
                fichero = new FileWriter("/home/gorro/Escritorio/Textos/" + texto1 + "/" + texto1 +  ".txt");
                pw = new PrintWriter(fichero);
                String linea;
                String Texto = "";
                while((linea=br.readLine())!=null){
                    Texto += linea + " ";
                }                
                pw.print(HerramientasLucene.Whitespace(Texto).toLowerCase());
                fr.close();
                fichero.close();
            }
        }
        catch(Exception e){
           e.printStackTrace();
        }
        try {
            for (String texto1 : texto) {
                archivo = new File("/home/gorro/Escritorio/Textos/" + texto1 + "/" + texto1 +  ".txt");
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);
                fichero_sn = new FileWriter("/home/gorro/Escritorio/Textos/" + texto1 + "/" + texto1 +  "_sn.txt");
                fichero_cn = new FileWriter("/home/gorro/Escritorio/Textos/" + texto1 + "/" + texto1 +  "_cn.txt");
                cn = new PrintWriter(fichero_cn);
                sn = new PrintWriter(fichero_sn);
                
                String linea;
                
                while((linea=br.readLine())!=null)
                    if(    linea.contains(" no ")	
                        || linea.contains(" tampoco ") 	
                        || linea.contains(" nadie ") 	
                        || linea.contains(" jamás ") 	
                        || linea.contains(" ninguno ") 	
                        || linea.contains(" ni ") 	
                        || linea.contains(" sin ") 	
                        || linea.contains(" nada ") 	
                        || linea.contains(" nunca "))
                        cn.println(linea);
                    else{
                        linea = HerramientasLucene.RemoverAcentoyPuntuacion(linea);
                        linea = HerramientasLucene.StopAnalyzer(linea);
                        linea = HerramientasLucene.Steamming(linea);
                        sn.println(linea);
                    }
                
                fr.close();
                fichero_sn.close();
                fichero_cn.close();
            }
        }
        catch(Exception e){
           e.printStackTrace();
        }
    }
    
}
