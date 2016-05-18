package Praktikum5ADS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Der InputReader kapselt die FÃ¤higkeit des Programms Nutzereingaben von der
 * Konsole zu lesen.
 *
 * @author Sasha Koß, FH Bielefeld
 * @author Peter Dick, FH Bielefeld
 * @date 24.04.2016
 */
public class ConsoleReader {
	
    private BufferedReader reader;
    
    /**
     * Erzeugt einen neuen Reader zum Einlesen von Zeichen von der Konsole.
     * Nutzt dazu den übergebenen BufferedReader.
     *
     * @param r
     *            BufferedReader zum Einlesen von der Konsole
     */
    public ConsoleReader(BufferedReader r) {
    	if(r == null) {
    		r = new BufferedReader(new InputStreamReader(System.in));
    	}
    	reader = r;
    }
    
    /**
     * Gibt den reader zurück.
     * 
     * @return reader  
     * */
    public BufferedReader getReader() {
    	return reader;
    }
    
    /**
     * Setzt den reader.
     * 
     * @param r BufferedReader zum Einlesen von der Konsole
     * */
    public void setReader(BufferedReader r) {
    	if(r == null) {
    		r = new BufferedReader(new InputStreamReader(System.in));
    	}
    	reader = r;
    }
    
    
    /**
     * Liest ein Zeichen von der Konsole und reicht dieses weiter an den
     * Aufrufer.
     *
     * Wenn es beim Einlesen Probleme gab oder keine Zeichen im Stream vorhanden
     * sind, wird eine IOException ausgelÃ¶st.
     *
     * @return Eingelesenes Zeichen
     * @throws IOException
     */
    
    public char readNextChar() throws IOException {
    	try {
			int charTemp = reader.readLine().toLowerCase().charAt(0);
			while ((char) charTemp == '\r' || (char) charTemp == '\n' || (char) charTemp == ' ') {
				charTemp = reader.readLine().toLowerCase().charAt(0);
			}
			if(charTemp != -1) {
				return (char) charTemp;
			} else {
				throw new IOException("BufferedReader ist leer.");
			}
		} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
			throw new IOException("BufferedReader ist leer.");
		}
    }
    
    public String readNextLine() throws IOException {
    	try {
			return reader.readLine();
		} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
			throw new IOException("BufferedReader ist leer.");
		}
    }
}