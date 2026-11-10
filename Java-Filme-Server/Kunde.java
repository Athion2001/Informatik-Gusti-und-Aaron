
/**
 * @author 
 * @version 
 */
public class Kunde
{
    // Bezugsobjekte
    
    // Attribute
    String name;
    String geburtsdatum;
    String ip;
    int port;
    String favoriten;      //Nummern der Filme durch Leerzeichen getrennt
    int ausgeliehen;       //Die Filme werden durch ihre Nummer gespeichert.
    boolean angemeldet;
    String pwd;
    // Konstruktor
    public Kunde(String pName, String pGeburtsdatum, String ppwd)
    {
    
    name = pName;
    geburtsdatum = pGeburtsdatum;
    ip = ""; //Bedeutet:
    port = 0;// Nicht angemeldet
    favoriten = "";
    angemeldet = false;
    ausgeliehen = 0;   // 0 steht für 'kein Film ausgeliehen'
    pwd = ppwd;
    }

    // Dienste
   public void fuegeZuFavoritenHinzu(int pFilmNr)
   {
    favoriten = favoriten + " " + pFilmNr;
   }
   
    public String favoritenAlsText()
   {
   return favoriten;    
   }
   
   public int ausleihStatus()
   {
           return ausgeliehen;   
    }
    
   public void setzeAusleihStatus(int pAusleihStatus)
   {
       ausgeliehen = pAusleihStatus;
    }
   
   public void neueIP(String pIP)
   {
       ip = pIP;
   }
   
   public void neuerPort(int pPort)
   {
      port = pPort;
   }
    
   public String gibName()
   {
       return name;
   }
   
   public String gibGeburtsdatum()
   {
       return geburtsdatum;
   }
   public String gibIP()
   {
       return ip;
   }
   
   public int gibPort()
   {
      return port;
   }
   
   public boolean istAngemeldet(){
     return angemeldet;   
   }
   
   public void anmelden(String pIP, int pPort){
       angemeldet = true;
       ip = pIP;
       port = pPort;
       
   }
   
    public void abmelden(){
       angemeldet = false;
       ip = "";
       port = 0;
       
   }
   
   public String passwd(){
     return pwd;    
   }
  
}
