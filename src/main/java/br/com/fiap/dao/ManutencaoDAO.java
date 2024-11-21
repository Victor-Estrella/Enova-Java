package br.com.fiap.dao;

import br.com.fiap.to.ManutencaoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManutencaoDAO extends Repository {

    public ArrayList<ManutencaoTO> findAll() {
        ArrayList<ManutencaoTO> manutencoes = new ArrayList<>();
        String sql = "SELECT * FROM T_ENOVA_MANUTENCAO ORDER BY dt_manutencao";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ManutencaoTO manutencao = new ManutencaoTO();
                    manutencao.setIdManutencao(rs.getLong("id_manutencao"));
                    manutencao.setIdEnergia(rs.getLong("id_energia"));
                    manutencao.setDsManutencao(rs.getString("ds_manutencao"));
                    manutencao.setDtManutencao(rs.getDate("dt_manutencao").toLocalDate());
                    manutencao.setTpManutencao(rs.getString("tp_manutencao"));

                    manutencoes.add(manutencao);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return manutencoes;
    }

    public ManutencaoTO findByCodigo(Long idManutencao) {
        ManutencaoTO manutencao = new ManutencaoTO();
        String sql = "SELECT * FROM T_ENOVA_MANUTENCAO WHERE id_manutencao = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idManutencao);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                manutencao.setIdManutencao(rs.getLong("id_manutencao"));
                manutencao.setIdEnergia(rs.getLong("id_energia"));
                manutencao.setDsManutencao(rs.getString("ds_manutencao"));
                manutencao.setDtManutencao(rs.getDate("dt_manutencao").toLocalDate());
                manutencao.setTpManutencao(rs.getString("tp_manutencao"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return manutencao;
    }

    public ArrayList<ManutencaoTO> findByIdEnergia(Long idEnergia) {
        String sql = "SELECT * FROM t_enova_manutencao WHERE id_energia = ?";
        ArrayList<ManutencaoTO> manutencoes = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idEnergia);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ManutencaoTO manutencao = new ManutencaoTO();
                    manutencao.setIdManutencao(rs.getLong("id_manutencao"));
                    manutencao.setIdEnergia(rs.getLong("id_energia"));
                    manutencao.setDsManutencao(rs.getString("ds_manutencao"));
                    manutencao.setDtManutencao(rs.getDate("dt_manutencao").toLocalDate());
                    manutencao.setTpManutencao(rs.getString("tp_manutencao"));

                    manutencoes.add(manutencao);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar manutenções por ID de energia: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return manutencoes;
    }


    public ManutencaoTO save(ManutencaoTO manutencao, Long idEnergia) {
        String sql = "INSERT INTO T_ENOVA_MANUTENCAO (id_energia, dt_manutencao, tp_manutencao, ds_manutencao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idEnergia);
            ps.setDate(2, Date.valueOf(manutencao.getDtManutencao()));
            ps.setString(3, manutencao.getTpManutencao());
            ps.setString(4, manutencao.getDsManutencao());
            ps.executeUpdate();
            return manutencao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Long idManutencao) {
        String sql = "DELETE FROM T_ENOVA_MANUTENCAO WHERE id_manutencao = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idManutencao);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public ManutencaoTO update(ManutencaoTO manutencao) {
        String sql = "UPDATE T_ENOVA_MANUTENCAO SET ds_manutencao = ?, dt_manutencao = ?, tp_manutencao = ? WHERE id_manutencao = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, manutencao.getDsManutencao());
            ps.setDate(2, Date.valueOf(manutencao.getDtManutencao()));
            ps.setString(3, manutencao.getTpManutencao());
            ps.setLong(4, manutencao.getIdManutencao()); // Corrigido índice
            if (ps.executeUpdate() > 0) {
                return manutencao;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
}
