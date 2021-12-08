import static javax.swing.JOptionPane.showMessageDialog;
class paint extends bot{
    public static void main(String[] args){
        int[][] field = new int[24][10];
        for(int i= 0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                field[i][j]=-1;
            }
        }
        pieceBag u = new pieceBag();

        UI b = new UI(24,10,30);
        boolean ttrruuee=true;
        int count=0;
        /**
         * running the while method while it's not game over 
         */
        while(ttrruuee){
           
            field=best_field(field,u.nextPiece());
            b.setState(field);
            Field d = new Field(24, 10,field);
            count+=d.checkRows();
            //pause(1000);
            
            field = d.getField();
            count++;
            b.setState(field);
            pause(20);
            if(count==240){
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
     * method to pause the program for ms milliseconds
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