import java.util.concurrent.TimeUnit;
class paint{
    public static void main(String[] args){
        all_position a = new all_position();
        int[][] field = new int[17][8];
        for(int i= 0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                field[i][j]=-1;
            }
        }
        UI b = new UI(17,8,60);
          char[] pento= {'I','P','X','Z','P','N','Y','I','P','X','Z','P','N','Y'};
        //char[] pento ={'I','I','I','I','I','I','I','I','T','P','I','I','I','I'};
        boolean ttrruuee=true;
        int count=0;
        while(ttrruuee){
        for (int c=0; c < pento.length; c++){
            field=a.best_field(field,pento[c]);
            count++;
            b.setState(field);
            pause(150);
            
                for(int j=0;j<field[0].length;j++){
                    if(field[1][j]!=-1){
                        ttrruuee=false;
                        
                    }
                    if(!ttrruuee){
                        ;
                        break;
                    }
                }
                    if(!ttrruuee){
                        System.out.println("GAME OVER!!!!");
                        break;
                    }
                        
                
            }
        }
System.out.println(count);  
    

/** 
        //field=a.best_field(field,'X');
        b.setState(field);
        field=a.best_field(field,'I');
        System.out.println("matrixxx");
        for(int i= 0;i<field.length;i++){
            for(int j=0;j<field[0].length;j++){
                System.out.print(field[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        boolean piecemoved=false;
        
        field=a.best_field(field,'P');
        b.setState(field);
        pause(2000);
            field=a.best_field(field,'X');
            b.setState(field);
            //TimeUnit.SECONDS.sleep(2);
            field=a.best_field(field,'T');
        
        /** 
         field=a.best_field(field,'X');
        field=a.best_field(field,'X');
        field=a.best_field(field,'I');
        field=a.best_field(field,'I');
        field=a.best_field(field,'I');

       */
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