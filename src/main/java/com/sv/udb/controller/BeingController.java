/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controller;

import com.sv.udb.model.Being;
import com.sv.udb.resourcess.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class BeingController {
    private final Connection conn;

    public BeingController() {
        this.conn = new ConnectionDB().getConn();
    }
    
    private Being getBeing (int id) {
        Being resp = null;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM seresvivos WHERE codi_sere = ?");
            cmd.setInt(1, id);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp = new Being(rs.getInt(1), rs.getString(2), rs.getString(3), this.getBeing(rs.getInt(4)));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar ser padre: " + ex.getMessage());
        } 
        return resp;
    }
    
    public List<Being> getAll () {
        List<Being> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM seresvivos");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Being(rs.getInt(1), rs.getString(2), rs.getString(3), this.getBeing(rs.getInt(4))));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar seres vivos: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión al consultar seres vivos: " + ex.getMessage());
            }
        }
        return resp;
    }
    
    public boolean save (String nomb_sere, String desc_sere, Being refe_sere) {
        boolean resp = false;
        
        try {
            
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO seresvivos VALUES(NULL, ?, ?, ?)");
            cmd.setString(1, nomb_sere);
            cmd.setString(2, desc_sere);
            if (refe_sere != null)
                cmd.setInt(3, refe_sere.getCodi_sere());
            else
                cmd.setNull(3, Types.INTEGER);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al guardar ser " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al guardar ser: " + e.getMessage());
            }
        }
        
        return resp;
    }
    
    public boolean update (int codi_sere, String nomb_sere, String desc_sere, Being refe_sere) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE seresvivos SET nomb_sere = ?, desc_sere = ?, codi_refe_sere = ? WHERE codi_sere = ?");
            cmd.setString(1, nomb_sere);
            cmd.setString(2, desc_sere);
            if (refe_sere != null)
                cmd.setInt(3, refe_sere.getCodi_sere());
            else
                cmd.setNull(3, Types.INTEGER);
            cmd.setInt(4, codi_sere);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al modificar ser " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al modificar ser : " + e.getMessage());
            }
        }
        
        return resp;
    }
    
    
    public boolean delete (int codi_sere) {
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM seresvivos WHERE codi_sere = ?");
            cmd.setInt(1, codi_sere);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al eliminar ser" + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion al eliminar ser: " + e.getMessage());
            }
        }
        
        return resp;
    }
    
}
