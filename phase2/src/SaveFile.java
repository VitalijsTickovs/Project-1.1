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

    public SaveFile(String playerName, int toSave) {
        this.toSave = toSave;
        this.playerName += playerName.substring(0, 1).toUpperCase() + playerName.substring(1);
        LoadToFile(ReadFromFile());
    }

    public SaveFile(){
        this.toSave = 0;
    }

    private HashMap<String, Integer> ReadHash = new HashMap<String, Integer>();
    private HashMap<String,Integer> LoadHash = new HashMap<String, Integer>();
    private Properties loadProperties = new Properties();
    private Properties storeProperties = new Properties();
    public HashMap<String, Integer> ReadFromFile() {
        try {
            loadProperties.load(new FileInputStream(savePath));
            LoadHash = sortByValue(LoadHash);

            for (String key : loadProperties.stringPropertyNames()) {
                ReadHash.put(key, Integer.parseInt(loadProperties.getProperty(key)));
            }

            return sortByValue(ReadHash);
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void LoadToFile(HashMap<String,Integer> LoadHash) {
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

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> fileObj)
    {
        //create a list from elements of hashmap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<>(fileObj.entrySet());

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