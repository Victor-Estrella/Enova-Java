package br.com.fiap.dao;

import br.com.fiap.to.UsuariosTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuariosDAO extends Repository{

    public ArrayList<UsuariosTO> findAll() {
        ArrayList<UsuariosTO> usuarios = new ArrayList<UsuariosTO>();
        String sql = "select * from T_ENOVA_USUARIOS order by id_usuario";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()){
                    UsuariosTO usuario = new UsuariosTO();
                    usuario.setIdUsuario(rs.getLong("id_usuario"));
                    usuario.setNmUsuario(rs.getString("nm_usuario"));
                    usuario.setTxEmail(rs.getString("tx_email"));
                    usuario.setTxSenha(rs.getString("tx_senha"));
                    usuario.setDtCadastro(rs.getDate("dt_cadastro").toLocalDate());
                    usuarios.add(usuario);
                }
            }
        }catch (SQLException e){
            System.out.println("Erro de sql: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return usuarios;
    }

    public UsuariosTO findByCodigo (Long idUsuario){
        UsuariosTO usuario = new UsuariosTO();
        String sql = "SELECT * from T_ENOVA_USUARIOS where id_usuario = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario.setNmUsuario(rs.getString("nm_usuario"));
                usuario.setTxEmail(rs.getString("tx_email"));
                usuario.setTxSenha(rs.getString("tx_senha"));
                usuario.setDtCadastro(rs.getDate("dt_cadastro").toLocalDate());
            }else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return usuario;
    }

    public UsuariosTO save(UsuariosTO usuario){
        String sql = "insert into T_ENOVA_USUARIOS(nm_usuario,tx_email,tx_senha,dt_cadastro) values (?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, usuario.getNmUsuario());
            ps.setString(2, usuario.getTxEmail());
            ps.setString(3, usuario.getTxSenha());
            ps.setDate(4, Date.valueOf(usuario.getDtCadastro()));
            if (ps.executeUpdate() > 0){
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro de sql:" + e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete(Long idUsuario) {
        String sql = "delete from T_ENOVA_USUARIOS where id_usuario = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return false;
    }

    public UsuariosTO update(UsuariosTO usuario) {
        String sql = "update T_ENOVA_USUARIOS set nm_usuario=?, tx_email=?, tx_senha=?, dt_cadastro=? where id_usuario=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getNmUsuario());
            ps.setString(2, usuario.getTxEmail());
            ps.setString(3, usuario.getTxSenha());
            ps.setDate(4, Date.valueOf(usuario.getDtCadastro()));
            ps.setLong(5, usuario.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
}
