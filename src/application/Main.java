package application;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import entities.Funcionario;
import entities.Usuario;

public class Main {

	public static void main(String[] args) throws SQLException {

		Usuario user = new Usuario();
		String opcao = "";
		boolean testeLogin = false;
		String nomeX = "";
		String senhaX = "";
		Funcionario func = new Funcionario();

		opcao = JOptionPane.showInputDialog(null,
				"Selecione uma opção: \n<1> - Cadastrar usuário \n<2> - Logar \n<3> - Cadastrar Funcionário");
		int opcaoX1 = Integer.parseInt(opcao);
		switch (opcaoX1) {
		case 1:
			user.cadastrarUsuario();
			break;

		case 2:
			nomeX = JOptionPane.showInputDialog(null, "Digite seu nome");
			senhaX = JOptionPane.showInputDialog(null, "Digite sua senha");
			testeLogin = user.logarUsuario(nomeX, senhaX);

		case 3:
			func.cadastrarFuncionario();
		}

	}
}
