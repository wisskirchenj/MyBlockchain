package topics.memento;

/**
 * Roll back
 * Your task is to complete the implementation of the Undo class so that takeSnapshot and rollback methods work correctly.
 * Keep in mind that the rollback method may be called any time, so make sure that the program does not crash when
 * the rollback method is called.
 */
class Undo {
    private final Editor editor;
    private Editor.EditorState editorState;

    public Undo(Editor editor) {
        this.editor = editor;
        takeSnapshot();
    }

    public void takeSnapshot() {
        editorState = editor.getState();
    }

    public void rollback() {
        editor.setState(editorState);
    }
}
