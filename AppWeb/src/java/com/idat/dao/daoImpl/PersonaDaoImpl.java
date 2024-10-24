package com.idat.dao.daoImpl;

import com.idat.dao.IPersonadao; // Fixed the interface name

import com.idat.entity.Persona;
import com.idat.repository.ConexionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoImpl implements IPersonadao {

    @Override
    public int operacioneaEscritura(String indicador, Persona p) {

        Connection con = null;
        CallableStatement cst = null;
        int procesar = -1;
        String procedimiento = "{ call usp_persona_crud (?, ?, ?, ?) }";

        try {
            con = new ConexionMysql().conectar();
            cst = con.prepareCall(procedimiento);
            cst.setString(1, indicador);
            cst.setInt(2, p.getCodigo());
            cst.setString(3, p.getNombre());
            cst.setString(4, p.getApellido());
            procesar = cst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("operacionEscritura - error: " + ex.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return procesar;

    }

    @Override
    public List<Persona> opracionesLectura(String indicador, Persona p) {

        Connection con = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        List<Persona> lista = new ArrayList<>();
        String procedimiento = "{ call  usp_persona_crud (?, ?, ?, ?) }";

        try {
            con = new ConexionMysql().conectar();
            cst = con.prepareCall(procedimiento);
            cst.setString(1, indicador);
            cst.setInt(2, p.getCodigo());
            cst.setString(3, p.getNombre());
            cst.setString(4, p.getApellido());
            rs = cst.executeQuery();
            Persona objPersona;
            while (rs.next()) {
                objPersona = new Persona();
                objPersona.setCodigo(rs.getInt(1));
                objPersona.setNombre(rs.getString(2));
                objPersona.setApellido(rs.getString(3));
                lista.add(objPersona);
            }
        } catch (SQLException ex) {
            System.out.println("operacionesLectura - error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
        return lista;

    }
}
