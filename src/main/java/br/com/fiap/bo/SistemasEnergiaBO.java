package br.com.fiap.bo;

import br.com.fiap.dao.SistemasEnergiaDAO;
import br.com.fiap.to.SistemasEnergiaTO;
import br.com.fiap.exceptions.*;

import java.util.ArrayList;

public class SistemasEnergiaBO {
    private SistemasEnergiaDAO sistemasEnergiaDAO;

    public ArrayList<SistemasEnergiaTO> findAll() {
        sistemasEnergiaDAO = new SistemasEnergiaDAO();
        // Aqui se implementa a regra de negócios
        return sistemasEnergiaDAO.findAll();
    }

    public SistemasEnergiaTO findByCodigo(Long idEnergia) {
        sistemasEnergiaDAO = new SistemasEnergiaDAO();

        // Validar o ID
        if (idEnergia == null || idEnergia <= 0) {
            throw new SistemaEnergiaInvalidIdException("ID de sistema de energia inválido.");
        }

        SistemasEnergiaTO sistema = sistemasEnergiaDAO.findByCodigo(idEnergia);
        if (sistema == null) {
            throw new SistemaEnergiaNotFoundException("Sistema de energia não encontrado.");
        }
        return sistema;
    }

    public SistemasEnergiaTO save(SistemasEnergiaTO sistema) {
        sistemasEnergiaDAO = new SistemasEnergiaDAO();

        try {
            return sistemasEnergiaDAO.save(sistema);
        } catch (Exception e) {
            throw new SistemaEnergiaSaveException("Erro ao salvar o sistema de energia: " + e.getMessage());
        }
    }

    public boolean delete(Long idEnergia) {
        sistemasEnergiaDAO = new SistemasEnergiaDAO();

        try {
            if (idEnergia == null || idEnergia <= 0) {
                throw new SistemaEnergiaInvalidIdException("ID de sistema de energia inválido para exclusão.");
            }
            // Aqui se implementa a regra de negócios para exclusão
            return sistemasEnergiaDAO.delete(idEnergia);
        } catch (Exception e) {
            throw new SistemaEnergiaDeleteException("Erro ao excluir o sistema de energia: " + e.getMessage());
        }
    }

    public SistemasEnergiaTO update(SistemasEnergiaTO sistema) {
        sistemasEnergiaDAO = new SistemasEnergiaDAO();

        // Validar se o sistema existe
        try {
            SistemasEnergiaTO sistemaExistente = findByCodigo(sistema.getIdEnergia());
            if (sistemaExistente == null) {
                throw new SistemaEnergiaNotFoundException("Sistema de energia não encontrado para atualização.");
            }
            return sistemasEnergiaDAO.update(sistema);
        } catch (SistemaEnergiaNotFoundException e) {
            throw e;  // Repassando a exceção encontrada
        } catch (Exception e) {
            throw new SistemaEnergiaUpdateException("Erro ao atualizar o sistema de energia: " + e.getMessage());
        }
    }
}
