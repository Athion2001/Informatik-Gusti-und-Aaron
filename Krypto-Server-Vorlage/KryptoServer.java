/**
 * Kryptologie-Server (Vorlage).
 *
 * Aufbau in drei Schichten - haltet sie bitte getrennt:
 *  1. Netzwerk:              Klasse Server (vorgegeben, nicht aendern)
 *  2. Protokollverarbeitung: diese Klasse (processMessage, bearbeiteAnfrage)
 *  3. Verschluesselung:      Klasse Caesar (kennt weder Netzwerk noch Protokoll)
 *
 * Schreibt hier euer Protokoll aus Aufgabe 2 als Kommentar auf:
 *
 *   TODO: Anfragen (Client an Server) und Antworten (Server an Client)
 *
 * @author (Namen eintragen)
 * @version (Datum)
 */
public class KryptoServer extends Server
{

    public static void main(String args[]){
        KryptoServer meinKryptoServer = new KryptoServer(8080);
    }
    public KryptoServer(int pPort)
    {
        super(pPort);
    }

    // ---------------------------------------------------------------
    // Ereignisbehandlung (wird von der Klasse Server aufgerufen)
    // ---------------------------------------------------------------

    public void processNewConnection(String pClientIP, int pClientPort)
    {
        // TODO (optional): Begruessung an den Client senden, wenn euer Protokoll
        //                  eine vorsieht. 
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage)
    {
        // Die Auswertung der Anfrage passiert in bearbeiteAnfrage (ohne Netzwerk).
        //String antwort = bearbeiteAnfrage(pMessage);
        //send(pClientIP, pClientPort, antwort);

        // TODO: Wenn euer Protokoll ein Verbindungsende vorsieht (z. B. "ENDE"),
        //       muss der Server hier nach der Antwort die Verbindung beenden

        String verfahren = feldAn(pMessage, 1);
        String operation = feldAn(pMessage, 2);
        switch(verfahren){
            case "Caesar":
                if(operation.equals("Verschlüsseln")){
                    send(pClientIP, pClientPort, "Der Text wurde mit " + verfahren + "verschluesselt :" + Caesar.verschluesseln(feldAn(pMessage,4),Integer.parseInt(feldAn(pMessage,3))));
                }
                else if(operation.equals("Entschlüsseln")){
                    send(pClientIP, pClientPort, "Der Text wurde mit " + verfahren + "entschluesselt :" + Caesar.entschluesseln(feldAn(pMessage,4),Integer.parseInt(feldAn(pMessage,3))));
                }
                else{
                    send(pClientIP, pClientPort, "-FEHLER:Unbekannter Operator");
                }
                break;
            default:
                send(pClientIP, pClientPort, "-FEHLER:Unbekanntes Verfahren");
        }
    }

    public void processClosingConnection(String pClientIP, int pClientPort)
    {
        System.out.println("Verbindung beendet: " + pClientIP + ":" + pClientPort);
    }

    // ---------------------------------------------------------------
    // Protokollverarbeitung
    // ---------------------------------------------------------------

    /**
     * Wertet eine Anfrage nach eurem Protokoll aus und liefert die Antwort.
     * Diese Methode soll KEINE Netzwerkmethoden (send, closeConnection) benutzen,
     * damit man sie auch ohne Verbindung testen kann.
     *
     * Reihenfolge-Tipp: Erst pruefen, ob die Anfrage gueltig ist (Befehl, Verfahren,
     * Schluessel, Text), dann erst die Klasse Caesar aufrufen.
     */
    public String bearbeiteAnfrage(String pAnfrage)
    {
        // TODO: Anfrage zerlegen (Hilfsmethoden unten), Fehlerfaelle abfangen,
        //       Caesar.verschluesseln bzw. Caesar.entschluesseln aufrufen und
        //       die Antwort gemaess Protokoll zusammensetzen.
        return "-ERR:Noch nicht implementiert";
    }

    // ---------------------------------------------------------------
    // Hilfsmethoden (fertig, wie im Mathe-Projekt)
    // ---------------------------------------------------------------

    // Liefert das Feld mit der Nummer pNummer (Zaehlung ab 1), sonst "".
    // Felder sind durch Doppelpunkte getrennt.
    private String feldAn(String pText, int pNummer)
    {
        String[] felder = pText.split(":");
        if (pNummer >= 1 && pNummer <= felder.length)
        {
            return felder[pNummer - 1];
        }
        return "";
    }

    // Liefert alles nach dem ersten Doppelpunkt, sonst "".
    private String loescheErstesFeld(String pText)
    {
        int stelle = pText.indexOf(":");
        if (stelle > -1)
        {
            return pText.substring(stelle + 1);
        }
        return "";
    }
}
