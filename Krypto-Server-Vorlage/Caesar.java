/**
 * Verschluesselungslogik: Caesar-Verfahren (Vorlage).
 *
 * Diese Klasse kennt weder das Netzwerk noch das Protokoll.
 * Sie arbeitet nur mit Texten und einem Schluessel.
 *
 * Festlegung fuer unseren Server:
 *  - Grossbuchstaben A-Z und Kleinbuchstaben a-z werden verschoben,
 *    die Gross-/Kleinschreibung bleibt erhalten.
 *  - Alle anderen Zeichen (Leerzeichen, Ziffern, Satzzeichen, Umlaute)
 *    bleiben unveraendert.
 *
 * Pruefen koennt ihr eure Loesung mit der Klasse CaesarTest (main-Methode starten).
 *
 * @author (Namen eintragen)
 * @version (Datum)
 */
public class Caesar
{
    public static final int ALPHABETLAENGE = 26;

    /**
     * Verschluesselt pKlartext, indem jeder Buchstabe um pSchluessel
     * Stellen im Alphabet nach hinten verschoben wird.
     */
    public static String verschluesseln(String pKlartext, int pSchluessel)
    {
        while(pSchluessel > 26){
            pSchluessel = pSchluessel - 26;
        }
        String verschluesselt = "";
        for(int i = 0;i < pKlartext.length();i++){
            int asciiValue = (int) (pKlartext.charAt(i));
            if((asciiValue < 65) || (asciiValue > 90 && asciiValue < 97) || (asciiValue > 122)){
                verschluesselt = verschluesselt + pKlartext.charAt(i);
            }
            else{
                int neueValue = asciiValue + pSchluessel;
                if(neueValue > 90 && asciiValue <= 90){
                    neueValue = neueValue - 26;
                }
                if(neueValue > 122){
                    neueValue = neueValue - 26;
                }
                char verschluesseltesZeichen = (char) neueValue;
                verschluesselt = verschluesselt + verschluesseltesZeichen;
            }
        }
        return verschluesselt;
    }

    /**
     * Entschluesselt pGeheimtext, indem jeder Buchstabe um pSchluessel
     * Stellen im Alphabet nach vorne verschoben wird.
     */
    public static String entschluesseln(String pGeheimtext, int pSchluessel)
    {
        while(pSchluessel > 26){
            pSchluessel = pSchluessel - 26;
        }
        String entschluesselt = "";
        for(int i = 0;i < pGeheimtext.length();i++){
            int asciiValue = (int) (pGeheimtext.charAt(i));
            if((asciiValue < 65) || (asciiValue > 90 && asciiValue < 97) || (asciiValue > 122)){
                entschluesselt = entschluesselt + pGeheimtext.charAt(i);
            }
            else{
                int neueValue = asciiValue - pSchluessel;
                if(neueValue < 65 && asciiValue <= 90){
                    neueValue = neueValue + 26;
                }
                if(neueValue < 97 && asciiValue >= 97){
                    neueValue = neueValue + 26;
                }
                char entschluesseltesZeichen = (char) neueValue;
                entschluesselt = entschluesselt + entschluesseltesZeichen;
            }
        }
        return entschluesselt;
    }

    // Tipps (loescht sie, wenn ihr sie nicht braucht):
    //  - Ein char ist in Java auch eine Zahl: 'A' + 1 ergibt 66, (char) 66 ergibt 'B'.
    //  - Position eines Buchstabens im Alphabet: zeichen - 'A' (bzw. zeichen - 'a').
    //  - Mit % kommt man wieder an den Anfang des Alphabets. Achtung: In Java ist
    //    (-3) % 26 gleich -3, nicht 23! Wie lasst sich das beim Entschluesseln loesen?
    //  - Mit einer privaten Hilfsmethode, die beide Richtungen abdeckt, schreibt ihr
    //    die Verschiebung nur einmal.
}
