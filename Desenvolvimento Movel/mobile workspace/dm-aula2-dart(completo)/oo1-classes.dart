class Aluno {
  String ra;
  String nome;
  int idade;
  String curso;

  Aluno(
      {required this.ra,
      required this.nome,
      required this.curso,
      this.idade = 0});

  String toString() {
    return '${this.runtimeType.toString()} : ${ra} - ${nome} - ${curso} - ${idade}';
  }
}

void main() {
  print('Orientação a Objetos');

  Aluno aluno1 = Aluno(ra: '1234', nome: 'Guilherme', curso: 'BCC');
  Aluno aluno2 = Aluno(ra: '1234', nome: 'Guilherme', curso: 'BCC', idade: 34);

  print(aluno1.toString());
  print(aluno2.toString());
}
