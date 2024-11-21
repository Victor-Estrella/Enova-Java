package br.com.fiap.bo;

import br.com.fiap.dao.ManutencaoDAO;
import br.com.fiap.exceptions.*;
import br.com.fiap.to.ManutencaoTO;

import java.util.ArrayList;

public class ManutencaoBO {
    private ManutencaoDAO manutencaoDAO;

    public ManutencaoBO() {
        this.manutencaoDAO = new ManutencaoDAO();
    }

    public ArrayList<ManutencaoTO> findAll() {
        manutencaoDAO = new ManutencaoDAO();
        return manutencaoDAO.findAll();
    }

    public ManutencaoTO findByCodigo(Long idManutencao) throws ManutencaoNotFoundException, ManutencaoInvalidIdException {
        manutencaoDAO = new ManutencaoDAO();

        // Validar o ID
        if (idManutencao == null || idManutencao <= 0) {
            throw new ManutencaoInvalidIdException("ID de manutenção inválido.");
        }

        ManutencaoTO manutencao = manutencaoDAO.findByCodigo(idManutencao);
        if (manutencao == null) {
            throw new ManutencaoNotFoundException("Manutenção não encontrada.");
        }
        return manutencao;
    }

    public ArrayList<ManutencaoTO> findByIdEnergia(Long idEnergia) {
        return manutencaoDAO.findByIdEnergia(idEnergia);
    }


    public ManutencaoTO save(ManutencaoTO manutencao, Long idEnergia) throws ManutencaoSaveException {
        manutencaoDAO = new ManutencaoDAO();

        // Validação
        if (manutencao.getDsManutencao() == null || manutencao.getDsManutencao().trim().isEmpty()) {
            throw new ManutencaoSaveException("A descrição da manutenção não pode estar vazia.");
        }

        // Salvar no DAO
        return manutencaoDAO.save(manutencao, idEnergia);
    }

    public boolean delete(Long idManutencao) throws ManutencaoDeleteException {
        manutencaoDAO = new ManutencaoDAO();

        try {
            ManutencaoTO manutencao = findByCodigo(idManutencao);
            if (manutencao == null) {
                throw new ManutencaoNotFoundException("Manutenção não encontrada para exclusão.");
            }
            return manutencaoDAO.delete(idManutencao);
        } catch (Exception e) {
            throw new ManutencaoDeleteException("Erro ao excluir a manutenção: " + e.getMessage());
        }
    }

    public ManutencaoTO update(ManutencaoTO manutencao) throws ManutencaoUpdateException {
        manutencaoDAO = new ManutencaoDAO();

        try {
            // Verificar se a manutenção existe
            ManutencaoTO manutencaoExistente = findByCodigo(manutencao.getIdManutencao());
            if (manutencaoExistente == null) {
                throw new ManutencaoNotFoundException("Manutenção não encontrada para atualização.");
            }
            return manutencaoDAO.update(manutencao);
        } catch (Exception e) {
            throw new ManutencaoUpdateException("Erro ao atualizar a manutenção: " + e.getMessage());
        }
    }
}
