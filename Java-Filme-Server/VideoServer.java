
/**
 * @author 
 * @version 
 */
public class VideoServer extends Server
{
    // Bezugsobjekte
    List<Film> filmListe;
    List<Kunde> kundenListe;
    int anzahlFilme;
    int anzahlKunden;
    // Attribute

    // Konstruktor
    // *** fertig ***
    public VideoServer(int pPortNr)
    {
        super(pPortNr);
        filmListe = new List<Film>();
        filmListe.append(new Film(1, "Ich b in doch kein Thor!")); 
        filmListe.append(new Film(2, "Hurry up, Potter! 1")); 
        filmListe.append(new Film(3, "Hurry up, Potter! 2"));
        filmListe.append(new Film(4, "Hurry up, Potter! 3"));
        filmListe.append(new Film(5, "Beam me up, Scotty"));
        filmListe.append(new Film(6, "Captain Africa"));        
        anzahlFilme = 6;
        kundenListe = new List<Kunde>();
        kundenListe.append(new Kunde("Meier1", "123", "pwd"));
        kundenListe.append(new Kunde("Meier2", "321", "pwd"));

    }

    // Dienste
    // *** fertig ***    
    public void processNewConnection(String pClientIP, int pClientPort){
        this.send(pClientIP,pClientPort,"+OK Bitte identifizieren Sie sich");
    }       
    
    // *** fertig ***    
    public void processClosingConnection(String pClientIP, int pClientPort){
        Kunde derKunde = kundeSuchen(pClientIP, pClientPort);
        if(derKunde != null){
            derKunde.abmelden();
        }
    }    
    
    // *** fertig ***    
    public Kunde kundeSuchen(String pClientIP, int pClientPort){
        kundenListe.toFirst();

        while(kundenListe.hasAccess() && (!(pClientIP.equals((kundenListe.getContent()).gibIP())) ||(pClientPort != (kundenListe.getContent()).gibPort()))){

            kundenListe.next();
        }
        if (kundenListe.hasAccess()){
            return kundenListe.getContent();
        } else {
            return null;
        }
    }    
        
    // *** fertig ***    
    public void processMessage(String pClientIP, 
    int pClientPort, String pMessage) {
        String anweisung = wortAn(pMessage, 1);
        String ohneAnweisung = loescheErstesWort(pMessage);
        String derKunde = kundeZuIP(pClientIP, pClientPort);
        //erstes Keyword wird abgefragt -- laut Protokoll wird dann die Abmeldung durchgeführt
        if (anweisung.equals("NAME")) {
            if (derKunde == null || 
            !derKunde.equals(ohneAnweisung)) {
                vorherigenAbmelden(pClientIP, pClientPort);
                if (istKunde(ohneAnweisung)) {
                    kundeAnmelden(pClientIP, pClientPort, 
                        ohneAnweisung);
                    send(pClientIP, pClientPort, 
                        "+OK Sie wurden angemeldet");      
                } else {
                    send(pClientIP, pClientPort, 
                        "-ERR Benutzername unbekannt");   
                }
            } else {
                send(pClientIP, pClientPort, 
                    "+OK Sie sind bereits angemeldet");
            }
        } else {
            if (derKunde == null) {  
                send(pClientIP, pClientPort, 
                    "-ERR Sie sind nicht angemeldet");
            } else {
                bearbeiteAusleihphase(pClientIP, pClientPort, 
                    pMessage);
            }
        }
    }
    
    // *** HIER ARBEITEN ***
    private void bearbeiteAusleihphase(String pClientIP, int pClientPort, String pMessage) {
        String keyWord = loescheErstesWort(pMessage);
        String op = wortAn(pMessage, 1);
        switch (op) {
            case "LIST":
                FilmeSenden(pClientIP, pClientPort, keyWord);
                break;
            case "STATUS":
                ausleihStatus(String pClientIP, int keyWord);
                break;
            case "AUSLEIHEN":
                filmAusleihen(String pClientIP, int pClientPort, String keyWord);
                break;
            case "ENDE":
                kundeAbmelden(pClientIP, pClientPort);
                break;
            default:
                send(pClientIP, pClientPort, "Was willst du?");
                break;
        }  
    }
    
    // *** HIER ARBEITEN ***
    public boolean istKunde(String pName) {
        // ..
        return true;
    }
    
    // *** HIER ARBEITEN ***
    public void kundeAnmelden(String pClientIP, int pClientPort, String pName) {
        // ..
    }
       
    // *** HIER ARBEITEN ***   
    public String kundeZuIP(String pClientIP, int pClientPort){
        // ..
        return "";
    }  

    // *** fertig ***    
    public void vorherigenAbmelden(String pClientIP, int pClientPort){
        Kunde derKunde = kundeSuchen(pClientIP, pClientPort);
        if(derKunde != null){
            derKunde.abmelden();
        }   
    }
    
