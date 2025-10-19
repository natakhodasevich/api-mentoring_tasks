package core;


public class ApiTestContext {
    private static final ThreadLocal<String> testName = new ThreadLocal<>();
    private static final ThreadLocal<String> testId = new ThreadLocal<>();
    private static final ThreadLocal<String> stepName = new ThreadLocal<>();

    public static void setTestName(String name) {
        testName.set(name);
    }

    public static String getTestName() {
        return testName.get();
    }

    public static void setTestId(String id) {
        testId.set(id);
    }

    public static String getTestId() {
        return testId.get();
    }

    public static void setStepName(String name) {
        stepName.set(name);
    }

    public static String getStepName() {
        return stepName.get();
    }

    public static void clear() {
        testName.remove();
        testId.remove();
        stepName.remove();
    }
}

