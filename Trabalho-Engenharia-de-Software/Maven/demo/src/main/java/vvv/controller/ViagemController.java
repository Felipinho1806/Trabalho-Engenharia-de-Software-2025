package vvv.controller;
import java.util.Date;
import vvv.model.Viagem;
import vvv.dao.ViagemDAO;
import java.util.List;

public class ViagemController {
    private ViagemDAO viagemDAO;

    public ViagemController() {
        this.viagemDAO = new ViagemDAO();
    }

    // Método para adicionar uma Viagem
    public void adicionarViagem(Date data, String horaPartida, String horaChegada) {
        Viagem viagem = new Viagem();
        viagem.setData(data);
        viagem.setHoraPartida(horaPartida);
        viagem.setHoraChegada(horaChegada);
        
        viagemDAO.adicionarViagem(viagem);
    }

    // Método para listar todas as Viagens
    public void listarViagens() {
        List<Viagem> viagens = viagemDAO.listarViagens();
        for (Viagem viagem : viagens) {
            System.out.println("Data: " + viagem.getData() +
                               ", Hora Partida: " + viagem.getHoraPartida() +
                               ", Hora Chegada: " + viagem.getHoraChegada());
        }
    }

    // Método para buscar uma viagem por data
    public void buscarViagemPorData(Date data) {
        Viagem viagem = viagemDAO.buscarViagem(data);
        if (viagem != null) {
            System.out.println("Viagem encontrada:");
            System.out.println("Data: " + viagem.getData() +
                               ", Hora Partida: " + viagem.getHoraPartida() +
                               ", Hora Chegada: " + viagem.getHoraChegada());
        } else {
            System.out.println("Nenhuma viagem encontrada para a data: " + data);
        }
    }

    // Método para excluir uma viagem pelo ID
    public void excluirViagemPorId(int id_viagem) {
        boolean sucesso = viagemDAO.excluirViagem(id_viagem);
        if (sucesso) {
            System.out.println("Viagem com ID " + id_viagem + " foi excluída com sucesso.");
        } else {
            System.out.println("Erro ao excluir viagem com ID " + id_viagem + ". Verifique se o ID existe.");
        }
    }
}