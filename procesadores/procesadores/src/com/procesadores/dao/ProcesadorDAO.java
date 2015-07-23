/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.procesadores.dao;


import com.procesadores.model.ProcesadorModel;
import java.sql.SQLException;
import java.util.List;

public interface ProcesadorDAO extends AutoCloseable{
    
    public void add(ProcesadorModel procesador);
    
    public void update(ProcesadorModel procesador) throws SQLException;

    public void delete(int id) throws SQLException;

    public ProcesadorModel findById(int id) throws SQLException;

    public List<ProcesadorModel> getAllProcesador() throws SQLException;
    
}
