fun main() {

    // Pergunta 2 números e um operador
    println("Digite a soma ex (num + num):")
    var soma = readln()

    // Separa
    var partes = soma.split("+")

    // Print soma
    println(partes[0].trim().toInt() + partes[1].trim().toInt())
}