    // *** fertig ***    
    private void kundeAbmelden(String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, "+OK abgemeldet.");
        closeConnection(pClientIP, pClientPort);
    }
    
    // *** fertig ***    
    public void filmAusleihen(String pClientIP, int pClientPort, String pFilmNr){
        Kunde derKunde = kundeSuchen(pClientIP, pClientPort);
        Film derFilm = filmSuchen(Integer.parseInt(pFilmNr));
        if (derFilm == null){
            send(pClientIP, pClientPort, "-ERR Film " + String.valueOf(pFilmNr) + " existiert nicht.");    
        }else{
            if(derFilm.istAusgeliehen()){
                send(pClientIP, pClientPort, "-ERR Film ist verliehen.");   
            }else if (derKunde.ausleihStatus()==0){
                send(pClientIP, pClientPort, "+OK Film wird zugeschickt");
                derKunde.setzeAusleihStatus(Integer.parseInt(pFilmNr));
                derFilm.ausleihen();
                //dem Kunden zusenden!!  
            }
            else {//Film nicht ausgeliehen, aber Kunde hat bereits einen Film.
                send(pClientIP, pClientPort, "Sie haben bereits einen Film ausgeliehen.");
            }
        }
    }    

    // *** fertig ***        
    public Film filmSuchen(int filmNr){
        filmListe.toFirst();
        if (filmNr <= anzahlFilme){
            for(int i=1; i<filmNr; i++) {
                filmListe.next();
            }
            return filmListe.getContent();
        } else {
            return null;
        }
    }
    
    // *** fertig ***        
    public void FilmeSenden(String pClientIP, int pClientPort, String pSuchwort){
        send(pClientIP, pClientPort,"+OK Filme mit " + pSuchwort + ':');
        filmListe.toFirst();
        while (filmListe.hasAccess()){
            String filmNr = String.valueOf((filmListe.getContent()).gibFilmNr());
            String titel = (filmListe.getContent()).gibTitel();
            boolean treffer = true;
            int laengeSuchwort = pSuchwort.length();
            int laengeTitel = titel.length();
            //System.out.println("Titel: " + Integer.toString(laengeTitel));
            //System.out.println("Suchwort: " + Integer.toString(laengeSuchwort));
            if(laengeSuchwort > laengeTitel){treffer = false;}
            else{
                for(int i = 0; i < laengeSuchwort; i++){
                    if(pSuchwort.charAt(i) != titel.charAt(i)){treffer = false;}
                }
            }
            if(treffer){send(pClientIP, pClientPort, filmNr + ": "+ titel);} 
            filmListe.next();
        }
        send(pClientIP, pClientPort, "END OF LIST" );
    }    

    // *** fertig ***    
    public void ausleihStatus(String pClientIP, int pClientPort){
        Kunde derKunde = kundeSuchen(pClientIP, pClientPort);
        if (derKunde.ausleihStatus() == 0){
            send(pClientIP, pClientPort, "+OK Sie haben keinen Film ausgeliehen."); 
        }else{
            Film derFilm = filmSuchen(derKunde.ausleihStatus());
            send(pClientIP, pClientPort, "+OK Sie haben:" + String.valueOf(derFilm.gibFilmNr())+ ": " + derFilm.gibTitel() ); 
        }
    }    
    
    // *** fertig ***    
    private String wortAn(String pText, int pNummer) {
        int i=1, neueStelle=0, letzteStelle=0;

        if (pNummer<1)
            return "";
        else {
            while (i <= pNummer && letzteStelle>-1){ 
                if (neueStelle>-1)
                    letzteStelle =neueStelle;
                else
                    letzteStelle=-1;
                neueStelle=pText.indexOf(" ",letzteStelle+1);
                i++;
            }  
            if (letzteStelle!=-1) //pNummer größer als Anzahl der Wörter
                if (neueStelle!=-1 || letzteStelle==0) 
                    if (letzteStelle==0)
                        if (neueStelle==-1)
                            return pText.substring(letzteStelle,pText.length()); //erstes und einzigesWort
                        else
                            return pText.substring(letzteStelle,neueStelle); //erstes Wort
                    else
                        return pText.substring(letzteStelle+1,neueStelle); // mittendrin
                else //letztes Wort oder pNummer größer als Anzahl der Wörter
                if (letzteStelle!=-1)
                    return pText.substring(letzteStelle+1,pText.length());
                else
                    return "";
            else 
                return "";

        }  
    }    

    // *** fertig ***    
    private String loescheErstesWort(String pText){
        if (pText.indexOf(" ") > -1){
            return pText.substring(pText.indexOf(" ")+1,pText.length());
        }else{
            return "";
        }
    }    
    




}
