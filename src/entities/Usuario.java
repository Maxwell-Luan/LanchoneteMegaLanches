package entities;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import conexaobd.CadastrarUsuario;
import conexaobd.ConexaoBancoDeDados;
import conexaobd.LogarUsuario;
import entities.Usuario;

public class Usuario {

	private String nome;
	private String email;
	private String senha;
	private ConexaoBancoDeDados cbd = new ConexaoBancoDeDados();
	private CadastrarUsuario novoUsuario = null;
	private LogarUsuario login = null;

	ArrayList<Usuario> usuarios = new ArrayList<>();

	public Usuario() {
		this.login = new LogarUsuario();
	}

	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Realizar o cadastro de um usuario.
	public void cadastrarUsuario() throws SQLException {
		nome = JOptionPane.showInputDialog(null, "Digite seu nome");
		while (nome.length() < 1 || nome.isEmpty() || nome.isBlank()) {
			nome = JOptionPane.showInputDialog(null, "Nome inválido! Digite novamente");
		}
		email = JOptionPane.showInputDialog(null, "Digite seu e-mail");
		while (email.length() < 1 || email.isEmpty() || email.isBlank()) {
			email = JOptionPane.showInputDialog(null, "Nome inválido! Digite novamente");
		}
		senha = JOptionPane.showInputDialog(null, "Digite sua senha");
		while (senha.length() < 1 || senha.isEmpty() || senha.isBlank()) {
			senha = JOptionPane.showInputDialog(null, "Nome inválido! Digite novamente");

			if (cbd.validarUsuarioBancoNome(nome) == true) {
				JOptionPane.showMessageDialog(null,
						"O nome digitado já foi escolhido por outro usuário. Por favor, defina um novo.");
			} else if (cbd.validarUsuarioBancoEmail(email) == true) {
				JOptionPane.showMessageDialog(null,
						"O email digitado já está em uso. Por favor, selecione outro email.");
			} else {
				novoUsuario.inserirUsuarioBanco(nome, email, senha);
				Usuario user = new Usuario(nome, email, senha);
				usuarios.add(user);
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
			}
		}
	}

	//Permitir login do usuário no sistema.
	public boolean logarUsuario(String nomeX, String senhaX) throws SQLException {

			boolean encontradoBanco = login.verificarUsuarioBanco(nomeX, senhaX);

			if (encontradoBanco) {
				JOptionPane.showMessageDialog(null, "Bem vindo a Mega Lanches!");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Acesso negado! Usuário ou senha inválidos.");
				return false;
			}
		
		}

	}

