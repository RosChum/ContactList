package com.example.contactlist.util;

import com.example.contactlist.model.Contact;
import com.example.contactlist.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Listener {

    private final ContactRepository contactRepository;

//    @EventListener(ApplicationReadyEvent.class)
    public void addContacts() {

        for (int i = 0; i < 10; i++) {

            Contact contact = new Contact();
            contact.setId(System.currentTimeMillis());
            contact.setEmail(String.valueOf(System.currentTimeMillis()));
            contact.setPhone(String.valueOf(System.currentTimeMillis()));
            contact.setFirstName(String.valueOf(System.currentTimeMillis()) + (i * 150));
            contact.setLastName(String.valueOf(System.currentTimeMillis()) + (i * 6) );
            contactRepository.save(contact);

        }

    }

}
