import 'Aluno.dart';
import 'Autor.dart';
import 'Editora.dart';
import 'Livro.dart';
import 'Biblioteca.dart';

void main() {
  Aluno a1 = new Aluno(ra: '1', nome: 'Gabriel', idade: 22, curso: 'ADS');
  Aluno a2 = new Aluno(ra: '2', nome: 'Guilherme', idade: 20, curso: 'ADS');
  Aluno a3 = new Aluno(ra: '3', nome: 'Carlos', idade: 27, curso: 'ADS');
  Autor at1 = new Autor(id: 1, nome: 'Ernesto Haberkon', idade: 80);
  Autor at2 = new Autor(id: 2, nome: 'Roger S. Pressman', idade: 75);
  Autor at3 = new Autor(id: 3, nome: 'Júlio Verne', idade: 77);
  Editora e1 = new Editora(id: 1, nome: 'Abril');
  Livro l1 = new Livro(
      id: 1,
      titulo: 'Um Bate-papo Sobre T.I.',
      autor: at1,
      editora: e1,
      categoria: Categoria.Tecnologia,
      anoPublicacao: 2017);
  Livro l2 = new Livro(
      id: 2,
      titulo: 'Engenharia de Software: Uma Abordagem Profissional',
      autor: at2,
      editora: e1,
      categoria: Categoria.Tecnologia,
      anoPublicacao: 2016);
  Livro l3 = new Livro(
      id: 3,
      titulo: 'A Volta ao Mundo em 80 dias',
      autor: at3,
      editora: e1,
      categoria: Categoria.Aventura,
      anoPublicacao: 1872);

  Biblioteca biblio = new Biblioteca();
  biblio.adicionarAluno(a1);
  biblio.adicionarAluno(a2);
  biblio.adicionarAluno(a3);
  print('\n');
  biblio.adicionarLivro(l1);
  biblio.adicionarLivro(l2);
  biblio.adicionarLivro(l3);
  print('\n');
  biblio.listarAlunos();
  print('\n');
  biblio.listarLivros();
  print('\n');
  biblio.emprestarLivro(a1, l1);
  try {
    biblio.emprestarLivro(a1, l1);
  } catch (e) {
    print(e.toString());
  }
  try {
    biblio.emprestarLivro(a1, l3);
  } catch (e) {
    print(e.toString());
  }
  try {
    biblio.devolverLivro(a1, l1);
  } catch (e) {
    print(e.toString());
  }
  try {
    biblio.devolverLivro(a2, l2);
  } catch (e) {
    print(e.toString());
  }
  print(biblio.livrosEmp);
}
