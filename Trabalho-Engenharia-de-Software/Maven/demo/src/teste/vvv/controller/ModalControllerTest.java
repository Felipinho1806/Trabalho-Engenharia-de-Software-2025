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

import vvv.dao.ModalDAO;
import vvv.model.Modal;
import vvv.controller.ModalController;

@ExtendWith(MockitoExtension.class)
class ModalControllerTest {

    @Mock
    private ModalDAO modalDAO;

    @InjectMocks
    private ModalController modalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarModal() {
        Modal modalMock = new Modal();
        doNothing().when(modalDAO).adicionarModal(any(Modal.class));
        
        modalController.adicionarModal(1, 50, "Ônibus", "Executivo", "Mercedes");
        
        verify(modalDAO, times(1)).adicionarModal(any(Modal.class));
    }

    @Test
    void testListarModais() {
        Modal modal1 = new Modal();
        Modal modal2 = new Modal();
        List<Modal> modaisMock = Arrays.asList(modal1, modal2);
        
        when(modalDAO.listarModais()).thenReturn(modaisMock);
        
        modalController.listarModais();
        
        verify(modalDAO, times(1)).listarModais();
    }

    @Test
    void testBuscarModal_ModalExiste() {
        Modal modalMock = new Modal();
        when(modalDAO.buscarModal(1)).thenReturn(modalMock);
        
        modalController.buscarModal(1);
        
        verify(modalDAO, times(1)).buscarModal(1);
    }

    @Test
    void testBuscarModal_ModalNaoExiste() {
        when(modalDAO.buscarModal(999)).thenReturn(null);
        
        modalController.buscarModal(999);
        
        verify(modalDAO, times(1)).buscarModal(999);
    }

    @Test
    void testExcluirModal_Sucesso() {
        when(modalDAO.excluirModal(1)).thenReturn(true);
        
        modalController.excluirModal(1);
        
        verify(modalDAO, times(1)).excluirModal(1);
    }

    @Test
    void testExcluirModal_Falha() {
        when(modalDAO.excluirModal(1)).thenReturn(false);
        
        modalController.excluirModal(1);
        
        verify(modalDAO, times(1)).excluirModal(1);
    }
}
