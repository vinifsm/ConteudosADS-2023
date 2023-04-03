void main(List<String> args) {
  print('Hello, Dart : ' + args.join(','));

  print(args);

  args.forEach((element) {
    print('> ' + element);
  });

  if (args.length > 0) {
    // double nota1 = double.parse(args[0]);
    // double nota2 = double.parse(args[1]);
    // double nota3 = double.parse(args[2]);
    // double media = double.parse(((nota1 + nota2 + nota3) / 3).toStringAsFixed(2));
    // print(media);

    double media2 = args
            .map((arg) => double.parse(arg))
            .reduce((value, element) => value + element) /
        args.length;
    media2 = double.parse(media2.toStringAsFixed(2));
    print(media2);
  }
}
