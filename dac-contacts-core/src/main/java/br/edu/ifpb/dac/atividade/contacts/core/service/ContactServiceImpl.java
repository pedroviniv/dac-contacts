/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.contacts.core.service;

import br.edu.ifpb.dac.atividade.contacts.core.dao.ContactDAO;
import br.edu.ifpb.dac.atividade.contacts.shared.Contact;
import br.edu.ifpb.dac.atividade.contacts.shared.ContactService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Pedro Arthur
 */
@Stateless
@Remote(ContactService.class)
public class ContactServiceImpl implements ContactService {
    
    @EJB
    private ContactDAO contactDAO;

    @Override
    public void newContact(Contact contact) {
        contactDAO.persist(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactDAO.update(contact);
    }
    
    @Override
    public List<Contact> getContactsByFirstLetterOrderByName(Character letter) {
        return contactDAO.getContactsByFirstLetterOrderByName(letter);
    }
    
    @Override
    public List<Character> getAllFirstLettersAsc() {
        return contactDAO.getAllContactsFirstLettersAsc();
    }
    
    @Override
    public List<Contact> getContacsByName(String name) {
        return contactDAO.searchByName(name);
    }

    @Override
    public void removeContact(Contact contact) {
        contactDAO.delete(contact.getId());
    }
    
}
