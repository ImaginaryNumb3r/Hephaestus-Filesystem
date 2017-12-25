package filesystem.files;

import filesystem.files.stream.DirectoryIterator;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.RelativeDirectory;
import filesystem.path.interfaces.compound.RelativeFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Creator: Patrick
 * Created: 04.08.2017
 * Purpose:
 */
public class NDirectory extends FileEntry {
    protected final AbsoluteDirectory _path;

    public NDirectory(Path path) {
        super(path);
        _path = AbsoluteDirectory.from(path);
    }

    @Override
    public AbsoluteDirectory toPath() {
        return _path;
    }

    public DirectoryStream<NDirectory> newDirectoryStream() throws IOException {
        DirectoryStream<Path> stream = Files.newDirectoryStream(_path);
        return new DirectoryIterator(stream);
    }

    public DirectoryStream<NDirectory> newDirectoryStream(String glob) throws IOException {
        DirectoryStream<Path> stream = Files.newDirectoryStream(_path, glob);
        return new DirectoryIterator(stream);
    }

    public DirectoryStream<NDirectory> newDirectoryStream(DirectoryStream.Filter<? super Path> filter) throws IOException {
        DirectoryStream<Path> stream = Files.newDirectoryStream(_path, filter);
        return new DirectoryIterator(stream);
    }

    public NFile createFile(RelativeFile file, FileAttribute<?>... attrs) throws IOException {
        return new NFile(Files.createFile(_path.append(file), attrs));
    }

    public NDirectory createDirectory(RelativeDirectory directory, FileAttribute<?>... attrs) throws IOException {
        return new NDirectory(Files.createDirectory(_path.append(directory), attrs));
    }

    public NFile createTempFile(RelativeFile file, String prefix, String suffix, FileAttribute<?>... attrs)
            throws IOException {
        return new NFile(Files.createTempFile(_path.append(file), prefix, suffix, attrs));
    }

    public NDirectory createTempDirectory(RelativeDirectory directory, String prefix, FileAttribute<?>... attrs)
            throws IOException {
        return new NDirectory(Files.createTempDirectory(_path.append(directory), prefix, attrs));
    }

    public void delete() throws IOException {
        Files.delete(_path);
    }

    public NDirectory copy(AbsoluteDirectory target, CopyOption... options) throws IOException {
        Path copy = Files.copy(_path, target.getPath(), options);
        return new NDirectory(copy);
    }

    public NDirectory move(AbsoluteDirectory target, CopyOption... options) throws IOException {
        Path movedDir = Files.move(_path, target.getPath(), options);
        return new NDirectory(movedDir);
    }

    public Stream<Path> list() throws IOException {
        return Files.list(_path);
    }

    public Stream<Path> walk(int maxDepth, FileVisitOption... options)
            throws IOException {
        return Files.walk(_path, maxDepth, options);
    }

    public Stream<Path> walk(FileVisitOption... options)
            throws IOException {
        return Files.walk(_path, options);
    }
}
