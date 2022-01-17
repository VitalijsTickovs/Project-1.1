package dancingLinks;

public class Header extends Square {
    //class that represents a header in the doubly linked structure used by "dancing list"
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

