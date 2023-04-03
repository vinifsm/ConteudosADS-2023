class LivroRepetidoException implements Exception {
  String toString() {
    return 'Erro: Livro Repetido';
  }
}

class NenhumEmprestadoException implements Exception {
  String toString() {
    return 'Erro: Nenhum livro emprestado';
  }
}
