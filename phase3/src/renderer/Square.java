package renderer;

public class Square {
    public Square L = this;
    public Square R = this;
    public Square U = this;
    public Square D = this;
    public Header C;
    public int row;

    public Square(){

    }

    public Square(Square l,Square r,Square u,Square d,Header c){
        L = l;R= r; U = u; D = d; C=c;
    }


}
