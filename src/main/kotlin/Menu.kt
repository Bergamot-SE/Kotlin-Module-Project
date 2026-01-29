import java.util.Scanner

class Menu(
    private val title: String,
    private val items: List<Pair<String, () -> Unit>>
) {
    private val scanner = Scanner(System.`in`)

    fun show() {
        while (true) {
            println("\n=== $title ===")
            println("0. ${items[0].first}")

            items.forEachIndexed { index, (name, _) ->
                if (index > 0) {
                    println("$index. $name")
                }
            }

            print("Введите номер пункта: ")
            val input = scanner.nextLine().trim()

            if (!input.matches(Regex("\\d+"))) {
                println("Ошибка: необходимо ввести цифру!")
                continue
            }

            val choice = input.toInt()

            if (choice < 0 || choice >= items.size) {
                println("Ошибка: пункта с номером $choice не существует!")
                continue
            }

            if (choice == 0) {
                return // Выход из меню
            }

            items[choice].second.invoke()
        }
    }
}
