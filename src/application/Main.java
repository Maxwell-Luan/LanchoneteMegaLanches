package application;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import entities.Funcionario;
import entities.Pedido;
import entities.Produto;

public class Main {

	// Classe utilizada apenas para testar os métodos à medida que são criados
	public static void main(String[] args) throws SQLException {
		Pedido pedido = new Pedido();
		Funcionario func = new Funcionario();
		Produto prod = new Produto();

		String opcao = JOptionPane.showInputDialog(null, "Selecione uma opção: \n<1> - Cadastrar Pedido "
				+ "\n<2> - Listar Pedidos \n<3> - Cadastrar Funcionário "
				+ "\n<4> - Listar Funcionários \n<5> - Adicionar Saldo "
				+ "\n<6> - Reduzir Saldo \n<7> - Cadastrar Produto \n<8> - Listar Produtos \n<9> - Alterar Produto");

		int opcaoX = Integer.parseInt(opcao);

		switch (opcaoX) {
		case 1:
			pedido.cadastrarPedido();
			break;
		case 2:
			pedido.listarPedidos();
			break;
		case 3:
			func.cadastrarFuncionario();
			break;
		case 4:
			func.listarFuncionarios();
			break;
		case 5:
			func.atribuirSaldo();
			break;
		case 6:
			func.reduzirSaldo();
			break;
		case 7:
			prod.cadastrarProduto();
			break;
		case 8:
			prod.listarProdutos();
			break;
		case 9:
			prod.alterarProduto();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção inválida!");
			break;
		}
	}
}
