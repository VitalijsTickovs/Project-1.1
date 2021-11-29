import java.io.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.*;
public class SaveFile {
    private final String savePath = "phase2/src/Save.txt";
    private int toSave;
    private String playerName = "";
    File file = new File(savePath);
    File tempFile = new File(file.getAbsoluteFile() + ".tmp");
    boolean replaced = false;

    public SaveFile(String playerName, int toSave) {
        this.toSave = toSave;
        this.playerName += playerName.substring(0, 1).toUpperCase() + playerName.substring(1);
        SaveFile();
    }

    public void SaveFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(tempFile);
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(fw);


            String line = null;
            int saveLimit = 1;
            while ((line = br.readLine()) != null && saveLimit < 5) {
                String subName = "";
                String subScore = "";
                int subScoreInt;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        subName += line.substring(0, i);
                        subScore += line.substring(i + 2);
                    }
                }
                subScoreInt = Integer.parseInt(subScore);

                if (subName.equals(playerName) &&  subScoreInt < toSave) {//player is found in save.txt but the score from current game is bigger
                    bw.write(playerName + ": " + toSave + "\n");
                    replaced = true;
                }else if(subName.equals(playerName)){                       //player is found, but current save is bigger than scored in current session
                    bw.write(line + "\n");
                    replaced = true;
                }else{                                                      //different player
                    bw.write(line + "\n");
                }
                saveLimit++;
            }
            if (saveLimit <= 5 && !replaced) {                              //player was not found and the limit of the save file is smaller than 5
                bw.write(playerName + ": " + toSave + "\n");
            }

            //add sorting

            //if the current session score is bigger than in the save file, replace it

            bw.close();
            br.close();

            file.delete();
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}