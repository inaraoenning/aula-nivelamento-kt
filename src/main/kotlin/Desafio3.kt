fun main() {
    // Ler 2 numeros inteiros
    // Console com opcoes + - / *

    while (true) {

        println(
            """
=====================
Escolha uma operação:
1- Somar
2- Subtração
3- Multiplicação
4- Divisão
5- Sair
        """
        )
        var opcao: Int = readln().toInt();

        if (opcao !in 1..4) {
            return
        }

        print("Insira o primeiro numero:")
        var num1: Int = readln().toInt();

        print("Insira o segundo numero:")
        var num2: Int = readln().toInt();

        when (opcao) {
            1 -> println("$num1 + $num2 = ${num1 + num2}")
            2 -> println("$num1 - $num2 = ${num1 - num2}")
            3 -> println("$num1 * $num2 = ${num1 * num2}")
            4 -> {
                if (num2 == 0) {
                    println("⚠️ Não é possivel dividir por zero.")
                } else println("$num1 / $num2 = ${num1 / num2}")
            }
        }
    }


}