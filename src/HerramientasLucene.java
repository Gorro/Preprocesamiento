/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

/**
 *
 * @author Jorge
 */
public class HerramientasLucene 
{
    public static final Set<?> ENGLISH_STOP_WORDS_SET;     
      static {
        final List<String> stopWords = Arrays.asList(
            "i","de","la","que","el","en","ya","los","del","se","las","por","un","para","con","no","unas","u","a","y",
            "es","hubiese","hubieses","habias","habiamos","lo","como","mas","pero","sus","le","ya","o","fue","este","ha","si","porque",
            "esta","son","entre","está","cuando","fui","fuiste","fue","fuimos","muy","sin","sobre","ser","tiene","también","me","hasta",
            "hay","donde","han","quien","estan","estado","desde","seamos","seais","sean","todo","nos","durante","estados","todos","uno",
            "les","ni","contra","otros","fueron","ese","eso","había","ante","tienes","tiene","estuvimos","ellos","e","esto","mí","antes",
            "algunos","que","unos","yo","otro","otras","otra","el","tanto","esa","estos","mucho","quienes","nada","muchos","cual","sea",
            "poco","ella","estar","haber","estas","estaba","estamos","algunas","algo","nosotros","mi","mis","tu","te","ti","tus",
            "ellas","nosotras","vosotros","vosotras","os","mio","mia","mios","mias","tuyo","tuya","tuyos","tuyas","suyo","suya","suyos",
            "suyas","nuestro","nuestra","nuestros","nuestras","vuestra","vuestros","vuestras","esos","esas","estoy","estás","esta",
            "estamos","estais","estan","este","estes","estemos","esteis","esten","estare","estaras","estara","estaremos","estareis",
            "estaran","estaria","estarias","estariamos","estariais","estarian","estaba","estabas","estabamos","estabais","estaban",
            "estuve","estuviste","estuvo","estuvisteis","estuvieron","estuviera","estuvieras","estuvieramos","estuvierais","estuvieran",
            "estuviese","estuvieses","estuviesemos","estuvieseis","estuviesen","estando","estado","estada","estados","estadas","estado",
            "he","has","haz","ha","hemos","habeis","han","haya","hayas","hayamos","hayais","hayan","habre","habras","habra","habremos",
            "habreis","habran","habria","habrias","habriamos","habriais","habrian","habia","habiais","habian","hube","hubiste","hubo",
            "hubimos","hubisteis","hubieron","hubiera","hubieras","hubieramos","hubierais","hubieran","hubiesemos","hubieseis","hubiesen",
            "habiendo","habido","habida","habidos","habidas","soy","eres","es","somos","sois","son","sea","seas","sere","seras","sera",
            "seremos","sereis","seran","seria","serias","seriamos","seriais","serian","era","eras","éramos","erais","eran","fuisteis",
            "fueron","fuera","fueras","fueramos","fuerais","fueran","fuese","fueses","fuesemos","fueseis","fuesen","siendo","sido","tengo",
            "tenemos","teneis","tienen","tenga","tengas","tengamos","tengais","tengan","tendre","tendras","tendra","tendremos","tendreis",
            "tendran","tendria","tendrias","tendriamos","tendriais","tendrian","tenia","tenias","teniamos","teniais","tenian","tuve",
            "tuviste","tuvo","tuvimos","tuvisteis","tuvieron","tuviera","tuvieras","tuviramos","tuvierais","tuvieran","tuviese","tuvieses",
            "tuviésemos","tuvieseis","tuviesen","teniendo","tenido","tenida","tenidos","tenidas","tened"
       );
         final CharArraySet stopSet = new CharArraySet(stopWords.size(), false);
         stopSet.addAll(stopWords);  
         ENGLISH_STOP_WORDS_SET = CharArraySet.unmodifiableSet(stopSet); 
      }
        public static String LeerTxt()
        {
            File f;
            javax.swing.JFileChooser j = new javax.swing.JFileChooser();
            j.showOpenDialog(j);
            String patch = j.getSelectedFile().getAbsolutePath();
            String lectura = "";
            f = new File(patch);
                try
                {      
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String aux;
                    while((aux = br.readLine()) != null)
                                lectura=lectura+aux+"\n";
                }catch(IOException e)
                {
                    System.out.println("Error: "+e.getMessage());
                }
                lectura = RemoverAcentoyPuntuacion(lectura);
           return lectura.trim();
        }
        public static String Whitespace(String testString) throws Exception
        {
            int i=0;
            Analyzer analyzer = new WhitespaceAnalyzer(); 
            StringBuilder result = new StringBuilder();
            if (testString!=null && testString.trim().length()>0)
            {
            Reader r = new StringReader(testString);      
            TokenStream tStream = analyzer.tokenStream("contents", r);
            TermAttribute term = tStream.addAttribute(TermAttribute.class);
                try {
                    String aux;
                    while (tStream.incrementToken()){
                        if(term.term().contains("."))
                            if(    term.term().contains("1") 
                                || term.term().contains("2")
                                || term.term().contains("3")
                                || term.term().contains("4")
                                || term.term().contains("5")
                                || term.term().contains("6")
                                || term.term().contains("7")
                                || term.term().contains("8")
                                || term.term().contains("9")){
                                aux = term.term().replaceAll("\\.", "");
                                result.append(aux);
                                result.append(" ");
                            }else{
                                result.append(term.term());
                                result.append(" ");
                            }
                        else{
                            result.append(term.term());
                            result.append(" ");
                        }
                    /*if(i==20)
                        {
                            result.append("\n");
                            i=1;
                        }
                        i++;*/
                    }
                } catch (IOException ioe){
                    System.out.println("Error: "+ioe.getMessage());
                }
            }
            if (result.length()==0)
                result.append(testString);
            String Texto = "";
            StringTokenizer palabras = new StringTokenizer(result.toString().trim(),"\\.");
            while(palabras.hasMoreTokens()){
                //if(palabras.nextToken().indexOf(" ") == -1)
                    Texto += palabras.nextToken()+".\n";
            }
            return Texto.trim();  
        }
     public static String SimpleAnalyzer(String testString) throws Exception{
        Analyzer analyzer = new SimpleAnalyzer();      
        StringBuilder result = new StringBuilder();
        if (testString!=null && testString.trim().length()>0)
        {
        int i=0;
        Reader r = new StringReader(testString);      
        TokenStream tStream = analyzer.tokenStream("contents", r);
        TermAttribute term = tStream.addAttribute(TermAttribute.class);
            try {
                while (tStream.incrementToken())
                {
                    result.append(term.term());
                    result.append(" ");
                    if(i==20)
                    {
                        result.append("\n");
                        i=1;
                    }
                    i++;
                }
            } catch (IOException ioe){
                System.out.println("Error: "+ioe.getMessage());
            }
        }
        if (result.length()==0)
            result.append(testString);
        return result.toString().trim();  
        }   
    public static String StopAnalyzer(String testString) throws Exception
    {
        int i=0;
        Analyzer analyzer = new StopAnalyzer(Version.LUCENE_30, ENGLISH_STOP_WORDS_SET);      
        StringBuilder result = new StringBuilder();
        if (testString!=null && testString.trim().length()>0)
        {
        Reader r = new StringReader(testString);      
        TokenStream tStream = analyzer.tokenStream("contents", r);
        TermAttribute term = tStream.addAttribute(TermAttribute.class);
            try {
                while (tStream.incrementToken()){
                    result.append(term.term());
                    result.append(" ");
                if (result.length()==0)
                    result.append(testString);
                    if(i==20)
                    {
                        result.append("\n");
                        i=1;
                    }
                    i++;
                }
            } catch (IOException ioe){
                System.out.println("Error: "+ioe.getMessage());
            }
        }
        return result.toString().trim();  
        } 
    public static String RemoverAcentoyPuntuacion(String input) 
    {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii    = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i=0; i<original.length(); i++) 
            output = output.replace(original.charAt(i), ascii.charAt(i));
        return output.trim();
    }
    public static String Steamming(String text)
    {
        int i=0;
        StringBuilder result = new StringBuilder();
        if (text!=null && text.trim().length()>0)
        {
            StringReader tReader = new StringReader(text);
            Analyzer analyzer = new SnowballAnalyzer(Version.LUCENE_30,"Spanish");
            TokenStream tStream = analyzer.tokenStream("contents", tReader);
            TermAttribute term = tStream.addAttribute(TermAttribute.class);
            try {
                while (tStream.incrementToken()){
                    result.append(term.term());
                    result.append(" ");
                if (result.length()==0)
                    result.append(text);
                     if(i==20)
                    {
                        result.append("\n");
                        i=1;
                    }
                    i++;
                }
            } catch (IOException ioe){
                System.out.println("Error: "+ioe.getMessage());
            }
        }
        return result.toString().trim();        
    }
        public static void GuardarArchivo(String stream)throws IOException 
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        fichero = new FileWriter("c:/canciones.txt",true);
        pw = new PrintWriter(fichero);
        pw.print(""+stream+"\r\n");
        fichero.close();
    }
}
