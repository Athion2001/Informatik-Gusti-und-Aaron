/**
 * Kryptologie-Client (Vorlage).
 *
 * Der Client kennt das Protokoll (Aufbau der Anfragen), aber nicht die
 * Verschluesselung selbst - die macht der Server.
 *
 *
 * @author (Namen eintragen)
 * @version (Datum)
 */
public class KryptoClient extends Client
{
    public KryptoClient(String pServerIP, int pServerPort)
    {
        super(pServerIP, pServerPort);
    }
    public static void main(String[] args) {
        KryptoClient meinClient = new KryptoClient("localhost", 8080);
    }

    // ---------------------------------------------------------------
    // Anfragen nach Protokoll
    // ---------------------------------------------------------------

    public void verschluesseln(String pVerfahren, int pSchluessel, String pText)
    {
        send("U:Ver");
        send("V:" + pVerfahren);
        send("S:" + pSchluessel);
        send("T:" + pText);
    }

    public void entschluesseln(String pVerfahren, int pSchluessel, String pText)
    {
        send("U:Ent");
        send("V:" + pVerfahren);
        send("S:" + pSchluessel);
        send("T:" + pText);
    }

    public void beenden()
    {
        close();
    }

    // ---------------------------------------------------------------
    // Antworten des Servers (wird von der Klasse Client aufgerufen)
    // ---------------------------------------------------------------

    public void processMessage(String pMessage)
    {
        System.out.println("Server: " + pMessage);
    }
}
