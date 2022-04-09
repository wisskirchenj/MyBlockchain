package topics.filehierarchies;

import java.io.File;

/**
 * Download and unpack the following archive: https://stepik.org/media/attachments/lesson/91426/basedir.zip.
 *
 * If you open it, you will see a hierarchy of files.
 *
 * Write a program that finds the deepest file (or a directory) in this hierarchy.
 *
 * Enter the name of the file you will get.
 */
public class DeepestFileFinder {

    private static int maxDepth = 0;
    private static String filename = "";

    public static void main(String[] args) {
        File base = new File("/Users/jwisskirchen/Documents/basedir");
        recursiveScanDir(base, 0);
        System.out.println("File: " + filename + "\nDepth: " + maxDepth);
    }

    private static void recursiveScanDir(File file, int depth) {
        if (file.isFile()) {
            if (depth > maxDepth) {
                maxDepth = depth;
                filename = file.getName();
            }
            return;
        }
        for (File f: file.listFiles()) {
            recursiveScanDir(f, depth + 1);
        }
    }
}
