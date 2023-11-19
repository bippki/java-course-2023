package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    @Override
    boolean accept(Path entry);

    default AbstractFilter and(AbstractFilter other) {
        return (entry) -> this.accept(entry) && other.accept(entry);
    }

    static AbstractFilter largerThan(long size) {
        return (entry) -> {
            try {
                return Files.size(entry) > size;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    static AbstractFilter magicNumber(int... magicNumbers) {
        return (entry) -> {
            if (magicNumbers.length == 0) {
                return true;
            }
            try {
                byte[] bytes = Files.readAllBytes(entry);
                if (bytes.length < magicNumbers.length) {
                    return false;
                }
                for (int i = 0; i < magicNumbers.length; i++) {
                    if (bytes[i] != (byte) magicNumbers[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    static AbstractFilter globMatches(String glob) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return (entry) -> {
            Path fileName = entry.getFileName();
            return fileName != null && matcher.matches(fileName);
        };
    }

    static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return (entry) -> {
            Path fileName = entry.getFileName();
            if (fileName == null) {
                return false;
            }
            Matcher matcher = pattern.matcher(fileName.toString());
            return matcher.find();
        };
    }
}
