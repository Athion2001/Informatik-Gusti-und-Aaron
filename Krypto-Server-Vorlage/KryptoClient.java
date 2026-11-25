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
        meinClient.Caesar("Verschlüsseln","1","Gustav ist blöd");
    }

    // ---------------------------------------------------------------
    // Anfragen nach Protokoll
    // ---------------------------------------------------------------

    public void Caesar(String pOperator, String pSchluessel, String pText)
    {
        send("Caesar:" + pOperator + ":" + pSchluessel + ":" + pText);
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
