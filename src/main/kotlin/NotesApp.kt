import java.util.Locale.getDefault

class NotesApp {
    private val archives = mutableListOf<Archive>()

    fun run() {
        val menuItems = mutableListOf(
            "Создать архив" to { createArchive() },
            "Выход" to { return@to }
        )

        archives.forEachIndexed { index, archive ->
            menuItems.add(index + 2, archive.name to { openArchive(archive) })
        }

        val menu = Menu(title = "Список архивов:", items = menuItems)
        menu.show()
    }

    private fun createArchive() {
        println("\n=== СОЗДАНИЕ АРХИВА ===")
        val name = readNotEmptyInput("Введите название архива:") { it.isNotBlank() }

        archives.add(Archive(name))
        println("Архив '$name' создан!")
    }

    private fun openArchive(archive: Archive) {
        val menuItems = mutableListOf(
            "Создать заметку" to { createNote(archive) },
            "Назад" to { return@to }
        )

        archive.notes.forEachIndexed { index, note ->
            menuItems.add(index + 2, note.title to { openNote(note) })
        }

        val menu = Menu("Архив: ${archive.name}", menuItems)
        menu.show()
    }

    private fun createNote(archive: Archive) {
        println("\n=== СОЗДАНИЕ ЗАМЕТКИ ===")
        val title = readNotEmptyInput("Введите заголовок заметки:") { it.isNotBlank() }
        val content = readNotEmptyInput("Введите текст заметки:") { it.isNotBlank() }

        archive.addNote(Note(title, content))
        println("Заметка '$title' создана!")
    }

    private fun openNote(note: Note) {
        println("\n=== ${note.title.uppercase(getDefault())} ===")
        println("Содержание:")
        println(note.content)
        println("\nНажмите Enter для возврата...")
        readlnOrNull()
    }

    private fun readNotEmptyInput(prompt: String, validator: (String) -> Boolean): String {
        while (true) {
            print("$prompt ")
            val input = readlnOrNull()?.trim() ?: ""

            if (validator(input)) {
                return input
            }
            println("Поле не может быть пустым. Попробуйте снова.")
        }
    }
}