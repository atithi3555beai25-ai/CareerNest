import java.io.File;
public class StorageInitializer {
    public boolean ready() {
        File folder = new File(StorageConfig.folder());
        if (folder.isDirectory() || folder.mkdirs()) return true;
        System.out.println(new StorageException("Cannot create data folder").getMessage());
        return false;
    }
}
