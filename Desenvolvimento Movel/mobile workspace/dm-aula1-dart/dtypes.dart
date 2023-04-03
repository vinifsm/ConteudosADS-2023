import 'package:intl/intl.dart';

void main(List<String> args) {
  var id = '123-456-789';

  String curso = 'Análise e Desenvolvimento de Sistemas';
  int turma = 11;
  double mensalidade = 950.00;
  bool matriculado = true;

  // https://pub.dev/documentation/intl/latest/intl/DateFormat-class.html
  final DateFormat DATE_FORMATTER = DateFormat('dd/MM/yyyy');

  DateTime dataAtual = DateTime.now();
  DateTime dataMatricula = DATE_FORMATTER.parse('15/12/2022');
  int diasMatriculado = dataAtual.difference(dataMatricula).inDays;

  List<double> notas = [9.5, 7.8, 10.0];
  Map<String, List<String>> professores = {
    'dr': ['alex', 'almir', 'marisa'],
    'me': ['diomara', 'guilherme']
  };
  Set<String> formacoesAcademicas = {
    'graduação',
    'especialização',
    'mestrado',
    'doutorado',
    'pós-doutorado'
  };

  professores['me']?.add('fernando');
  professores['me']?[1] = 'guilherme.farto';
  professores.putIfAbsent('esp', () => []);

  print(id);

  print(curso);
  print(turma);
  print(mensalidade);
  print(matriculado);

  print(dataAtual);
  print(DATE_FORMATTER.format(dataAtual));
  print(dataMatricula);
  print(diasMatriculado);

  print(notas);
  print(professores);
  print(professores['me']);
  print(formacoesAcademicas);

  print(id.runtimeType);
  print(id.runtimeType == String);
  print(notas.runtimeType);
  print(notas.runtimeType == List<double>);

  final String password = '123abc';
  print(password);
}
