package teste.vvv.controller;

import vvv.controller.UsuarioController;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import vvv.dao.UsuarioDAO;
import vvv.model.Usuario;
import vvv.controller.UsuarioController;

class UsuarioControllerTest {

    @ExtendWith(MockitoExtension.class)

    @Mock
    private UsuarioDAO usuarioDAO; // Mock do DAO para evitar interações reais com o banco

    @InjectMocks
    private UsuarioController usuarioController; // Classe que será testada

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks do Mockito
    }

    @Test
    void testLogar_ComCredenciaisCorretas_DeveRetornarTrue() {
        // Simulando um usuário existente no banco
        Usuario usuarioMock = new Usuario("João", "joao@email.com", "123456");
        when(usuarioDAO.buscarUsuario("joao@email.com")).thenReturn(usuarioMock);

        // Testa a função de login
        boolean resultado = usuarioController.logar("joao@email.com", "123456");

        // Verificações
        assertTrue(resultado);
        assertEquals(usuarioMock, usuarioController.getUsuarioLogado());
    }

    @Test
    void testLogar_ComSenhaErrada_DeveRetornarFalse() {
        // Simula um usuário com senha diferente
        Usuario usuarioMock = new Usuario("João", "joao@email.com", "654321");
        when(usuarioDAO.buscarUsuario("joao@email.com")).thenReturn(usuarioMock);

        // Testa login com senha incorreta
        boolean resultado = usuarioController.logar("joao@email.com", "123456");

        // Verificações
        assertFalse(resultado);
        assertNull(usuarioController.getUsuarioLogado());
    }

    @Test
    void testLogar_ComUsuarioInexistente_DeveRetornarFalse() {
        when(usuarioDAO.buscarUsuario("inexistente@email.com")).thenReturn(null);

        boolean resultado = usuarioController.logar("inexistente@email.com", "123456");

        assertFalse(resultado);
        assertNull(usuarioController.getUsuarioLogado());
    }

    @Test
    void testCadastrarCliente_Sucesso_DeveRetornarTrue() {
        when(usuarioDAO.inserirUsuario(any(Usuario.class))).thenReturn(true);

        boolean resultado = usuarioController.cadastrarCliente("Maria", "maria@email.com", "senha123");

        assertTrue(resultado);
    }

    @Test
    void testCadastrarCliente_ErroNoBanco_DeveRetornarFalse() {
        when(usuarioDAO.inserirUsuario(any(Usuario.class))).thenReturn(false);

        boolean resultado = usuarioController.cadastrarCliente("Maria", "maria@email.com", "senha123");

        assertFalse(resultado);
    }

    @Test
    void testSolicitarReserva_SemUsuarioLogado_DeveMostrarMensagem() {
        usuarioController.solicitarReserva("Paris", "2025-06-10");

        // Como não há retorno, apenas garantimos que o usuário não está logado
        assertNull(usuarioController.getUsuarioLogado());
    }

    @Test
    void testExcluirUsuarioPorId_Sucesso() {
        when(usuarioDAO.excluirUsuario(1)).thenReturn(true);

        usuarioController.excluirUsuarioPorId(1);

        verify(usuarioDAO, times(1)).excluirUsuario(1);
    }

    @Test
    void testExcluirUsuarioPorId_Falha() {
        when(usuarioDAO.excluirUsuario(1)).thenReturn(false);

        usuarioController.excluirUsuarioPorId(1);

        verify(usuarioDAO, times(1)).excluirUsuario(1);
    }

    @Test
    void testDeslogar_DeveDefinirUsuarioLogadoComoNulo() {
        // Simula um usuário logado
        Usuario usuarioMock = new Usuario("João", "joao@email.com", "123456");
        when(usuarioDAO.buscarUsuario("joao@email.com")).thenReturn(usuarioMock);

        usuarioController.logar("joao@email.com", "123456");
        usuarioController.deslogar();

        assertNull(usuarioController.getUsuarioLogado());
    }
}

