package vvv.model;

public class Cliente extends Usuario {
    private String cpf;
    private String telefone;

    public Cliente (String nome, String email, String senha, String cpf, String telefone) {
        super(nome, email, senha);
        this.cpf = cpf;  
        this.telefone = telefone;
    }
    public Cliente() {
        
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

   
}