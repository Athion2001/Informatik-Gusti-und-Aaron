


/**
 * Beschreiben Sie hier die Klasse MatheClient.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class MatheClient extends Client
{
    public static void main(String[] args){
        System.out.println("Client läuft");
        MatheClient meinClient = new MatheClient("localhost",8080);
        meinClient.sendeRechnung("+:2:2");
        meinClient.sendeRechnung("-:4:2");
        meinClient.sendeRechnung("*:5:5");
        meinClient.sendeRechnung("/:33:11");
        try {
            Thread.sleep(10000); 
        } catch (Exception e) {}
    }
    public MatheClient(String pServerIP, int pServerPort)
    {
        super(pServerIP, pServerPort);
    }
    
    public void processMessage(String pMessage)
    {
        System.out.println(pMessage);
        System.out.flush();
    } 
    
    public void sendeRechnung(String pRechnung)
    {
        super.send(pRechnung);
    } 
}
