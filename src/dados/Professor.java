package dados;
import java.util.*;

public class Professor {
    private String nome;
    private String cpf;
    private String departamento;
    private String senha;
    private List<Aluno> alunos = new ArrayList<Aluno>();
    private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

    public Professor(String nome, String cpf, String departamento, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.departamento = departamento;
        this.senha = senha;
        alunos = new ArrayList<Aluno>();
        disciplinas = new ArrayList<Disciplina>();
    }

    public Professor() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Disciplina> adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        return disciplinas;
    }

    public List<Disciplina> removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
        return disciplinas;
    }

    public List<Disciplina> editarDisciplina(Disciplina disciplina, String codigo, String nome, String departamento) {
        for (Disciplina disc: disciplinas) {
            if (disc.equals(disciplina)) {
                for(Avaliacao aval : disc.getAvaliacoes()) {
                    if(aval.getCodDisciplina().equals(disc.getCodigo())) {
                        aval.setCodDisciplina(codigo);
                    }
                }
                disc.setCodigo(codigo);
                disc.setNome(nome);
                disc.setDepartamento(departamento);
            }
        }
        return disciplinas;
    }

    public List<Avaliacao> adicionarAvaliacaoDisciplina(Avaliacao avaliacao, Disciplina disciplina) {
        List<Avaliacao> aval = disciplina.adicionarAvaliacao(avaliacao);
        return aval;
    }

    public void adicionarAvaliacao(Aluno aluno, List<Avaliacao> avalAluno, Disciplina disciplina) {
        aluno.alterarAvaliacoes(avalAluno, disciplina);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String toString() {
        return "Nome: " + nome + " CPF: " + cpf + " Departamento: " + departamento;
    }
}
