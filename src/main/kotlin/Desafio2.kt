fun main() {
    // Ler 2 numeros inteiros
    // Console com opcoes + - / *

    while (true) {

        println(
            """=======================
Selecione uma operação
1-Soma
2-Subtração
3-Multiplicação
4-Divisão
5-Sair
                """

        )
        var opcao: Int = readln().toInt()

        if (opcao !in 1..4) return

        print("Digite o primeiro numero:")
        var num1 = readln().toInt()

        print("Digite o segundo numero:")
        var num2 = readln().toInt()

        when (opcao) {
            1 -> println("$num1 + $num2 = ${num1 + num2}")
            2 -> println(" $num1 - $num2 = ${num1 - num2}")
            3 -> println(" $num1 * $num2 = ${num1 * num2}")
            4 -> {
                if (num2 == 0) {
                    println("⚠️ Não é possivel dividir por zero.")
                } else println(" $num1 / $num2 = ${num1 / num2}")
            }

            else -> return

        }
    }
}