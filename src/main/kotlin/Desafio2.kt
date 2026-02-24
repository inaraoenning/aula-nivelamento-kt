fun main() {
    // Ler 2 numeros inteiros
    // Console com opcoes + - / *

    println("Digite o primeiro numero:")
    var num1 = readln().toInt()

    println("Digite o segundo numero:")
    var num2 = readln().toInt()

    println(
        """Selecione uma operação
                1-Soma
                2-Subtração
                3-Multiplicação
                4-Divisão
                5-Sair"""

    )
    var operacao: Int = readln().toInt()

    when (operacao) {
        1 -> println("num1+ num2 = ${num1 + num2}")
        2 -> println("num1 - num2 = ${num1 - num2}")
        3 -> println("num1 * num2 = ${num1 * num2}")
        4 -> println("num1 / num2 = ${num1 / num2}")
        else -> return

    }
}