package br.com.fatecpg.portal;

import java.util.ArrayList;

public class Permissao {
    private int cod;
    private String nome;

    public Permissao(int cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public static ArrayList<Permissao> getPermissoes() throws Exception {
        String SQL = "SELECT CD_PERMISSAO, NM_PERMISSAO FROM TB_PERMISSAO ";
        ArrayList<Permissao> permissoes = new ArrayList<>();
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, new Object[]{});
        for (int i = 0; i < list.size(); i++) {
            Object row[] = list.get(i);
            Permissao p = new Permissao(
                    (int) row[0],
                    (String) row[1]);
            permissoes.add(p);
        }
        return permissoes;
    }
}
