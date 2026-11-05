/**
 *
 * Beschreibung
 *
 * @version 2026
 * @author DIE
 */

public class MatheServer extends Server {
    static List<String> angemeldeteClients;
    static List<String> bClients;

    public static void main(String[] args){
        System.out.println("Server läuft");
        MatheServer meinServer = new MatheServer(8080);
        
    }

    public MatheServer (int pPortNum) {
        super(pPortNum);
        bClients = new List<String>();
        angemeldeteClients = new List<String>();
        bClients.append("0:123");

    }

    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println(pClientIP + "/" + pClientPort + ": Anmeldung"); //Konsolenausgabe zur Dokumentation auf Serverseite.
        System.out.println(pClientIP + "/" + pClientPort + ": Willkommen auf dem MatheServer!"); //Konsolenausgabe zur Dokumentation auf Serverseite.
        this.send(pClientIP, pClientPort, "Willkommen auf dem MatheServer!");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        //Hier programmieren.
        System.out.println(pMessage);
        char switcher = part(pMessage,0).charAt(0);
        switch(switcher){
            case 'a':
                anmeldung(pClientIP, pClientPort, pMessage);
                break;
            case 'b':
                abmeldung(pMessage);
                break;
            case 'r':
                rechnung(pClientIP, pClientPort, pMessage);
                break;     
            default:
                
            }
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
    
    private void anmeldung(String pClientIP, int pClientPort, String pMessage){
        boolean angemeldet = false;
        if(part(pMessage,0).equals("a")){
            bClients.toFirst();
            while(bClients.hasAccess()){
                if(part(pMessage,1).equals(part(bClients.getContent(),0))){
                    if(part(pMessage,2).equals(part(bClients.getContent(),1))){
                        send(pClientIP, pClientPort, "Anmeldung erfolgreich");
                        angemeldeteClients.append(part(pMessage,1));
                        angemeldet = true;
                        break;
                    }
                }
                bClients.next();
            }
            if(!angemeldet){send(pClientIP, pClientPort, "Anmeldung war nicht erfolgreich");}
            
        }
    }   
    
    private void abmeldung(String pMessage){
        angemeldeteClients.toFirst();
            while(angemeldeteClients.hasAccess()){
                if(part(pMessage,1).equals(angemeldeteClients.getContent())){
                    angemeldeteClients.remove();
                }
            }
    }

    private void rechnung(String pClientIP, int pClientPort, String pMessage){
        boolean angemeldet = false;
        //überrpüfung ob die ID angemeldet ist
        angemeldeteClients.toFirst();
        while(angemeldeteClients.hasAccess()){
            if(part(pMessage,3).equals(angemeldeteClients.getContent())){
                angemeldet = true;
                break;
            }
            angemeldeteClients.next();
        }
        if(!angemeldet){send(pClientIP, pClientPort, "du bist nicht angemeldet");}
        else{
            int a = Integer.valueOf(part(pMessage,1));
            int b = Integer.valueOf(part(pMessage,2));
            char op = part(pMessage,0).charAt(0);
            int result;
            switch(op){
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                case '/':
                    result = a / b;
                    break;   
                default:
                    result = 0;
            }
            String antw = "das ergebnis aus " + part(pMessage,1) + " " + part(pMessage,0) + " " + part(pMessage,2) + " ist " + String.valueOf(result);
            send(pClientIP, pClientPort, antw);
        }
    }
    
}
