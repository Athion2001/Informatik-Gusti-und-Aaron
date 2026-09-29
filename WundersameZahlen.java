public class WundersameZahlen{
    static double[] tripel = new double[3];

    public static double[] istWundersam(double x){
        double i = 0;
        double max = 0;
        tripel[0] = x;
        while(x > 1){
            if(x % 2 != 0){
                x = x * 3 + 1;
            }
            else{
                x = x * 0.5;
            }
            if(x > max){max = x;}
            i++;
            }
        tripel[1] = i;
        tripel[2] = max;
        return tripel;      
    }  
}
