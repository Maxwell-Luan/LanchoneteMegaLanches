package entities;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexaobd.UsuarioBancoOperacoes;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private UsuarioBancoOperacoes userBD = null;

    ArrayList<Usuario> usuarios = new ArrayList<>();

    public Usuario() {
        this.userBD = new UsuarioBancoOperacoes();
    }

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.userBD = new UsuarioBancoOperacoes();
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.userBD = new UsuarioBancoOperacoes();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    // Permitir login do usuário no sistema.
    public boolean logarUsuario() throws SQLException {
        String nome = JOptionPane.showInputDialog(null, "Digite seu nome");
        String senha = JOptionPane.showInputDialog(null, "Digite sua senha");

        boolean encontradoBanco = userBD.validarLoginUsuarioBanco(nome, senha);

        if (encontradoBanco) {
            JOptionPane.showMessageDialog(null, "Bem-vindo a Mega Lanches!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Acesso negado! Usuário ou senha inválidos.");
            return false;
        }
    }

    // Realizar o cadastro de um usuario.
    public void cadastrarUsuario() throws SQLException {
        String nome = JOptionPane.showInputDialog(null, "Digite seu nome");
        while (nome.length() < 1 || nome.isEmpty() || nome.isBlank()) {
            nome = JOptionPane.showInputDialog(null, "Nome inválido! Digite novamente");
        }
        String email = JOptionPane.showInputDialog(null, "Digite seu e-mail");
        while (email.length() < 1 || email.isEmpty() || email.isBlank()) {
            email = JOptionPane.showInputDialog(null, "E-mail inválido! Digite novamente");
        }
        String senha = JOptionPane.showInputDialog(null, "Digite sua senha");
        while (senha.length() < 1 || senha.isEmpty() || senha.isBlank()) {
            senha = JOptionPane.showInputDialog(null, "Senha inválida! Digite novamente");
        }

        if (userBD.validarUsuarioBancoNome(nome) == true) {
            JOptionPane.showMessageDialog(null,
                    "O nome digitado já foi escolhido por outro usuário. Por favor, defina um novo.");
        } else if (userBD.validarUsuarioBancoEmail(email) == true) {
            JOptionPane.showMessageDialog(null, "O email digitado já está em uso. Por favor, selecione outro email.");
        } else {
            userBD.inserirUsuarioBanco(nome, email, senha);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        }
    }
    
    public void alterarUsuario() throws HeadlessException, SQLException {
        String nome = JOptionPane.showInputDialog(null, "Qual usuário deseja alterar o nome?");
        if (userBD.validarUsuarioBancoNome(nome) == false) {
            JOptionPane.showMessageDialog(null,
                    "Usuário não encontrado!");
        } else {
            String novoNome = JOptionPane.showInputDialog(null, "Altere o nome: ");
            userBD.alterarUsuarioBanco(nome, novoNome);
        }
    }

}
