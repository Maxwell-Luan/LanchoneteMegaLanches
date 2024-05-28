package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioBancoOperacoes extends ConexaoBancoDeDados {

	private static final String VALIDAR_QUERY_USUARIO_NOME ="SELECT COUNT(id) FROM tbusuario WHERE nome =?";
	private static final String VALIDAR_QUERY_USUARIO_EMAIL ="SELECT COUNT(id) FROM tbusuario WHERE email =?";
	private static final String VALIDAR_LOGIN_USER = "SELECT COUNT(id) AS count FROM tbusuario WHERE nome = ? AND senha = ?";
	private static final String INSERT_USER = "INSERT INTO tbusuario" + " (nome, email, senha) VALUES " + " (?, ?, ?);";
	private static final String UPDATE_USERNAME = "UPDATE tbusuario SET nome = ? WHERE nome = ?;";

	public boolean validarUsuarioBancoNome(String nome) throws SQLException {
		boolean encontrado = false;

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(VALIDAR_QUERY_USUARIO_NOME)) {

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
	
	 public boolean validarUsuarioBancoEmail(String email) throws SQLException {
		    boolean encontrado = false;

		    try (Connection connection = DriverManager.getConnection(url, user, password);
		    		
		        PreparedStatement preparedStatement = connection.prepareStatement(VALIDAR_QUERY_USUARIO_EMAIL)) {

		        preparedStatement.setString(1, email);

		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        System.out.println(preparedStatement);

		        // Se houver algum resultado na consulta, significa que o usuário foi encontrado
		        if (resultSet.next() && resultSet.getInt("count") > 0) {
		            encontrado = true;
		        }
		    } catch (SQLException e) {
		        printSQLException(e);
		    }

		    return encontrado;
		}

	public boolean validarLoginUsuarioBanco(String nome, String senha) throws SQLException {
		boolean encontrado = false;

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(VALIDAR_LOGIN_USER)) {

			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, senha);

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

	public void inserirUsuarioBanco(String nome, String email, String senha) throws SQLException {
		System.out.println(INSERT_USER);
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, senha);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public void alterarUsuarioBanco(String nome, String novoNome) throws SQLException {
		System.out.println(UPDATE_USERNAME);
		// Step 1: Establishing a Connection
		try (Connection connection = DriverManager.getConnection(url, user, password);

				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERNAME)) {
			preparedStatement.setString(1, novoNome);
			preparedStatement.setString(2, nome);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

}