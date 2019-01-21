/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.ex_springboot.service;

import com.manhpd.ex_springboot.domain.Contact;
import java.util.List;

/**
 *
 * @author manh.phanduc
 */
public interface ContactService {
    Iterable<Contact> findAll();
    
    List<Contact> search(String q);
    
    Contact findOne(int id);
    
    void save(Contact contact);
    
    void delete(int id);
}
