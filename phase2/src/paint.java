import java.util.concurrent.TimeUnit;
import java.util.Random;
class paint{
    public static void main(String[] args){
        all_position a = new all_position();
        int[][] field = new int[25][8];
        for(int i= 0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                field[i][j]=-1;
            }
        }
        UI b = new UI(25,8,60);
        char[] pento= {'I','P','X','Z','W','Y','T','U','V','N','L','F'};
        //char[] pento ={'I','I','I','I','I','I','I','I','Z','I','I','I','I','I'};
        boolean ttrruuee=true;
        int count=0;
        Random random = new Random();
        while(ttrruuee){
            int c = random.nextInt(12);
            field=a.best_field(field,pento[c]);
            Field d = new Field(25, 8,field);
            count+=d.checkRows();
            d.checkRows();
            field = d.getField();
            count++;
            b.setState(field);
            pause(20);
            if(count==150){
                ttrruuee=false;
            }
            if(!d.AddPiece(pento[c])){
                ttrruuee=false;
            }
                    if(!ttrruuee){
                        System.out.println("GAME OVER!!!!");
                        break;
                    
                        
                
            }
        }
System.out.println("Final score: "+count);  
        b.setState(field);
    }
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}