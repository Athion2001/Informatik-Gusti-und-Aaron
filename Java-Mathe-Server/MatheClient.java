


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
        String ID = "0";
        MatheClient meinClient = new MatheClient("localhost",8080);
        meinClient.anmeldung(ID,"123");
        meinClient.sendeRechnung("+:2:2:" + ID);
        meinClient.sendeRechnung("-:4:2:" + ID);
        meinClient.sendeRechnung("*:5:5:" + ID);
        meinClient.sendeRechnung("/:33:11:" + ID);
        meinClient.abmeldung(ID);
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
    
    public void anmeldung(String pID, String pPw){
        String nachricht = "a:" + pID + ":" + pPw;
        System.out.println(nachricht);
        System.out.println("Anmeldung erfolgt...");
        super.send(nachricht);
    }
    public void abmeldung(String pID){
        super.send("b:" + pID);
    }
    public void sendeRechnung(String pRechnung)
    {
        super.send("r:" + pRechnung);
    } 
}
