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

import vvv.dao.LocalDAO;
import vvv.controller.LocalController;
import vvv.model.Local;

@ExtendWith(MockitoExtension.class)
class LocalControllerTest {

    @Mock
    private LocalDAO localDAO;

    @InjectMocks
    private LocalController localController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarLocal() {
        Local localMock = new Local();
        doNothing().when(localDAO).adicionarLocal(any(Local.class));
        
        localController.adicionarLocal("Praça Central", "Rua A, 123");
        
        verify(localDAO, times(1)).adicionarLocal(any(Local.class));
    }

    @Test
    void testListarLocais() {
        Local local1 = new Local();
        Local local2 = new Local();
        List<Local> locaisMock = Arrays.asList(local1, local2);
        
        when(localDAO.listarLocais()).thenReturn(locaisMock);
        
        localController.listarLocais();
        
        verify(localDAO, times(1)).listarLocais();
    }

    @Test
    void testBuscarLocalPorNome_Existe() {
        Local localMock = new Local();
        when(localDAO.buscarLocal("Praça Central")).thenReturn(localMock);
        
        Local resultado = localController.buscarLocalPorNome("Praça Central");
        
        assertNotNull(resultado);
        verify(localDAO, times(1)).buscarLocal("Praça Central");
    }

    @Test
    void testBuscarLocalPorNome_NaoExiste() {
        when(localDAO.buscarLocal("Inexistente")).thenReturn(null);
        
        Local resultado = localController.buscarLocalPorNome("Inexistente");
        
        assertNull(resultado);
        verify(localDAO, times(1)).buscarLocal("Inexistente");
    }

    @Test
    void testExcluirLocal_Sucesso() {
        when(localDAO.excluirLocal("Praça Central")).thenReturn(true);
        
        localController.excluirLocal("Praça Central");
        
        verify(localDAO, times(1)).excluirLocal("Praça Central");
    }

    @Test
    void testExcluirLocal_Falha() {
        when(localDAO.excluirLocal("Inexistente")).thenReturn(false);
        
        localController.excluirLocal("Inexistente");
        
        verify(localDAO, times(1)).excluirLocal("Inexistente");
    }
}
