abstract class Pessoa {
  String nome;
  int idade;

  Pessoa({required this.nome, required this.idade});
}

abstract class Professor extends Pessoa {
  String matricula;
  String tituloAcademico;

  Professor(
      {required this.matricula,
      required String nome,
      required this.tituloAcademico,
      idade = 0})
      : super(nome: nome, idade: idade);

  String toString() {
    return '${this.runtimeType.toString()} : ${matricula} - ${nome} - ${tituloAcademico} - ${idade}';
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
  Professor professor2 =
      ProfessorDoutor(matricula: '1234', nome: 'Guilherme', idade: 34);

  print(professor1.toString());
  print(professor1.getQuantidadeMaximaOrientandos());
  print(professor1.podeOrientarPIBIC());

  print(professor2.toString());
  print(professor2.getQuantidadeMaximaOrientandos());
  print(professor2.podeOrientarPIBIC());
}
