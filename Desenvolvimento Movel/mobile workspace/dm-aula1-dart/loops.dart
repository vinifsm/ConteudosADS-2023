void main(List<String> args) {
  List<double> salario = [];
  args.forEach((element) {
    salario.add(double.parse(element));
  });
  salario.sort();
  double soma = 0;
  salario.forEach((element) {
    soma += element;
  });

  print('A quantidade de salários é: ${salario.length}');

  print('O menor salário é: ${salario.first}');
  print('O maior salário é: ${salario.last}');
  print('A média é: ${(soma / salario.length).toStringAsFixed(2)}');
  print('A soma é: ${soma}');
  print('Salários em ordem crescente: ${salario}');
  print('Salários em ordem decrescente: ${salario.reversed}');

  print('Menores salários: ${salario[0]} ${salario[1]} ${salario[2]}');
  print(
      'Maiores salários: ${salario[salario.length - 1]} ${salario[salario.length - 2]} ${salario[salario.length - 3]}');
  salario.forEach((element) {
    print(
        '${element}: [5%: ${(element * 1.05).toStringAsFixed(2)}| 10%: ${(element * 1.10).toStringAsFixed(2)}| 15%: ${(element * 1.15).toStringAsFixed(2)}]');
  });
  print(salario.sublist(0, 3));
}
