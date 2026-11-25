import java.io.*;
import java.util.ArrayList;

public class FileStorage {
    public static ArrayList<String> read(String name) throws IOException {
        ArrayList<String> rows = new ArrayList<>();
        File file = new File(StorageConfig.folder(), name + ".txt");
        if (!file.exists()) return rows;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String line;
            while ((line = reader.readLine()) != null) rows.add(line);
        } finally { reader.close(); }
        return rows;
    }
    public static void write(String name, ArrayList<String> rows) throws IOException {
        File file = new File(StorageConfig.folder(), name + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        try {
            for (String row : rows) { writer.write(row); writer.newLine(); }
        } finally { writer.close(); }
    }
}
