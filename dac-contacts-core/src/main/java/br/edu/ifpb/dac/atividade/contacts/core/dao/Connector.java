/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.contacts.core.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author Pedro Arthur
 */
public class Connector implements Serializable {
    
    private static final String PROP_NAME = "db.properties";
    private static Properties prop = new Properties();
    private static PGPoolingDataSource dataSource = null;
    
    private static void initDataSource(){   
        
        try {
            prop.load(new FileInputStream(Connector.class.getClassLoader().getResource(PROP_NAME).toURI().getPath()));
            
            dataSource = new PGPoolingDataSource();
            dataSource.setDataSourceName("dac-contacts data-source");
           
            dataSource.setDatabaseName(prop.getProperty("database"));
            dataSource.setServerName(prop.getProperty("host"));
            dataSource.setPortNumber(Integer.valueOf(prop.getProperty("port")));
            dataSource.setUser(prop.getProperty("user"));
            dataSource.setPassword(prop.getProperty("password"));
            dataSource.setMaxConnections(10);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        if(dataSource == null) {
            initDataSource();
        } return dataSource.getConnection();
    }
    
    
}
