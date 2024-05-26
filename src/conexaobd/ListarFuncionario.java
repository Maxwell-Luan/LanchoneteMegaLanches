package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ListarFuncionario extends ConexaoBancoDeDados {

	private static final String FIND_ALL = "SELECT * FROM tbfuncionario;";

	public boolean listarFuncionarios() throws SQLException {
		boolean encontrado = false;
		
		ArrayList<String> funcionarios = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {

			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			while(resultSet.next()) {
            	String nomeFuncionario = resultSet.getString("nome");
            	double saldoFuncionario = resultSet.getDouble("saldo");
            	funcionarios.add(nomeFuncionario + " - " + saldoFuncionario);
            }
			
			StringBuilder funcionariosText = new StringBuilder();
            for (String func : funcionarios) {
                funcionariosText.append(func).append("\n");
            }
            JOptionPane.showMessageDialog(null, funcionariosText.toString());
			
		} catch (SQLException e) {
			printSQLException(e);
		}

		return encontrado;
	}
}
