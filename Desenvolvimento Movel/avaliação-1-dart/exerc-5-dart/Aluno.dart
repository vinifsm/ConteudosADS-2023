import 'Pessoa.dart';
import 'MixinRepository.dart';

class Aluno extends Pessoa {
  String ra;
  String curso;

  Aluno(
      {required this.ra,
      required String nome,
      required int idade,
      this.curso = 'Não Informado'})
      : super(nome: nome, idade: idade);

  String toString() {
    return '${this.runtimeType.toString()} : ${nome} - ${ra} - ${curso} - ${idade}';
  }
}

class AlunoRepository with MixinRepository<Aluno> {}
