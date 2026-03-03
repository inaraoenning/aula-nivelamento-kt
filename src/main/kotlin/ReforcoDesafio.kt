fun main() {


    /*
    *
    * 1 - Cadastrar item (Livro ou Revista)
    * 2 - Emprestar item
            3 - Devolver item
            4 - Ver catálogo
            5 - Ver usuários e empréstimos
            6 - Financeiro (multas arrecadadas)
    * 0 - Sair
    */

    val biblioteca = Biblioteca()

    // Menu Biblioteca
    println("========================")
    print(
        """
Selecione uma operação
1- Cadastrar
2- Emprestar
3- Devolver 
4- Ver catálogo
5- Ver usuários e empréstimos
6- Financeiro (multas arrecadadas)
0- Sair
    """.trimIndent()
    )
    val opcao: Int = readln().toInt()

    when (opcao) {
        1 -> {
            println("Título:")
            val titulo = readln()

            println("Autor:")
            val autor = readln()

            biblioteca.cadastrarLivro(
                titulo, autor,
                disponivel = true
            )
        }
    }
}


class Livro(val titulo: String, val autor: String, var disponivel: Boolean) {

}

class Biblioteca {
    private var saldoFinanceiro: Double = 0.0

    // Cria o estoque com uma mutablelist vazia
    private val estoque: MutableList<Livro> = mutableListOf()

    //1- Cadastrar Livro ou Revista
    fun cadastrarLivro(titulo: String, autorLivro: String, disponivel: Boolean) {
        val temEstoque = estoque.find { it.titulo == titulo }
        // Verifica se o livro já existe

        if (temEstoque?.titulo == titulo) {
            println("⚠️ Livro já cadastrado!")
            return // early return
        }

        // Adiciona o Livro no Estoque
        estoque.add(Livro(titulo, autorLivro, disponivel))

        println(Livro(titulo, autorLivro, disponivel))
    }

    // 4 - Ver Catalogo
    fun verCatalogo(titulo: String) {
        if (estoque.isEmpty()) {
            println("⚠️ Estoque vazio!")
            return
        }

        println("Livros disponiveis:")
        estoque.forEach { livro ->
            println("Título:${livro.titulo}\nAutor:${livro.autor}\nDisponibilidade ${livro.disponivel}")
        }

    }
}



