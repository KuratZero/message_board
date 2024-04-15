package id.atom.messages_board.exception;

/**
 * An exception for the case of non-existence of an object.
 *
 * @author <a href="https://github.com/KuratZero">Artemii Kazakov</a>
 */
public class NoSuchData extends RuntimeException {
    public NoSuchData(String entry) {
        super(String.format("Not found such %s.", entry));
    }
}
