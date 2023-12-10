package edu.hw9;

import edu.hw9.Task2.DirectorySearchTask;
import edu.hw9.Task2.FileSearchTask;
import java.io.File;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Task9_2Test{
    private static final String rootDirectoryPath = "D:\\Games\\Dragon Age Origins";
    @Test
    public void testPrintAllFileNames() {
        FileSearchTask fileSearchTask = new FileSearchTask(new File(rootDirectoryPath), file -> true);
        List<File> allFiles = fileSearchTask.invoke();

        System.out.println("All file names:");
        allFiles.forEach(file -> System.out.println(file.getName()));

        assertFalse("List of files is empty", allFiles.isEmpty());
    }

    @Test
    public void testRandomFileSearch() {
        FileSearchTask fileSearchTask = new FileSearchTask(new File(rootDirectoryPath), file -> true);
        List<File> allFiles = fileSearchTask.invoke();

        assertTrue("List of files is empty", !allFiles.isEmpty());

        Random random = new Random();
        File randomFile = allFiles.get(random.nextInt(allFiles.size()));
        System.out.println("Randomly selected file:");
        System.out.println("File name: " + randomFile.getName());
        System.out.println("File path: " + randomFile.getAbsolutePath());

        assertTrue(randomFile.exists());
    }

    @Test
    public void testDirectorySearchTask() {
        DirectorySearchTask directorySearchTask = new DirectorySearchTask(new File(rootDirectoryPath));
        List<File> directoriesWithMoreThan1000Files = directorySearchTask.invoke();
        assertNotNull(directoriesWithMoreThan1000Files);
        assertFalse("List of directories is empty", directoriesWithMoreThan1000Files.isEmpty());

        System.out.println("Directories with more than 1000 files:");
        directoriesWithMoreThan1000Files.forEach(directory -> {
            if (!directory.isDirectory())
                return;
            File[] files = directory.listFiles();
            assertNotNull(files);
            assertTrue(files.length > 1000);
            System.out.println("Directory: " + directory.getAbsolutePath() + ", Files count: " + files.length);
        });
    }
}
