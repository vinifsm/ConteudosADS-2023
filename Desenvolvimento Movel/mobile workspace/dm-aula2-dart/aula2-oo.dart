mixin RepositoryMixin<T> {
  List<T> _items = [];

  void adicionar(T item) {
    _items.add(item);
    print('Adicionado...: $item');
  }

  void remover(T item) {
    _items.remove(item);
    print('Removido.....: $item');
  }

  List<T> listar() {
    return _items;
  }
}

abstract class Pessoa {
  String nome;
  int? idade;

  Pessoa(this.nome, this.idade);

  void imprimirNome() {
    print(nome);
  }
}

abstract class Professor extends Pessoa with Orientar, MinistrarAulas {
  int matricula;
  String tituloAcademico;

  Professor(
      {required this.matricula,
      required String nome,
      required this.tituloAcademico})
      : super(nome, null);

  @override
  String toString() {
    return '${tituloAcademico} ${nome} - ${matricula}';
  }

  double calcularSalario(int quantidadeAulas);
}

class ProfessorMestre extends Professor {
  ProfessorMestre({required int matricula, required String nome})
      : super(matricula: matricula, nome: nome, tituloAcademico: 'Me');

  @override
  double calcularSalario(int quantidadeAulas) {
    return quantidadeAulas * 40.0;
  }
}

class ProfessorDoutor extends Professor {
  ProfessorDoutor({required int matricula, required String nome})
      : super(matricula: matricula, nome: nome, tituloAcademico: 'Dr');
  @override
  double calcularSalario(int quantidadeAulas) {
    return quantidadeAulas * 60.0;
  }
}

class ProfessorEspecialista extends Professor {
  ProfessorEspecialista({required int matricula, required String nome})
      : super(matricula: matricula, nome: nome, tituloAcademico: 'Esp');

  @override
  double calcularSalario(int quantidadeAulas) {
    return quantidadeAulas * 20.0;
  }
}

mixin Orientar {
  List<String> alunos = [];
  int orientar(String aluno) {
    alunos.add(aluno);
    return alunos.length;
  }

  int concluir(String aluno) {
    alunos.remove(aluno);
    return alunos.length;
  }

  List<String> listarAlunos() {
    return alunos;
  }

  int getQuantidadeAlunos() {
    return alunos.length;
  }
}

mixin MinistrarAulas {
  List<String> conteudos = [];
  int adicionar(String disciplina, String conteudo) {
    conteudos.add(disciplina + "," + conteudo);
    return conteudos.length;
  }

  List<String> listarConteudos() {
    return conteudos;
  }

  int getQuantidadeConteudos() {
    return conteudos.length;
  }
}

class Aluno extends Pessoa {
  int ra;
  bool matriculado;

  Aluno({required this.ra, required String nome, this.matriculado = false})
      : super(nome, null);

  @override
  String toString() {
    return '${ra} | ${nome} | ${matriculado}';
  }

  void matricular() {
    this.matriculado = true;
  }

  void cancelarMatricula() {
    this.matriculado = false;
  }
}

void main(List<String> args) {
  Aluno a1 = new Aluno(ra: 1, nome: "Nom");

  print(a1);
  a1.cancelarMatricula();
  print(a1);
  a1.matricular();
  print(a1);

  print('---');
  a1.imprimirNome();

  print('---');
  Professor mestre = new ProfessorMestre(matricula: 01, nome: "Guilherme");
  Professor doutor = new ProfessorDoutor(matricula: 02, nome: "Alex");
  Professor espec = new ProfessorEspecialista(matricula: 03, nome: "Flavio");
  print(mestre.calcularSalario(10));
  print(doutor.calcularSalario(10));
  print(espec.calcularSalario(10));

  mestre.orientar('João');
  mestre.orientar('Maria');

  print(mestre.getQuantidadeAlunos());
  print(mestre.listarAlunos());

  doutor.adicionar('Dev Mobile', 'Flutter');
  doutor.adicionar('Dev Mobile', 'Dart');
  doutor.adicionar('Eng de Software', 'Scrum');

  print(doutor.getQuantidadeConteudos());
  print(doutor.listarConteudos());
}
