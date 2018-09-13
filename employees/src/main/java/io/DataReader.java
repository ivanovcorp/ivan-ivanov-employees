package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import io.exception.DataReaderException;

public final class DataReader {
    private DataReader() {}

    public static List<String> readFile(String filePath) {
        return readAllLines(new File(filePath));
    }

    public static List<String> readFile(File file) {
        return readAllLines(file);
    }

    private static List<String> readAllLines(File file) {
        if (!file.exists()) {
            throw new DataReaderException(DataReaderException.FILE_NOT_FOUND_EXCEPTION_MESSAGE + file.getAbsolutePath());
        }

        if (file.isDirectory()) {
            throw new DataReaderException(DataReaderException.PROVIDED_FILE_IS_DIRECTORY_EXCEPTION_MESSAGE + file.getAbsolutePath());
        }

        try {
            return Files.readAllLines(Paths.get(file.toURI()));
        } catch (IOException e) {
            throw new DataReaderException(e.getMessage());
        }
    }
}
