void main(List<String> args) {
  List<String> aprovados = [];
  args.forEach((element) {
    final nota = element.split(':');

    if (double.parse(nota[1]) >= 7) {
      aprovados.add(nota[0]);
    }
  });
  print(aprovados);
}
