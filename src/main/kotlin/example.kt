fun main() {

    // while para o programa continuar em loop
    // when para opcoes
    // menu + - * /

    while (true) {

        println(
            """
            ===================
            Selecione a operação:
            1- Somar
            2- Subtração
            3- Multiplicação
            4- Divisão
            5- Sair
        """
        )
        var opcao: Int = readln().toInt()

        if (opcao !in 1..4) {
            return
        }

        println("Informe o primeiro numero:")
        var num1: Int = readln().toInt()

        println("Informe o segundo número")
        var num2: Int = readln().toInt()

        when (opcao) {
            1 -> println("$num1 + $num2 = ${num1 + num2}")
            2 -> println("$num1 - $num2 = ${num1 - num2}")
            3 -> println("$num1 * $num2 = ${num1 * num2}")
            4 -> {
                if (num2 == 0) {
                    println("Não é possivel dividir por zero.")
                } else return println("$num1 / $num2 = ${num1 / num2}")
            }

        }
    }
}