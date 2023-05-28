package apresentacao;

        import dados.Aluno;
        import dados.Avaliacao;
        import dados.Disciplina;
        import dados.Professor;
        import negocio.Sistema;

        import javax.swing.table.AbstractTableModel;
        import javax.swing.table.TableColumn;
        import java.util.ArrayList;
        import java.util.LinkedList;
        import java.util.List;

public class Tabela extends AbstractTableModel {
    private List<Aluno> alunos;
    private Disciplina disciplina;
    private String[] nomeColunas = {"Nome"};

    public Tabela() {
    }

    public Tabela(List<Aluno> alunos, Disciplina disciplina) {
        this.alunos = alunos;
        this.disciplina = disciplina;
        this.criaColunas(this.alunos.get(0), disciplina);
    }

    private void criaColunas(Aluno aluno, Disciplina disc){
        String[] colunas = new String[disc.getAvaliacoes().size()+1];
        int i = 0;
        colunas[0] = "Nome";
        i++;
        for(Avaliacao a: aluno.getAvaliacoes()){
            if(a.getCodDisciplina().equals(disc.getCodigo())){
                colunas[i] = a.getNome();
                i++;
            }
        }
        nomeColunas = colunas;
    }

    public String getColumnName(int coluna){
        return nomeColunas[coluna];
    }

    public int getRowCount() {
        if(this.alunos == null) return 0;
        return this.alunos.size();
    }

    public int getColumnCount() {
        return nomeColunas.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(this.alunos == null) return null;
        switch (columnIndex){
            case 0:
                return this.alunos.get(rowIndex).getNome();
            default:
                String nome = getColumnName(columnIndex);
                return this.alunos.get(rowIndex).getNotaAvaliacao(this.disciplina, this.alunos.get(rowIndex).getAvaliacaoPorNomeDisciplina(nome, this.disciplina));
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(this.alunos == null) return;
        switch (columnIndex){
            case 0:
//                this.alunos.get(rowIndex).setNome((String) aValue);
                return;
            default:
                String nome = getColumnName(columnIndex);
                this.alunos.get(rowIndex).setNotaAvaliacao(this.disciplina, this.alunos.get(rowIndex).getAvaliacaoPorNomeDisciplina(nome, this.disciplina), Float.parseFloat((String) aValue));
                return;
        }
    }
}

