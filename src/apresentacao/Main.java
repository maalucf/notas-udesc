package apresentacao;
import negocio.*;
import dados.*;
import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Sistema sistema = new Sistema();
    public static Professor professorLogado;

    public static void cadastrarProfessor() {
        System.out.println("Digite o nome do professor:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do professor:");
        String cpf = scanner.nextLine();
        System.out.println("Digite o departamento do professor:");
        String departamento = scanner.nextLine();
        System.out.println("Digite a senha do professor:");
        String senha = scanner.nextLine();
        Professor professor = new Professor(nome, cpf, departamento, senha);

        if(sistema.existeCPFProfessor(cpf)) {
            System.out.println("CPF já cadastrado!");
        }
        else {
            sistema.cadastrarProfessor(professor);
            if(sistema.getProfessor(cpf) != null) {
                System.out.println("Professor cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar professor!");
            }
        }
    }

    public static boolean logarProfessor() {
        System.out.println("Digite o CPF do professor:");
        String cpf = scanner.nextLine();
        System.out.println("Digite a senha do professor:");
        String senha = scanner.nextLine();

        if (sistema.logarProfessor(cpf, senha)) {
            System.out.println("Logado com sucesso!");
            professorLogado = sistema.getProfessor(cpf);
            return true;
        } else {
            System.out.println("CPF ou senha incorretos!");
            return false;
        }
    }

    public static void cadastrarAluno() {
        System.out.println("Digite o nome do aluno:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        System.out.println("Digite o curso do aluno:");
        String curso = scanner.nextLine();
        Aluno aluno = new Aluno(nome, cpf, curso);

        if(sistema.existeCPFAluno(cpf)) {
            System.out.println("CPF já cadastrado!");
        }
        else {
            sistema.cadastrarAluno(aluno);
            if(sistema.getAluno(cpf) != null) {
                System.out.println("Aluno cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar aluno!");
            }
        }
    }

    public static void editarAluno() {
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        Aluno aluno = sistema.getAluno(cpf);
        System.out.println("Digite o novo nome do aluno:");
        String nome = scanner.nextLine();
        System.out.println("Digite o novo curso do aluno:");
        String curso = scanner.nextLine();

        if(!sistema.getListaAlunos().contains(aluno)) {
            System.out.println("Aluno não encontrado!");
        }
        else {
            sistema.editarAluno(aluno, nome, curso, cpf);

            if (sistema.getAluno(cpf).getNome().equals(nome) && sistema.getAluno(cpf).getCurso().equals(curso)) {
                System.out.println("Aluno editado com sucesso!");
            } else {
                System.out.println("Erro ao editar aluno!");
            }
        }
    }

    public static void removerAluno() {
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        Aluno aluno = sistema.getAluno(cpf);

        if(!sistema.getListaAlunos().contains(aluno)) {
            System.out.println("Aluno não encontrado!");
        }
        else {
            sistema.removerAluno(aluno);
            if (sistema.getAluno(cpf) == null) {
                System.out.println("Aluno removido com sucesso!");
            } else {
                System.out.println("Erro ao remover aluno!");
            }
        }
    }

    public static void cadastrarDisciplina() {
        System.out.println("Digite o nome da disciplina:");
        String nome = scanner.nextLine();
        System.out.println("Digite o código da disciplina:");
        String codigo = scanner.nextLine();
        System.out.println("Digite o departamento da disciplina:");
        String departamento = scanner.nextLine();
        Disciplina disciplina = new Disciplina(nome, codigo, departamento);

        if(sistema.existeCodigoDisciplina(codigo)) {
            System.out.println("Código já cadastrado!");
        }
        else {
            sistema.cadastrarDisciplinaProfessor(disciplina, professorLogado);
            if (sistema.getDisciplina(codigo) != null) {
                System.out.println("Disciplina cadastrada com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar disciplina!");
            }
        }
    }

    public static void editarDisciplina() {
        System.out.println("Digite o código da disciplina:");
        String codigo = scanner.nextLine();
        Disciplina disciplina = sistema.getDisciplina(codigo);
        System.out.println("Digite o novo nome da disciplina:");
        String nome = scanner.nextLine();
        System.out.println("Digite o novo departamento da disciplina:");
        String departamento = scanner.nextLine();

        if(!sistema.getListaDisciplinasProfessor().contains(disciplina)) {
            System.out.println("Disciplina não encontrada!");
        }
        else {
            sistema.editarDisciplina(disciplina, nome, codigo, departamento, professorLogado);

            if (sistema.getDisciplina(codigo).getNome().equals(nome) && sistema.getDisciplina(codigo).getDepartamento().equals(departamento)) {
                System.out.println("Disciplina editada com sucesso!");
            } else {
                System.out.println("Erro ao editar disciplina!");
            }
        }
    }

    public static void removerDisciplina() {
        System.out.println("Digite o código da disciplina:");
        String codigo = scanner.nextLine();
        Disciplina disciplina = sistema.getDisciplina(codigo);

        if(!sistema.getListaDisciplinasProfessor().contains(disciplina)) {
            System.out.println("Disciplina não encontrada!");
        }
        else {
            sistema.removerDisciplinaProfessor(disciplina, professorLogado);

            if (sistema.getDisciplina(codigo) == null) {
                System.out.println("Disciplina removida com sucesso!");
            } else {
                System.out.println("Erro ao remover disciplina!");
            }
        }
    }

    public static void cadastrarDisciplinaAluno() {
        System.out.println("Digite o código da disciplina:");
        String codigo = scanner.nextLine();
        Disciplina disciplina = sistema.getDisciplina(codigo);
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        Aluno aluno = sistema.getAluno(cpf);

        if(sistema.getAluno(cpf).getDisciplinas().contains(disciplina)) {
            System.out.println("Aluno já cadastrado na disciplina!");
        }
        else {
            if (sistema.getListaDisciplinasProfessor().contains(disciplina) && sistema.getListaAlunos().contains(aluno)) {
                sistema.cadastrarDisciplinaAluno(disciplina, aluno);

                List<Avaliacao> avaliacoes = disciplina.getAvaliacoes();
                if(avaliacoes.size() > 0) {
                    sistema.cadastrarAvaliacaoNC(aluno, disciplina, avaliacoes);
                }

                if (sistema.getAluno(cpf).getDisciplinas().contains(disciplina)) {
                    System.out.println("Disciplina cadastrada com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar disciplina!");
                }
            } else {
                System.out.println("Disciplina ou aluno não encontrados!");
            }
        }
    }

    public static void removerDisciplinaAluno() {
        System.out.println("Digite o código da disciplina:");
        String codigo = scanner.nextLine();
        Disciplina disciplina = sistema.getDisciplina(codigo);
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        Aluno aluno = sistema.getAluno(cpf);

        if((sistema.getListaDisciplinasProfessor().contains(disciplina) && sistema.getListaAlunos().contains(aluno))) {
            sistema.removerDisciplinaAluno(disciplina, aluno);

            if (!sistema.getAluno(cpf).getDisciplinas().contains(disciplina)) {
                System.out.println("Disciplina removida com sucesso!");
            } else {
                System.out.println("Erro ao remover disciplina!");
            }
        }
        else {
            System.out.println("Disciplina ou aluno não encontrados!");
        }

    }

    public static void cadastrarAvaliacao() {
        System.out.println("Digite o código da disciplina:");
        String codigo = scanner.nextLine();
        Disciplina disciplina = sistema.getDisciplina(codigo);
        System.out.println("Digite o nome da avaliação:");
        String nome = scanner.nextLine();
        System.out.println("Digite a data da avaliação:");
        String data = scanner.nextLine();
        System.out.println("Digite o peso da avaliação:");
        float peso = scanner.nextFloat();
        scanner.nextLine();
        Avaliacao avaliacao = new Avaliacao(codigo, nome, peso, data, 0);
        List<Avaliacao> avaliacoesEnviadas = new LinkedList<>();

        for(int i = 0; i < sistema.getListaAlunos().size(); i++){
            if(sistema.getListaAlunos().get(i).getDisciplinas().contains(disciplina)){
                Avaliacao aval = new Avaliacao(codigo, nome, peso, data, 0);
                avaliacoesEnviadas.add(aval);
            }
        }

        if(!sistema.getListaDisciplinasProfessor().contains(disciplina)) {
            System.out.println("Disciplina não encontrada!");
        }
        else if(sistema.existeAvaliacao(disciplina, nome)) {
            System.out.println("Avaliação já cadastrada!");
        }
        else {
            sistema.cadastrarAvaliacao(disciplina, avaliacao, professorLogado, avaliacoesEnviadas);

            if (sistema.getDisciplina(codigo).getAvaliacoes().contains(avaliacao)) {
                System.out.println("Avaliação cadastrada com sucesso!");
            } else {
                System.out.println("Erro ao cadastrar avaliação!");
            }
        }
    }

    public static void cadastrarAvaliacaoAluno() {
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        Aluno aluno = sistema.getAluno(cpf);
        System.out.println("Digite o código da disciplina:");
        String codigo = scanner.nextLine();
        Disciplina disciplina = sistema.getDisciplina(codigo);
        System.out.println("Digite o nome da avaliação:");
        String nome = scanner.nextLine();
        Avaliacao avaliacao = sistema.getAvaliacao(disciplina, nome);
        avaliacao.setCodDisciplina(codigo);
        System.out.println("Digite a nota da avaliação:");
        float nota = scanner.nextFloat();
        scanner.nextLine();
        avaliacao = new Avaliacao(codigo, nome, avaliacao.getPeso(), avaliacao.getData(), nota);
        List<Avaliacao> avalAluno = new ArrayList<Avaliacao>();
        avalAluno.add(avaliacao);

        if(!sistema.getListaAlunos().contains(aluno) && !sistema.getListaDisciplinasProfessor().contains(disciplina)) {
            System.out.println("Aluno ou disciplina não encontrados!");
        }
        else if(!sistema.getAluno(cpf).getDisciplinas().contains(disciplina)) {
            System.out.println("Aluno não está matriculado na disciplina!");
        }
        else if(!avaliacao.getNome().equals(nome)) {
            System.out.println("Avaliação não encontrada!");
        }
        else {
            sistema.cadastrarAvaliacaoAluno(aluno, avalAluno, professorLogado, disciplina);
            System.out.println("Avaliação cadastrada com sucesso!");
        }
    }

    public static void mostrarMediasFinais() {
        String s = sistema.mostrarMediasFinais();
        System.out.println(s);
    }

    public static void mostrarAlunos() {
        sistema.mostrarAlunos();

        if (sistema.getListaAlunos().isEmpty()) {
            System.out.println("Não há alunos cadastrados!");
        }
    }

    public static void mostrarDisciplinas() {
        sistema.mostrarDisciplinas();

        if (sistema.getListaDisciplinasProfessor().isEmpty()) {
            System.out.println("Não há disciplinas cadastradas!");
        }
    }

    public static void mostrarAvaliacoes() {
        System.out.println("Digite o código da disciplina:");
        String codigo = scanner.nextLine();
        Disciplina disciplina = sistema.getDisciplina(codigo);
        sistema.mostrarAvaliacoes(disciplina);

        if (sistema.getDisciplina(codigo).getAvaliacoes().isEmpty()) {
            System.out.println("Não há avaliações cadastradas!");
        }

        if(!sistema.getListaDisciplinasProfessor().contains(disciplina)) {
            System.out.println("Disciplina não cadastrada!");
        }
    }

    public static void mostrarProfessores() {
        sistema.mostrarProfessores();

        if (sistema.getListaProfessores().isEmpty()) {
            System.out.println("Não há professores cadastrados!");
        }
    }

    public static void mostrarAvaliacoesAlunos() {
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        Aluno aluno = sistema.getAluno(cpf);
        List<Avaliacao> avaliacoes = aluno.getAvaliacoes();

        System.out.println("Avaliações do aluno " + aluno.getNome() + ":");
        for (Avaliacao avaliacao : avaliacoes) {
            System.out.println(avaliacao.getCodDisciplina() + " - " + avaliacao.getNome() + " - " + avaliacao.getNota());
        }
    }

    public Professor getProfessorLogado() {
        return professorLogado;
    }

    public static void Menu1() {
        System.out.println("1 - Cadastrar professor");
        System.out.println("2 - Logar professor");
        System.out.println("3 - Cadastrar aluno");
        System.out.println("4 - Editar aluno");
        System.out.println("5 - Remover aluno");
        System.out.println("0 - Sair");
    }

    public static void Menu2() {
        System.out.println("1 - Cadastrar disciplina");
        System.out.println("2 - Editar disciplina");
        System.out.println("3 - Remover disciplina");
        System.out.println("4 - Cadastrar disciplina para aluno");
        System.out.println("5 - Remover disciplina de aluno");
        System.out.println("6 - Cadastrar avaliação para a disciplina");
        System.out.println("7 - Cadastrar avaliação para aluno");
        System.out.println("8 - Mostrar médias finais para a disciplina");
        System.out.println("9 - Mostrar alunos e disciplinas do aluno");
        System.out.println("10 - Mostrar disciplinas");
        System.out.println("11 - Mostrar professores");
        System.out.println("12 - Mostrar avaliações das disciplinas");
        System.out.println("13 - Mostrar avaliações do aluno");
        System.out.println("0 - Sair");
    }

    public static void main(String[] args) {
        int opcao = 1;
        while (opcao != 0) {
            Menu1();
            opcao =  scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarProfessor();
                    break;
                case 2:
                    if(logarProfessor()) {
                        int opcao1 = 1;
                        while (opcao1 != 0) {
                            System.out.println("\n[Bem vindo, " + professorLogado.getNome() + "!]");
                            Menu2();
                            opcao1 =  scanner.nextInt();
                            scanner.nextLine();
                            switch (opcao1) {
                                case 1:
                                    cadastrarDisciplina();
                                    break;
                                case 2:
                                    editarDisciplina();
                                    break;
                                case 3:
                                    removerDisciplina();
                                    break;
                                case 4:
                                    cadastrarDisciplinaAluno();
                                    break;
                                case 5:
                                    removerDisciplinaAluno();
                                    break;
                                case 6:
                                    cadastrarAvaliacao();
                                    break;
                                case 7:
                                    cadastrarAvaliacaoAluno();
                                    break;
                                case 8:
                                    mostrarMediasFinais();
                                    break;
                                case 9:
                                    mostrarAlunos();
                                    break;
                                case 10:
                                    mostrarDisciplinas();
                                    break;
                                case 11:
                                    mostrarProfessores();
                                    break;
                                case 12:
                                    mostrarAvaliacoes();
                                    break;
                                case 13:
                                    mostrarAvaliacoesAlunos();
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Opção inválida");
                            }
                        }
                    }
                    break;
                case 3:
                    cadastrarAluno();
                    break;
                case 4:
                    editarAluno();
                    break;
                case 5:
                    removerAluno();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
