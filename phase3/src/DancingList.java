import java.util.ArrayList;

public class DancingList {

    public ArrayList<Square> Os = new ArrayList<>();


    public boolean search(int k, final Square h){
        if(h.R.equals(h)){
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


        Square r = c.D;
        
        while(!r.equals(c)){
            if(k>=Os.size()){
                Os.add(r);
            }else{
                Os.set(k,r);
            }

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
        boolean endColumn = false;
        Square i = c.D;
        if(i.equals(c))endColumn=true;

        while(!endColumn){

            boolean endRow = false;
            Square j = i.R;
            if(j.equals(i))endRow = true;

            while(!endRow){
                j.D.U = j.U;
                j.U.D = j.D;
                j.C.S--;

                if(j.R.equals(i))endRow = true;
                j = j.R;
            }



            if(i.D.equals(c))endColumn=true;
            i = i.D;

        }
    }


    public static void uncover(Header c){
        Square i = c.U;
        boolean endColumn = false;
        if(i.equals(c))endColumn = true;
        
        while(!endColumn){

            Square j = i.L;
            boolean endRow = false;
            if(j.equals(i))endRow=true;

            while(!endRow){
                j.C.S++;
                j.D.U = j;
                j.U.D = j;

                if(j.L.equals(i))endRow=true;
                j = j.L;
            }

            if(i.U.equals(c))endColumn = true;
            i = i.U;
        }
        c.R.L = c;
        c.L.R = c;
    }
}
