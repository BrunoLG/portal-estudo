package br.com.fatecpg.portal;

import java.util.ArrayList;
import java.util.Collections;

public class Questao {
    private int cod;
    private String questao;
    private String resposta;
    private int codTeste;

    public Questao(int cod, String questao, String resposta, int codTeste) {
        this.cod = cod;
        this.questao = questao;
        this.resposta = resposta;
        this.codTeste = codTeste;
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public int getCodTeste() {
        return codTeste;
    }

    public void setCodTeste(int codTeste) {
        this.codTeste = codTeste;
    }
    
    public static ArrayList<Questao> getQuestoes() throws Exception {
        String SQL = "SELECT CD_QUESTAO, DS_QUESTAO, NM_RESPOSTA, CD_TESTE" +
                    " FROM TB_QUESTAO ";
        ArrayList<Questao> questoes = new ArrayList<>();
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, new Object[]{});
        for (int i = 0; i < list.size(); i++) {
            Object row[] = list.get(i);
            Questao q = new Questao(
                    (int) row[0],
                    (String) row[1],
                    (String) row[2],
                    (int) row[3]);
            questoes.add(q);
        }
        return questoes;
    }
    
    public static ArrayList<Questao> getQuestoes(int cod) throws Exception {
        String SQL = "SELECT CD_QUESTAO, DS_QUESTAO, NM_RESPOSTA, CD_TESTE" +
                    " FROM TB_QUESTAO WHERE CD_TESTE = ? ";
        ArrayList<Questao> questoes = new ArrayList<>();
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, new Object[]{cod});
        for (int i = 0; i < list.size(); i++) {
            Object row[] = list.get(i);
            Questao q = new Questao(
                    (int) row[0],
                    (String) row[1],
                    (String) row[2],
                    (int) row[3]);
            questoes.add(q);
        }
        Collections.shuffle(questoes);
        return questoes;
    }
    
    public static void adicionarQuestao (String enunciado, String resposta, String teste) throws Exception{
        String SQL = "INSERT INTO TB_QUESTAO VALUES (default, ?, ?, ? ) ";
        Object parameters[] = {enunciado, resposta, teste};
        DatabaseConnector.execute(SQL, parameters);
    }
}
