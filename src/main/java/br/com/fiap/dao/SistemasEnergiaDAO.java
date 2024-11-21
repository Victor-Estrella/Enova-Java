package br.com.fiap.dao;

import br.com.fiap.to.SistemasEnergiaTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SistemasEnergiaDAO extends Repository{
    public ArrayList<SistemasEnergiaTO> findAll() {
        ArrayList<SistemasEnergiaTO> sistemas = new ArrayList<SistemasEnergiaTO>();
        String sql = "select * from T_ENOVA_SISTEMAS_ENERGIA order by id_energia";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()){
                    SistemasEnergiaTO sistema = new SistemasEnergiaTO();
                    sistema.setIdEnergia(rs.getLong("id_energia"));
                    sistema.setTxTipo(rs.getString("tx_tipo"));
                    sistema.setTxLocalizacao(rs.getString("tx_localizacao"));
                    sistema.setNrCapacidade(rs.getDouble("nr_capacidade"));
                    sistema.setDtInstalacao(rs.getDate("dt_instalacao").toLocalDate());
                    sistema.setStSistema(rs.getString("st_sistema"));
                    sistemas.add(sistema);
                }
            }
        }catch (SQLException e){
            System.out.println("Erro de sql: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return sistemas;
    }

    public SistemasEnergiaTO findByCodigo (Long idEnergia){
        SistemasEnergiaTO sistema = new SistemasEnergiaTO();
        String sql = "SELECT * from T_ENOVA_SISTEMAS_ENERGIA where id_energia = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1,idEnergia);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                sistema.setTxTipo(rs.getString("tx_tipo"));
                sistema.setTxLocalizacao(rs.getString("tx_localizacao"));
                sistema.setNrCapacidade(rs.getDouble("nr_capacidade"));
                sistema.setDtInstalacao(rs.getDate("dt_instalacao").toLocalDate());
                sistema.setStSistema(rs.getString("st_sistema"));
            }else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return sistema;
    }

    public SistemasEnergiaTO save(SistemasEnergiaTO sistema){
        String sql = "insert into T_ENOVA_SISTEMAS_ENERGIA(tx_tipo,tx_localizacao,nr_capacidade,dt_instalacao,st_sistema) values (?,?,?,?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, sistema.getTxTipo());
            ps.setString(2, sistema.getTxLocalizacao());
            ps.setDouble(3, sistema.getNrCapacidade());
            ps.setDate(4, Date.valueOf(sistema.getDtInstalacao()));
            ps.setString(5, sistema.getStSistema());
            if (ps.executeUpdate() > 0){
                return sistema;
            }
        } catch (SQLException e) {
            System.out.println("Erro de sql:" + e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete(Long idEnergia) {
        String sql = "delete from T_ENOVA_SISTEMAS_ENERGIA where id_energia = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idEnergia);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return false;
    }

    public SistemasEnergiaTO update(SistemasEnergiaTO sistema) {
        String sql = "update T_ENOVA_SISTEMAS_ENERGIA set tx_tipo=?, tx_localizacao=?, nr_capacidade=?, dt_instalacao=?, st_sistema=? where id_energia=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, sistema.getTxTipo());
            ps.setString(2, sistema.getTxLocalizacao());
            ps.setDouble(3, sistema.getNrCapacidade());
            ps.setDate(4, Date.valueOf(sistema.getDtInstalacao()));
            ps.setString(5, sistema.getStSistema());
            ps.setLong(6, sistema.getIdEnergia());
            if (ps.executeUpdate() > 0) {
                return sistema;
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
