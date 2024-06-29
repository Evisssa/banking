package com.evisa.banking.services;

import java.util.List;

public interface AbstractService<T> {

    Integer save(T dto);
    List<T> findall();
    T findById(Integer id);
    void delete(Integer id);

}
