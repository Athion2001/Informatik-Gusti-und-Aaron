public class BoeseLehrzeichen{
    public static void main(String args[]){
        System.out.println(entferneLehrzeichen("Ich habe alle Lehrzeichen entfernt"));
    }
    public static String entferneLehrzeichen(String pEingabe){
        String neuerText = pEingabe.replace(" ", "");
        return neuerText;
    }

    public static boolean parser(String pEingabe){
        for(int i = 0; i < pEingabe.length()-1; i++){
            if(pEingabe.substring(i,i+1).equals("x")){

            }
        }
        return false;
    }
}