package entities;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexaobd.ProdutoBancoOperacoes;

public class Produto {

	private int id;
	private String descricao;
	private double valor;
	private ProdutoBancoOperacoes produtoBD = null;

	public Produto() {
		this.produtoBD = new ProdutoBancoOperacoes();
	}

	public Produto(int id, String descricao, double valor) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void listarProdutos() throws SQLException {
		produtoBD.listarProdutos();
	}

	public void cadastrarProduto() throws SQLException {
		String descricao = JOptionPane.showInputDialog(null, "Digite o nome do produto");
		while (descricao.length() < 1 || descricao.isEmpty() || descricao.isBlank()) {
			descricao = JOptionPane.showInputDialog(null, "Nome inválido! Digite novamente");
		}
		if (produtoBD.validarProdutoNome(descricao)) {
			JOptionPane.showMessageDialog(null, "Já existe um produto com esse nome! Digite um novo.");
		} else {
			String valorX = JOptionPane.showInputDialog(null, "Digite o valor do produto");
			try {
				valor = Double.parseDouble(valorX);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor inválido! Digite um número válido.");
				return;
			}
			produtoBD.inserirProdutoBanco(descricao, valor);
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
		}
	}

	public void alterarProduto() throws SQLException {
		String nome = JOptionPane.showInputDialog(null, "Qual produto deseja alterar?");
		if (produtoBD.validarProdutoNome(nome)) {
			String valorX = JOptionPane.showInputDialog(null, "Digite o novo valor: ");
			try {
				valor = Double.parseDouble(valorX);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor inválido! Digite um número válido.");
				return;
			}
			produtoBD.alterarProdutoBanco(valor, nome);
		}
		else {
			JOptionPane.showMessageDialog(null, "Produto não localizado.");
		}
	}

}
