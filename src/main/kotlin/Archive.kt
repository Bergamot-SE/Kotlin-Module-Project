class Archive(val name: String) {
    private val _notes = mutableListOf<Note>()
    val notes: List<Note> get() = _notes

    fun addNote(note: Note) {
        _notes.add(note)
    }
}
