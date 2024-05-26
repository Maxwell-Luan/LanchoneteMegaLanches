package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarFuncionario extends ConexaoBancoDeDados{
	
	private static final String INSERT_FUNCIONARIO = "INSERT INTO tbfuncionario" +
            " (nome, saldo) VALUES " +
            " (?, ?);";

    public void inserirFuncionarioBanco(String nome, double saldo) throws SQLException {
        System.out.println(INSERT_FUNCIONARIO);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FUNCIONARIO)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setDouble(2, saldo);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
