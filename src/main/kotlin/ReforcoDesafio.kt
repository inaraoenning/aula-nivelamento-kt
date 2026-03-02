
fun main (){


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

}


data class Livro(val titulo: String, val autor:String, var disponivel: Boolean) {

}

class Biblioteca {
    private var saldoFinanceiro: Double = 0.0
    private val estoque: MutableList<Livro> = mutableListOf()

    //1- Cadastrar Livro ou Revista
    fun cadastrarLivro(titulo:String, autorLivro: String, disponivel: Boolean){

    }
}



