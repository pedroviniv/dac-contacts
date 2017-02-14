/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.contacts.shared;

import java.util.List;

/**
 *
 * @author Pedro Arthur
 */
public interface ContactService {
    
    void newContact(Contact contact);
    void updateContact(Contact contact);
    void removeContact(Contact contact);
    List<Contact> getContactsByFirstLetterOrderByName(Character firstLetter);
    List<Character> getAllFirstLettersAsc();
    List<Contact> getContacsByName(String name);
}
