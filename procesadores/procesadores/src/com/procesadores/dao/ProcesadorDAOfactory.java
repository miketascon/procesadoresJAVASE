

package com.procesadores.dao;

import com.procesadores.dao.ProcesadorDAO;


public class ProcesadorDAOfactory {
    public ProcesadorDAO createProcesadorDAO(){
        return new ProcesadorDAOJDBCImpl();
    } 
}
