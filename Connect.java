
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author sqlitetutorial.net
 */
public class Connect {
     /**
     * Connect to a sample database
     */
    
    public static Connection connect(String databaseName) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + databaseName;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting");
            System.exit(1);
        }
        return conn;
    }

    public static void selectAll(Connection conn){
        String sql = "SELECT agent_code, agent_name, commission, country FROM agents;";
        try (
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("agent_code") +  "\t" + 
                                   rs.getString("agent_name") + "\t" +
                                   rs.getDouble("commission") + "\t"+
                                   rs.getString("country"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Parameter expected: name of the database file");
            System.exit(1);
        }
        Connection conn = connect(args[0]);
        selectAll(conn);

    }
}
