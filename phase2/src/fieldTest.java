public class fieldTest {
    public static void main(String[] args) {

        int horizontalFieldSize = 12;
        int verticalFieldSize = 20;

        UI ui = new UI(verticalFieldSize, horizontalFieldSize,30);


        Field field = new Field( verticalFieldSize,horizontalFieldSize);
        System.out.println(field.AddPiece('I'));
       
        field.rotate();
        for (int i = 0; i < 21; i++) {
            field.down(); 
        } 
        
        field.setPiece();
        System.out.println(field.AddPiece('X'));

        field.rotate();

        for (int i = 0; i < 21; i++) {
            field.down(); 
        }
        field.setPiece();
        System.out.println(field.AddPiece('N'));
        field.rotate();
        field.left();
        field.left();
        field.left();

        for (int i = 0; i < 21; i++) {
            field.down(); 
        }
        field.left();
        field.down();
        field.down();
        field.right();
        field.setPiece();

        ui.setState(field.getField());

    }
}
