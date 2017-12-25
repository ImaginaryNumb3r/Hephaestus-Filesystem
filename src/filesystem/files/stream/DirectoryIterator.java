package filesystem.files.stream;

import filesystem.files.NDirectory;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

/**
 * Creator: Patrick
 * Created: 04.08.2017
 * Purpose:
 */
public class DirectoryIterator extends FileDataIterator<NDirectory> {

    public DirectoryIterator(DirectoryStream<Path> directoryStream) {
        super(directoryStream);
    }

    @Override
    protected NDirectory fileConstructor(Path path) {
        return new NDirectory(path);
    }
}
