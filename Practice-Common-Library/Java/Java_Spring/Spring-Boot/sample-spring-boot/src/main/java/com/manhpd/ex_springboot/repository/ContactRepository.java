/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manhpd.ex_springboot.repository;

import com.manhpd.ex_springboot.domain.Contact;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author manh.phanduc
 */
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findByNameContaining(String q);
}