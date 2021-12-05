import javax.swing.text.AbstractDocument.LeafElement;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import static javax.swing.JOptionPane.showMessageDialog;
class print{
    public static UI b = new UI(25,8,60);
    public static void main(String[] args){
        all_position a = new all_position();
        int[][] field = new int[25][8];
        for(int i= 0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                field[i][j]=-1;
            }
        }
    
        char[] pento= {'P','X','Z','W','Y','I','T','U','V','N','L','F'};
        //char[] pento ={'I','I','I','I','I','I','I','I','Z','I','I','I','I','I'};
        boolean ttrruuee=true;
        int count=0;
        Random random = new Random();
        for(int i=0;i<5;i++){
            System.out.println(i);
            Field d = new Field(25, 8,field);
            int c = random.nextInt(12);
            int[][] goal=a.best_field(field,pento[c]);
            Field y = new Field(25, 8, goal);
            d.AddPiece(pento[c]);
            postion(d,y);
            System.out.println("stop");
            b.setState(d.getField());
           
            //b.setState(d.getField());
            count+=d.checkRows();
            field = d.getField();
            count++;
            //b.setState(field);
            //pause(200);
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

   

    
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void postion(Field a,Field y){
        boolean test=true;
        int[][] array = a.getField();
        int[][] goal =y.getField();
        //System.out.println("desired_x "+y.xPiece);
        //int[][] k= a.getField();
        // current_x= k[0].length/2;
        //System.out.println("current_x "+current_x);
            
            for(int i=0;i<a.getField().length;i++){
                for(int j=0;j<a.getField()[0].length;j++){
                    if(equal(a.getField(),goal)){
                        return;
                    }
                    if(!equal(a.getField(),goal)){
                        if(a.getField()[i][j]>goal[i][j]){
                        while(a.getField()[i][j]>goal[i][j]){
                            a.left();
                            pause(250);
                            a.rotate();
                           a.down();
                            b.setState(a.getField());
                            pause(100);
                        }                      
                    }
                      if(a.getField()[i][j]<goal[i][j]){
                      while(a.getField()[i][j]<goal[i][j]){
                        a.right();
                        a.rotate();
                        a.down();
                        pause(250);
                        b.setState(a.getField());
                        pause(100);
                      }
                    }

                }
                      }
                    }
                    a.setPiece();
                    return;
            }
            public static boolean equal(int[][] a,int[][] b){
                for(int i=0;i<a.length;i++){
                    for(int j=0;j<a[0].length;i++){
                        if(a[i][j]!=b[i][j]){
                            return false;
                        }
                    }
                }
                return true;
            } 
        }
     
                   
                 /*** 
            System.out.println("test");
            if(current_x>desired_x){
                while(current_x!=desired_x){
                    a.left();
                    current_x--;
                    b.setState(a.getField());
                    pause(100);
                }
            }
            if(current_x<desired_x){
                while(current_x!=desired_x){
                    System.out.println("one step left");
                    a.right();
                    current_x++;
                    b.setState(a.getField());
                    pause(100);
                    }           
            } 
            */
    /** 
        for(int i = 0; i< 10; i++) {
        try {
            //sending the actual Thread of execution to sleep X milliseconds
            Thread.sleep(100);
        } catch(InterruptedException ie) {}
        a.down();
        b.setState(a.getField());
    }
*/
    



/**
 * for(int i=0;i<length;i++){
 *      for(int j=0;<[0].length;j++){
 *          if(a[i][i]!=goal[i][j])
 *                 if(a[i][i]>goal[i][j])
 *                         left
 *      }
 * }
 * 
 */