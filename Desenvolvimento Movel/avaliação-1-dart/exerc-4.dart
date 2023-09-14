import 'dart:io';
import 'dart:math';

extension InputExtensions on Stdin {
  String read(String message) {
    print('> ${message}');
    return stdin.readLineSync() ?? '';
  }

  double readAsDouble(String message) {
    print('> ${message}');
    final String value = stdin.readLineSync() ?? '-1.00';
    return double.tryParse(value) ?? -1.00;
  }
}

void main(List<String> args) {
  final String nome = stdin.read('Informe seu nome:');
  final double altura = stdin.readAsDouble('Informe sua altura:');
  final double peso = stdin.readAsDouble('Informe seu peso:');

  final double imc = peso / pow(altura, 2);
  final String resultado;
  if (imc < 18.5) {
    resultado = 'Peso baixo';
  } else if (imc >= 18.5 && imc < 25) {
    resultado = 'Peso normal';
  } else if (imc >= 25 && imc < 30) {
    resultado = 'Sobrepeso';
  } else if (imc >= 30 && imc < 35) {
    resultado = 'Obesidade grau I';
  } else if (imc >= 35 && imc < 40) {
    resultado = 'Obesidade grau II';
  } else {
    resultado = 'Obesidade Mórbida';
  }
  print(
      'Resultado do IMC do ${nome}: ${imc.toStringAsFixed(1)} (${resultado})');
}
