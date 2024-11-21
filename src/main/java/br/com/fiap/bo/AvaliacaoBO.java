package br.com.fiap.bo;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.exceptions.AvaliacaoNotFoundException;
import br.com.fiap.to.AvaliacaoTO;


import java.util.ArrayList;

public class AvaliacaoBO {
    private AvaliacaoDAO avaliacaoDAO;
    public ArrayList<AvaliacaoTO> findAll(){
        avaliacaoDAO = new AvaliacaoDAO();
        // aqui se implementa a regra de negócios
        return avaliacaoDAO.findAll();
    }

    public AvaliacaoTO findByCodigo(Long idAvaliacao){
        avaliacaoDAO = new AvaliacaoDAO();

        AvaliacaoTO avaliacao = avaliacaoDAO.findByCodigo(idAvaliacao);
        if (avaliacao == null) {
            throw new AvaliacaoNotFoundException("Avaliação não encontrada para o código fornecido: " + idAvaliacao);
        }
        return avaliacaoDAO.findByCodigo(idAvaliacao);
    }

    public AvaliacaoTO save(AvaliacaoTO avaliacao){
        avaliacaoDAO = new AvaliacaoDAO();

        return avaliacaoDAO.save(avaliacao);
    }

    public boolean delete(Long idAvaliacao) {
        avaliacaoDAO = new AvaliacaoDAO();
        AvaliacaoTO avaliacao = avaliacaoDAO.findByCodigo(idAvaliacao);
        if (avaliacao == null) {
            throw new AvaliacaoNotFoundException("Avaliação não encontrada para exclusão.");
        }
        return avaliacaoDAO.delete(idAvaliacao);
    }

    public AvaliacaoTO update(AvaliacaoTO avaliacao) {
        avaliacaoDAO = new AvaliacaoDAO();
        //aqui se implementa as regras de negocio
        return avaliacaoDAO.update(avaliacao);

    }


}
