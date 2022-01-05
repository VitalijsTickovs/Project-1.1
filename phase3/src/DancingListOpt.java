import java.util.ArrayList;

public class DancingListOpt {

    public int Ascore = 3;
    public int Bscore = 4;
    public int Cscore = 5;



    public ArrayList<Square> Os = new ArrayList<>();

    public int maxScore = 0;

    public ArrayList<Square> maxOs = new ArrayList<>();


    public boolean search(int k, final Square h){
        if(h.R.equals(h) || maxScore >= 244){
            return true;
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
        cover(c);
        //System.out.println("on layer " + k + "choosing column" + c.N);


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
                if(RowSize == 16){
                    score+=Ascore;
                }else{
                    if (RowSize == 24) {
                        score+=Bscore;
                    } else {
                        if (RowSize == 27) {
                            score+=Cscore;
                        } else {
                            System.out.println("shape of size " + RowSize + "??");
                        }
                    }
                }
            }


            if(score > maxScore){
                System.out.println("new high score of " + score + " at branch " + k);
                maxScore = score;
                maxOs.clear();
                for (Square square : Os) {
                    maxOs.add(square);
                }
            }
            //return false;

        }


        Square r = c.D;
        if(!r.equals(c)){
            Os.add(r);
        }
        while(!r.equals(c)){
            Os.set(Os.size()-1, r);
            

            Square j = r.R;
    

            while(!j.equals(r)){
                cover(j.C);
                
                j=j.R;
            }

            if(search(k+1,h)){
                result = true;
            }
            r = Os.get(k);
            c = r.C;

            j = r.L;
            

            while(!j.equals(r)){
                uncover(j.C);
                j=j.L;
            }

            
            r = r.D;
        }
        uncover(c);


        //System.out.println("end branch at layer " + k);

        

        return result;

    }

    

    public static void cover(Header c){
        c.R.L = c.L;
        c.L.R = c.R;
        Square i = c.D;

        while(!i.equals(c)){

            Square j = i.R;

            while(!j.equals(i)){
                j.D.U = j.U;
                j.U.D = j.D;
                j.C.S--;

                
                j = j.R;
            }



        
            i = i.D;

        }
    }


    public static void uncover(Header c){
        Square i = c.U;
    
        
        while(!i.equals(c)){

            Square j = i.L;
            

            while(!j.equals(i)){
                j.C.S++;
                j.D.U = j;
                j.U.D = j;

                j = j.L;
            }

    
            i = i.U;
        }
        c.R.L = c;
        c.L.R = c;
    }
}
