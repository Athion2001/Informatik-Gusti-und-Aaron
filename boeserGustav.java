public class BoeserGustav{
    boolean gustavIstNochNichtDa;
    public static void main(String[] args){
        BoeserGustav bG = new BoeserGustav();
    }
    public BoeserGustav(){
        gustavIstNochNichtDa = false;
        while(!gustavIstNochNichtDa){
            System.out.println("böser Gustav!");
        }
    }
}