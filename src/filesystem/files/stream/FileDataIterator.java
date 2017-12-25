package filesystem.files.stream;

import filesystem.files.FileEntry;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * Creator: Patrick
 * Created: 04.08.2017
 * Purpose:
 */
public abstract class FileDataIterator<T extends FileEntry> implements DirectoryStream<T> {
    private final DirectoryStream<Path> _directoryStream;

    public FileDataIterator(DirectoryStream<Path> directoryStream) {
        _directoryStream = directoryStream;
    }

    @Override
    public void close() throws IOException {
        _directoryStream.close();
    }

    @Override
    public Iterator<T> iterator() {
        return new FileIterator(_directoryStream);
    }

    private class FileIterator implements Iterator<T> {
        private final Iterator<Path> _iterator;

        private FileIterator(DirectoryStream<Path> dirStream) {
            _iterator = dirStream.iterator();
        }

        @Override
        public boolean hasNext() {
            return _iterator.hasNext();
        }

        @Override
        public T next() {
            return fileConstructor(_iterator.next());
        }
    }

    protected abstract T fileConstructor(Path path);

}
