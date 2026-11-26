/**
 * 
 * 
 * 
 * 
 * 
 */
public class Viginaer{
    public static String verschluesseln(String pKlartext, String pSchluessel){
        int sCounter = 0;
        String verschluesselt = "";
        for(int i = 0;i < pKlartext.length();i++){
            if(sCounter > pSchluessel.length()-1){sCounter = 0;}
            int schluesselValue = (int) (pSchluessel.charAt(sCounter));
            if((schluesselValue > 96 && schluesselValue < 123)){schluesselValue = schluesselValue - 7 -26 -65}
            else if(schluesselValue >= 65 && schluesselValue <= 90){schluesselValue = schluesselValue - 65}
            else{schluesselValue = 0}
            int klartextValue = (int) (pKlartext.charAt(i));
            if((klartextValue < 65) || (klartextValue > 90 && klartextValue < 97) || (klartextValue > 122)){
                verschluesselt = verschluesselt + pKlartext.charAt(i);
            }
            else{
                int neueValue = klartextValue + schluesselValue;
                if(neueValue > 90 && klartextValue <= 90){
                    neueValue = neueValue - 26;
                }
                if(neueValue > 122){
                    neueValue = neueValue - 26;
                }
                char verschluesseltesZeichen = (char) neueValue;
                verschluesselt = verschluesselt + verschluesseltesZeichen;
            }
            sCounter++;
        }
        return verschluesselt;
    }
    public static String entschluesseln(String pKlartext, String pSchluessel){
        int sCounter = 0;
        String entschluesselt = "";
        for(int i = 0;i < pKlartext.length();i++){
            if(sCounter > pSchluessel.length()-1){sCounter = 0;}
            int schluesselValue = (int) (pSchluessel.charAt(sCounter));
            while(schluesselValue > 26){
                schluesselValue = schluesselValue - 26;
            }
            int klartextValue = (int) (pKlartext.charAt(i));
            if((klartextValue < 65) || (klartextValue > 90 && klartextValue < 97) || (klartextValue > 122)){
                entschluesselt = entschluesselt + pKlartext.charAt(i);
            }
            else{
                int neueValue = klartextValue - schluesselValue;
                if(neueValue > 90 && klartextValue <= 90){
                    neueValue = neueValue + 26;
                }
                if(neueValue > 122){
                    neueValue = neueValue + 26;
                }
                char entschluesseltesZeichen = (char) neueValue;
                entschluesselt = entschluesselt + entschluesseltesZeichen;
            }
            sCounter++;
        }
        return entschluesselt;
    }
}