public class BoeseLehrzeichen{
    public static void main(String args[]){
        System.out.println(entferneLehrzeichen("Ich habe alle Lehrzeichen entfernt"));
    }
    public static String entferneLehrzeichen(String pEingabe){
        String neuerText = pEingabe.replace(" ", "");
        return neuerText;
    }
}