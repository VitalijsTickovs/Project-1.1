import java.util.concurrent.TimeUnit;
import java.util.Random;
import static javax.swing.JOptionPane.showMessageDialog;
class paint{
    public static void main(String[] args){
        bot a = new bot();
        int[][] field = new int[15][5];
        for(int i= 0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                field[i][j]=-1;
            }
        }
        pieceBag u = new pieceBag();

        UI b = new UI(15,5,60);
        boolean ttrruuee=true;
        int count=0;
        /**
         * running the while method while it's not game over 
         */
        while(ttrruuee){
           
            field=a.best_field(field,u.nextPiece());
            b.setState(field);
            Field d = new Field(15, 5,field);
            count+=d.checkRows();
            field = d.getField();
            count++;
            b.setState(field);
            pause(20);
            if(count==150){
                ttrruuee=false;
            }
            if(!d.AddPiece(u.nextPiece())){
                ttrruuee=false;
            }
                    if(!ttrruuee){
                        System.out.println("GAME OVER!!!!");
                        break;
                    
                        
                
            }
        }
        
        showMessageDialog(null, "Final score: "+count);
System.out.println("Final score: "+count);  
        b.setState(field);
    }
    
    /**
     * method to pause the program for ms milliseconde
     * @param ms time we want to pause
     */
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}