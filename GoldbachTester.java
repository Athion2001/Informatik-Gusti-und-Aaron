public class Goldbachtester{
    public static long i;
    public static void main(String args[]){
        for(i = 4; i <= 100;i++){
            System.out.print(i);
            System.out.println(istGoldbach(i));
        
        }
    }
    public static boolean istGoldbach(long pN){
        // erster summand und sein Vorgänger       
        for(int p = 1; p < pN; p++){
            if(istPrimzahl(p)){
                if(istPrimzahl(pN - p)){
                    System.out.print(" = ");
                    System.out.print(p);
                    System.out.print(" + ");
                    System.out.print(pN - p);
                    return true;
                }
            }
        }
        
        return false;
    }

    public static boolean istPrimzahl(long value) {
        if (value <= 16) {
            return (value == 2 || value == 3 || value == 5 || value == 7 || value == 11 || value == 13);
        }
        if (value % 2 == 0 || value % 3 == 0 || value % 5 == 0 || value % 7 == 0) {
            return false;
        }
        for (long i = 10; i * i <= value; i += 10) {
            if (value % (i+1) == 0) {  // 11, 21, 31, 41, 51, ...
                return false;
            }
            if (value % (i+3) == 0) {  // 13, 23, 33, 43, 53, ...
                return false;
            }
            if (value % (i+7) == 0) {  // 17, 27, 37, 47, 57, ...
                return false;
            }
            if (value % (i+9) == 0) {  // 19, 29, 39, 49, 59, ...
                return false;
            }
        }
        return true;
    }
}
