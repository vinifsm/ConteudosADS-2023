import 'dart:collection';

// Classe abstrata Pessoa
abstract class Pessoa {
  String nome;
  String telefone;

  Pessoa(this.nome, {this.telefone});

  @override
  String toString() {
    return 'Nome: $nome\nTelefone: $telefone';
  }
}

// Classe Autor que herda de Pessoa e implementa o mixin AutorMixin
class Autor extends Pessoa with AutorMixin {
  String email;

  Autor(String nome, {String telefone, this.email})
      : super(nome, telefone: telefone);

  @override
  String toString() {
    return '${super.toString()}\nE-mail: $email';
  }
}

// Mixin AutorMixin
mixin AutorMixin {
  List<String> obras = [];

  void adicionarObra(String obra) {
    obras.add(obra);
  }
}

// Classe Editora
class Editora {
  String nome;
  String endereco;

  Editora(this.nome, this.endereco);

  @override
  String toString() {
    return 'Nome: $nome\nEndereço: $endereco';
  }
}

// Enumeração Categoria
enum Categoria { romance, ficcao, aventura, suspense, biografia }

// Classe Livro que implementa o mixin AutorMixin
class Livro with AutorMixin {
  String titulo;
  Editora editora;
  int anoPublicacao;
  Categoria categoria;

  Livro(this.titulo, this.editora, this.anoPublicacao, this.categoria);

  @override
  String toString() {
    return 'Título: $titulo\nEditora: $editora\nAno de publicação: $anoPublicacao\nCategoria: $categoria\nObras do autor: ${obras.join(", ")}';
  }
}

// Classe Aluno
class Aluno extends Pessoa {
  String matricula;

  Aluno(String nome, {String telefone, this.matricula})
      : super(nome, telefone: telefone);

  // Método que permite realizar a reserva de um livro
  void reservarLivro(Biblioteca biblioteca, Livro livro) {
    biblioteca.reservarLivro(this, livro);
  }

  // Método que permite realizar o empréstimo de um livro
  void emprestarLivro(Biblioteca biblioteca, Livro livro) {
    biblioteca.emprestarLivro(this, livro);
  }

  // Método que permite realizar a devolução de um livro
  void devolverLivro(Biblioteca biblioteca, Livro livro) {
    biblioteca.devolverLivro(this, livro);
  }

  @override
  String toString() {
    return '${super.toString()}\nMatrícula: $matricula';
  }
}

// Classe Biblioteca
class Biblioteca {
  Map<Aluno, List<Livro>> livrosEmprestados = {};

  void adicionarLivro(Aluno aluno, Livro livro) {
    // Se o aluno ainda não tiver nenhum livro emprestado, cria uma nova lista
    if (!_map.containsKey(aluno)) {
      _map[aluno] = <Livro>[];
    }

    // Adiciona o livro à lista de livros emprestados do aluno
    _map[aluno]!.add(livro);
  }

  // Método para adicionar um livro à lista de livros disponíveis na biblioteca
  void adicionarLivro(Livro livro) {
    livros.add(livro);
  }

  // Método para remover um livro da lista de livros disponíveis na biblioteca
  void removerLivro(Livro livro) {
    livros.remove(livro);
  }
}
