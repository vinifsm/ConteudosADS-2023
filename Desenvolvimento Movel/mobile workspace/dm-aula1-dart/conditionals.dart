bool equalsIgnoreCase(o1, o2) {
  return o1.toLowerCase() == o2.toLowerCase();
}

extension StringExtensions on String {
  bool equalsIgnoreCase(String other) =>
      this.toLowerCase() == other.toLowerCase();
}

void main(List<String> args) {
  final String curso = 'ADS';

  if (equalsIgnoreCase(curso, 'ADS')) {
    print('Duração: 3 anos');
  } else if (curso.equalsIgnoreCase('BCC')) {
    print('Duração: 4 anos');
  } else {
    print('Curso não reconhecido');
  }

  print('---');

  switch (curso.toLowerCase()) {
    case 'ads':
      print('Duração: 3 anos');
      break;
    case 'bcc':
      print('Duração: 4 anos');
      break;
    default:
      print('Curso não reconhecido');
  }

  print('---');

  final String evento = 'GERAR_BOLETO';
  // final String evento = 'PAGAR_BOLETO';

  switch (evento) {
    case 'GERAR_BOLETO':
      print('Gerando boleto...');
      continue pagarBoleto;
    pagarBoleto:
    case 'PAGAR_BOLETO':
      print('Pagando boleto...');
      break;
  }

  print('---');

  final String GUEST_VALUE = 'guest';

  String usuario = 'guest';
  bool isVisitante =
      usuario.trim() == '' || usuario.toLowerCase() == GUEST_VALUE
          ? true
          : false;

  print('"${usuario}" : isVisitante = ${isVisitante}');
}
