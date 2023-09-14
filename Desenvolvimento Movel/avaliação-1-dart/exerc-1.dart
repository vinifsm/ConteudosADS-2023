void main(List<String> args) {
  List<double> notas = [];
  args.forEach((element) {
    notas.add(double.parse(element));
  });
  notas.sort();
  double soma = 0;
  notas.forEach((element) {
    soma += element;
  });

  print('A média é: ${(soma / notas.length).toStringAsFixed(2)}');
}
