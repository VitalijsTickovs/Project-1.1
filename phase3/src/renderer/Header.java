package renderer;

public class Header extends Square {
    public String N;
    public int S = 0;

    public Header(){};

    public Header(String n){
        N=n;
    }

    public Header(Square l,Square r,Square u,Square d,String n){
        L = l;R= r; U = u; D = d;
        N = n;
    }
}

