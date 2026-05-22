package teste.vvv.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import vvv.dao.ClienteDAO;
import vvv.model.Cliente;
import vvv.controller.ClienteController;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private ClienteDAO clienteDAO;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarCliente() {
        Cliente clienteMock = new Cliente();
        doNothing().when(clienteDAO).adicionarCliente(any(Cliente.class));
        
        clienteController.adicionarCliente("João", "joao@email.com", 123456789, "9999-9999");
        
        verify(clienteDAO, times(1)).adicionarCliente(any(Cliente.class));
    }

    @Test
    void testListarClientes() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        List<Cliente> clientesMock = Arrays.asList(cliente1, cliente2);
        
        when(clienteDAO.listarClientes()).thenReturn(clientesMock);
        
        clienteController.listarClientes();
        
        verify(clienteDAO, times(1)).listarClientes();
    }

    @Test
    void testBuscarClientePorEmail_ClienteExiste() {
        Cliente clienteMock = new Cliente();
        when(clienteDAO.buscarClientePorEmail("joao@email.com")).thenReturn(clienteMock);
        
        Cliente resultado = clienteController.buscarClientePorEmail("joao@email.com");
        
        assertNotNull(resultado);
        verify(clienteDAO, times(1)).buscarClientePorEmail("joao@email.com");
    }

    @Test
    void testBuscarClientePorEmail_ClienteNaoExiste() {
        when(clienteDAO.buscarClientePorEmail("naoexiste@email.com")).thenReturn(null);
        
        Cliente resultado = clienteController.buscarClientePorEmail("naoexiste@email.com");
        
        assertNull(resultado);
        verify(clienteDAO, times(1)).buscarClientePorEmail("naoexiste@email.com");
    }
}
