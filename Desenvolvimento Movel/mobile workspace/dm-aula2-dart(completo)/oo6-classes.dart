class Pessoa {
  String nome;
  int idade;
  double altura;
  double peso;

  Pessoa(this.nome, this.idade, this.altura, this.peso);

  double calcularIMC() {
    return peso / (altura * altura);
  }

  bool isMaiorDeIdade() {
    return idade >= 18;
  }
}

void main() {
  Pessoa pessoa1 = Pessoa('Guilherme', 25, 1.8, 70);
  Pessoa pessoa2 = Pessoa('Diomara', 16, 1.6, 50);

  double imcPessoa1 = pessoa1.calcularIMC();
  double imcPessoa2 = pessoa2.calcularIMC();

  bool maiorDeIdadePessoa1 = pessoa1.isMaiorDeIdade();
  bool maiorDeIdadePessoa2 = pessoa2.isMaiorDeIdade();

  print(
      '${pessoa1.nome} tem IMC de $imcPessoa1 e é maior de idade? $maiorDeIdadePessoa1');
  print(
      '${pessoa2.nome} tem IMC de $imcPessoa2 e é maior de idade? $maiorDeIdadePessoa2');
}
