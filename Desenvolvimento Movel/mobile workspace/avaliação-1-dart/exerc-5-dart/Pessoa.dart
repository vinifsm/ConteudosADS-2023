abstract class Pessoa {
  String nome;
  int idade;

  Pessoa({
    required this.nome,
    required this.idade,
  });

  String toString() {
    return '${this.runtimeType.toString()} : ${nome} - ${idade}';
  }
}
