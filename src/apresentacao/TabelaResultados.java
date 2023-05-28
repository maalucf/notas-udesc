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

public class TabelaResultados extends AbstractTableModel {
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;
    private String[] nomeColunas = {"Nome Aluno"};

    public TabelaResultados() {
    }

    public TabelaResultados(List<Aluno> alunos, List<Disciplina> disciplinas) {
        this.alunos = alunos;
        this.disciplinas = disciplinas;
        this.criaColunas();
    }

    private void criaColunas(){
        String[] colunas = new String[disciplinas.size()+1];
        int i = 0;
        colunas[0] = "Nome";
        i++;
        for(Disciplina disc : disciplinas){
            colunas[i] = disc.getNome() + " (" + disc.getCodigo() + ")";
            i++;
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
                String s = "";
                float mediaFinal = this.alunos.get(rowIndex).calcularMediaFinal(this.disciplinas.get(columnIndex-1));
                float mediaExame = this.alunos.get(rowIndex).calcularMediaExame(this.disciplinas.get(columnIndex-1));
                if (mediaFinal >= 7) {
                    s += String.format("%.2f", mediaFinal) + " - APROVADO";
                } else if(mediaFinal < 1.7) {
                    s += String.format("%.2f", mediaFinal) + " - REPROVADO";
                } else if (mediaFinal >= 1.7) {
                    s += String.format("%.2f", mediaFinal) + " - EXAME (nota: " + String.format("%.2f", mediaExame) + ")";
                }
                return s;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}

