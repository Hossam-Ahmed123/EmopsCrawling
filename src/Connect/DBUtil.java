/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author hossam.ahmed
 */
public class DBUtil {

    static Logger log = Logger.getLogger(DBUtil.class.getName());

    public static Connection connectDataBase() throws IOException {
        Properties configFile = new Properties();
        Connection connection = null;
        configFile.load(DBUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        String url = configFile.getProperty("URL");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            connection = DriverManager.getConnection(url);
            log.info("Database Up.....");
        } catch (Exception ex) {
            log.error("Error ...", ex);
        }
        return connection;

    }
}
