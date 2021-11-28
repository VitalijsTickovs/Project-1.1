import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.*;
public class SaveFile {
    private final String savePath = "phase2/src/Save.txt";
    private int save;
    private String playerName = "";
    File file = new File(savePath);
    File tempFile = new File(file.getAbsoluteFile() + ".tmp");
    boolean replaced = false;

    public SaveFile(String playerName, int save) {
        this.save = save;
        this.playerName += playerName;
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

            String subName = "";
            String subScore = "";
            String line = null;
            int saveLimit = 1;
            while ((line = br.readLine()) != null || saveLimit < 5) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        subName += line.substring(0, i);
                        subScore += line.substring(i + 2);
                    }
                }
                if (subName.equals(playerName) && Integer.parseInt(subScore) < save) {
                    bw.write(playerName + ": " + save + "\n");
                    replaced = true;
                } else if (!subName.equals(playerName)) {
                    bw.write(line + "\n");
                }
                saveLimit++;
            }
            if (saveLimit <= 5 && !replaced) {
                bw.write(playerName + ": " + save + "\n");
            }
            bw.close();
            br.close();

            file.delete();
            tempFile.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}