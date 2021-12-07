import java.io.*;
import java.util.*;
import javax.swing.*;
public class SaveFile {
    private final String savePath = "phase2/src/Save.txt";
    private final int toSave;
    private String playerName = "";
    private final String tempSavePath = "phase2/src/Save.temp";
    File origFile = new File(savePath);
    File tempFile = new File(tempSavePath);

    /**
     * Constructor to then save user score
     * @param playerName name of the user
     * @param toSave score from that session
     */
    public SaveFile(String playerName, int toSave) {
        this.toSave = toSave;
        this.playerName += playerName.substring(0, 1).toUpperCase() + playerName.substring(1);
        LoadToFile(ReadFromFile());
    }

    /**
     * This method is used to output the stored leaderboard
     */
    public SaveFile(){
        this.toSave = 0;
    }

    /**
     * Method to save user's data
     * @param LoadHash HashMap that will be saved
     */
    public void LoadToFile(HashMap<String,Integer> LoadHash) {
        Properties storeProperties = new Properties();
        try {
            if(LoadHash.containsKey(playerName)){                                       //if the player is in the leaderboard
                if(LoadHash.get(playerName)<toSave)LoadHash.put(playerName, toSave);    //his score is better from this game -> replace it
            }else LoadHash.put(playerName, toSave);                                     //else, if there is no player in the leaderboard, add it to HashMap

            for (Map.Entry<String, Integer> entry : LoadHash.entrySet()) {              //putting all the data from hashmap to the Properties variable
                storeProperties.put(entry.getKey(), entry.getValue().toString());
            }
            storeProperties.store(new FileOutputStream(tempSavePath), null);   //storing all data to the temp file
            origFile.delete();                                                           //deleting the original file
            tempFile.renameTo(origFile);                                                 //renaming temp file to the original
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method to read data from Save.txt file
     * @return HashMap with all users sorted in descending order
     */
    public HashMap<String, Integer> ReadFromFile() {
        HashMap<String, Integer> ReadHash = new HashMap<String, Integer>();
        Properties loadProperties = new Properties();
        try {
            loadProperties.load(new FileInputStream(savePath));

            for (String key : loadProperties.stringPropertyNames()) {
                ReadHash.put(key, Integer.parseInt(loadProperties.getProperty(key)));
            }

            return sortByValue(ReadHash);
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Method used to sort HashMap
     * @param savedData unsorted HashMap
     * @return ordered HashMap
     */
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> savedData)
    {
        //create a list from elements of hashmap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(savedData.entrySet());

        //sort the list
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        //put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}