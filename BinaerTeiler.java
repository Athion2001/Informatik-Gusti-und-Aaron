public class BinaerTeiler{
    private boolean akzeptiert;
    private char akk;

    public static void main(String[] args){
        BinaerTeiler bt = new BinaerTeiler("10");
        
    }
    public BinaerTeiler(String input){
        akzeptiert = false;
        akk = '3';
        zustand0(input, 0);
        if(akzeptiert){System.out.println(input + " wurde akzeptiert");}
        else{System.out.println(input + " wurde nicht akzeptiert");}
    }

    public void zustand0(String input, int pIndex){
        if(pIndex < input.length()){akk = input.charAt(pIndex);}
        else{akk = '2';}

        switch(akk){
            case '0':
                zustand0(input, pIndex+1);
                break;
            case '1':
                zustand1(input, pIndex+1);
                break;
            case '2':
                akzeptiert = true;
                break;
        }
    }
    public void zustand1(String input, int pIndex){
        if(pIndex < input.length()){akk = input.charAt(pIndex);}
        else{akk = '2';} 
        switch(akk){
            case '0':
                zustand2(input, pIndex+1);
                break;
            case '1':
                zustand0(input, pIndex+1);
                break;
            case '2':
                this.akzeptiert = false;
                break;
        }
    }
    public void zustand2(String input, int pIndex){
        if(pIndex < input.length()){akk = input.charAt(pIndex);}
        else{akk = '2';}
        switch(akk){
            case '0':
                zustand1(input, pIndex+1);
                break;
            case '1':
                zustand2(input, pIndex+1);
                break;
            case '2':
                akzeptiert = false;
                break;
            

        }
    }
}