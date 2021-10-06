public class test {
   public static void main(String[] args) {
       
    boolean[][] testTable = {{false, false, true, false, true, true, false},
                                {false, false,false,true,false,false,true},
                                {false, true, true,false,false, true,false },
                                {false, false, false,true,false,false,false},
                                {false, true,false,false,false,false, true},
                                {false,false,false,true,true,false, true}
                            };
                            Dancing_links test = new Dancing_links();
                            System.out.println(test.Solving(testTable));
   }

  
}
