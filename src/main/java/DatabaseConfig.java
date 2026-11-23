public class DatabaseConfig {

    public static String url() {
        return "jdbc:mysql://localhost:3306/careernest_new";
    }

    public static String user() {
        return "root";
    }

    public static String password() {
        return System.getenv("DB_PASSWORD");
    }
}