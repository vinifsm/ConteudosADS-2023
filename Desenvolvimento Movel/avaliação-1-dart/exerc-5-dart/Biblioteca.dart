import 'Livro.dart';
import 'Aluno.dart';
import 'Exception.dart';

class Biblioteca {
  Map<Aluno, List<Livro>> livrosEmp = {};
  AlunoRepository alunoRepository = AlunoRepository();
  LivroRepository livroRepository = LivroRepository();
  void emprestarLivro(Aluno aluno, Livro livro) {
    if (!livrosEmp.containsKey(aluno)) {
      livrosEmp[aluno] = <Livro>[];
    }
    if (livrosEmp[aluno]!.contains(livro)) {
      throw LivroRepetidoException();
    }
    livrosEmp[aluno]!.add(livro);
    print('Livro emprestado com sucesso');
  }

  void devolverLivro(Aluno aluno, Livro livro) {
    if (!livrosEmp.containsKey(aluno)) {
      throw NenhumEmprestadoException();
    }
    livrosEmp[aluno]!.remove(livro);

    if (livrosEmp[aluno]!.isEmpty) {
      livrosEmp.remove(aluno);
    }
    print('Livro devolvido com sucesso');
  }

  void adicionarAluno(Aluno aluno) {
    alunoRepository.adicionar(aluno);
  }

  void removerAluno(Aluno aluno) {
    alunoRepository.remover(aluno);
  }

  void listarAlunos() {
    print(alunoRepository.listar());
  }

  void adicionarLivro(Livro livro) {
    livroRepository.adicionar(livro);
  }

  void removerLivro(Livro livro) {
    livroRepository.remover(livro);
  }

  void listarLivros() {
    print(livroRepository.listar());
  }
}
