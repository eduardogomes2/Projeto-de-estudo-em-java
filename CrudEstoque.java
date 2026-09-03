import java.util.ArrayList;
import java.util.Scanner;

public class CrudEstoque {
    public static void main(String[] args) {
      ArrayList<Produto> produtos = new ArrayList<>();

      Scanner scanner = new Scanner(System.in);
      
      Produto produto1 = new Produto(1, "Teclado", 10, 150.49);
      
      
    
      Produto produto2 = new Produto(2, "Mouse", 20, 50.00);
      

      produtos.add(produto1);
      produtos.add(produto2);
      int proximoId = 3;

      

      

      int opcao;

      do{
            exibirMenu();
            

            opcao = lerOpcaoMenu(scanner);


            switch (opcao){
                case 1:
                System.out.println("Opcao de cadastro selecionada");
                System.out.println("------------------------------------");

                Produto novoProduto = cadastrarProduto(scanner, proximoId);

                produtos.add(novoProduto);
                proximoId++;

                System.out.println("Produto cadastrado com sucesso!");
                
                System.out.println("------------------------------------");
                break;
                
                
                case 2:
                listarProdutos(produtos);
                                
                break;  
                
                
                
                
                
                
                
                
                case 3:{
                    System.out.println("Opcao de busca selecionada");
                    System.out.println("------------------------------------");
                    System.out.println("Digite o ID do produto que deseja buscar:");
                    int idBusca = scanner.nextInt();
                    scanner.nextLine();

                    Produto produtoEncontrado = buscarProduto(produtos, idBusca);

                    if (produtoEncontrado != null){
                        System.out.println("Produto encontrado:");
                        System.out.println("ID: " + produtoEncontrado.getId());
                        System.out.println("Nome: " + produtoEncontrado.getNome());
                        System.out.println("Quantidade: " + produtoEncontrado.getQuantidade());
                        System.out.println("Preco: " + produtoEncontrado.getPreco());
                    } else {
                        System.out.println("Produto nao encontrado.");
                    }

                
                
                
                
                 break;
                
                }
                   
                case 4:{
                System.out.println("Opcao de atualizacao selecionada");
                System.out.println("------------------------------------");
                System.out.println("Digite o ID do produto que deseja atualizar:");
                int idAtualizar = scanner.nextInt();
                scanner.nextLine();

                Produto produtoAtualizar = buscarProduto(produtos, idAtualizar);

                if (produtoAtualizar != null){
                    atualizarProduto(scanner, produtoAtualizar);
                } else {
                    System.out.println("Produto nao encontrado.");
                }

                 



                System.out.println("------------------------------------");
                break;
                }
                
                
                
             case 5:{
                System.out.println("Opcao de remocao selecionada");
                System.out.println("------------------------------------");
                System.out.println("Digite o ID do produto que deseja remover:");
                int idRemover = scanner.nextInt();
                scanner.nextLine();

                boolean removido = removerProduto(produtos, idRemover);

                if (removido){
                    System.out.println("Produto removido com sucesso!");
                } else {
                    System.out.println("Produto nao encontrado.");
                }
                
                System.out.println("------------------------------------");
                
             break;
               }
               
               
                case 6:
                System.out.println("Saindo do programa");
                break;

                default:
                System.out.println("Opcao invalida");
            }
      

        }while(opcao != 6);   
    
      scanner.close();
    }    
    
    public static Produto cadastrarProduto(Scanner scanner, int id) {

    String nome;

    do {
        System.out.print("Digite o nome do produto: ");
        nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("O nome nao pode ficar vazio.");
        }

    } while (nome.isEmpty());

    int quantidade = lerInteiroPositivo( scanner, "Digite a quantidade: ");

    double preco = lerDoublePositivo( scanner, "Digite o preco: ");

    return new Produto(id, nome, quantidade, preco);
    }


        
    

    

    
    
    





    public static void listarProdutos(ArrayList<Produto> produtos){
        System.out.println("Lista de produtos cadastrados:");
        if (produtos.isEmpty()){
            System.out.println("Nenhum produto cadastrado.");
            return;
        } 

        for (Produto produto : produtos){
            System.out.println("------------------------------------");
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.println("Preco: " + produto.getPreco());
            System.out.println("------------------------------------");
        }
    }

    public static Produto buscarProduto(ArrayList<Produto> produtos, int idBusca){
        for (Produto produto : produtos){
            if (produto.getId() == idBusca){
                return produto;
            }
        }
        return null;
    }

    public static void atualizarProduto(
        Scanner scanner, Produto produto) {

    System.out.println("Produto encontrado:");
    System.out.println("ID: " + produto.getId());
    System.out.println("Nome: " + produto.getNome());
    System.out.println("Quantidade: " + produto.getQuantidade());
    System.out.println("Preco: " + produto.getPreco());

    String novoNome;

    do {
        System.out.print("Digite o novo nome: ");
        novoNome = scanner.nextLine().trim();

        if (novoNome.isEmpty()) {
            System.out.println(
                    "O nome nao pode ficar vazio.");
        }

    } while (novoNome.isEmpty());

    int novaQuantidade = lerInteiroPositivo(
            scanner, "Digite a nova quantidade: ");

    double novoPreco = lerDoublePositivo(
            scanner, "Digite o novo preco: ");

    produto.setNome(novoNome);
    produto.setQuantidade(novaQuantidade);
    produto.setPreco(novoPreco);

    System.out.println("Produto atualizado com sucesso!");
}
    
    
    
    public static boolean removerProduto(ArrayList<Produto> produtos, int idRemover) {
        
        Produto produtoRemover = buscarProduto(produtos, idRemover);

        if (produtoRemover != null){
            produtos.remove(produtoRemover);
            return true;
        } else {
            return false;
        }
    }


    public static void exibirMenu() {
    System.out.println();
    System.out.println("---- Controle de Estoque ----");
    System.out.println("1. Adicionar produto");
    System.out.println("2. Listar produtos");
    System.out.println("3. Buscar produto");
    System.out.println("4. Atualizar produto");
    System.out.println("5. Remover produto");
    System.out.println("6. Sair");
    System.out.print("Escolha uma opcao: ");
    }


    public static int lerInteiroPositivo(
        Scanner scanner, String mensagem) {

    while (true) {
        System.out.print(mensagem);
        String entrada = scanner.nextLine().trim();

        try {
            int valor = Integer.parseInt(entrada);

            if (valor > 0) {
                return valor;
            }

            System.out.println(
                    "Digite um numero maior que zero.");

        } catch (NumberFormatException erro) {
            System.out.println(
                    "Digite um numero inteiro valido.");
        }
    }
    }

    public static double lerDoublePositivo(
        Scanner scanner, String mensagem) {

    while (true) {
        System.out.print(mensagem);

        String entrada = scanner.nextLine()
                .trim()
                .replace(',', '.');

        try {
            double valor = Double.parseDouble(entrada);

            if (valor > 0) {
                return valor;
            }

            System.out.println(
                    "Digite um numero maior que zero.");

        } catch (NumberFormatException erro) {
            System.out.println(
                    "Digite um numero decimal valido.");
        }
    }
    }


    public static int lerOpcaoMenu(Scanner scanner) {
    while (true) {
        String entrada = scanner.nextLine().trim();

        try {
            int opcao = Integer.parseInt(entrada);

            if (opcao >= 1 && opcao <= 6) {
                return opcao;
            }

            System.out.println(
                    "Digite um numero entre 1 e 6.");

        } catch (NumberFormatException erro) {
            System.out.println(
                    "Digite um numero inteiro valido.");
        }
        System.out.print("Escolha uma opcao: ");
    }
}

}





