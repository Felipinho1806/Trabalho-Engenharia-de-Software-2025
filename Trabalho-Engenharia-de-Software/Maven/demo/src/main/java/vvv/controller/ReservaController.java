package vvv.controller;

import vvv.model.Reserva;
import vvv.dao.ReservaDAO;

import java.util.Date;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO();
    }

    // Método para adicionar uma Reserva
    public void adicionarReserva(double valor, Date data, String status) {
        Reserva reserva = new Reserva();
        reserva.setValor(valor);
        reserva.setData(data);
        reserva.setStatus(status);

        reservaDAO.adicionarReserva(reserva);
        System.out.println("Reserva adicionada com sucesso!");
    }

    // Método para buscar uma Reserva por ID
    public void buscarReserva(int id_reserva) {
        Reserva reserva = reservaDAO.buscarReserva(id_reserva);
        if (reserva != null) {
            System.out.println("Reserva encontrada:");
            System.out.println("Valor: " + reserva.getValor() +
                               ", Data: " + reserva.getData() +
                               ", Status: " + reserva.getStatus());
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    // Método para excluir uma Reserva por ID
    public void excluirReserva(int id_reserva) {
        boolean sucesso = reservaDAO.excluirReserva(id_reserva);
        if (sucesso) {
            System.out.println("Reserva excluída com sucesso!");
        } else {
            System.out.println("Erro ao excluir a reserva. ID não encontrado.");
        }
    }
}
