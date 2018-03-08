/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.model;

/**
 *
 * @author Estudiante
 */
public class Being {
    private int codi_sere;
    private String nomb_sere;
    private String desc_sere;
    private Being refe_sere;

    public Being() {
    }

    public Being(int codi_sere, String nomb_sere, String desc_sere, Being refe_sere) {
        this.codi_sere = codi_sere;
        this.nomb_sere = nomb_sere;
        this.desc_sere = desc_sere;
        this.refe_sere = refe_sere;
    }

    public int getCodi_sere() {
        return codi_sere;
    }

    public void setCodi_sere(int codi_sere) {
        this.codi_sere = codi_sere;
    }

    public String getNomb_sere() {
        return nomb_sere;
    }

    public void setNomb_sere(String nomb_sere) {
        this.nomb_sere = nomb_sere;
    }

    public String getDesc_sere() {
        return desc_sere;
    }

    public void setDesc_sere(String desc_sere) {
        this.desc_sere = desc_sere;
    }

    public Being getRefe_sere() {
        return refe_sere;
    }

    public void setRefe_sere(Being refe_sere) {
        this.refe_sere = refe_sere;
    }
    

    @Override
    public String toString() {
        return nomb_sere;
    }
    
    
}
