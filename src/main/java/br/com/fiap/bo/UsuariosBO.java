package br.com.fiap.bo;

import br.com.fiap.dao.UsuariosDAO;
import br.com.fiap.to.UsuariosTO;
import br.com.fiap.exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuariosBO {
    private UsuariosDAO usuariosDAO;

    public UsuariosBO() {
        this.usuariosDAO = new UsuariosDAO();  // Inicializa a instância de usuariosDAO
    }

    public ArrayList<UsuariosTO> findAll() {
        usuariosDAO = new UsuariosDAO();
        // Aqui se implementa a regra de negócios
        return usuariosDAO.findAll();
    }

    public UsuariosTO findByCodigo(Long idUsuario) {
        usuariosDAO = new UsuariosDAO();
        UsuariosTO usuario = usuariosDAO.findByCodigo(idUsuario);

        if (usuario == null) {
            throw new UsuarioNotFoundException("Usuário não encontrado.");
        }
        return usuario;
    }

    public UsuariosTO save(UsuariosTO usuario) {
        validarUsuario(usuario); // Valida os dados do usuário

        usuario.setDtCadastro(LocalDate.now()); // Define a data de cadastro automaticamente

        try {
            return usuariosDAO.save(usuario);
        } catch (Exception e) {
            throw new UsuarioSaveException("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    public boolean delete(Long idUsuario) {
        usuariosDAO = new UsuariosDAO();

        try {
            if (idUsuario == null || idUsuario <= 0) {
                throw new UsuarioNotFoundException("ID de usuário inválido.");
            }
            return usuariosDAO.delete(idUsuario);
        } catch (Exception e) {
            throw new UsuarioDeleteException("Erro ao excluir o usuário: " + e.getMessage());
        }
    }

    public UsuariosTO update(UsuariosTO usuario) {
        usuariosDAO = new UsuariosDAO();
        validarUsuario(usuario); // Valida os dados do usuário


        try {
            return usuariosDAO.update(usuario);
        } catch (Exception e) {
            throw new UsuarioUpdateException("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }

    public boolean isEmailValido(Long idUsuario) {
        usuariosDAO = new UsuariosDAO();
        UsuariosTO usuario = usuariosDAO.findByCodigo(idUsuario);

        if (usuario == null) {
            throw new UsuarioNotFoundException("Usuário não encontrado.");
        }

        return usuario.isEmailValido();
    }

    private void validarUsuario(UsuariosTO usuario) {
        if (usuario == null) {
            throw new UsuarioInvalidDataException("Usuário não pode ser nulo.");
        }

        if (usuario.getNmUsuario() == null || usuario.getNmUsuario().trim().isEmpty()) {
            throw new UsuarioInvalidDataException("O nome do usuário é obrigatório.");
        }

        if (usuario.getTxEmail() == null || usuario.getTxEmail().trim().isEmpty()) {
            throw new UsuarioInvalidDataException("O email do usuário é obrigatório.");
        }

        if (!usuario.isEmailValido()) {
            throw new UsuarioEmailInvalidException("O email do usuário é inválido.");
        }

        if (usuario.getTxSenha() == null || usuario.getTxSenha().trim().isEmpty()) {
            throw new UsuarioInvalidDataException("A senha do usuário é obrigatória.");
        }
    }

}
