

package com.procesadores.controller;


import com.procesadores.dao.ProcesadorDAO;
import com.procesadores.dao.ProcesadorDAOfactory;
import com.procesadores.model.ProcesadorModel;
import java.util.List;

import java.io.IOException;
import javax.swing.JOptionPane;

public class ProcesadorController {
    private static final ProcesadorController controller = new ProcesadorController();
    private int id;
    private ProcesadorModel procesadorActual;
    private List<ProcesadorModel> listaProcesador=null;
    private ProcesadorDAOfactory fabrica;

    public ProcesadorController() {
        fabrica=new ProcesadorDAOfactory();
    }

    public static ProcesadorController getController() {
        return controller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProcesadorModel getProcesadorActual() {
        return procesadorActual;
    }

    public void setProcesadorActual(ProcesadorModel procesadorActual) {
        this.procesadorActual = procesadorActual;
    }

    public List<ProcesadorModel> getListaProcesador() {
        if (listaProcesador == null) {
            try (ProcesadorDAO dao = fabrica.createProcesadorDAO();) {
                listaProcesador = dao.getAllProcesador();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "message" + e.getMessage(), "Error" + e.getClass().getName()
                        + ",quiting", JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "message" + e.getMessage(), "Error closing resource" + e.getClass().getName()
                        + ",quiting", JOptionPane.ERROR_MESSAGE
                );
            }

        }
        return listaProcesador;
    }

    
    
    
    public void setListaProcesador(List<ProcesadorModel> listaProcesador) {
        this.listaProcesador = listaProcesador;
    }

    public ProcesadorDAOfactory getFabrica() {
        return fabrica;
    }

    public void setFabrica(ProcesadorDAOfactory fabrica) {
        this.fabrica = fabrica;
    }
    
    public void recargarListaProcesador(){
        listaProcesador=null;
        
    }
    
    
    
       public boolean addProcesador() {
        try (ProcesadorDAO dao = fabrica.createProcesadorDAO();) {
            dao.add(procesadorActual);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "message" + e.getMessage(), "Error" + e.getClass().getName()
                    + ",quiting", JOptionPane.ERROR_MESSAGE
            );
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "message" + e.getMessage(), "Error closing resource" + e.getClass().getName()
                    + ",quiting", JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

    }
       
        public boolean updateProcesador() {
        try (ProcesadorDAO dao = fabrica.createProcesadorDAO();) {
            dao.update(procesadorActual);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "message" + e.getMessage(), "Error" + e.getClass().getName()
                    + ",quiting", JOptionPane.ERROR_MESSAGE
            );
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "message" + e.getMessage(), "Error closing resource" + e.getClass().getName()
                    + ",quiting", JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

    }
    
        public boolean deleteProcesador(int id) {
        this.id = id;
        try (ProcesadorDAO dao = fabrica.createProcesadorDAO();) {
            dao.delete(id);
            return true;
        } catch (IOException e) {
            System.out.println("Error " + e.getClass().getName() + " , quiting.");
            System.out.println("Message: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error closing resource " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
            return false;
        }

    }
}
