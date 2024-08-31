package entities;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexaobd.FuncionarioBancoOperacoes;

public class Funcionario {

	private String nome;
	private double saldo;
	private FuncionarioBancoOperacoes funcBD = null;	

	ArrayList<Funcionario> funcionarios = new ArrayList<>();
	
	public Funcionario() {
		this.funcBD = new FuncionarioBancoOperacoes();
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
	
	// Método para atribuir saldo ao funcionário
	public void atribuirSaldo() throws SQLException {
	    double valor = 0.00;
	    String nomeFuncionario = JOptionPane.showInputDialog(null, "Digite o nome do funcionário que receberá mais saldo:");

	    if (funcBD.validarFuncionarioNome(nomeFuncionario)) {
	        double saldoAtual = funcBD.getSaldoFuncionario(nomeFuncionario);  // Obter o saldo atual do funcionário

	        String valorX = JOptionPane.showInputDialog(null, "Digite quanto quer acrescentar:");

	        try {
	            valor = Double.parseDouble(valorX);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Valor inválido! Digite um número válido.");
	            return;  // Retorna após erro de entrada para evitar execução adicional
	        }

	        if (valor > 0) {
	            saldoAtual += valor;  // Adiciona o valor ao saldo atual
	            funcBD.atualizarSaldoFuncionario(nomeFuncionario, saldoAtual);  // Atualiza o saldo no banco de dados
	            JOptionPane.showMessageDialog(null, "Valor adicionado com sucesso!");
	        } else {
	            JOptionPane.showMessageDialog(null, "Valor inválido para adicionar saldo!");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
	    }
	}

	// Método para reduzir o saldo do funcionário
	public void reduzirSaldo() throws SQLException {
	    double valor = 0.00;
	    String nomeFuncionario = JOptionPane.showInputDialog(null, "Digite o nome do funcionário que deseja deduzir o saldo:");

	    if (funcBD.validarFuncionarioNome(nomeFuncionario)) {
	        double saldoAtual = funcBD.getSaldoFuncionario(nomeFuncionario);  // Obter o saldo atual do funcionário

	        String valorX = JOptionPane.showInputDialog(null, "Digite quanto quer deduzir:");

	        try {
	            valor = Double.parseDouble(valorX);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Valor inválido! Digite um número válido.");
	            return;  // Retorna após erro de entrada para evitar execução adicional
	        }

	        if (valor > 0) {
	            saldoAtual -= valor;  // Adiciona o valor ao saldo atual
	            funcBD.atualizarSaldoFuncionario(nomeFuncionario, saldoAtual);  // Atualiza o saldo no banco de dados
	            JOptionPane.showMessageDialog(null, "Valor deduzido com sucesso!");
	        } else {
	            JOptionPane.showMessageDialog(null, "Valor inválido para deduzir saldo!");
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
	    }
	}

	// Cadastrar funcionário e verificar se saldo é válido.
	public void cadastrarFuncionario() throws SQLException {
		double saldo = 0.00;
		String nome = JOptionPane.showInputDialog(null, "Digite o nome do funcionário");
		while (nome.length() < 1 || nome.isEmpty() || nome.isBlank()) {
			nome = JOptionPane.showInputDialog(null, "Nome inválido! Digite novamente");
		}
		String saldoX = JOptionPane.showInputDialog(null, "Digite o saldo do funcionário");
		try {
			saldo = Double.parseDouble(saldoX);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Saldo inválido! Digite um número válido.");
		}

		if (funcBD.validarFuncionarioNome(nome) == true) {
			JOptionPane.showMessageDialog(null, "Já existe um funcionário com este nome!");
		} else {
			funcBD.inserirFuncionarioBanco(nome, saldo);
			Funcionario func = new Funcionario(nome, saldo);
			funcionarios.add(func);
			JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
		}
	}
	
	public void listarFuncionarios() throws SQLException {
			funcBD.listarFuncionarios();
	}

}