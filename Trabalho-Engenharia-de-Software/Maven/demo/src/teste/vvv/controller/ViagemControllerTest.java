package vvv.controller;

import vvv.model.Viagem;
import vvv.dao.ViagemDAO;
import vvv.controller.ViagemController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class ViagemControllerTest {

    private ViagemController viagemController;
    private ViagemDAO viagemDAO;

    @BeforeEach
    public void setUp() {
        // Configuração inicial antes de cada teste
        viagemController = new ViagemController();
        viagemDAO = new ViagemDAO();
    }

    @Test
    public void testAdicionarViagem() {
        Date data = new Date();
        String horaPartida = "08:00";
        String horaChegada = "12:00";

        // Adiciona a viagem
        viagemController.adicionarViagem(data, horaPartida, horaChegada);

        // Verifica se a viagem foi adicionada corretamente
        Viagem viagem = viagemDAO.buscarViagem(data);  // Assume que a data é única para a viagem
        assertNotNull(viagem, "Viagem não encontrada!");
        assertEquals(horaPartida, viagem.getHoraPartida(), "Hora de partida está incorreta.");
        assertEquals(horaChegada, viagem.getHoraChegada(), "Hora de chegada está incorreta.");
    }

    @Test
    public void testListarViagens() {
        // Lista todas as viagens
        viagemController.listarViagens();

        // Verifica se a lista não está vazia
        List<Viagem> viagens = viagemDAO.listarViagens();
        assertFalse(viagens.isEmpty(), "Nenhuma viagem encontrada.");
    }

    @Test
    public void testBuscarViagemPorData() {
        Date data = new Date(); // Supondo que já exista uma viagem com essa data
        viagemController.buscarViagemPorData(data);

        Viagem viagem = viagemDAO.buscarViagem(data);
        assertNotNull(viagem, "Viagem não encontrada para a data fornecida.");
        assertEquals(data, viagem.getData(), "Data da viagem está incorreta.");
    }

    @Test
    public void testExcluirViagemPorId() {
        int idViagem = 1;  // Supondo que já existe uma viagem com ID 1

        // Exclui a viagem
        viagemController.excluirViagemPorId(idViagem);

        // Verifica se a viagem foi excluída
        Viagem viagem = viagemDAO.buscarViagem(idViagem);
        assertNull(viagem, "A viagem não foi excluída corretamente.");
    }
}
