mixin RepositoryMixin<T> {
  List<T> _items = [];

  void adicionar(T item) {
    _items.add(item);
    print('Adicionado...: $item');
  }

  void remover(T item) {
    _items.remove(item);
    print('Removido.....: $item');
  }

  List<T> listar() {
    return _items;
  }
}

class AlunoRepository with RepositoryMixin<String> {}

void main() {
  AlunoRepository userRepository = AlunoRepository();

  userRepository.adicionar('Guilherme');
  userRepository.adicionar('Diomara');
  userRepository.remover('Alex');

  print(userRepository.listar());
}
