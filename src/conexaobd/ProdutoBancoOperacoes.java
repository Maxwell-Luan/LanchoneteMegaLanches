package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProdutoBancoOperacoes extends ConexaoBancoDeDados {

	private static final String VALIDAR_QUERY_PRODUTO_NOME = "SELECT COUNT(id) FROM tbproduto WHERE descricao =?";
	private static final String INSERT_PRODUTO = "INSERT INTO tbproduto (descricao, valor) VALUES (?, ?)";
	private static final String FIND_ALL = "SELECT * FROM tbproduto";
	private static final String UPDATE_PRODUTO = "UPDATE tbproduto SET valor = ? WHERE descricao = ?";

	public boolean validarProdutoNome(String descricao) throws SQLException {
		boolean encontrado = false;
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(VALIDAR_QUERY_PRODUTO_NOME)) {
			preparedStatement.setString(1, descricao);
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

	public boolean listarProdutos() throws SQLException {
		boolean encontrado = false;
		ArrayList<String> produtos = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			while (resultSet.next()) {
				String descricaoProduto = resultSet.getString("descricao");
				double valorProduto = resultSet.getDouble("valor");
				produtos.add(descricaoProduto + " - " + valorProduto);
			}

			StringBuilder produtosText = new StringBuilder();
			for (String prod : produtos) {
				produtosText.append(prod).append("\n");
			}
			JOptionPane.showMessageDialog(null, produtosText.toString());

		} catch (SQLException e) {
			printSQLException(e);
		}
		return encontrado;
	}

	public void inserirProdutoBanco(String descricao, double valor) throws SQLException {
		System.out.println(INSERT_PRODUTO);

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUTO)) {
			preparedStatement.setString(1, descricao);
			preparedStatement.setDouble(2, valor);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public void alterarProdutoBanco(double valor, String descricao) throws SQLException {
		System.out.println(INSERT_PRODUTO);

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUTO)) {
			preparedStatement.setDouble(1, valor);
			preparedStatement.setString(2, descricao);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
}
