package dancingLinks;

import java.util.ArrayList;

public class DancingListOpt {

    public int Ascore = 3;
    public int Bscore = 4;
    public int Cscore = 5;
    

    public Square h;

    public ArrayList<Square> Os = new ArrayList<>();

    public int maxScore = 0;

    public int timeTaken =0;

    public ArrayList<Square> maxOs = new ArrayList<>();

    /**
     * constructor for the class
     * @param h start head of the doubly linked structure
     */
    public DancingListOpt(Square h){
        this.h = h;
    }


    /**
     * recusive method that seafches for the best score for a set amount of time
     * @param k: indicator of what layer we are on, starts at 0, incremented each recursion
     * @param timeAllowed: time in ms given for it to run
     * @param timeStart: the time the algorithm is started
     * @param boxes: boolean that determines wether we are using boxes, or pentominoes
     * @return boolean that specifies if a solution (complete cover) has been found
     */
    public boolean search(int k,int timeAllowed,long timeStart,boolean boxes){
        if(h.R.equals(h) ){
            //System.out.println("solution found with n of pieces: " + Os.size());
            int score = 0;
            
            for (Square squa : Os) {
                int RowSize = 1;
                Square i = squa.L;
                while(!i.equals(squa)){
                    RowSize++;
                    i = i.L;
                }
                if((boxes && RowSize == 16)||(!boxes && squa.row < 13968)){
                    score+=Ascore;
                }else{
                    if ((boxes && RowSize == 24)||(!boxes && squa.row < 30088)) {
                        score+=Bscore;
                    } else {
                        if ((boxes && RowSize == 27)||(!boxes && squa.row < 37144)){
                            score+=Cscore;
                        } else {
                            System.out.println("shape of size " + RowSize + "??");
                        }
                    }
                }
            }
            if(score>maxScore){
                	maxOs.clear();
                    for (Square square : Os) {
                        maxOs.add(square);
                    }
                    maxScore = score;
            }
            timeTaken = (int) (timeStart - System.currentTimeMillis());
            return true;

            
        }

        if(System.currentTimeMillis()-timeStart>timeAllowed){
            return false;
        }

        boolean result = false;

        Square cc = h.R;
        Header c = (Header) h.R;
        boolean endMax = false;
        int minS = -1;
        //at the end of this loop c should be the header of the column with least squares in it
        while(!endMax){
            if(((Header)cc).S < minS || minS ==-1){
                minS = ((Header)cc).S;
                c = (Header) cc;
            }
            if(cc.R.equals(h))endMax=true;
            cc = cc.R;
        }
        
        //System.out.println("on layer " + k + ", " + Os.size() + "choosing column" + c.N + "and S of :" + minS);


        //here is our stopping condition, if there are remaining columns that have no squares in them
        if(minS == 0){
            int score = 0;
            
            for (Square squa : Os) {
                int RowSize = 1;
                Square i = squa.L;
                while(!i.equals(squa)){
                    RowSize++;
                    i = i.L;
                }
                if((boxes && RowSize == 16)||(!boxes && squa.row < 13968)){
                    score+=Ascore;
                }else{
                    if ((boxes && RowSize == 24)||(!boxes && squa.row < 30088)) {
                        score+=Bscore;
                    } else {
                        if ((boxes && RowSize == 27)||(!boxes && squa.row < 37144)){
                            score+=Cscore;
                        } else {
                            System.out.println("shape of size " + RowSize + "??");
                        }
                    }
                }
            }


            if(score > maxScore){
                //System.out.println("new high score of " + score + " at branch " + k);
                maxScore = score;
                maxOs.clear();
                for (Square square : Os) {
                    maxOs.add(square);
                }
            }
            int columns = 0;
            for(Square x = h.R;!x.equals(h);x = x.R){
                columns++;
            }
            if((boxes&&(maxScore>score+columns*0.1875))||((!boxes)&&(maxScore>score+columns))){
                //System.out.println("gonna be a dead end");
                return false;
            }


        }

        cover(c);
        
        if(Os.size()<k+1){
            Os.add(null);
        }


        



        for(Square r2 = c.U;(!r2.equals(c))&&(true);
            r2=r2.U){
        
            Os.set(k, r2);
            

            Square j = r2.R;
    

            while(!j.equals(r2)){
                cover(j.C);
                
                j=j.R;
            }

            if(search(k+1,timeAllowed,timeStart,boxes)){
                result = true;
            }
            r2 = Os.get(k);
            c = r2.C;

            j = r2.L;
            

            while(!j.equals(r2)){
                uncover(j.C);
                j=j.L;
            }

        } 
           
        uncover(c);


        //System.out.println("end branch at layer " + k + " with result: " + result + " with no of pieces " + Os.size());

        if(!result){
            Os.remove(k);
        }

        return result;

    }

    

    public static void cover(Header c){
        c.R.L = c.L;
        c.L.R = c.R;
        

        for(Square i = c.D;!i.equals(c);i = i.D){

            
            for(Square j = i.R;!j.equals(i);j=j.R){
                j.D.U = j.U;
                j.U.D = j.D;
                j.C.S = j.C.S-1;
            }
                
                



        
        }

        
    }


    public static void uncover(Header c){
        
        for(Square i = c.U;!i.equals(c);i = i.U){
            for(Square j = i.L;!j.equals(i);j=j.L){
                j.C.S++;
                j.D.U = j;
                j.U.D = j;

                
            }

    
        }     
        c.R.L = c;
        c.L.R = c;
    }

   
}
