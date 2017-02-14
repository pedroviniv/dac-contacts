/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.contacts.webapp.beans;

import br.edu.ifpb.dac.atividade.contacts.shared.Contact;
import br.edu.ifpb.dac.atividade.contacts.shared.ContactService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pedro Arthur
 */
@Named
@SessionScoped
public class ContactBean implements Serializable {
    
    @Inject
    private ContactService contactService;
    
    private List<Contact> contacts; 
    private String searchArgument = "";
    
    private List<Character> characters;
    private Character activePage;

    private Contact contact = new Contact();
    
    private boolean editing = false;
    private boolean searching = false;
    
    @PostConstruct
    private void postConstruct() {
        this.activePage = Character.MIN_VALUE;
        loadContacts();
    }
    
    public String getContactsByFirstLetterOrderByName(Character firstLetter) {
        this.activePage = firstLetter;
        loadContactsPaged();
        this.characters = contactService.getAllFirstLettersAsc();
        return null;
    }
    
    public String editContact(Contact contact) {
        
        this.contact = contact;
        this.editing = true;
        
        return null;
    }
    
    public String saveChanges() { 
        this.contactService.updateContact(contact);
        loadContacts();
        
        return null;
    }
    
    public String preparingToSave() {
        this.contact = new Contact();
        this.editing = false;
        
        return null;
    }
    
    public String persistContact() {
        this.contactService.newContact(contact);
        loadContacts();
        
        return null;
    }
    
    public String deleteContact(Contact contact) {
        this.contactService.removeContact(contact);
        loadContacts();
        
        return null;
    }
    
    public String searchContactsByName() {
        filterContactsByName(searchArgument);
        return null;
    }
    
    private void filterContactsByName(String name) {
        this.contacts = this.contactService.getContacsByName(name);
        this.activePage = Character.MIN_VALUE;
    }
    
    // AUXILIAR
    private void loadContacts() {
        if(this.activePage == Character.MIN_VALUE) 
            filterContactsByName(searchArgument);
        else 
            loadContactsPaged();
            
        this.characters = contactService.getAllFirstLettersAsc();
    }
    
    private void loadContactsPaged() {
        this.contacts = contactService.getContactsByFirstLetterOrderByName(activePage);
    }

    public List<Contact> getContacts() {
        return contacts;
    }
    
    public String getSearchArgument() {
        return searchArgument;
    }

    public void setSearchArgument(String searchArgument) {
        this.searchArgument = searchArgument;
    }

    public Character getActivePage() {
        return activePage;
    }

    public void setActivePage(Character activePage) {
        this.activePage = activePage;
    }
    
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isEditing() {
        return editing;
    }

    public boolean isSearching() {
        return searching;
    }

    public void setSearching(boolean searching) {
        this.searching = searching;
    }
    
    public List<Character> getCharacters() {
        return this.characters;
    }
}
