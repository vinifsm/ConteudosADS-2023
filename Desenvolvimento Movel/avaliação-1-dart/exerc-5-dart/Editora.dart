import 'MixinRepository.dart';

class Editora {
  int id;
  String nome;
  String endereco;
  String telefone;

  Editora(
      {required this.id,
      required this.nome,
      this.endereco = 'Não Informado',
      this.telefone = 'Não Informado'});

  String toString() {
    return '${this.runtimeType.toString()} : ${nome} - ${endereco} - ${telefone}';
  }
}

class EditoraRepository with MixinRepository<Editora> {}
