package vvv.controller;

import vvv.model.Reserva;
import vvv.dao.ReservaDAO;
import vvv.controller.ReservaController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class ReservaControllerTest {

    private ReservaController reservaController;
    private ReservaDAO reservaDAO;

    @BeforeEach
    public void setUp() {
        // Configuração inicial antes de cada teste
        reservaController = new ReservaController();
        reservaDAO = new ReservaDAO();
    }

    @Test
    public void testAdicionarReserva() {
        double valor = 150.0;
        Date data = new Date();
        String status = "Confirmada";

        // Adiciona a reserva
        reservaController.adicionarReserva(valor, data, status);

        // Verifica se a reserva foi adicionada corretamente no banco (simulado por DAO)
        Reserva reserva = reservaDAO.buscarReserva(1);  // Assume que o ID gerado é 1
        assertNotNull(reserva, "Reserva não encontrada!");
        assertEquals(valor, reserva.getValor(), "Valor da reserva está incorreto.");
        assertEquals(status, reserva.getStatus(), "Status da reserva está incorreto.");
    }

    @Test
    public void testBuscarReserva() {
        int idReserva = 1;  // Supondo que já existe uma reserva com ID 1
        Reserva reserva = reservaDAO.buscarReserva(idReserva);

        assertNotNull(reserva, "Reserva não encontrada.");
        assertEquals(idReserva, reserva.getId(), "ID da reserva está incorreto.");
    }

    @Test
    public void testExcluirReserva() {
        int idReserva = 1;  // Supondo que já existe uma reserva com ID 1

        // Exclui a reserva
        reservaController.excluirReserva(idReserva);

        // Verifica se a reserva foi excluída
        Reserva reserva = reservaDAO.buscarReserva(idReserva);
        assertNull(reserva, "A reserva não foi excluída corretamente.");
    }
}
