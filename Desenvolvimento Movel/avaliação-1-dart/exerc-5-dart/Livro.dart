import 'MixinRepository.dart';
import 'Editora.dart';
import 'Autor.dart';

enum Categoria { Romance, Fantasia, Horror, Aventura, Tecnologia }

class Livro {
  int id;
  String titulo;
  Autor autor;
  Editora editora;
  Categoria categoria;
  int anoPublicacao;

  Livro({
    required this.id,
    required this.titulo,
    required this.autor,
    required this.editora,
    required this.categoria,
    required this.anoPublicacao,
  });

  String toString() {
    return '${this.runtimeType.toString()} : ${titulo} - ${autor.nome} - ${editora.nome} - ${categoria} - ${anoPublicacao}';
  }
}

class LivroRepository with MixinRepository<Livro> {}
