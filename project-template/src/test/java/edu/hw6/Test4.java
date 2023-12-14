package edu.hw6;

import edu.hw6.Task4.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Test4{

    @Test
    void testFileContent() throws IOException {
        Path filePath = Paths.get("src/main/java/edu/hw6/Task4/output");

        Main.main(null);

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }

        assertThat(content.toString()).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");

        long expectedChecksum = 52041773L;
        long actualChecksum;
        try (CheckedInputStream cis = new CheckedInputStream(Files.newInputStream(filePath), new Adler32())) {
            byte[] buffer = new byte[8192];
            while (cis.read(buffer) >= 0) {
            }
            actualChecksum = cis.getChecksum().getValue();
        }

        assertThat(actualChecksum).isEqualTo(expectedChecksum);
    }
}
