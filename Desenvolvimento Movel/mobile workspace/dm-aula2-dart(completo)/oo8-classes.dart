class IdadeInvalidaException implements Exception {
  int idade;

  IdadeInvalidaException({required this.idade});

  @override
  String toString() {
    return 'Idade inválida: ${idade}';
  }
}

class Pessoa {
  String nome;
  int idade;

  // regras de negócio no construtor
  Pessoa(this.nome, this.idade) {
    if (idade < 16 || idade > 100) {
      throw IdadeInvalidaException(idade: idade);
    }
  }

  void aniversario() {
    idade++;
  }
}

class Aluno extends Pessoa {
  String curso;

  Aluno({required String nome, required int idade, required this.curso})
      : super(nome, idade);

  void matricular(String curso) {
    this.curso = curso;
  }
}

void main() {
  try {
    var aluno =
        Aluno(nome: 'Guilherme', idade: 14, curso: 'Engenharia de Software');

    print('Nome....: ${aluno.nome}');
    print('Idade...: ${aluno.idade}');
    print('Curso...: ${aluno.curso}');

    aluno.matricular('Desenvolvimento Móvel');

    print('Curso...: ${aluno.curso}');
  } catch (e) {
    print(e.toString());
  }
}
