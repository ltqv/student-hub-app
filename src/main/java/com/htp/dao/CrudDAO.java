/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htp.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author htphu
 * @param <T>
 * @param <ID>
 */
public interface CrudDAO<T, ID> {
    Optional<T> create(T entity); 
    void update(T entity); 
    void deleteById(ID id); 
    List<T> findAll(); 
    Optional<T> findById(ID id);
}
