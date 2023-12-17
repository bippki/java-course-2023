package edu.hw10.Task10_1.provider;

import edu.hw10.Task10_1.api.Provider;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;

public class FileStringProvider extends BaseStringArrayProvider {

    @Override
    public Provider<String> setDataSource(String dataSource) {
        super.setDataSource(dataSource);
        File dataFile = new File(dataSource);
        if (!dataFile.isFile()) {
            try {
                dataFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(dataSource)).toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        try (FileInputStream stream = new FileInputStream(dataFile)) {
            input = new BufferedReader(new InputStreamReader(stream)).lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
