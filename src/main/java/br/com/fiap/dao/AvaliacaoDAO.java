package br.com.fiap.dao;

import br.com.fiap.to.AvaliacaoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AvaliacaoDAO extends Repository{
    public ArrayList<AvaliacaoTO> findAll() {
        ArrayList<AvaliacaoTO> avaliacoes = new ArrayList<AvaliacaoTO>();
        String sql = "select * from T_ENOVA_AVALIACAO order by id_avaliacao";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()){
                    AvaliacaoTO avaliacao = new AvaliacaoTO();
                    avaliacao.setIdAvaliacao(rs.getLong("id_avaliacao"));
                    avaliacao.setNmUsuario(rs.getString("nm_usuario"));
                    avaliacao.setTxEmail(rs.getString("tx_email"));
                    avaliacao.setTxMensagem(rs.getString("tx_mensagem"));
                    avaliacao.setNrAvaliacao(rs.getInt("nr_avaliacao"));
                    avaliacoes.add(avaliacao);
                }
            }
        }catch (SQLException e){
            System.out.println("Erro de sql: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return avaliacoes;
    }

    public AvaliacaoTO findByCodigo (Long idAvaliacao){
        AvaliacaoTO avaliacao = new AvaliacaoTO();
        String sql = "SELECT * from T_ENOVA_AVALIACAO where id_avaliacao = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1,idAvaliacao);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                avaliacao.setNmUsuario(rs.getString("nm_usuario"));
                avaliacao.setTxEmail(rs.getString("tx_email"));
                avaliacao.setTxMensagem(rs.getString("tx_mensagem"));
                avaliacao.setNrAvaliacao(rs.getInt("nr_avaliacao"));
            }else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return avaliacao;
    }

    public AvaliacaoTO save(AvaliacaoTO avaliacao){
        String sql = "insert into T_ENOVA_AVALIACAO(nm_usuario,tx_email,tx_mensagem,nr_avaliacao) values (?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, avaliacao.getNmUsuario());
            ps.setString(2, avaliacao.getTxEmail());
            ps.setString(3, avaliacao.getTxMensagem());
            ps.setInt(4, avaliacao.getNrAvaliacao());
            if (ps.executeUpdate() > 0){
                return avaliacao;
            }
        } catch (SQLException e) {
            System.out.println("Erro de sql:" + e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete(Long idAvaliacao) {
        String sql = "delete from T_ENOVA_AVALIACAO where id_avaliacao = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idAvaliacao);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return false;
    }

    public AvaliacaoTO update(AvaliacaoTO avaliacao) {
        String sql = "update T_ENOVA_AVALIACAO set nm_usuario=?, tx_email=?, tx_mensagem=?, nr_avaliacao=? where id_avaliacao=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, avaliacao.getNmUsuario());
            ps.setString(2, avaliacao.getTxEmail());
            ps.setString(3, avaliacao.getTxMensagem());
            ps.setInt(4, avaliacao.getNrAvaliacao());
            ps.setLong(5, avaliacao.getIdAvaliacao());
            if (ps.executeUpdate() > 0) {
                return avaliacao;
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
