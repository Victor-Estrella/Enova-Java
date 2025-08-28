package br.com.fiap.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection conexao;
    private String url;
    private String user;
    private String pass;
    private String driver;


    public ConnectionFactory(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

    public static ConnectionFactory getInstance() {
        ConnectionFactory result = instance;
        if (result != null) {
            return result;
        }
        Properties prop = new Properties();
        boolean loaded = false;
        try {
            // Tenta carregar do classpath
            java.io.InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("application.properties");
            if (input != null) {
                prop.load(input);
                loaded = true;
                input.close();
            }
            // Se não achou, tenta pelo caminho absoluto (Docker)
            if (!loaded) {
                java.io.File file = new java.io.File("/app/application.properties");
                if (file.exists()) {
                    java.io.FileInputStream fis = new java.io.FileInputStream(file);
                    prop.load(fis);
                    loaded = true;
                    fis.close();
                }
            }
            if (!loaded) {
                throw new FileNotFoundException("application.properties não encontrado no classpath nem em /app");
            }
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String pass = prop.getProperty("db.password");
            String driver = prop.getProperty("db.driver");
            if (instance == null) {
                instance = new ConnectionFactory(url, user, pass, driver);
            }
            return instance;
        } catch (FileNotFoundException e) {
            System.out.println("Erro (FileNotFoundException): " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro (IOException): " + e.getMessage());
        }
        return null;
    }


    public Connection getConexao() {
        try {
            if (this.conexao != null && !this.conexao.isClosed()) {
                return this.conexao;
            }
            if (this.getDriver() == null || this.getDriver().isEmpty()) {
                throw new ClassNotFoundException("Nome da classe nulo ou em branco");
            }
            if (this.getUrl() == null || this.getUrl().isEmpty()) {
                throw new SQLException("URL de conexão nulo ou em branco");
            }
            if (this.getUser() == null || this.getUser().isEmpty()) {
                throw new SQLException("Usuário de conexão nulo ou em branco");
            }
            Class.forName(this.getDriver());
            this.conexao = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPass());
        } catch (SQLException e) {
            System.out.println("Erro de sql: " + e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro nome da classe: " + e.getMessage());
            System.exit(1);
        }
        return conexao;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }
}
