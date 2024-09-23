package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import conexaobd.PedidoBancoOperacoes;
import conexaobd.FuncionarioBancoOperacoes;

public class Pedido {

	private int id;
	private String descricao;
	private double valor;
	private LocalDate data;
	private LocalTime hora;
	private Funcionario funcionario;
	private PedidoBancoOperacoes pedidoBD = null;
	private FuncionarioBancoOperacoes funcionarioBD = null;

	ArrayList<Pedido> pedidos = new ArrayList<>();

	public Pedido() {
		this.pedidoBD = new PedidoBancoOperacoes();
		this.funcionarioBD = new FuncionarioBancoOperacoes();
	}

	public Pedido(int id, String descricao, double valor, LocalDate data, LocalTime hora, Funcionario funcionario) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.hora = hora;
		this.funcionario = funcionario;
		this.pedidoBD = new PedidoBancoOperacoes();
		this.funcionarioBD = new FuncionarioBancoOperacoes();
	}

	public Pedido(String descricao, double valor, LocalDate data, LocalTime hora, Funcionario funcionario) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.hora = hora;
		this.funcionario = funcionario;
		this.pedidoBD = new PedidoBancoOperacoes();
		this.funcionarioBD = new FuncionarioBancoOperacoes();
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void cadastrarPedido() throws SQLException {
		String nomeFuncionario = JOptionPane.showInputDialog(null, "Digite o nome do funcionário responsável");

		if (!funcionarioBD.validarFuncionarioNome(nomeFuncionario)) {
			JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
			return;
		}

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nomeFuncionario);

		String descricao = JOptionPane.showInputDialog(null, "Digite a descrição do pedido");
		while (descricao.length() < 1 || descricao.isEmpty() || descricao.isBlank()) {
			descricao = JOptionPane.showInputDialog(null, "Descrição inválida! Digite novamente");
		}

		double valor = 0.00;
		String valorX = JOptionPane.showInputDialog(null, "Digite o valor do pedido");
		try {
			valor = Double.parseDouble(valorX);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor inválido! Digite um número válido.");
			return;
		}

		double saldoAtual = funcionarioBD.getSaldoFuncionario(nomeFuncionario);
		double saldoAtualizado = saldoAtual - valor;
		funcionarioBD.atualizarSaldoFuncionario(nomeFuncionario, saldoAtualizado);

		LocalDate dataAtual = LocalDate.now();
		LocalTime horaAtual = LocalTime.now();

		pedidoBD.inserirPedidoBanco(funcionario.getNome(), descricao, valor, dataAtual, horaAtual);

		Pedido pedido = new Pedido(descricao, valor, dataAtual, horaAtual, funcionario);
		pedidos.add(pedido);

		JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso! Saldo atualizado: " + saldoAtualizado);
	}

	public void listarPedidos() throws SQLException {
		pedidoBD.listarPedidos();
	}
}
