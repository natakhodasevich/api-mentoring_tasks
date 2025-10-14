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
//TODO figure how to log request content
    public static void saveFullApiLog(String testName, String testId, String stepName /*String method, String url,
                                      String requestHeaders, String requestBody*/, String responseHeaders, String responseBody,int statusCodeResponse) throws IOException {
        StringBuilder logBuilder = new StringBuilder();

//        logBuilder.append("=== REQUEST ===\n");
//        logBuilder.append("Method: ").append(method).append("\n");
//        logBuilder.append("URL: ").append(url).append("\n");
//        logBuilder.append("Headers: ").append(requestHeaders).append("\n");
//        logBuilder.append("Body: ").append(requestBody).append("\n\n");

        logBuilder.append("=== RESPONSE ===\n");
        logBuilder.append("Status Code: ").append(statusCodeResponse).append("\n");
        logBuilder.append("Headers: ").append(responseHeaders).append("\n");
        logBuilder.append("Body: ").append(responseBody).append("\n");

        saveLogs(testName, testId, stepName + "_api_log", logBuilder.toString());
    }
}
