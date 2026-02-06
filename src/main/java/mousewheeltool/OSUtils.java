package mousewheeltool;

/**
 * OS 判定ユーティリティ
 */
public class OSUtils {
    
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();
    
    public static boolean isWindows() {
        return OS_NAME.contains("win");
    }
    
    public static boolean isLinux() {
        return OS_NAME.contains("linux");
    }
    
    public static boolean isMac() {
        return OS_NAME.contains("mac");
    }
    
    public static String getOSName() {
        if (isWindows()) {
            return "Windows";
        } else if (isLinux()) {
            return "Linux";
        } else if (isMac()) {
            return "macOS";
        }
        return "Unknown";
    }
}
