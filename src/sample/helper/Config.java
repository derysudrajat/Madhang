package sample.helper;

class Config {
    private final String dbHost = "localhost";
    private final String dbPort = "3306";
    private final String dbUser = "root";
    private final String dbPass = "";
    private final String dbName = "madhang";

    String getDbHost() {
        return dbHost;
    }

    String getDbPort() {
        return dbPort;
    }

    String getDbUser() {
        return dbUser;
    }

    String getDbPass() {
        return dbPass;
    }

    String getDbName() {
        return dbName;
    }
}
