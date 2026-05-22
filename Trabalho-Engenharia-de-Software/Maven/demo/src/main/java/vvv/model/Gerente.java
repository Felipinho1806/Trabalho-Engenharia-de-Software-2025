package vvv.model;

public class Gerente extends Funcionario {

    public Gerente(String nome, String email, String senha, double totalVendas) {
        super(nome, email, senha, totalVendas);
    }

    public void registrarVenda() {
        System.out.println("Venda registrada pelo gerente: " + getNome());
        // Lógica para armazenar a venda no banco pode ser implementada aqui
    }

    public void aprovarReserva() {
        System.out.println("Reserva aprovada pelo gerente: " + getNome());
        // Lógica para aprovar reservas no banco
    }

    public void registrarFuncionario() {
        System.out.println("Funcionário registrado pelo gerente: " + getNome());
        // Aqui pode ser inserida a lógica para adicionar funcionários ao sistema
    }

    public void cadastrarModais() {
        System.out.println("Modal cadastrado pelo gerente: " + getNome());
        // Código para cadastrar tipos de transporte ou outros modais
    }

    public void registrarLocais() {
        System.out.println("Local registrado pelo gerente: " + getNome());
        // Lógica para cadastrar locais na plataforma
    }
}
