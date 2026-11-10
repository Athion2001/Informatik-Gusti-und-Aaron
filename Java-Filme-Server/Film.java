
/**
 * @author 
 * @version 
 */
public class Film
{
    // Bezugsobjekte

    // Attribute
    int filmNr;
    String titel;
    String bewertung;
    boolean ausgeliehen; 
    // Konstruktor
    public Film(int pFilmNr, String pTitel)
    {
    filmNr = pFilmNr;
    titel = pTitel;
    bewertung = "";
    ausgeliehen = false; 
    }

    // Dienste
    
    public boolean istAusgeliehen()
    {
        return ausgeliehen;
    }
    
    public void ausleihen()
    {
        ausgeliehen = true;
    }
    
    public void rueckgabe()
    {
        ausgeliehen = false;
    }
    
    public void ergaenzeBewertung(String pBewertung)
    {
        bewertung = bewertung + ";" + pBewertung;
    }
    
    public int gibFilmNr(){
      return filmNr;
    }
    
     public String gibTitel(){
      return titel;
    }

}
