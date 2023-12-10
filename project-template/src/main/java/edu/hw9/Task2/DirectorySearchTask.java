package edu.hw9.Task2;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearchTask extends RecursiveTask<List<File>> {
    private final File directory;

    public DirectorySearchTask(File directory) {
        this.directory = directory;
    }

    @Override
    protected List<File> compute() {
        List<DirectorySearchTask> subtasks = new ArrayList<>();
        List<File> result = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    DirectorySearchTask subtask = new DirectorySearchTask(file);
                    subtask.fork();
                    subtasks.add(subtask);
                } else {
                    result.add(file);
                }
            }
        }

        for (DirectorySearchTask subtask : subtasks) {
            result.addAll(subtask.join());
        }

        if (result.size() > 1000 && directory.isDirectory()) {
            System.out.println("Directory with more than 1000 files: " + directory.getAbsolutePath());
        }

        return result;
    }
}
