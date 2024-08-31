package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FuncionarioBancoOperacoes extends ConexaoBancoDeDados {

	private static final String VALIDAR_QUERY_FUNCIONARIO_NOME = "SELECT COUNT(id) FROM tbfuncionario WHERE nome =?";
	private static final String FIND_ALL = "SELECT * FROM tbfuncionario;";
	private static final String INSERT_FUNCIONARIO = "INSERT INTO tbfuncionario" + " (nome, saldo) VALUES "
			+ " (?, ?);";
	private static final String UPDATE_SALDO = "UPDATE tbfuncionario SET saldo = ? WHERE nome = ?" ;
	private static final String GET_SALDO_QUERY = "SELECT saldo FROM tbfuncionario WHERE nome = ?";

	public boolean validarFuncionarioNome(String nome) throws SQLException {
		boolean encontrado = false;
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(VALIDAR_QUERY_FUNCIONARIO_NOME)) {
			preparedStatement.setString(1, nome);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			if (resultSet.next() && resultSet.getInt("count") > 0) {
				encontrado = true;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return encontrado;
	}

	public boolean listarFuncionarios() throws SQLException {
		boolean encontrado = false;
		ArrayList<String> funcionarios = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			while (resultSet.next()) {
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

	public void inserirFuncionarioBanco(String nome, double saldo) throws SQLException {
		System.out.println(INSERT_FUNCIONARIO);
		
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FUNCIONARIO)) {
			preparedStatement.setString(1, nome);
			preparedStatement.setDouble(2, saldo);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public double getSaldoFuncionario(String nome) throws SQLException {
	    double saldo = 0.0;
	    try (Connection connection = DriverManager.getConnection(url, user, password);
	         PreparedStatement preparedStatement = connection.prepareStatement(GET_SALDO_QUERY)) {
	        preparedStatement.setString(1, nome);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            saldo = resultSet.getDouble("saldo");
	        }
	    } catch (SQLException e) {
	        printSQLException(e);
	    }
	    return saldo;
	}

	
	public void atualizarSaldoFuncionario(String nome, double valor) throws SQLException {
		System.out.println(UPDATE_SALDO);
		// Step 1: Establishing a Connection
		try (Connection connection = DriverManager.getConnection(url, user, password);

				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SALDO)) {
			preparedStatement.setDouble(1, valor);
			preparedStatement.setString(2, nome);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
}
