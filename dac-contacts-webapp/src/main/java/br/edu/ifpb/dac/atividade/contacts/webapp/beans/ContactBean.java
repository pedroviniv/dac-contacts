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
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pedro Arthur
 */
@Named
@ConversationScoped
public class ContactBean implements Serializable {
    
    private final static String REDIRECT="index?faces-redirect=true";
    
    @Inject
    private Conversation conversation;
    
    @Inject
    private ContactService contactService;
    
    private List<Contact> contacts; 
    private String searchArgument = "";
    
    private Contact contact = new Contact();
    private boolean editing = false;
    
    @PostConstruct
    private void postConstruct() {
        System.out.println("Construindo ContactBean...");
        this.conversation.begin();
        this.contacts = contactService.getContacsByName(searchArgument);
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

    public void setEditing(boolean Editing) {
        this.editing = Editing;
    }
    
    public String editContact(Contact contact) {
        System.out.println("Preparing to EDIT: "+contact.getFirstname()+" "+contact.getLastname());
        this.contact = contact;
        this.editing = true;
        
        return null;
    }
    
    public String preparingToSave() {
        this.contact = new Contact();
        System.out.println("Preparing to SAVE new contact... ");
        this.editing = false;
        
        return null;
    }
    
    public String saveChanges() {
        
        System.out.println("SAVING CHANGES: "+contact.getFirstname()+" "+contact.getLastname());
        this.contactService.updateContact(contact);
        
        this.conversation.end();
        
        return REDIRECT;
    }
    
    public String deleteContact(Contact contact) {
        this.contactService.removeContact(contact);
        
        this.conversation.end();

        return REDIRECT;
    }
    
    public String persistContact() {
        System.out.println("PERSISTING: "+contact.getFirstname()+" "+contact.getLastname());
        this.contactService.newContact(contact);
        
        this.conversation.end();
        
        return REDIRECT;
    }
    
    public String orderContactsByName() {
        this.contacts = this.contactService.getContactsOrderByName();
        return null;
    }
    
    public String filterContactsByName() {
        this.contacts = this.contactService.getContacsByName(searchArgument);
        return null;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    public String getSearchArgument() {
        return searchArgument;
    }

    public void setSearchArgument(String searchArgument) {
        this.searchArgument = searchArgument;
    }
}
