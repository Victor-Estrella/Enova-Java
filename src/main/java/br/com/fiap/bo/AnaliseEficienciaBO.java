package br.com.fiap.bo;

import br.com.fiap.dao.AnaliseEficienciaDAO;
import br.com.fiap.exceptions.AnaliseNotFoundException;
import br.com.fiap.exceptions.AnaliseValidationException;
import br.com.fiap.to.AnaliseEficienciaTO;

import java.util.ArrayList;

public class AnaliseEficienciaBO {
    private AnaliseEficienciaDAO analiseEficienciaDAO;

    public ArrayList<AnaliseEficienciaTO> findAll() {
        analiseEficienciaDAO = new AnaliseEficienciaDAO();
        return analiseEficienciaDAO.findAll();
    }

    public AnaliseEficienciaTO findByCodigo(Long idAnalise) {
        analiseEficienciaDAO = new AnaliseEficienciaDAO();

        AnaliseEficienciaTO analise = analiseEficienciaDAO.findByCodigo(idAnalise);
        if (analise == null) {
            throw new AnaliseNotFoundException("Análise de eficiência não encontrada para o código: " + idAnalise);
        }

        return analise;
    }

    public ArrayList<AnaliseEficienciaTO> findByIdEnergia(Long idEnergia) {
        AnaliseEficienciaDAO dao = new AnaliseEficienciaDAO();
        return dao.findByIdEnergia(idEnergia);
    }


    public AnaliseEficienciaTO save(AnaliseEficienciaTO analise, Long idEnergia) throws AnaliseValidationException {
        analiseEficienciaDAO = new AnaliseEficienciaDAO();

        // Validação
        if (analise.getNrProducaoEnergia() <= 0 || analise.getNrConsumoEnergia() <= 0) {
            throw new AnaliseValidationException("Produção e consumo de energia devem ser maiores que zero.");
        }

        // Salvar no DAO
        return analiseEficienciaDAO.save(analise, idEnergia); // Incluindo idEnergia
    }


    public boolean delete(Long idAnalise) {
        analiseEficienciaDAO = new AnaliseEficienciaDAO();

        AnaliseEficienciaTO analise = analiseEficienciaDAO.findByCodigo(idAnalise);
        if (analise == null) {
            throw new AnaliseNotFoundException("Análise de eficiência não encontrada para exclusão.");
        }

        return analiseEficienciaDAO.delete(idAnalise);
    }

    public AnaliseEficienciaTO update(AnaliseEficienciaTO analise) {
        analiseEficienciaDAO = new AnaliseEficienciaDAO();

        // Validar existência
        AnaliseEficienciaTO analiseExistente = analiseEficienciaDAO.findByCodigo(analise.getIdAnalise());
        if (analiseExistente == null) {
            throw new AnaliseNotFoundException("Análise de eficiência não encontrada para atualização.");
        }

        return analiseEficienciaDAO.update(analise, analise.getIdEnergia());
    }


    public String gerarResumoAnalise(AnaliseEficienciaTO analise) {
        if (analise.getNrEficiencia() == null) {
            throw new AnaliseValidationException("Eficiência ainda não calculada. Não é possível gerar resumo.");
        }

        return analise.gerarResumoAnalise();
    }
}
