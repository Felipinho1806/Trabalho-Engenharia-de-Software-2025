package teste.vvv.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import vvv.dao.FuncionarioDAO;
import vvv.model.Funcionario;
import vvv.controller.FuncionarioController;

@ExtendWith(MockitoExtension.class)
class FuncionarioControllerTest {

    @Mock
    private FuncionarioDAO funcionarioDAO;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogar_ComCredenciaisCorretas_DeveRetornarTrue() {
        Funcionario funcionarioMock = new Funcionario("Carlos", "carlos@email.com", "123456", 0);
        when(funcionarioDAO.buscarFuncionarioPorEmail("carlos@email.com")).thenReturn(funcionarioMock);

        boolean resultado = funcionarioController.logar("carlos@email.com", "123456");

        assertTrue(resultado);
        assertEquals(funcionarioMock, funcionarioController.getFuncionarioLogado());
    }

    @Test
    void testLogar_ComSenhaErrada_DeveRetornarFalse() {
        Funcionario funcionarioMock = new Funcionario("Carlos", "carlos@email.com", "senhaErrada", 0);
        when(funcionarioDAO.buscarFuncionarioPorEmail("carlos@email.com")).thenReturn(funcionarioMock);

        boolean resultado = funcionarioController.logar("carlos@email.com", "123456");

        assertFalse(resultado);
        assertNull(funcionarioController.getFuncionarioLogado());
    }

    @Test
    void testDeslogar_DeveDefinirFuncionarioLogadoComoNulo() {
        Funcionario funcionarioMock = new Funcionario("Carlos", "carlos@email.com", "123456", 0);
        funcionarioController.logar("carlos@email.com", "123456");
        funcionarioController.deslogar();

        assertNull(funcionarioController.getFuncionarioLogado());
    }

    @Test
    void testCadastrarFuncionario_Sucesso_DeveRetornarTrue() {
        when(funcionarioDAO.inserirFuncionario(any(Funcionario.class))).thenReturn(true);

        boolean resultado = funcionarioController.cadastrarFuncionario("Ana", "ana@email.com", "senha123");

        assertTrue(resultado);
    }

    @Test
    void testCadastrarFuncionario_ErroNoBanco_DeveRetornarFalse() {
        when(funcionarioDAO.inserirFuncionario(any(Funcionario.class))).thenReturn(false);

        boolean resultado = funcionarioController.cadastrarFuncionario("Ana", "ana@email.com", "senha123");

        assertFalse(resultado);
    }

    @Test
    void testRealizarVenda_SemFuncionarioLogado_DeveMostrarMensagem() {
        funcionarioController.realizarVenda();
        assertNull(funcionarioController.getFuncionarioLogado());
    }

    @Test
    void testConsultarReserva_SemFuncionarioLogado_DeveMostrarMensagem() {
        funcionarioController.consultarReserva();
        assertNull(funcionarioController.getFuncionarioLogado());
    }
}

