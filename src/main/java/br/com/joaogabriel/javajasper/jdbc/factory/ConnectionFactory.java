package br.com.joaogabriel.javajasper.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.joaogabriel.javajasper.jdbc.exception.ConnectionFactoryException;

public class ConnectionFactory {

	 private static ConnectionFactory instance = null;
	    private Connection connection = null;
	    private final Logger logger = Logger.getLogger(getClass().getName());
	    
	    private ConnectionFactory() {
	        createConnection();
	    }
	    
	    private void createConnection() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            this.connection = 
	                    DriverManager
	                            .getConnection("jdbc:mysql://127.0.0.1:3306/jaspercurso", 
	                                "root", "123456789");
	            
	        } catch(SQLException | ClassNotFoundException exception) {
	            logger.log(Level.SEVERE, "Fail to create Connection.");
	            throw new ConnectionFactoryException(exception.getMessage());
	        }
	    }
	    
	    public static synchronized ConnectionFactory getInstance() {
	        if (Objects.isNull(instance)) {
	            ConnectionFactory.instance = new ConnectionFactory();
	        }
	        return ConnectionFactory.instance;
	    }
	    
	    public Connection getConnection() {
	        return this.connection;
	    }
	
	
}
