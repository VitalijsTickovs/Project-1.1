package dancingLinks;

import java.util.ArrayList;
import java.util.function.IntPredicate;

public class TableToList {


    /**
     * turns a 2D boolean array into the doubly linked structure made of squares and headers, used in "dancing list"
     * @param input: 2d matrix to be turned into the structure
     * @return the root square h that points to the structure
     */
    public static  Square ToList(boolean[][] input){
        Square h = new Square();


        ArrayList<Header> heads = new ArrayList<>();

        heads.add(new Header("1"));

        h.R = heads.get(0);
        h.R.L = h;

        for (int i = 1; i < input[0].length; i++) {
            heads.add(new Header(""+(i+1)));
            heads.get(i).L = heads.get(i-1);
            heads.get(i-1).R = heads.get(i);
        }

        heads.get(heads.size()-1).R = h;
        h.L = heads.get(heads.size()-1);
        int[] counts = new int[input[0].length];
        ArrayList<Square> squas = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            int rowSize = 0;
            for (int j = 0; j < input[0].length; j++) {





                if(input[i][j]){
                    squas.add(new Square());
                    squas.get(rowSize).row = i;
                    heads.get(j).U.D = squas.get(rowSize);
                    squas.get(rowSize).C = heads.get(j);
                    squas.get(rowSize).U = heads.get(j).U;

                    heads.get(j).U = squas.get(rowSize);
                    squas.get(rowSize).D = heads.get(j);

                    counts[j]++;
                    rowSize++;
                }
            }
            for (int j = 0; j < squas.size(); j++) {
                squas.get(j).R = squas.get((j+1)%(squas.size()));
                squas.get((j+1)%squas.size()).L = squas.get(j);

            }
            squas.clear();


        }

        for (int i = 0; i < heads.size(); i++) {
            heads.get(i).S = counts[i];
        }




        return h;

    }
}
