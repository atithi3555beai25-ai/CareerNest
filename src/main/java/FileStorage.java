import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class FileStorage {

    // Get file path
    private static Path getFilePath(String name)
            throws Exception {

        if (name == null ||
            !name.matches("[a-zA-Z0-9_-]+")) {

            throw new Exception("Invalid file name");
        }

        Path folder = Paths.get(StorageConfig.folder());

        // Create folder if it does not exist
        Files.createDirectories(folder);

        return folder.resolve(name + ".txt");
    }

    // Read data from file
    public static ArrayList<String> read(String name)
            throws Exception {

        ArrayList<String> list = new ArrayList<>();

        Path path = getFilePath(name);

        if (!Files.exists(path)) {
            return list;
        }

        try {

            list.addAll(
                Files.readAllLines(
                    path,
                    StandardCharsets.UTF_8
                )
            );

        } catch (IOException e) {

            throw new Exception(
                "Error reading file: " + name, e
            );
        }

        return list;
    }

    // Write data into file
    public static void write(
            String name,
            ArrayList<String> data)
            throws Exception {

        Path path = getFilePath(name);

        Path tempPath = path.resolveSibling(
            path.getFileName().toString() + ".tmp"
        );

        try {

            Files.write(
                tempPath,
                data,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );

            try {

                Files.move(
                    tempPath,
                    path,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE
                );

            } catch (AtomicMoveNotSupportedException e) {

                Files.move(
                    tempPath,
                    path,
                    StandardCopyOption.REPLACE_EXISTING
                );
            }

        } catch (IOException e) {

            Files.deleteIfExists(tempPath);

            throw new Exception(
                "Error writing file: " + name, e
            );
        }
    }
}