package br.com.fiap.dao;

import java.sql.Connection;

public abstract class Repository {
    //classe abstrata é uma classe que não posso criar objetos
    protected Connection connection; //protected pode ser usada por todas as classes do mesmo pacote

    public Repository() {
        getConnection();
    }

    public Connection getConnection() {
        try{
            connection = ConnectionFactory.getInstance().getConexao();
            return connection;
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        return null;
    }

    public void closeConnection(){
        try{
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
