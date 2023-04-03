abstract class Pessoa {
  String nome;
  int idade;

  Pessoa({required this.nome, required this.idade});
}

mixin Orientar {
  List<String> alunos = [];

  int orientar(String aluno) {
    alunos.add(aluno);
    return alunos.length;
  }

  getTotalOrientandos() {
    return alunos.length;
  }
}

mixin MinistrarAula {
  List<String> aulas = [];

  int ministrarAula(String disciplina, String conteudo) {
    aulas.add('${disciplina} - ${conteudo}');
    return aulas.length;
  }

  getTotalAulas() {
    return aulas.length;
  }
}

abstract class Professor extends Pessoa with Orientar, MinistrarAula {
  String matricula;
  String tituloAcademico;

  Professor(
      {required this.matricula,
      required String nome,
      required this.tituloAcademico,
      idade = 0})
      : super(nome: nome, idade: idade);

  String toString() {
    return '${this.runtimeType.toString()} : ${matricula} - ${nome} - ${tituloAcademico} - ${idade} - ${alunos} - ${aulas}';
  }

  int getQuantidadeMaximaOrientandos();

  bool podeOrientarPIBIC();
}

class ProfessorMestre extends Professor {
  ProfessorMestre({required String matricula, required String nome, idade = 0})
      : super(
            matricula: matricula,
            nome: nome,
            tituloAcademico: 'Me',
            idade: idade);

  @override
  int getQuantidadeMaximaOrientandos() {
    return 2;
  }

  @override
  bool podeOrientarPIBIC() {
    return false;
  }
}

class ProfessorDoutor extends Professor {
  ProfessorDoutor({required String matricula, required String nome, idade = 0})
      : super(
            matricula: matricula,
            nome: nome,
            tituloAcademico: 'Dr',
            idade: idade);

  @override
  int getQuantidadeMaximaOrientandos() {
    return 5;
  }

  @override
  bool podeOrientarPIBIC() {
    return true;
  }
}

void main() {
  print('Orientação a Objetos');

  Professor professor1 =
      ProfessorMestre(matricula: '1234', nome: 'Guilherme', idade: 34);

  print(professor1.toString());
  print(professor1.getQuantidadeMaximaOrientandos());
  print(professor1.podeOrientarPIBIC());

  professor1.orientar('Almir');
  professor1.orientar('Alex');
  professor1.orientar('Diomara');

  professor1.ministrarAula(
      'Engenharia de Software', 'Levantamento de Requisitos');
  professor1.ministrarAula('Engenharia de Software', 'Testes de Software');
  professor1.ministrarAula('Bancos de Dados I', 'Introdução à SQL');
  professor1.ministrarAula('Tópicos Avançados', 'Ciência de Dados com Python');

  print(professor1.toString());
  print('${professor1.getTotalOrientandos()} orientandos');
  print('${professor1.getTotalAulas()} aulas');
}
