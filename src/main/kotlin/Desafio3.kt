fun main() {

    // menu Compra Venda Estoque Financeiro
    // Compra: Armazenar Nome QTD Valor (Descontar do financeiro) (To uppercase)
    // Venda: Remover Nome QTD Valor (Adicionar ao financeiro +5%)
    // Estoque: Mostra itens em estoque
    // Financeiro: Inicia com 100 / mostra saldo atual do financeiro

    // Declarar o obj fora do while para não iniciar um novo objeto cada loop
    val mercado = Mercado()

    // Menu de compra
    while (true) {  // Mantem o loop até que seja fechado (opção 5)

        print(
            """
=======================
Selecione uma operação
1-Compra
2-Venda
3-Estoque
4-Financeiro
5-Editar Produto
6-Sair
    """.trimIndent() // remove os espaços em branco no inicio de cada linha
        )
        val opcao: Int = readln().toInt()  // Guardar a opcao selecionada

        when (opcao) { // avalia o número digitado e executa o bloco de código correspondente (switch-case em outras linguagens)
            1 -> {
                println("===========Comprar============")
                println("Digite o nome do produto:")
                val nome: String = readln()

                println("Digite a quantidade do produto:")
                val quantidade: Int = readln().toInt()

                println("Digite o valor do produto:")
                val valor: Double = readln().toDouble()

                mercado.comprar(nome, quantidade, valor)
            }

            2 -> {
                println("===========Venda============")
                println("Digite o nome do produto:")
                val nome: String = readln()

                println("Digite a quantidade do produto:")
                val quantidade: Int = readln().toInt()

                mercado.vender(nome, quantidade)
            }

            3 -> {
                mercado.verEstoque()
            }

            4 -> {
                mercado.verFinanceiro()
            }
            5 -> {
                println("===========Editar Produto============")

                println("Digite o nome do produto que deseja alterar:")
                val nome: String = readln()

                println("Digite o novo nome: ")
                val nomeAtual: String = readln()

                mercado.alterarNome(nome, nomeAtual)
            }
        }
    }
}

// Val = imutavel
// var = mutavel

// classe especializada em armazenar dados
// gera automaticamente métodos úteis (como toString(), equals())

// parametros originais privados
data class Produto(private var nomeParam: String, var quantidade: Int, private var valorParam: Double) {
    // propriedades customizadas para os parametros
    var nome: String
        get() = nomeParam.uppercase() // garante padrão para nome do item, LER o dado
        set(value) {
            nomeParam = value // Gravar novo dado
        }
    val valor: Double get() = valorParam // retorna o valor original

    // get printar
    // set setar ou atribuir valor
}

// Regras de Negócio
class Mercado {
    // Apenas as funções de Mercado podem alterar o valor
    private var saldoInicial: Double = 100.0

    // Lista Mutável - uma lista normal não permite adicionar itens depois de criada, mutable usa o método .add
    private val estoque: MutableList<Produto> = mutableListOf() // estoque iniciado como uma lista vazia

    // Calcula o custo total (valor * quantidade). (To uppercase)
    fun comprar(nome: String, quantidade: Int, valor: Double) {
        val nomePadrao = nome.uppercase()
        val custo = valor * quantidade

        // Se o saldoInicial for menor que o custo impede a compra
        if (saldoInicial < custo) {
            println("Saldo insuficiente para realizar a compra.")
            return // Early Return
        }

        // Procura no estoque se o produto já existe
        if (estoque.find { it.nome == nomePadrao } != null) {  // it representa cada produto dentro do loop interno da lista

            // Se o produto já existe no estoque, atualiza a quantidade
            estoque.find { it.nome == nomePadrao }?.quantidade += quantidade  // Safe Call (?.) evitar o NullPointerException

            // Desconta o custo do saldo
            saldoInicial -= custo
            return // Early return
        }

        // Adiciona um Produto novo na lista usando estoque.add
        estoque.add(Produto(nomePadrao, quantidade, valor))

        // Desconta o valor do fianceiro
        saldoInicial -= custo
    }

    // a soma para venda tem algo errado, verificar
    fun vender(nome: String, quantidade: Int) {
        val nomePadrao = nome.uppercase()
        val produto = estoque.find { it.nome == nomePadrao } // Acessa o produto encontrado no estoque

        // Verifica se o produto existe
        if (produto == null) {
            println("Produto não encontrado no estoque!")
            return
        }

        // Faz o calculo da venda
        val valorVenda = produto.valor.times(quantidade) // Valor do produto * quantidade

        // Remove apenas a quantidade do produto vendido, não o produto inteiro
        produto.quantidade -= quantidade
        val valorVendido: Double = valorVenda * 1.05 // Adiciona 5% ao valor de venda
        saldoInicial += valorVendido
        println("Produto vendido: $nomePadrao, Quantidade: $quantidade, Valor de venda: $valorVendido")
    }

    fun verEstoque() {
        if (estoque.isEmpty()) {
            println("Não há produtos em estoque.")
            return
        }

        println("Produtos em estoque:")
        estoque.forEach { produto ->    // ForEach percorre cada elemento da lista e executa o println para cada item adicionado
            println("Nome: ${produto.nome}, Quantidade: ${produto.quantidade}, Valor: ${produto.valor}")
        }
    }

    fun verFinanceiro() {
        println("Saldo Atual: $saldoInicial")
    }

    fun alterarNome(nome: String, nomeAtual: String) {

        val nomePadrao = nome.uppercase()
        val produto = estoque.find { it.nome == nomePadrao }

        // Verificar se existe produto com esse nome
        if (estoque.find { it.nome == nomePadrao } == null) {
            println("Esse produto não existe no estoque!")
            return
        }

        produto?.nome = nomeAtual
        println("Nome alterado! $nomePadrao para ${nomeAtual.uppercase()}!")

    }

}


//?. (Safe Call): "Acessar a informação apenas se for seguro e o objeto existir."

//!! (Non-Null Assertion): "variável não é nula, pode acessar à força."
// Se estiver a variável estiver vazia, o programa vai quebrar na hora.
// (deve ser evitada).