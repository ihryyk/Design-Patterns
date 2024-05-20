package com.app.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

    public static String readFromFile(Path path) throws IOException {
        if (Files.exists(path)) {
            return Files.readString(path);
        } else {
            throw new FileNotFoundException(String.format("Failed to red file %s.", path));
        }
    }

}
