/**
 * Selbsttest fuer die Klasse Caesar.
 */
public class CaesarTest
{
    private static int fehler = 0;

    public static void main(String[] args)
    {
        // Grundfaelle
        pruefe("Verschluesseln, Schluessel 3", Caesar.verschluesseln("HALLO", 3), "KDOOR");
        pruefe("Entschluesseln, Schluessel 3", Caesar.entschluesseln("KDOOR", 3), "HALLO");

        // Ueberlauf am Alphabetende
        pruefe("Ueberlauf X,Y,Z", Caesar.verschluesseln("XYZ", 3), "ABC");
        pruefe("Unterlauf A,B,C", Caesar.entschluesseln("ABC", 3), "XYZ");

        // Gross- und Kleinschreibung, Leerzeichen, Satzzeichen
        pruefe("Gemischter Text", Caesar.verschluesseln("Hallo Welt!", 3), "Kdoor Zhow!");
        pruefe("Zahlen bleiben", Caesar.verschluesseln("Raum 42", 1), "Sbvn 42");

        // Sonderfaelle beim Schluessel
        pruefe("Schluessel 26 (volle Runde)", Caesar.verschluesseln("Test", 26), "Test");
        pruefe("Schluessel 29 = 3", Caesar.verschluesseln("ABC", 29), "DEF");
        pruefe("Negativer Schluessel -1", Caesar.verschluesseln("BCD", -1), "ABC");

        // Umlaute werden nicht veraendert (Festlegung im Protokoll)
        pruefe("Umlaut bleibt", Caesar.verschluesseln("\u00e4a", 1), "\u00e4b");

        // Leerer Text
        pruefe("Leerer Text", Caesar.verschluesseln("", 5), "");

        // Rundreise fuer alle Schluessel
        String text = "Der Kryptologie-Server laeuft! 123";
        boolean rundreiseOk = true;
        for (int k = 1; k <= 25; k++)
        {
            if (!Caesar.entschluesseln(Caesar.verschluesseln(text, k), k).equals(text))
            {
                rundreiseOk = false;
            }
        }
        pruefe("Rundreise fuer Schluessel 1 bis 25", String.valueOf(rundreiseOk), "true");

        if (fehler == 0)
        {
            System.out.println("Alle Tests bestanden.");
        }
        else
        {
            System.out.println(fehler + " Test(s) fehlgeschlagen.");
        }
    }

    private static void pruefe(String pName, String pIst, String pSoll)
    {
        if (pSoll.equals(pIst))
        {
            System.out.println("OK      " + pName);
        }
        else
        {
            fehler++;
            System.out.println("FEHLER  " + pName + ": erwartet \"" + pSoll + "\", erhalten \"" + pIst + "\"");
        }
    }
}
