/**
 *
 * Beschreibung
 *
 * @version 2026
 * @author DIE
 */
import JavaServer.Server;
public class MatheServer extends Server {

    public MatheServer (int pPortNum) {
        super(pPortNum);
    }

    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println(pClientIP + "/" + pClientPort + ": Anmeldung"); //Konsolenausgabe zur Dokumentation auf Serverseite.
        System.out.println(pClientIP + "/" + pClientPort + ": Willkommen auf dem MatheServer!"); //Konsolenausgabe zur Dokumentation auf Serverseite.
        this.send(pClientIP, pClientPort, "Willkommen auf dem MatheServer!");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        //Hier programmieren.
    }

    public void processClosingConnection(String pClientIP, int pClientPort) {
        this.send(pClientIP, pClientPort, "Du bist abgemeldet.");
        System.out.println(pClientIP + "/" + pClientPort + ": Abmeldung"); //Konsolenausgabe zur Dokumentation auf Serverseite.
    }

    /**
     * Diese Anfrage liefert das Wort mit der angegebenen Wortnummer in dem
     * angegebenen Text. Die Wörter sind dabei beginnend mit 0 nummeriert und
     * durch Trennzeichen voneinander getrennt. 
     * Falls kein Wort an der angegebenen
     * Wortnummer existiert, wird ein leerer String zurückgegeben.
     * @param pStr der bezuegliche Trennzeichen zu zerlegende String
     * @param pNumber der heraus zu trennende String (beginnend mit 0) 
     * @return das Wort auf dem String pStr, welches bezueglich einer 
     * Trennung mit Trennzeichen an der Stelle pNumber steht
     */
    private String part(String pStr, int pNumber) {
        String[] help = pStr.split(":");
        if (pNumber < help.length) {
            return help[pNumber];
        }
        else return "";
    }  
    
    
    
    
}
