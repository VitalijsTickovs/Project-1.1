import java.util.concurrent.TimeUnit;
import java.util.Random;
import static javax.swing.JOptionPane.showMessageDialog;
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
        char[] pento= {'P','X','Z','W','Y','I','T','U','V','N','L','F'};
        //char[] pento ={'I','I','I','I','I','I','I','I','Z','I','I','I','I','I'};
        boolean ttrruuee=true;
        int count=0;
        Random random = new Random();
        while(ttrruuee){
            int c = random.nextInt(12);
            field=a.best_field(field,pento[c]);
            Field d = new Field(25, 8,field);
            count+=d.checkRows();
            field = d.getField();
            count++;
            b.setState(field);
            pause(10);
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
        
        showMessageDialog(null, "Final score: "+count);
System.out.println("Final score: "+count);  
        b.setState(field);
    }
    public static void postion(Field a){
        int desired_x= a.xPiece;
        int[][] b= a.getField();
        int current_x= b[0].length/2;
        if(current_x>desired_x){
            while(current_x!=desired_x){
                a.left();
                current_x--;
            }
        }
        if(current_x<desired_x){
            while(current_x!=desired_x){
                a.right();
                current_x++;
                }           
        } 

    }
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}