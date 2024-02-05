package com.example.contactlist.service;

import com.example.contactlist.model.Contact;
import com.example.contactlist.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact save(Contact contact){
        return contactRepository.save(contact);
    }

    public Contact update(Contact contact){
        return contactRepository.update(contact);
    }

    public void deleteById(Long id){
        contactRepository.deleteById(id);
    }

}
