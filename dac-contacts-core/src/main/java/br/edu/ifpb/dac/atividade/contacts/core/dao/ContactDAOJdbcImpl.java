/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.contacts.core.dao;

import br.edu.ifpb.dac.atividade.contacts.shared.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 *
 * @author Pedro Arthur
 */
@Stateless
@Local(ContactDAO.class)
public class ContactDAOJdbcImpl implements ContactDAO {

    @Override
    public void persist(Contact obj) {
        try {      
            Connection conn = Connector.getConnection();
            
            String sql = "INSERT INTO contact(firstname,lastname,email,phone)"
                    + " VALUES(?,?,?,?)";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            int i = 1;
            
            pstm.setString(i++, obj.getFirstname());
            pstm.setString(i++, obj.getLastname());
            pstm.setString(i++, obj.getEmail());
            pstm.setString(i++, obj.getPhone());
            
            pstm.executeUpdate();
            
            pstm.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAOJdbcImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Long id) {
        try {      
            Connection conn = Connector.getConnection();
            
            String sql = "DELETE FROM contact WHERE id = ?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            int i = 1;
            
            pstm.setLong(i++, id);
            
            pstm.executeUpdate();
            
            pstm.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAOJdbcImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Contact obj) {
        try {      
            Connection conn = Connector.getConnection();
            
            String sql = "UPDATE contact SET firstname = ?, lastname = ?, email = ?, phone = ?"
                    + " WHERE id = ?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            int i = 1;
            
            pstm.setString(i++, obj.getFirstname());
            pstm.setString(i++, obj.getLastname());
            pstm.setString(i++, obj.getEmail());
            pstm.setString(i++, obj.getPhone());
            
            pstm.setLong(i++, obj.getId());
            
            pstm.executeUpdate();
            
            pstm.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAOJdbcImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Contact> listOrderByName() {
        
        List<Contact> result = new ArrayList<>();
        
        try {      
            Connection conn = Connector.getConnection();
            
            String sql = "SELECT * FROM contact ORDER BY firstname || lastname ASC";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                result.add(createContact(rs));
            }
            
            pstm.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAOJdbcImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public List<Contact> searchByName(String argument) {
        List<Contact> result = new ArrayList<>();
        
        try {      
            Connection conn = Connector.getConnection();
            
            String sql = "SELECT * FROM contact WHERE firstname || ' ' || lastname ilike ?";  
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            int i = 1;
            
            pstm.setString(i, argument.concat("%"));
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()) {
                result.add(createContact(rs));
            }
            
            pstm.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAOJdbcImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    private Contact createContact(ResultSet rs) throws SQLException {
        
        Contact contact = new Contact();
        contact.setFirstname(rs.getString("firstname"));
        contact.setLastname(rs.getString("lastname"));
        contact.setEmail(rs.getString("email"));
        contact.setPhone(rs.getString("phone"));
        contact.setId(rs.getLong("id"));
        
        return contact;
    }
    
}
