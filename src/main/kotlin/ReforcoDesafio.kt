import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun main() {

    val biblioteca = Biblioteca()

    while (true) {
        println("========================")
        print(
            """
Selecione uma operação
1- Emprestar
2- Devolução
3- Ver Emprestimos
4- Ver Financeiro (Multas)
5- Cadastrar Livro
6- Ver catálogo 
7- Cadastrar Usuário
8- Ver Usuários
9- Sair
    """.trimIndent()
        )
        val opcao: Int = readln().toInt()

        when (opcao) {
            1 -> {
                println("=============Emprestar Livro===============")
                println("Informe o código do Livro")
                val codigo = readln().toInt()

                println("Informe o código do cliente")
                val codCliente = readln().toInt()

                biblioteca.emprestarLivro(codigo, codCliente)
            }

            2 -> {
                println("=============Devolver Livro===============")
                println("Informe o código do livro: ")
                val codigo = readln().toInt()
                biblioteca.devolucaoLivro(codigo)
            }

            3 -> {
                println("==============Empréstimos================")

                biblioteca.verUsuariosEmprestimos()
            }

            4 -> {
                println("==============Financeiro================")
            }

            5 -> {
                println("=============Cadastrar Livro===============")
                println("Cód. Livro:")
                val codigo = readln().toInt()

                println("Título:")
                val titulo = readln()

                println("Autor:")
                val autor = readln()

                biblioteca.cadastrarLivro(codigo, titulo, autor)
            }

            6 -> {
                println("==============Ver Catálogo================")
                biblioteca.verCatalogo()
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
                biblioteca.verUsuarios()
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
    val multa: Multa,
)

class Multa(
    val dataEmprestimo: LocalDate,
    val dataDevolucao: LocalDate,
    val diasPermitidos: Long = 7,
    val valorPorDia: BigDecimal

) {
    val diasAtraso: Long
        get() {
            val diasEntre = ChronoUnit.DAYS.between(dataEmprestimo, dataDevolucao)
            val atraso = diasEntre - diasPermitidos
            return if (atraso > 0) atraso else 0
        }
    val valorMulta: BigDecimal
        get() = valorPorDia.multiply(BigDecimal.valueOf(diasAtraso))
}

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
            println("Aluno: ${livro.usuario.nome}    Livro: ${livro.usuario.nome}")

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



