int calcularAnoNascimento(int idade, {int anoAtual = 2023}) {
  return anoAtual - idade;
}

void main(List<String> args) {
  String nome = 'Guilherme';
  int idade = 34;

  print('Olá, ' + nome + '. Você nasceu em ' + (2023 - idade).toString() + '.');
  print('Olá, ${nome}. Você nasceu em ${2023 - idade}.');
  print('Olá, ${nome}. Você nasceu em ${calcularAnoNascimento(idade)}.');
  print(
      'Olá, ${nome}. Você nasceu em ${calcularAnoNascimento(idade, anoAtual: 2023)}.');
  print('Olá, ${nome}. Você nasceu em ${calcularAnoNascimento(idade)}.');
}
