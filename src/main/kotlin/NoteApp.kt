class NotesApp {
    private val archives = mutableListOf<Archive>()

    fun start() {
        while (true) {
            showArchivesMenu()
        }
    }

    private fun showArchivesMenu() {
        while (true) {
            println("\nСписок архивов:")
            archives.forEachIndexed { index, archive ->
                println("${index + 1}. ${archive.name}")
            }
            println("0. Создать архив")
            println("${archives.size + 1}. Выход")

            when (val input = readlnOrNull()?.trim() ?: "") {
                "0" -> createArchive()
                (archives.size + 1).toString() -> {
                    println("Выход из программы.")
                    kotlin.system.exitProcess(0)
                }
                else -> {
                    when (val choice = input.toIntOrNull()) {
                        null -> println("Пожалуйста, введите число")
                        in 1..archives.size -> {
                            openArchiveMenu(archives[choice - 1])
                            return
                        }
                        else -> println("Пожалуйста, введите число от 0 до ${archives.size + 1}")
                    }
                }
            }
        }
    }

    private fun createArchive() {
        println("Введите название архива:")
        val name = readlnOrNull()?.trim() ?: ""

        if (name.isEmpty()) {
            println("Название архива не может быть пустым!")
            return
        }

        archives.add(Archive(name))
        println("Архив \"$name\" создан!")
    }

    private fun openArchiveMenu(archive: Archive) {
        while (true) {
            println("\nАрхив \"${archive.name}\":")
            archive.notes.forEachIndexed { index, note ->
                println("${index + 1}. ${note.title}")
            }
            println("0. Создать заметку")
            println("${archive.notes.size + 1}. Назад")

            when (val input = readlnOrNull()?.trim() ?: "") {
                "0" -> createNote(archive)
                (archive.notes.size + 1).toString() -> return
                else -> {
                    when (val choice = input.toIntOrNull()) {
                        null -> println("Пожалуйста, введите число")
                        in 1..archive.notes.size -> showNoteContent(archive.notes[choice - 1])
                        else -> println("Пожалуйста, введите число от 0 до ${archive.notes.size + 1}")
                    }
                }
            }
        }
    }

    private fun createNote(archive: Archive) {
        println("Введите название заметки:")
        val title = readlnOrNull()?.trim() ?: ""

        if (title.isEmpty()) {
            println("Название заметки не может быть пустым!")
            return
        }

        println("Введите текст заметки:")
        val content = readlnOrNull()?.trim() ?: ""

        if (content.isEmpty()) {
            println("Текст заметки не может быть пустым!")
            return
        }

        archive.addNote(Note(title, content))
        println("Заметка \"$title\" создана!")
    }

    private fun showNoteContent(note: Note) {
        println("\n=== ${note.title} ===")
        println(note.content)
        println("=====================")
        println("\nНажмите Enter для возврата...")
        readlnOrNull()
    }
}