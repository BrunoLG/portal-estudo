package br.com.fatecpg.portal;
 import java.util.ArrayList;
 public class HistoricoAula {
    private int cod;
    private String aula;
    private String usuario;
     public HistoricoAula(int cod, String aula, String usuario) {
        this.cod = cod;
        this.aula = aula;
        this.usuario = usuario;
    }
    
    public int getCod() {
        return cod;
    }
     public void setCod(int cod) {
        this.cod = cod;
    }
     public String getAula() {
        return aula;
    }
     public void setAula(String aula) {
        this.aula = aula;
    }
     public String getUsuario() {
        return usuario;
    }
     public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public static ArrayList<HistoricoAula> getAulaHistorico() throws Exception {
        String SQL = "SELECT CD_HISTORICO, (SELECT A.NM_AULA FROM TB_AULA A " +
            " WHERE H.CD_AULA = A.CD_AULA), (SELECT U.NM_USUARIO FROM TB_USUARIO U " +
            " WHERE H.CD_USUARIO = U.CD_USUARIO) FROM TB_HISTORICO_AULA H ";
        ArrayList<HistoricoAula> historicoAulas = new ArrayList<>();
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, new Object[]{});
        for (int i = 0; i < list.size(); i++) {
            Object row[] = list.get(i);
            HistoricoAula ha = new HistoricoAula(
                    (int) row[0],
                    (String) row[1],
                    (String) row[2]);
            historicoAulas.add(ha);
        }
        return historicoAulas;
    }
}