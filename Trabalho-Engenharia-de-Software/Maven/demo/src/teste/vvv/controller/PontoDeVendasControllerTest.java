package vvv.controller;

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

import vvv.dao.PontoDeVendasDAO;
import vvv.dao.UsuarioDAO;
import vvv.model.PontoDeVendas;
import vvv.controller.PontoDeVendasController;

@ExtendWith(MockitoExtension.class)
class PontoDeVendasControllerTest {

    @Mock
    private PontoDeVendasDAO pontoDeVendasDAO;
    
    @Mock
    private UsuarioDAO usuarioDAO;

    @InjectMocks
    private PontoDeVendasController pontoDeVendasController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarPontoDeVendas() {
        PontoDeVendas pontoMock = new PontoDeVendas();
        doNothing().when(pontoDeVendasDAO).adicionarPontoDeVendas(any(PontoDeVendas.class));
        
        pontoDeVendasController.adicionarPontoDeVendas("Loja Centro", "Rua A, 123", "9999-9999");
        
        verify(pontoDeVendasDAO, times(1)).adicionarPontoDeVendas(any(PontoDeVendas.class));
    }

    @Test
    void testListarPontosDeVendas() {
        PontoDeVendas ponto1 = new PontoDeVendas();
        PontoDeVendas ponto2 = new PontoDeVendas();
        List<PontoDeVendas> pontosMock = Arrays.asList(ponto1, ponto2);
        
        when(pontoDeVendasDAO.listarPontosDeVendas()).thenReturn(pontosMock);
        
        pontoDeVendasController.listarPontosDeVendas();
        
        verify(pontoDeVendasDAO, times(1)).listarPontosDeVendas();
    }

    @Test
    void testBuscarPontoDeVendas_Existe() {
        PontoDeVendas pontoMock = new PontoDeVendas();
        when(pontoDeVendasDAO.buscarPontoDeVendas("Loja Centro")).thenReturn(pontoMock);
        
        pontoDeVendasController.buscarPontoDeVendas("Loja Centro");
        
        verify(pontoDeVendasDAO, times(1)).buscarPontoDeVendas("Loja Centro");
    }

    @Test
    void testBuscarPontoDeVendas_NaoExiste() {
        when(pontoDeVendasDAO.buscarPontoDeVendas("Loja Inexistente")).thenReturn(null);
        
        pontoDeVendasController.buscarPontoDeVendas("Loja Inexistente");
        
        verify(pontoDeVendasDAO, times(1)).buscarPontoDeVendas("Loja Inexistente");
    }

    @Test
    void testExcluirUsuario() {
        when(usuarioDAO.excluirUsuario(1)).thenReturn(true);
        
        pontoDeVendasController.excluirUsuario(1);
        
        verify(usuarioDAO, times(1)).excluirUsuario(1);
    }
}
