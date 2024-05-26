package entities;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexaobd.CadastrarFuncionario;
import conexaobd.ConexaoBancoDeDados;

public class Funcionario {

	private String nome;
	private double saldo;
	private CadastrarFuncionario inserirFuncionario = null;	
	private ConexaoBancoDeDados cbd = new ConexaoBancoDeDados();

	ArrayList<Funcionario> funcionarios = new ArrayList<>();
	
	public Funcionario() {
		this.inserirFuncionario = new CadastrarFuncionario();
	}

	public Funcionario(String nome, double saldo) {
		this.nome = nome;
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	// Cadastrar funcionário e verificar se saldo é válido.
	public void cadastrarFuncionario() throws SQLException {
		nome = JOptionPane.showInputDialog(null, "Digite o nome do funcionário");
		while (nome.length() < 1 || nome.isEmpty() || nome.isBlank()) {
			nome = JOptionPane.showInputDialog(null, "Nome inválido! Digite novamente");
		}
		String saldoX = JOptionPane.showInputDialog(null, "Digite o saldo do funcionário");
		try {
			saldo = Double.parseDouble(saldoX);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Saldo inválido! Digite um número válido.");
		}

		if (cbd.validarFuncionarioNome(nome) == true) {
			JOptionPane.showMessageDialog(null, "Já existe um usuário com este nome!");
		} else {
			inserirFuncionario.inserirFuncionarioBanco(nome, saldo);
			Funcionario func = new Funcionario(nome, saldo);
			funcionarios.add(func);
			JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
		}
	}

}