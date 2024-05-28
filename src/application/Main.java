package application;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import entities.Funcionario;
import entities.Usuario;

public class Main {

	public static void main(String[] args) throws SQLException {

		Usuario user = new Usuario();
		Funcionario func = new Funcionario();

		String opcao = JOptionPane.showInputDialog(null,
				"Selecione uma opção: \n<1> - Cadastrar usuário " + "\n<2> - Logar " + "\n<3> - Cadastrar Funcionário "
						+ "\n<4> - Listar Funcionários \n<5> - Alterar Usuário");
		int opcaoX = Integer.parseInt(opcao);

		switch (opcaoX) {
		case 1:
			user.cadastrarUsuario();
			break;
		case 2:
			user.logarUsuario();
			break;
		case 3:
			func.cadastrarFuncionario();
			break;
		case 4:
			func.listarFuncionarios();
		case 5:
			user.alterarUsuario();
		}

	}
}
