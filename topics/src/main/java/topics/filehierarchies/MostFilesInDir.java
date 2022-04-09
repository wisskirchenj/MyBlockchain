package topics.filehierarchies;

import java.io.File;
import java.util.Arrays;

/**
 * Download and unpack the following archive: https://stepik.org/media/attachments/lesson/91404/basedir.zip.
 *
 * If you unpack and open it, you will see a lot of directories containing files.
 *
 * You must write a program that finds a directory with the maximum number of files.
 *
 * Enter the name of the directory and the number of files in it separated by one space.
 */
public class MostFilesInDir {

    private static long maxFiles = 0;
    private static String dir ="";

    public static void main(String[] args) {
        File file = new File("/Users/jwisskirchen/Documents/basedir");
        scanForMostFiles(file);
        System.out.println("Dir: " + dir + " #Files: " + maxFiles);
    }

    private static void scanForMostFiles(File file) {
        if (file.isFile()) {
            return;
        }
        long filesContained = Arrays.stream(file.listFiles()).filter(File::isFile).count();
        if (filesContained > maxFiles) {
            maxFiles = filesContained;
            dir = file.getName();
        }
        for (File f: file.listFiles()) {
            scanForMostFiles(f);
        }
    }
}
