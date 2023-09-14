import 'MixinRepository.dart';
import 'Pessoa.dart';

class Autor extends Pessoa {
  int id;
  String email;

  Autor(
      {required this.id,
      required String nome,
      required int idade,
      this.email = 'Não Informado'})
      : super(nome: nome, idade: idade);

  String toString() {
    return '${this.runtimeType.toString()} : ${nome} - ${email} - ${idade}';
  }
}

class AutorRepository with MixinRepository<Autor> {}
