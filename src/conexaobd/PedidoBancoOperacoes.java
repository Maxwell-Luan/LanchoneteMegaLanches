package conexaobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.Pedido;

public class PedidoBancoOperacoes extends ConexaoBancoDeDados {

	private static final String INSERT_PEDIDO = "INSERT INTO tbpedido (funcionario, descricao, valor, data, hora) VALUES (?, ?, ?, ?, ?)";
	private static final String FIND_ALL_PEDIDOS = "SELECT * FROM tbpedido;";

	// Método para inserir um pedido no banco de dados
	public void inserirPedidoBanco(String funcionario, String descricao, double valor, LocalDate data, LocalTime hora)
			throws SQLException {
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PEDIDO)) {

			preparedStatement.setString(1, funcionario);
			preparedStatement.setString(2, descricao);
			preparedStatement.setDouble(3, valor);
			preparedStatement.setDate(4, java.sql.Date.valueOf(data)); // Converte LocalDate para java.sql.Date
			preparedStatement.setTime(5, java.sql.Time.valueOf(hora)); // Converte LocalTime para java.sql.Time

			preparedStatement.executeUpdate();
			System.out.println("Pedido inserido com sucesso: " + preparedStatement);
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// Método para listar pedidos
	public ArrayList<Pedido> listarPedidos() throws SQLException {
		ArrayList<String> pedidos = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PEDIDOS)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String descricao = resultSet.getString("descricao");
				double valor = resultSet.getDouble("valor");
				LocalDate data = resultSet.getDate("data").toLocalDate(); // Converte java.sql.Date para LocalDate
				LocalTime hora = resultSet.getTime("hora").toLocalTime(); // Converte java.sql.Time para LocalTime
				String nomeFuncionario = resultSet.getString("nome_funcionario"); // Pega o nome do funcionário

				pedidos.add(descricao + " - " + valor + " - " + data + " - " + hora + " - " + nomeFuncionario);
			}

			StringBuilder pedidosText = new StringBuilder();
			for (String pedido : pedidos) {
				pedidosText.append(pedido).append("\n");
			}

			JOptionPane.showMessageDialog(null, pedidosText.toString());

		} catch (SQLException e) {
			printSQLException(e);
		}
		return null;
	}
}
