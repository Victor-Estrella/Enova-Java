package br.com.fiap.dao;

import br.com.fiap.to.AnaliseEficienciaTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnaliseEficienciaDAO extends Repository {

    public ArrayList<AnaliseEficienciaTO> findAll() {
        ArrayList<AnaliseEficienciaTO> analises = new ArrayList<>();
        String sql = "SELECT * FROM T_ENOVA_ANALISE_EFICIENCIA ORDER BY id_analise";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AnaliseEficienciaTO analise = new AnaliseEficienciaTO();
                analise.setIdAnalise(rs.getLong("id_analise"));
                analise.setIdEnergia(rs.getLong("id_energia"));
                analise.setDtAnalise(rs.getDate("dt_analise").toLocalDate());
                analise.setNrProducaoEnergia(rs.getDouble("nr_producao_energia"));
                analise.setNrConsumoEnergia(rs.getDouble("nr_consumo_energia"));
                analise.setNrEficiencia(rs.getDouble("nr_eficiencia"));
                analises.add(analise);
            }
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return analises;
    }

    public AnaliseEficienciaTO findByCodigo(Long idAnalise) {
        AnaliseEficienciaTO analise = new AnaliseEficienciaTO();
        String sql = "SELECT * FROM T_ENOVA_ANALISE_EFICIENCIA WHERE id_analise = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idAnalise);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                analise.setIdAnalise(rs.getLong("id_analise"));
                analise.setIdEnergia(rs.getLong("id_energia"));
                analise.setDtAnalise(rs.getDate("dt_analise").toLocalDate());
                analise.setNrProducaoEnergia(rs.getDouble("nr_producao_energia"));
                analise.setNrConsumoEnergia(rs.getDouble("nr_consumo_energia"));
                analise.setNrEficiencia(rs.getDouble("nr_eficiencia"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return analise;
    }

    public ArrayList<AnaliseEficienciaTO> findByIdEnergia(Long idEnergia) {
        String sql = "SELECT * FROM T_ENOVA_ANALISE_EFICIENCIA WHERE id_energia = ? ORDER BY dt_analise DESC";
        ArrayList<AnaliseEficienciaTO> analises = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idEnergia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AnaliseEficienciaTO analise = new AnaliseEficienciaTO();
                analise.setIdAnalise(rs.getLong("id_analise"));
                analise.setIdEnergia(rs.getLong("id_energia"));
                analise.setDtAnalise(rs.getDate("dt_analise").toLocalDate());
                analise.setNrProducaoEnergia(rs.getDouble("nr_producao_energia"));
                analise.setNrConsumoEnergia(rs.getDouble("nr_consumo_energia"));
                analise.setNrEficiencia(rs.getDouble("nr_eficiencia"));

                analises.add(analise);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar análises por ID de energia: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return analises;
    }


    public AnaliseEficienciaTO save(AnaliseEficienciaTO analise, Long idEnergia) {
        String sql = "INSERT INTO T_ENOVA_ANALISE_EFICIENCIA (id_energia, dt_analise, nr_producao_energia, nr_consumo_energia, nr_eficiencia) VALUES (?, SYSDATE, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idEnergia);
            ps.setDouble(2, analise.getNrProducaoEnergia());
            ps.setDouble(3, analise.getNrConsumoEnergia());
            ps.setDouble(4, analise.getNrEficiencia());
            ps.executeUpdate();

            return analise;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar no banco: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete(Long idAnalise) {
        String sql = "DELETE FROM T_ENOVA_ANALISE_EFICIENCIA WHERE id_analise = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, idAnalise);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public AnaliseEficienciaTO update(AnaliseEficienciaTO analise, Long idEnergia) {
        String sql = "UPDATE T_ENOVA_ANALISE_EFICIENCIA SET nr_producao_energia = ?, nr_consumo_energia = ?, nr_eficiencia = ?, dt_analise = SYSDATE WHERE id_analise = ? AND id_energia = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, analise.getNrProducaoEnergia());
            ps.setDouble(2, analise.getNrConsumoEnergia());
            ps.setDouble(3, analise.getNrEficiencia());
            ps.setLong(4, analise.getIdAnalise());
            ps.setLong(5, idEnergia);
            if (ps.executeUpdate() > 0) {
                return analise;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar análise de eficiência: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

}
