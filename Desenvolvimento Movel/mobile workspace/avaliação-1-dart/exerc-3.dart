import 'dart:io';

extension RaExtensions on String {
  String anoRA() {
    return 'Ano: 20${this.substring(0, 2)}';
  }

  String semestreRA() {
    return 'Semestre: ${this.substring(2, 3)} °';
  }

  String cursoRA() {
    return 'Curso: ${this.substring(3, 6)}';
  }

  String matriculaRA() {
    return 'Matrícula: ${this.substring(6, 9)}';
  }

  String digitoRA() {
    return 'Dígito: ${this.substring(9, 10)}';
  }
}

extension InputExtensions on Stdin {
  String read(String message) {
    print('> ${message}');
    return stdin.readLineSync() ?? '';
  }
}

void main(List<String> args) {
  String ra = stdin.read('Informe seu RA:');

  print(ra.anoRA());
  print(ra.semestreRA());
  print(ra.cursoRA());
  print(ra.matriculaRA());
  print(ra.digitoRA());
}
