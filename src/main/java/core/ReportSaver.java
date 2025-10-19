package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportSaver {

    private static final String REPORTS_DIR = "reports";

    public static void saveLogs(String testName, String testId, String filePrefix, String content) throws IOException {
        String testDirName = testName + testId;
        saveToFile(testDirName, filePrefix + ".txt", content);
    }

    private static void saveToFile(String testDirName, String fileName, String content) throws IOException {
        File dir = new File(REPORTS_DIR, testDirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
}
