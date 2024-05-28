package conexaobd;
import java.sql.SQLException;

public class ConexaoBancoDeDados {
	 public final String url = "jdbc:postgresql://localhost/BDMegaLanches";
	 public final String user = "postgres";
	 public final String password = "22194";
	 
	    public static void printSQLException(SQLException ex) {
	            for (Throwable e: ex) {
	                if (e instanceof SQLException) {
	                    e.printStackTrace(System.err);
	                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                    System.err.println("Message: " + e.getMessage());
	                    Throwable t = ex.getCause();
	                    while (t != null) {
	                        System.out.println("Cause: " + t);
	                        t = t.getCause();
	                    }
	                }
	            }
	        }
	
	}
