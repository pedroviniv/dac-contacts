/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.atividade.contacts.webapp.producers;

import br.edu.ifpb.dac.atividade.contacts.shared.ContactService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Pedro Arthur
 */

@ApplicationScoped
public class ContactServiceProducer {
    
    private static final String RESOURCE = "java:global/dac-contacts-core/ContactServiceImpl!br.edu.ifpb.dac.atividade.contacts.shared.ContactService";
    
    @Produces
    @Dependent
    @Default
    private ContactService getContactService() {
        return (ContactService) new ServiceLocator().lookup(RESOURCE, ContactService.class);
    }
}
