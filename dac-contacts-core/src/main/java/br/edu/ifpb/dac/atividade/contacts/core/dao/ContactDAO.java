/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.contacts.core.dao;

import br.edu.ifpb.dac.atividade.contacts.shared.Contact;
import java.util.List;

/**
 *
 * @author Pedro Arthur
 */
public interface ContactDAO {
    
    void persist(Contact contact);
    void delete(Long id);
    void update(Contact contact);
    List<Contact> listOrderByName();
    List<Contact> searchByName(String argument);
    
}
