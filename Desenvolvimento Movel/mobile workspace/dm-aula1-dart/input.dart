import 'dart:io';

extension InputExtensions on Stdin {
  String read(String message) {
    print('> ${message}');
    return stdin.readLineSync() ?? '';
  }

  int readAsInt(String message) {
    print('> ${message}');
    final String value = stdin.readLineSync() ?? '-1';
    return int.tryParse(value) ?? -1;
  }

  double readAsDouble(String message) {
    print('> ${message}');
    final String value = stdin.readLineSync() ?? '-1.00';
    return double.tryParse(value) ?? -1.00;
  }

  List<String> readAsList(String message) {
    print('> ${message}');
    final String value = stdin.readLineSync() ?? '';
    return value
        .split(',')
        .map((item) => item.trim())
        .where((item) => item.isNotEmpty)
        .toList();
  }
}

void main(List<String> args) {
  final String nome = stdin.read('Informe seu nome:');
  final int idade = stdin.readAsInt('Informe sua idade:');
  final double salario = stdin.readAsDouble('Informe seu salário:');
  final List<String> cargos = stdin.readAsList('Informe seus cargos:');

  print('nome......: ${nome}');
  print('idade.....: ${idade}');
  print('salário...: ${salario.toStringAsFixed(2)}');
  print('cargos....: ${cargos}');
}
