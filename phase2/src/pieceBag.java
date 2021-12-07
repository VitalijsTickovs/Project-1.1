import java.util.ArrayList;
import java.util.List;

/**
 * Class that generates the next piece without replacement (once all pieces are chosen, it loops)
 */
public class pieceBag extends CharToID{

    private List<Character> Pieces;

    public pieceBag(){
        Pieces = new ArrayList<Character>();
        Pieces.add('X');
        Pieces.add('I');
        Pieces.add('Z');
        Pieces.add('U');
        Pieces.add('T');
        Pieces.add('W');
        Pieces.add('V');
        Pieces.add('L');
        Pieces.add('P');
        Pieces.add('F');
        Pieces.add('Y');
        Pieces.add('N');
    }

    public char nextPiece(){
        if(Pieces.size()<=0){
        Pieces.add('X');
        Pieces.add('I');
        Pieces.add('Z');
        Pieces.add('U');
        Pieces.add('T');
        Pieces.add('W');
        Pieces.add('V');
        Pieces.add('L');
        Pieces.add('P');
        Pieces.add('F');
        Pieces.add('Y');
        Pieces.add('N');
        }

        return (char)(Pieces.remove((int)(Math.random()*Pieces.size())));
    }
}
