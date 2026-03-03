fun main() {

    val biblioteca = Biblioteca()

    while (true) {
        println("========================")
        print(
            """
Selecione uma operação
1- Cadastrar
2- Emprestar
3- Devolver 
4- Ver catálogo
5- Ver Emprestimos
6- Financeiro (multas arrecadadas)
7- Cadastrar Usuário
8- Ver Usuários
    """.trimIndent()
        )
        val opcao: Int = readln().toInt()

        when (opcao) {
            1 -> {
                println("=============Cadastrar Livro===============")
                println("Cód. Livro:")
                val codigo = readln().toInt()

                println("Título:")
                val titulo = readln()

                println("Autor:")
                val autor = readln()

                biblioteca.cadastrarLivro(codigo, titulo, autor)
            }

            2 -> {
                println("=============Emprestar Livro===============")
                println("Informe o código do Livro")
                val codigo = readln().toInt()

                println("Informe o código do cliente")
                val codCliente = readln().toInt()

                biblioteca.emprestarLivro(codigo, codCliente)
            }

            3 -> {
                println("=============Devolver Livro===============")
                println("Informe o código do livro: ")
                val codigo = readln().toInt()
                biblioteca.devolucaoLivro(codigo)
            }

            4 -> {
                println("==============Ver Catálogo================")
                biblioteca.verCatalogo()
            }

            5 -> {
                println("==============Empréstimos================")

                biblioteca.verUsuariosEmprestimos()
            }

            6 -> {
                println("==============Financeiro================")
            }

            7 -> {
                println("==============Cadastrar Usuário================")
                println("Informe o código do Usuário")
                val codigo = readln().toInt()

                println("Informe o nome do Usuário")
                val nome = readln()

                biblioteca.cadastrarUsuario(codigo, nome)
            }

            8 -> {
                println("==============Lista de Usuário================")

            }
        }
    }
}

data class Livro(
    val codigo: Int,
    val titulo: String,
    val autor: String,
    var disponivel: Boolean = true
)

data class Usuario(
    val codigo: Int,
    val nome: String,
)

data class Emprestimo(
    val livro: Livro,
    val usuario: Usuario,
//    val dataEmprestimo:
//    val dataDevolucao:
)

class Biblioteca {
    private var saldoFinanceiro: Double = 0.0

    private val livros: MutableList<Livro> = mutableListOf()
    private val usuarios: MutableList<Usuario> = mutableListOf()
    private val emprestimos: MutableList<Emprestimo> = mutableListOf()

    fun cadastrarLivro(codLivro: Int, titulo: String, autorLivro: String) {
        val temEstoque = livros.find { it.titulo == titulo }
        // Verifica se o livro já existe

        if (temEstoque?.titulo == titulo) {
            println("⚠️ Livro já cadastrado!")
            return // early return
        }

        // Adiciona o Livro no Estoque
        livros.add(Livro(codLivro, autorLivro, autorLivro))
        println("Livro cadastrado com sucesso!")

    }

    fun cadastrarUsuario(codUsuario: Int, nomeUsuario: String) {
        if (usuarios.any { it.codigo == codUsuario }) {
            println("Já existe um usuário cadastrado com esse código.")
            return
        }

        usuarios.add(Usuario(codUsuario, nomeUsuario))
        println("Usuário cadastrado com sucesso!")

    }

    fun emprestarLivro(codLivro: Int, codUsuario: Int) {

        val livro = livros.find { it.codigo == codLivro }
        if (livro == null) {
            println("Livro não encontrado.")
            return
        }

        val usuario = usuarios.find { it.codigo == codUsuario }
        if (usuario == null) {
            println("Usuário não encontrado.")
            return
        }

        // Livro pode ser emprestado para apenas 1 pessoa
        val jaEmprestado = emprestimos.any { it.livro.codigo == codLivro }
        if (jaEmprestado) {
            val quem = emprestimos.find { it.livro.codigo == codLivro }?.usuario
            println("Livro já emprestado para ${quem?.nome}")
            return
        }

        emprestimos.add(Emprestimo(livro, usuario))
        livro.disponivel = false
        println("Empréstimo realizado! ${usuario.nome} pegou ${livro.titulo}")
    }

    fun devolucaoLivro(codLivro: Int) {
        val emprestimo = emprestimos.find { it.livro.codigo == codLivro }

        if (emprestimo == null) {
            println("Esse livro não está emprestado")
            return
        }
        emprestimo.livro.disponivel = true
        emprestimos.remove(emprestimo)
        println("Devolução feita! ${emprestimo.livro.titulo} voltou ao catalogo.")
    }

    fun verCatalogo() {
        if (livros.isEmpty()) {
            println("⚠️ Estoque vazio!")
            return
        }

        println("Livros disponiveis:")
        livros.forEach { livro ->
            println("Título:${livro.titulo}\nAutor:${livro.autor}\nDisponibilidade ${livro.disponivel}")
        }

    }

    fun verUsuariosEmprestimos() {
        println("🧒🏻 Usuário           📔 Livro")
        emprestimos.forEach { livro ->
            println("${livro.usuario}     ${livro.usuario}")

        }
    }

    fun verUsuarios() {
        if (usuarios.isEmpty()) {
            println("Nenhum usuário cadastrado.")
        }

        usuarios.forEach { usuario ->
            println("Código: ${usuario.codigo} Nome: ${usuario.nome}")
        }
    }
}



