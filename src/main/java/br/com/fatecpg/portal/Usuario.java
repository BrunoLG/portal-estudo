package br.com.fatecpg.portal;

import java.util.ArrayList;

public class Usuario {
    private int cod;
    private String nome;
    private String usuario;
    private int senha;
    private String curso;
    private String permissao;

    public Usuario(int cod, String nome, String usuario, int senha, String curso, String permissao) {
        this.cod = cod;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.curso = curso;
        this.permissao = permissao;
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public static Usuario getUsuario(String login, String senha) throws Exception {
        String SQL = "SELECT CD_USUARIO, NM_USUARIO, NM_LOGIN, CD_SENHA, " +
                        " (SELECT C.NM_CURSO FROM TB_CURSO C " +
                            " WHERE U.CD_CURSO = C.CD_CURSO), " +
                        " (SELECT P.NM_PERMISSAO FROM TB_PERMISSAO P " +
                            "WHERE U.CD_PERMISSAO = P.CD_PERMISSAO) " +
                    " FROM TB_USUARIO U WHERE NM_LOGIN = ? AND CD_SENHA = ? ";
        Object parameters[] = {login, senha.hashCode()};
        
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, parameters);
        if (list.isEmpty()){
            return null;
        } else {
            Object row[] = list.get(0);
            Usuario u = new Usuario(
                    (int) row[0],
                    (String) row[1],
                    (String) row[2], 
                    (int) row[3], 
                    (String) row[4],
                    (String) row[5]);
            return u;
        }
    }
    
    public static Usuario getUsuario(int cod) throws Exception {
        String SQL = "SELECT CD_USUARIO, NM_USUARIO, NM_LOGIN, CD_SENHA, " +
                        " (SELECT C.NM_CURSO FROM TB_CURSO C " +
                            " WHERE U.CD_CURSO = C.CD_CURSO), " +
                        " (SELECT P.NM_PERMISSAO FROM TB_PERMISSAO P " +
                            "WHERE U.CD_PERMISSAO = P.CD_PERMISSAO) " +
                    " FROM TB_USUARIO U WHERE CD_USUARIO = ? ";
        Object parameters[] = {cod};
        
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, parameters);
        if (list.isEmpty()){
            return null;
        } else {
            Object row[] = list.get(0);
            Usuario u = new Usuario(
                    (int) row[0],
                    (String) row[1],
                    (String) row[2], 
                    (int) row[3], 
                    (String) row[4],
                    (String) row[5]);
            return u;
        }
    }
    
    public static ArrayList<Usuario> getUsuarios() throws Exception {
        String SQL = "SELECT CD_USUARIO, NM_USUARIO, NM_LOGIN, CD_SENHA, " +
                        " (SELECT C.NM_CURSO FROM TB_CURSO C " +
                            " WHERE U.CD_CURSO = C.CD_CURSO), " +
                        " (SELECT P.NM_PERMISSAO FROM TB_PERMISSAO P " +
                            "WHERE U.CD_PERMISSAO = P.CD_PERMISSAO) " +
                    " FROM TB_USUARIO U ";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, new Object[]{});
        for (int i = 0; i < list.size(); i++) {
            Object row[] = list.get(i);
            Usuario u = new Usuario(
                    (int) row[0],
                    (String) row[1],
                    (String) row[2], 
                    (int) row[3], 
                    (String) row[4],
                    (String) row[5]);
            usuarios.add(u);
        }
        return usuarios;
    }
    
    public static void adicionarUsuario (String nome, String login, int senha, int curso, int permissao) throws Exception{
        String SQL = "INSERT INTO TB_USUARIO VALUES (default, ?, ?, ?, ?, ? )";
        Object parameters[] = {nome, login, senha, curso, permissao};
        DatabaseConnector.execute(SQL, parameters);
    }
    
    public static void alterarUsuario(String nome, String usuario, int senha, int curso, int permissao, int cod) throws Exception{
        String SQL = "UPDATE TB_USUARIO SET NM_USUARIO = ? , NM_LOGIN = ? , CD_SENHA = ? , CD_CURSO = ? , CD_PERMISSAO = ? WHERE CD_USUARIO = ? ";
        Object parameters[] = {nome, usuario, senha, curso, permissao, cod};
        DatabaseConnector.execute(SQL, parameters);
    }
    
    public static void removerUsuario(int id) throws Exception{
        String SQL = " DELETE FROM TB_USUARIO WHERE CD_USUARIO = ? ";
        Object parameters[] = {id};
        DatabaseConnector.execute(SQL, parameters);
    }
    
    
}
