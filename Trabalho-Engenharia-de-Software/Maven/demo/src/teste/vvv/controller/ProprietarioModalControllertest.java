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

import vvv.dao.ProprietarioModalDAO;
import vvv.model.ProprietarioModal;
import vvv.controller.ProprietarioModalController;

@ExtendWith(MockitoExtension.class)
class ProprietarioModalControllerTest {

    @Mock
    private ProprietarioModalDAO proprietarioModalDAO;

    @InjectMocks
    private ProprietarioModalController proprietarioModalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarProprietarioModal() {
        ProprietarioModal proprietarioMock = new ProprietarioModal();
        doNothing().when(proprietarioModalDAO).adicionarProprietarioModal(any(ProprietarioModal.class));
        
        proprietarioModalController.adicionarProprietarioModal("email@email.com", "9999-9999");
        
        verify(proprietarioModalDAO, times(1)).adicionarProprietarioModal(any(ProprietarioModal.class));
    }

    @Test
    void testListarProprietariosModal() {
        ProprietarioModal proprietario1 = new ProprietarioModal();
        ProprietarioModal proprietario2 = new ProprietarioModal();
        List<ProprietarioModal> proprietariosMock = Arrays.asList(proprietario1, proprietario2);
        
        when(proprietarioModalDAO.listarProprietarios()).thenReturn(proprietariosMock);
        
        proprietarioModalController.listarProprietariosModal();
        
        verify(proprietarioModalDAO, times(1)).listarProprietarios();
    }

    @Test
    void testBuscarProprietario_Existe() {
        ProprietarioModal proprietarioMock = new ProprietarioModal();
        when(proprietarioModalDAO.buscarProprietario("email@email.com")).thenReturn(proprietarioMock);
        
        proprietarioModalController.buscarProprietario("email@email.com");
        
        verify(proprietarioModalDAO, times(1)).buscarProprietario("email@email.com");
    }

    @Test
    void testBuscarProprietario_NaoExiste() {
        when(proprietarioModalDAO.buscarProprietario("naoexiste@email.com")).thenReturn(null);
        
        proprietarioModalController.buscarProprietario("naoexiste@email.com");
        
        verify(proprietarioModalDAO, times(1)).buscarProprietario("naoexiste@email.com");
    }
    
    @Test
    void testExcluirProprietario() {
        when(proprietarioModalDAO.excluirProprietario("email@email.com")).thenReturn(true);
        
        proprietarioModalController.excluirProprietario("email@email.com");
        
        verify(proprietarioModalDAO, times(1)).excluirProprietario("email@email.com");
    }
}
