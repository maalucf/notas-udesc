package apresentacao;

import dados.*;
import negocio.*;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Component;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.Color;
import java.awt.Font;

public class TelaPrincipal extends JFrame {

	public JFrame frame;
	private JTextField textFieldNomeAluno;
	private JTextField textFieldCpfAluno;
	private JTextField textFieldCursoAluno;

	private Sistema sistema = new Sistema();
	private JTextField textFieldNomeEditar;
	private JTextField textFieldCpfEditar;
	private JTextField textFieldCursoEditar;
	private JTextField textFieldNomeProf;
	private JTextField textFieldCpfProf;
	private JTextField textFieldDepProf;
	private JPasswordField passwordFieldSenhaProf;
	private JTextField textFieldCpfLogin;
	private JPasswordField passwordFieldSenhaLogin;
	private Professor professorLogado = new Professor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	public TelaPrincipal(Sistema sistema) {
		this.sistema = sistema;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 649, 392);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 637, 389);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(19, 80, 130));
		tabbedPane.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(0, 0, 636, 355);
		panel.add(tabbedPane);

		JPanel tabAluno = new JPanel();
		tabbedPane.addTab("Aluno", null, tabAluno, null);
		tabAluno.setLayout(null);

		JPanel backgroundCadastro = new JPanel();
		backgroundCadastro.setBackground(new Color(214, 233, 248));
		backgroundCadastro.setBounds(0, 0, 316, 335);
		tabAluno.add(backgroundCadastro);
		backgroundCadastro.setLayout(null);

		JButton btnCadastrarAluno = new JButton("Cadastrar Aluno");
		btnCadastrarAluno.setForeground(new Color(19, 80, 130));
		btnCadastrarAluno.setFont(new Font("Roboto Cn", Font.BOLD, 14));
		btnCadastrarAluno.setBounds(81, 232, 144, 21);
		backgroundCadastro.add(btnCadastrarAluno);

		JLabel labelCadastro = new JLabel("CADASTRO");
		labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		labelCadastro.setBounds(0, 25, 316, 26);
		backgroundCadastro.add(labelCadastro);
		labelCadastro.setForeground(new Color(19, 80, 130));
		labelCadastro.setFont(new Font("Roboto Cn", Font.BOLD, 24));

		JLabel labelCpfAluno = new JLabel("CPF: ");
		labelCpfAluno.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCpfAluno.setBounds(81, 132, 45, 13);
		backgroundCadastro.add(labelCpfAluno);
		labelCpfAluno.setForeground(new Color(19, 80, 130));
		labelCpfAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelCursoAluno = new JLabel("Curso: ");
		labelCursoAluno.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCursoAluno.setBounds(81, 178, 45, 13);
		backgroundCadastro.add(labelCursoAluno);
		labelCursoAluno.setForeground(new Color(19, 80, 130));
		labelCursoAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelNomeAluno = new JLabel("Nome: ");
		labelNomeAluno.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomeAluno.setBounds(81, 87, 45, 13);
		backgroundCadastro.add(labelNomeAluno);
		labelNomeAluno.setForeground(new Color(19, 80, 130));
		labelNomeAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		textFieldNomeAluno = new JTextField();
		textFieldNomeAluno.setBounds(129, 83, 96, 19);
		backgroundCadastro.add(textFieldNomeAluno);
		textFieldNomeAluno.setForeground(new Color(19, 80, 130));
		textFieldNomeAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldNomeAluno.setColumns(10);

		textFieldCursoAluno = new JTextField();
		textFieldCursoAluno.setBounds(129, 174, 96, 19);
		backgroundCadastro.add(textFieldCursoAluno);
		textFieldCursoAluno.setForeground(new Color(19, 80, 130));
		textFieldCursoAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldCursoAluno.setColumns(10);

		textFieldCpfAluno = new JTextField();
		textFieldCpfAluno.setBounds(129, 128, 96, 19);
		backgroundCadastro.add(textFieldCpfAluno);
		textFieldCpfAluno.setForeground(new Color(19, 80, 130));
		textFieldCpfAluno.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldCpfAluno.setColumns(10);

		JPanel backgroundEditar = new JPanel();
		backgroundEditar.setBackground(new Color(174, 211, 242));
		backgroundEditar.setBounds(313, 0, 318, 335);
		tabAluno.add(backgroundEditar);
		backgroundEditar.setLayout(null);

		JLabel labelEditarExcluir = new JLabel("EDITAR/EXCLUIR");
		labelEditarExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		labelEditarExcluir.setBounds(0, 28, 318, 19);
		backgroundEditar.add(labelEditarExcluir);
		labelEditarExcluir.setForeground(new Color(19, 80, 130));
		labelEditarExcluir.setFont(new Font("Roboto Cn", Font.BOLD, 24));

		JLabel labelNomeAluno_1_1 = new JLabel("Aluno: ");
		labelNomeAluno_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomeAluno_1_1.setBounds(82, 74, 45, 13);
		backgroundEditar.add(labelNomeAluno_1_1);
		labelNomeAluno_1_1.setForeground(new Color(19, 80, 130));
		labelNomeAluno_1_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelNomeAluno_1 = new JLabel("Nome: ");
		labelNomeAluno_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomeAluno_1.setBounds(82, 116, 45, 13);
		backgroundEditar.add(labelNomeAluno_1);
		labelNomeAluno_1.setForeground(new Color(19, 80, 130));
		labelNomeAluno_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelCpfAluno_1 = new JLabel("CPF: ");
		labelCpfAluno_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCpfAluno_1.setBounds(82, 162, 45, 13);
		backgroundEditar.add(labelCpfAluno_1);
		labelCpfAluno_1.setForeground(new Color(19, 80, 130));
		labelCpfAluno_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelCursoAluno_1 = new JLabel("Curso: ");
		labelCursoAluno_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCursoAluno_1.setBounds(82, 209, 45, 13);
		backgroundEditar.add(labelCursoAluno_1);
		labelCursoAluno_1.setForeground(new Color(19, 80, 130));
		labelCursoAluno_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JComboBox comboBoxAlunos = new JComboBox();
		comboBoxAlunos.setBounds(130, 71, 118, 21);
		backgroundEditar.add(comboBoxAlunos);
		comboBoxAlunos.setBackground(new Color(255, 255, 255));
		comboBoxAlunos.setForeground(new Color(19, 80, 130));
		comboBoxAlunos.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		textFieldNomeEditar = new JTextField();
		textFieldNomeEditar.setBounds(130, 113, 118, 19);
		backgroundEditar.add(textFieldNomeEditar);
		textFieldNomeEditar.setForeground(new Color(19, 80, 130));
		textFieldNomeEditar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldNomeEditar.setColumns(10);

		textFieldCpfEditar = new JTextField();
		textFieldCpfEditar.setBounds(130, 159, 118, 19);
		backgroundEditar.add(textFieldCpfEditar);
		textFieldCpfEditar.setForeground(new Color(19, 80, 130));
		textFieldCpfEditar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldCpfEditar.setColumns(10);

		textFieldCursoEditar = new JTextField();
		textFieldCursoEditar.setBounds(130, 206, 118, 19);
		backgroundEditar.add(textFieldCursoEditar);
		textFieldCursoEditar.setForeground(new Color(19, 80, 130));
		textFieldCursoEditar.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldCursoEditar.setColumns(10);

		JButton btnExcluirAluno = new JButton("Excluir Aluno");
		btnExcluirAluno.setBounds(161, 261, 128, 21);
		backgroundEditar.add(btnExcluirAluno);
		btnExcluirAluno.setForeground(new Color(19, 80, 130));
		btnExcluirAluno.setFont(new Font("Roboto Cn", Font.BOLD, 14));

		JButton btnEditarAluno = new JButton("Editar Aluno");
		btnEditarAluno.setBounds(26, 261, 118, 21);
		backgroundEditar.add(btnEditarAluno);
		btnEditarAluno.setForeground(new Color(19, 80, 130));
		btnEditarAluno.setFont(new Font("Roboto Cn", Font.BOLD, 14));

		JPanel tabProfessor = new JPanel();
		tabbedPane.addTab("Professor", null, tabProfessor, null);
		tabProfessor.setLayout(null);

		JPanel backgroundCadastroProf = new JPanel();
		backgroundCadastroProf.setBackground(new Color(214, 233, 248));
		backgroundCadastroProf.setBounds(0, 0, 316, 328);
		tabProfessor.add(backgroundCadastroProf);
		backgroundCadastroProf.setLayout(null);

		JLabel labelCpfProf = new JLabel("CPF: ");
		labelCpfProf.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCpfProf.setBounds(80, 120, 45, 13);
		backgroundCadastroProf.add(labelCpfProf);
		labelCpfProf.setForeground(new Color(19, 80, 130));
		labelCpfProf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelNomeProf = new JLabel("Nome: ");
		labelNomeProf.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNomeProf.setBounds(80, 82, 45, 13);
		backgroundCadastroProf.add(labelNomeProf);
		labelNomeProf.setForeground(new Color(19, 80, 130));
		labelNomeProf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		passwordFieldSenhaProf = new JPasswordField();
		passwordFieldSenhaProf.setBounds(135, 199, 96, 19);
		backgroundCadastroProf.add(passwordFieldSenhaProf);
		passwordFieldSenhaProf.setForeground(new Color(19, 80, 130));
		passwordFieldSenhaProf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		textFieldDepProf = new JTextField();
		textFieldDepProf.setBounds(135, 157, 96, 19);
		backgroundCadastroProf.add(textFieldDepProf);
		textFieldDepProf.setForeground(new Color(19, 80, 130));
		textFieldDepProf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldDepProf.setColumns(10);

		textFieldCpfProf = new JTextField();
		textFieldCpfProf.setBounds(136, 116, 96, 19);
		backgroundCadastroProf.add(textFieldCpfProf);
		textFieldCpfProf.setForeground(new Color(19, 80, 130));
		textFieldCpfProf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldCpfProf.setColumns(10);

		textFieldNomeProf = new JTextField();
		textFieldNomeProf.setBounds(137, 78, 96, 19);
		backgroundCadastroProf.add(textFieldNomeProf);
		textFieldNomeProf.setForeground(new Color(19, 80, 130));
		textFieldNomeProf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldNomeProf.setColumns(10);

		JLabel labelSenhaProf = new JLabel("Senha: ");
		labelSenhaProf.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSenhaProf.setBounds(61, 203, 64, 13);
		backgroundCadastroProf.add(labelSenhaProf);
		labelSenhaProf.setForeground(new Color(19, 80, 130));
		labelSenhaProf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		JLabel labelDepartProf = new JLabel("Departamento:");
		labelDepartProf.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDepartProf.setBounds(10, 161, 115, 13);
		backgroundCadastroProf.add(labelDepartProf);
		labelDepartProf.setForeground(new Color(19, 80, 130));
		labelDepartProf.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		
		JButton btnCadastrarProf = new JButton("Cadastrar Professor");
		btnCadastrarProf.setBounds(73, 247, 172, 21);
		backgroundCadastroProf.add(btnCadastrarProf);
		btnCadastrarProf.setForeground(new Color(19, 80, 130));
		btnCadastrarProf.setFont(new Font("Roboto Cn", Font.BOLD, 14));
				
		JLabel labelCadastro_1 = new JLabel("CADASTRO");
		labelCadastro_1.setBounds(0, 27, 314, 26);
		backgroundCadastroProf.add(labelCadastro_1);
		labelCadastro_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelCadastro_1.setForeground(new Color(19, 80, 130));
		labelCadastro_1.setFont(new Font("Roboto Cn", Font.BOLD, 24));

		JPanel backgroundLoginProf = new JPanel();
		backgroundLoginProf.setBackground(new Color(174, 211, 242));
		backgroundLoginProf.setBounds(313, 0, 318, 328);
		tabProfessor.add(backgroundLoginProf);
		backgroundLoginProf.setLayout(null);

		JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogin.setBounds(0, 27, 318, 26);
		backgroundLoginProf.add(labelLogin);
		labelLogin.setForeground(new Color(19, 80, 130));
		labelLogin.setFont(new Font("Roboto Cn", Font.BOLD, 24));

		JLabel labelCpfProf_1 = new JLabel("CPF: ");
		labelCpfProf_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCpfProf_1.setBounds(80, 90, 45, 13);
		backgroundLoginProf.add(labelCpfProf_1);
		labelCpfProf_1.setForeground(new Color(19, 80, 130));
		labelCpfProf_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		passwordFieldSenhaLogin = new JPasswordField();
		passwordFieldSenhaLogin.setBounds(135, 124, 96, 19);
		backgroundLoginProf.add(passwordFieldSenhaLogin);
		passwordFieldSenhaLogin.setForeground(new Color(19, 80, 130));
		passwordFieldSenhaLogin.setFont(new Font("Roboto Cn", Font.PLAIN, 14));

		textFieldCpfLogin = new JTextField();
		textFieldCpfLogin.setBounds(135, 86, 96, 19);
		backgroundLoginProf.add(textFieldCpfLogin);
		textFieldCpfLogin.setForeground(new Color(19, 80, 130));
		textFieldCpfLogin.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		textFieldCpfLogin.setColumns(10);

		JButton btnLogarProf = new JButton("Logar Professor");
		btnLogarProf.setBounds(87, 173, 144, 21);
		backgroundLoginProf.add(btnLogarProf);
		btnLogarProf.setForeground(new Color(19, 80, 130));
		btnLogarProf.setFont(new Font("Roboto Cn", Font.BOLD, 14));

		JLabel labelSenhaProf_1 = new JLabel("Senha: ");
		labelSenhaProf_1.setHorizontalAlignment(SwingConstants.RIGHT);
		labelSenhaProf_1.setBounds(78, 128, 47, 13);
		backgroundLoginProf.add(labelSenhaProf_1);
		labelSenhaProf_1.setForeground(new Color(19, 80, 130));
		labelSenhaProf_1.setFont(new Font("Roboto Cn", Font.PLAIN, 14));
		
		Professor prof = new Professor("Rui", "111", "BCC", "111");
		Aluno al = new Aluno("Malu", "111", "BCC");
		Aluno al2 = new Aluno("Enzo", "222", "BCC");

		if(!sistema.existeCPFProfessor(prof.getCpf())) {
			sistema.cadastrarAluno(al);
			sistema.cadastrarAluno(al2);
			sistema.cadastrarProfessor(prof);
		}

		//ABA ALUNO
		btnCadastrarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldNomeAluno.getText();
				String cpf = textFieldCpfAluno.getText();
				String curso = textFieldCursoAluno.getText();

				Aluno aluno = new Aluno(nome, cpf, curso);

				if (sistema.existeCPFAluno(cpf)) {
					JOptionPane.showMessageDialog(frame, "CPF já cadastrado!");
				} else {
					sistema.cadastrarAluno(aluno);
					if (sistema.getAluno(cpf) != null) {
						JOptionPane.showMessageDialog(frame, "Aluno cadastrado com sucesso!");

						DefaultComboBoxModel<Aluno> model = (DefaultComboBoxModel<Aluno>) comboBoxAlunos.getModel();
						List<Aluno> alunosCadastrados = sistema.getListaAlunos();

						Collections.sort(alunosCadastrados, new Comparator<Aluno>() {
							public int compare(Aluno aluno1, Aluno aluno2) {
								return aluno1.getNome().compareToIgnoreCase(aluno2.getNome());
							}
						});

						model.removeAllElements();

						for (Aluno a : alunosCadastrados) {
							model.addElement(a);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Erro ao cadastrar aluno!");
					}
				}

				textFieldNomeAluno.setText("");
				textFieldCpfAluno.setText("");
				textFieldCursoAluno.setText("");
				textFieldNomeEditar.setText("");
				textFieldCpfEditar.setText("");
				textFieldCursoEditar.setText("");
				comboBoxAlunos.setSelectedIndex(-1);

				// Faça algo com os valores obtidos, como salvar no banco de dados ou exibir em uma mensagem
				System.out.println("Nome: " + nome);
				System.out.println("CPF: " + cpf);
				System.out.println("Curso: " + curso);
			}
		});

		comboBoxAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno alunoSelecionado = (Aluno) comboBoxAlunos.getSelectedItem();

				if (alunoSelecionado != null) {
					textFieldNomeEditar.setText(alunoSelecionado.getNome());
					textFieldCpfEditar.setText(String.valueOf(alunoSelecionado.getCpf()));
					textFieldCursoEditar.setText(alunoSelecionado.getCurso());
				}
			}
		});

		btnEditarAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno alunoSelecionado = (Aluno) comboBoxAlunos.getSelectedItem();
				String nome = textFieldNomeEditar.getText();
				String cpf = textFieldCpfEditar.getText();
				String curso = textFieldCursoEditar.getText();

				if(alunoSelecionado == null) {
					JOptionPane.showMessageDialog(frame, "Selecione um aluno!");
				} else {
					sistema.editarAluno(alunoSelecionado, nome, curso, cpf);

					if (sistema.getAluno(cpf).getNome().equals(nome) && sistema.getAluno(cpf).getCurso().equals(curso)) {
						JOptionPane.showMessageDialog(frame, "Aluno editado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(frame, "Erro ao editar aluno!");
					}
				}
			}
		});

		btnExcluirAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno alunoSelecionado = (Aluno) comboBoxAlunos.getSelectedItem();
				String cpf = textFieldCpfEditar.getText();

				if(alunoSelecionado == null) {
					JOptionPane.showMessageDialog(frame, "Selecione um aluno!");
				} else {
					sistema.removerAluno(alunoSelecionado);

					if(sistema.getAluno(cpf) == null) {
						JOptionPane.showMessageDialog(frame, "Aluno excluído com sucesso!");
					} else {
						JOptionPane.showMessageDialog(frame, "Erro ao excluir aluno!");
					}
				}
			}
		});
		//FIM ABA ALUNO

		//ABA PROFESSOR
		btnCadastrarProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldNomeProf.getText();
				String cpf = textFieldCpfProf.getText();
				String departamento = textFieldDepProf.getText();
				String senha = passwordFieldSenhaProf.getText();

				Professor professor = new Professor(nome, cpf, departamento, senha);

				if (sistema.existeCPFProfessor(cpf)) {
					JOptionPane.showMessageDialog(frame, "CPF já cadastrado!");
				} else {
					sistema.cadastrarProfessor(professor);
					if (sistema.getProfessor(cpf) != null) {
						JOptionPane.showMessageDialog(frame, "Professor cadastrado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(frame, "Erro ao cadastrar professor!");
					}
				}

				textFieldNomeProf.setText("");
				textFieldCpfProf.setText("");
				textFieldDepProf.setText("");
				passwordFieldSenhaProf.setText("");

				// Faça algo com os valores obtidos, como salvar no banco de dados ou exibir em uma mensagem
				System.out.println("Nome: " + nome);
				System.out.println("CPF: " + cpf);
				System.out.println("Departamento: " + departamento);
				System.out.println("Senha: " + senha);
			}
		});

		btnLogarProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = textFieldCpfLogin.getText();
				String senha = passwordFieldSenhaLogin.getText();

				Professor professor = sistema.getProfessor(cpf);

				if (professor != null) {
					if (sistema.logarProfessor(cpf, senha)) {
						professorLogado = professor;
						JOptionPane.showMessageDialog(frame, "Professor logado com sucesso!");

						TelaSecundaria secundaria = new TelaSecundaria(professorLogado, sistema);
						secundaria.frame.setVisible(true);

						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(frame, "Senha incorreta!");
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Professor não encontrado!");
				}

				textFieldCpfLogin.setText("");
				passwordFieldSenhaLogin.setText("");
			}
		});
		//FIM ABA PROFESSOR
	}
}
