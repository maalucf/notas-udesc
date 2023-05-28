package negocio;
import dados.*;
import java.util.*;

public class Sistema {
    private List<Professor> professores = new ArrayList<Professor>();
    private List<Aluno> alunos = new ArrayList<Aluno>();
    private List<Disciplina> disciplinasProfessor = new ArrayList<Disciplina>();
    public void cadastrarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public void editarAluno(Aluno aluno, String nome, String curso, String cpf) {
        for (Aluno al: alunos) {
            if (al.getCpf() == aluno.getCpf()) {
                al.setNome(nome);
                al.setCurso(curso);
                al.setCpf(cpf);
            }
        }
    }

    public void cadastrarDisciplinaProfessor(Disciplina disciplina, Professor professor) {
        List<Disciplina> disc = professor.adicionarDisciplina(disciplina);
        disciplinasProfessor = disc;
    }

    public void cadastrarDisciplinaAluno(Disciplina disciplina, Aluno aluno) {
        aluno.adicionarDisciplina(disciplina);
        if(disciplina.getAvaliacoes().size() > 0) {
            cadastrarAvaliacaoNC(aluno, disciplina, disciplina.getAvaliacoes());
        }
    }

    public void removerDisciplinaAluno(Disciplina disciplina, Aluno aluno) {
        for (Disciplina disc : disciplinasProfessor) {
            List<Avaliacao> aval = disc.getAvaliacoes();
            if (disc.equals(disciplina)) {
                int aux = aval.size();
                for (int j = 0; j < aux; j++) {
                    if (aval.get(j).getCodDisciplina().equals(disc.getCodigo())) {
                        for (Aluno al : alunos) {
                            if(al.equals(aluno)) {
                                if (al.getDisciplinas().contains(disc)) {
                                    List<Avaliacao> a = al.getAvaliacoes();
                                    int tam = a.size();
                                    for (int i = 0; i < tam; i++) {
                                        if (a.get(i).getCodDisciplina().equals(disc.getCodigo())) {
                                            al.removerAvaliacao(disc, a.get(i));
                                            i--;
                                            tam = a.size();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for(Aluno al : alunos) {
                    if (al.equals(aluno)) {
                        List<Disciplina> d = al.getDisciplinas();
                        if (d.contains(disciplina)) {
                            al.removerDisciplina(disciplina);
                        }
                    }
                }
            }
        }
    }

    public void editarDisciplina(Disciplina disciplina, String nome, String codigo, String departamento, Professor professor) {
        List<Disciplina> disc = professor.editarDisciplina(disciplina, codigo, nome, departamento);
        disciplinasProfessor = disc;
        for(Aluno al : alunos) {
            List<Disciplina> d = al.getDisciplinas();
            for(Disciplina di : d) {
                if(di.equals(disciplina)) {
                    for(Avaliacao aval : di.getAvaliacoes()) {
                        if(aval.getCodDisciplina().equals(di.getCodigo())) {
                            aval.setCodDisciplina(codigo);
                        }
                    }
                }
            }
        }
    }

    public void cadastrarAvaliacao(Disciplina disciplina, Avaliacao avaliacao, Professor professor, List<Avaliacao> avals) {
        List<Avaliacao> avaliacoes = professor.adicionarAvaliacaoDisciplina(avaliacao, disciplina);
        int i = 0;
        for (Aluno aluno : alunos) {
            List<Disciplina> disciplinas = aluno.getDisciplinas();
            if (disciplinas.contains(disciplina)) {
                aluno.setAvaliacoes(avals.subList(i, i + 1));
                i++;
            }
        }
    }

    public void cadastrarAvaliacaoNC(Aluno aluno, Disciplina disciplina, List<Avaliacao> avals) {
        for (Aluno al : alunos) {
            if(al.equals(aluno)) {
                List<Disciplina> disciplinas = aluno.getDisciplinas();
                if (disciplinas.contains(disciplina)) {
                    List<Avaliacao> aux = new LinkedList<>();
                    for(Avaliacao a : avals){
                        Avaliacao newA = new Avaliacao(a.getCodDisciplina(), a.getNome(), a.getPeso(), a.getData(), a.getNota());
                        aux.add(newA);
                    }
                    aluno.setAvaliacoes(aux);
                }
            }
        }
    }
    public void cadastrarAvaliacaoAluno(Aluno aluno, List<Avaliacao> avalAluno, Professor professor, Disciplina disciplina) {
        professor.adicionarAvaliacao(aluno, avalAluno, disciplina);
    }

    public void removerDisciplinaProfessor(Disciplina disciplina, Professor professor) {
        for (Disciplina disc : disciplinasProfessor) {
            List<Avaliacao> aval = disc.getAvaliacoes();
            if (disc.equals(disciplina)) {
                int aux = aval.size();
                for (int j = 0; j < aux; j++) {
                    if (aval.get(j).getCodDisciplina().equals(disc.getCodigo())) {
                        for (Aluno al : alunos) {
                            if(al.getDisciplinas().contains(disc)) {
                                List<Avaliacao> a = al.getAvaliacoes();
                                int tam = a.size();
                                for(int i = 0; i < tam; i++){
                                    if(a.get(i).getCodDisciplina().equals(disc.getCodigo())){
                                        al.removerAvaliacao(disc, a.get(i));
                                        i--;
                                        tam = a.size();
                                    }
                                }
                            }
                        }
                        disc.removerAvaliacao(aval.get(j));
                        j--;
                        aux = aval.size();
                    }
                }
                for(Aluno al : alunos) {
                    List<Disciplina> d = al.getDisciplinas();
                    if (d.contains(disciplina)) {
                        al.removerDisciplina(disciplina);
                    }
                }
            }
        }
        List<Disciplina> disc = professor.removerDisciplina(disciplina);
        disciplinasProfessor = disc;
    }

    public String mostrarMediasFinais() {
        String aux = "";
        for(Disciplina disc : disciplinasProfessor) {
            String s = "\nNOTAS DISCIPLINA " + disc.getNome() + " \n";
            String s1 = "\nALUNOS DA DISCIPLINA " + disc.getNome() + " EM EXAME \n";
            for(Aluno aluno : alunos) {
                if (aluno.calcularMediaFinal(disc) >= 7) {
                    s += aluno.getNome() + " - Média Final: " + aluno.calcularMediaFinal(disc) + " - APROVADO \n";
                } else if(aluno.calcularMediaFinal(disc) < 1.7) {
                    s += aluno.getNome() + " - Média Final: " + aluno.calcularMediaFinal(disc) + " - REPROVADO \n";
                } else if (aluno.calcularMediaFinal(disc) < 7 && aluno.calcularMediaFinal(disc) >= 1.7) {
                    s += aluno.getNome() + " - Média Final: " + aluno.calcularMediaFinal(disc) + " - EXAME \n";
                    s1 += aluno.getNome() + " - Média Necessária Exame: " + aluno.calcularMediaExame(disc) + "\n";
                }
            }
            aux += s + s1;
        }
        return aux;
    }


    public boolean logarProfessor(String cpf, String senha) {
        for (Professor prof: professores) {
            if (prof.getCpf().equals(cpf) && prof.getSenha().equals(senha)) {
                disciplinasProfessor = prof.getDisciplinas();
                return true;
            }
        }
        return false;
    }

    public void deslogarProfessor() {
        disciplinasProfessor = null;
    }

    public Professor getProfessor(String cpf) {
        for (Professor prof: professores) {
            if (prof.getCpf().equals(cpf)) {
                return prof;
            }
        }
        return null;
    }

    public Aluno getAluno(String cpf) {
        for (Aluno aluno: alunos) {
            if (aluno.getCpf().equals(cpf)) {
                return aluno;
            }
        }
        return null;
    }

    public Disciplina getDisciplina(String codigo) {
        for (Disciplina disciplina: disciplinasProfessor) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }

    public boolean existeCodigoDisciplina(String codigo) {
        for (Disciplina disciplina: disciplinasProfessor) {
            if (disciplina.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeCPFAluno(String cpf) {
        for (Aluno al: alunos) {
            if (al.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeCPFProfessor(String cpf) {
        for (Professor prof: professores) {
            if (prof.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public Avaliacao getAvaliacao(Disciplina disciplina, String nome) {
        for (Disciplina disc : disciplinasProfessor) {
            if (disc.getCodigo().equals(disciplina.getCodigo())) {
                for (Avaliacao avaliacao : disc.getAvaliacoes()) {
                    if (avaliacao.getNome().equals(nome)) {
                        return avaliacao;
                    }
                }
            }
        }
        return null;
    }

    public void mostrarAlunos() {
        for (Aluno aluno: alunos) {
            System.out.println("\n" + aluno.getNome());
            for(Disciplina disc : aluno.getDisciplinas()) {
                System.out.println(" - " + disc.getNome() + "(" + disc.getCodigo() + ")");
                for(Avaliacao aval : aluno.getAvaliacoes()) {
                    if(aval.getCodDisciplina().equals(disc.getCodigo())) {
                        System.out.println("   - " + aval.getNome() + " - "+ aval.getNota());
                    }
                }
            }
        }
    }

    public void mostrarProfessores() {
        for (Professor professor : professores) {
            System.out.println(professor.getNome());
            for (Disciplina disc : professor.getDisciplinas()) {
                System.out.println(" - " + disc.getNome() + "(" + disc.getCodigo() + ")");
            }
        }
    }

    public void mostrarDisciplinas() {
        for (Disciplina disciplina: disciplinasProfessor) {
            System.out.println(disciplina.getNome() + "(" + disciplina.getCodigo() + ")");
        }
    }

    public void mostrarAvaliacoes(Disciplina disciplina) {
        for(Disciplina disc : disciplinasProfessor) {
            System.out.println(" - " + disc.getNome() + "(" + disc.getCodigo() + ")");
            if (disc.getCodigo().equals(disciplina.getCodigo())) {
                for (Avaliacao avaliacao : disc.getAvaliacoes()) {
                    System.out.println("   - " + avaliacao.getNome() + " - "+ avaliacao.getNota());
                }
            }
        }
    }

    public List<Aluno> getListaAlunos() {
        return alunos;
    }

    public List<Professor> getListaProfessores() {
        return professores;
    }

    public List<Disciplina> getListaDisciplinasProfessor() {
        return disciplinasProfessor;
    }

    public List<Avaliacao> getListaAvaliacoesAluno(Aluno aluno) {
        return aluno.getAvaliacoes();
    }

    public boolean existeAvaliacao(Disciplina disciplina, String nome) {
        for (Disciplina disc : disciplinasProfessor) {
            if (disc.getCodigo().equals(disciplina.getCodigo())) {
                for (Avaliacao avaliacao : disc.getAvaliacoes()) {
                    if (avaliacao.getNome().equals(nome)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Avaliacao> getAvaliacoesDisciplina(Disciplina disciplina) {
        for (Disciplina disc : disciplinasProfessor) {
            if (disc.getCodigo().equals(disciplina.getCodigo())) {
                return disc.getAvaliacoes();
            }
        }
        return null;
    }
}
