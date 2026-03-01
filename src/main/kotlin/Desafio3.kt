fun main() {

    // menu Compra Venda Estoque Financeiro
    // Compra: Armazenar Nome QTD Valor (Descontar do financeiro) (To uppercase)
    // Venda: Remover Nome QTD Valor (Adicionar ao financeiro +5%)
    // Estoque: Mostra itens em estoque
    // Financeiro: Inicia com 100 / mostra saldo atual do financeiro

    // Declarar o obj fora do while para não iniciar um novo objeto cada loop
    val mercado = Mercado()

    // Menu de compra
    while (true) {
        print(
            """
=======================
Selecione uma operação
1-Compra
2-Venda
3-Estoque
4-Financeiro
    """.trimIndent()
        )
        val opcao: Int = readln().toInt()

        when (opcao) {
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
        }
    }


}

// Val = imutavel
// var = mutavel

data class Produto(private var nomeParam: String, var quantidade: Int, private var valorParam: Double) {
    val nome: String get() = nomeParam.uppercase()
    val valor: Double get() = valorParam

    // get printar
    // set setar ou atribuir valor
}

class Mercado {
    private var saldoInicial: Double = 100.0
    private val estoque: MutableList<Produto> = mutableListOf()

    // Comprar Armazenar nome quantiade valor (Descontar do financeiro) (To uppercase)
    fun comprar(nome: String, quantidade: Int, valor: Double) {
        val nomePadrao = nome.uppercase()
        val custo = valor * quantidade

        // Verifica se o saldo é suficiente
        if (saldoInicial < custo) {
            println("Saldo insuficiente para realizar a compra.")
            return
        }

        // Procura no estoque se o produto já existe
        if (estoque.find { it.nome == nomePadrao } != null) {

            // Se o produto já existe no estoque, atualiza a quantidade
            estoque.find { it.nome == nomePadrao }?.quantidade += quantidade

            // Desconta o custo do saldo
            saldoInicial -= custo
            return // Early return (padrao)
        }

        estoque.add(Produto(nomePadrao, quantidade, valor))
        saldoInicial -= custo
    }

    // a soma para venda tem algo errado, verificar
    fun vender(nome: String, quantidade: Int) {
        val nomePadrao = nome.uppercase()
        val produto = estoque.find { it.nome == nomePadrao } // Acessa o produto encontrado no estoque
        val valorVenda = produto?.valor?.times(quantidade) // Adiciona 5% ao valor de venda

        if (produto == null) {
            println("Produto não encontrado no estoque")
            return
        }

        // remove apenas a quantidade do produto vendido, não o produto inteiro
        produto.quantidade -= quantidade
        val valorVendido: Double = saldoInicial + valorVenda!!.times(1.05) // Adiciona 5% ao valor de venda
        println("Produto vendido: ${nomePadrao}, Quantidade: $quantidade, Valor de venda: ${valorVendido}")
    }

    fun verEstoque() {
        estoque.find { it.nome.isEmpty() }?.let {
            println("Não há produtos em estoque.")
            return
        }

        println("Produtos em estoque:")
        estoque.forEach { produto ->    // ForEach percorre cada elemento da lista e executa o println para cada item adicionado
            println("Nome: ${produto.nome}, Quantidade: ${produto.quantidade}, Valor: ${produto.valor}")
        }
    }

    fun verFinanceiro() {
        println("Saldo Atual? $saldoInicial")
    }


}