package br.com.fatecpg.portal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseConnector {
    private static final String DRIVER = "org.postgresql.Driver";   
    private static final String URL = "jdbc:postgresql://ec2-54-243-228-140.compute-1.amazonaws.com:5432/de5c8m6o47mtg8?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";   
    private static final String USER = "ovpdqkmptbpvjy";
    private static final String PASS = "d1d019e3ac632dba6edccfc40b70411de24f2d3055883f53b70f8e02f00b5fe6";
   
    public static ArrayList<Object[]> getQuery(String SQL, Object[] parameters) throws Exception{
        ArrayList<Object[]> list = new ArrayList<>();
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement stmt = con.prepareStatement(SQL);
        for (int i = 0; i < parameters.length; i++) {
            stmt.setObject(i+1, parameters[i]);
        }
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Object row[] = new Object[rs.getMetaData().getColumnCount()];
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                row[i] = rs.getObject(i+1);
            }
            list.add(row);
        }
        rs.close(); stmt.close(); con.close();
        return list;
    }
    
    public static void execute (String SQL, Object[] parameters) throws Exception{
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        PreparedStatement stmt = con.prepareStatement(SQL);
        for (int i = 0; i < parameters.length; i++) {
            stmt.setObject(i+1, parameters[i]);
        }
        stmt.execute();
        stmt.close(); 
        con.close();
    }
}

