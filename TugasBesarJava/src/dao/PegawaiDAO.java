/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DbConnection;
import java.sql.Connection;

/**
 *
 * @author M S I
 */
public class PegawaiDAO {
    private static final DbConnection DBC = new DbConnection();
    private Connection con;
    private String sql = "";
    private int test;

    public PegawaiDAO(int test) {
        this.test = test;
    }

    public int getTest(){
        return test;
    }
    
}
