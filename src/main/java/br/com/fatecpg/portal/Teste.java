package br.com.fatecpg.portal;

import java.util.ArrayList;

public class Teste {

    private int cod;
    private String nome;
    private int disciplina;

    public Teste(int cod, String nome, int disciplina) {
        this.cod = cod;
        this.nome = nome;
        this.disciplina = disciplina;
    }

    public int getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(int disciplina) {
        this.disciplina = disciplina;
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

    public static ArrayList<Teste> getTeste(int cod) throws Exception {
        String SQL = "SELECT CD_TESTE, NM_TESTE, CD_DISCIPLINA"
                + " FROM TB_TESTE WHERE CD_DISCIPLINA = ? ";
        ArrayList<Teste> testes = new ArrayList<>();
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, new Object[]{cod});
        for (int i = 0; i < list.size(); i++) {
            Object row[] = list.get(i);
            Teste t = new Teste(
                    (int) row[0],
                    (String) row[1],
                    (int) row[2]);
            testes.add(t);
        }
        return testes;
    }

    public static ArrayList<Teste> getTestes(String cod) throws Exception {
        String SQL = "SELECT CD_TESTE, NM_TESTE, T.CD_DISCIPLINA "
                + " FROM TB_TESTE T JOIN TB_DISCIPLINA D ON T.CD_DISCIPLINA = D.CD_DISCIPLINA "
                + " WHERE D.CD_CURSO = (SELECT C.CD_CURSO FROM TB_CURSO C WHERE C.NM_CURSO = ?) ";
        ArrayList<Teste> testes = new ArrayList<>();
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, new Object[]{cod});
        for (int i = 0; i < list.size(); i++) {
            Object row[] = list.get(i);
            Teste t = new Teste(
                    (int) row[0],
                    (String) row[1],
                    (int) row[2]);
            testes.add(t);
        }
        return testes;
    }

    public static void adicionarTeste(String nome, int disciplina) throws Exception {
        String SQL = "INSERT INTO TB_TESTE VALUES (default, ?, ?)";
        Object parameters[] = {nome, disciplina};
        DatabaseConnector.execute(SQL, parameters);
    }
}
