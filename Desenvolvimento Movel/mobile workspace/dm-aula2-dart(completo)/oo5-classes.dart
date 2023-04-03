class Pessoa {
  String? _nome;
  int? _idade;

  Pessoa({required String nome, required int idade}) {
    this._nome = nome;
    this._idade = idade;
  }
}

class Aluno extends Pessoa {
  String ra;
  String curso;

  Aluno(
      {required this.ra, required String nome, required this.curso, idade = 0})
      : super(nome: nome, idade: idade);

  String toString() {
    return '${this.runtimeType.toString()} : ${ra} - ${_nome} - ${curso} - ${_idade}';
  }
}

void main() {
  print('Orientação a Objetos');

  Aluno aluno1 = Aluno(ra: '1234', nome: 'Guilherme', curso: 'BCC');
  Aluno aluno2 = Aluno(ra: '1234', nome: 'Guilherme', curso: 'BCC', idade: 34);

  print(aluno1.toString());
  print(aluno2.toString());
}
