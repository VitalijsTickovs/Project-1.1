import javax.print.attribute.standard.Fidelity;

public class fieldTest {
    public static void main(String[] args) {

        int horizontalFieldSize = 9;
        int verticalFieldSize = 60;

        UI ui = new UI(verticalFieldSize, horizontalFieldSize,20);


        Field field = new Field( verticalFieldSize,horizontalFieldSize);
        pieceBag bag = new pieceBag();



        for (int i = 0; i < 20; i++) {
        field.AddPiece(bag.nextPiece());   
        
        for (int j = 0; j < 60; j++) {
            field.down();
        } 
        

        field.setPiece();
        ui.setState(field.getField());
        }
    }
}
