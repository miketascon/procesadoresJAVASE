/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.procesadores.dao;

import com.procesadores.model.ProcesadorModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class ProcesadorDAOJDBCImpl implements ProcesadorDAO{
    
     private Connection conexion= null;

    public ProcesadorDAOJDBCImpl() {
        String url="jdbc:mysql://localhost:3306/procesadores?zeroDateTimeBehavior=convertToNull";
         String userName="root";
        String password="Persefone2014";
        try {
             conexion=DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no se pudo establecer conexion: " + e, "Conection lost", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    @Override
    public void add(ProcesadorModel procesador) {
         try (Statement stmt=conexion.createStatement()){
             String query = "INSERT INTO procesadores VALUES (" +procesador.getIdProcesador()
                     + ","+ "'" + procesador.getMarca() 
                     + "'," + "'" + procesador.getModelo() + "',"
                     + "'" + new java.sql.Date(procesador.getFechaVenta().getTime()) 
                     + "'," + procesador.getPrecio() +","+""+ procesador.getEntregado()+"" +")";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "No se ha creado el procesador" + e, "Error al agregar un procesador", JOptionPane.ERROR_MESSAGE);
        } 
        
        
        
    }

    @Override
    public void update(ProcesadorModel procesador) throws SQLException {
       
            try (Statement stmt = conexion.createStatement()) {
                String query = "UPDATE procesadores "
                        + "SET marca='" + procesador.getMarca() + "',"
                        + "modelo='" + procesador.getModelo() + "',"
                        + "fecha_venta='" + new java.sql.Date(procesador.getFechaVenta().getTime()) + "',"
                        + "precio=" + procesador.getPrecio()+ ","
                        + "entregado="+ procesador.getEntregado2()
                        + " WHERE id_procesador=" + procesador.getIdProcesador();
                if (stmt.executeUpdate(query) != 1) {
                    throw new SQLException("Error updating registro");
                }
            } catch (SQLException se) {
                throw new SQLException("Error updating procesador in DAO", se);
            }

    }

    @Override
    public void delete(int id) throws SQLException {
        ProcesadorModel procesador = findById(id);
        if (procesador == null) {
            throw new SQLException("Procesador id: " + id + " does not exist to delete.");
        }
        try (Statement stmt = conexion.createStatement()) {
            String query = "DELETE FROM procesadores WHERE id_procesador=" + id;
            
            if (stmt.executeUpdate(query) != 1) {
                throw new SQLException("Error deleting registro");
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new SQLException("Error deleting procesador in DAO", se);
        } 
    }

    @Override
    public ProcesadorModel findById(int id) throws SQLException {
        try (Statement stmt=conexion.createStatement()){
            String query = "SELECT * FROM procesadores WHERE id_procesador=" + id;
            ResultSet rs=stmt.executeQuery(query);
            if (rs.next()) {
               return new ProcesadorModel(rs.getInt("id_procesador"),
                       rs.getString("marca"),
                       rs.getString("modelo"),
                       rs.getDate("fecha_venta"),
                       rs.getFloat("precio"),
                       rs.getBoolean("entregado")
                        );
           }
            else{
                JOptionPane.showMessageDialog(null, "No encontrado", "ERROR", JOptionPane.ERROR_MESSAGE) ;
                return null;
            }
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "No se ha creado registro" + e, "Invalid Add", JOptionPane.ERROR_MESSAGE);
                        return null;
        } 
    }

    @Override
    public List<ProcesadorModel> getAllProcesador() throws SQLException {
        try (Statement stmt=conexion.createStatement()){
            String query="select * from procesadores";
            ResultSet rs=stmt.executeQuery(query);
            List<ProcesadorModel> lista= new ArrayList<>();
            while (rs.next()) { 
                lista.add(new ProcesadorModel(rs.getInt("id_procesador"),
                       rs.getString("marca"),
                       rs.getString("modelo"),
                       rs.getDate("fecha_venta"),
                       rs.getFloat("precio"),
                       rs.getBoolean("entregado")
                       
                ));
            }
            return lista;
            
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "No se ha creado registro" + e, "Invalid Add", JOptionPane.ERROR_MESSAGE);
                return null;
        }
    }

    @Override
    public void close() throws Exception {
        try {
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Se esta cerrando la conexión"
                    + e, "title", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
}
