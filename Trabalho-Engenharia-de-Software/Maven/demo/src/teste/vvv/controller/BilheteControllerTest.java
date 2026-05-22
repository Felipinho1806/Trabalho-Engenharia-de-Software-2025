package teste.vvv.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import vvv.dao.BilheteDAO;
import vvv.model.Bilhete;
import vvv.controller.BilheteController;;

@ExtendWith(MockitoExtension.class)
class BilheteControllerTest {

    @Mock
    private BilheteDAO bilheteDAO;

    @InjectMocks
    private BilheteController bilheteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarBilhete() {
        Bilhete bilheteMock = new Bilhete();
        doNothing().when(bilheteDAO).adicionarBilhete(any(Bilhete.class));
        
        bilheteController.adicionarBilhete(new Date());
        
        verify(bilheteDAO, times(1)).adicionarBilhete(any(Bilhete.class));
    }

    @Test
    void testListarBilhetes() {
        Bilhete bilhete1 = new Bilhete();
        Bilhete bilhete2 = new Bilhete();
        List<Bilhete> bilhetesMock = Arrays.asList(bilhete1, bilhete2);
        
        when(bilheteDAO.listarBilhetes()).thenReturn(bilhetesMock);
        
        bilheteController.listarBilhetes();
        
        verify(bilheteDAO, times(1)).listarBilhetes();
    }

    @Test
    void testBuscarBilhete_Existe() {
        Bilhete bilheteMock = new Bilhete();
        when(bilheteDAO.buscarBilhete(1)).thenReturn(bilheteMock);
        
        bilheteController.buscarBilhete(1);
        
        verify(bilheteDAO, times(1)).buscarBilhete(1);
    }

    @Test
    void testBuscarBilhete_NaoExiste() {
        when(bilheteDAO.buscarBilhete(999)).thenReturn(null);
        
        bilheteController.buscarBilhete(999);
        
        verify(bilheteDAO, times(1)).buscarBilhete(999);
    }

    @Test
    void testExcluirBilhetePorId_Sucesso() {
        when(bilheteDAO.excluirBilhete(1)).thenReturn(true);
        
        bilheteController.excluirBilhetePorId(1);
        
        verify(bilheteDAO, times(1)).excluirBilhete(1);
    }

    @Test
    void testExcluirBilhetePorId_Falha() {
        when(bilheteDAO.excluirBilhete(999)).thenReturn(false);
        
        bilheteController.excluirBilhetePorId(999);
        
        verify(bilheteDAO, times(1)).excluirBilhete(999);
    }
}

