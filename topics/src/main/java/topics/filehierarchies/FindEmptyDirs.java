package topics.filehierarchies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindEmptyDirs {

    private static final List<String> emptyDirs = new ArrayList<>();

    public static void main(String[] args) {
        File file = new File("/Users/jwisskirchen/Documents/basedir");
        scanForEmptyDirs(file);
        System.out.println(String.join(" ", emptyDirs));
    }

    private static void scanForEmptyDirs(File file) {
        if (file.isFile()) {
            return;
        }
        if (file.list().length == 0) {
            emptyDirs.add(file.getName());
        }
        for (File f: file.listFiles()) {
            scanForEmptyDirs(f);
        }
    }
}
