package application;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import entities.Usuario;

public class Main {

	public static void main(String[] args) throws SQLException {

		Usuario user = new Usuario();
		String opcao = "";
		boolean testeLogin = false;
		String nomeX = "";
		String senhaX = "";

		opcao = JOptionPane.showInputDialog(null, "Você já possui cadastro? \n<1> - Cadastrar \n<2> - Logar");
		int opcaoX1 = Integer.parseInt(opcao);
		switch (opcaoX1) {
		case 1:
			user.cadastrarUsuario();
			break;
		}

		nomeX = JOptionPane.showInputDialog(null, "Digite seu nome");
		senhaX = JOptionPane.showInputDialog(null, "Digite sua senha");
		testeLogin = user.logarUsuario(nomeX, senhaX);

	}
}
