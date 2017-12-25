package filesystem.files;

import filesystem.path.interfaces.compound.AbsoluteFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Creator: Patrick
 * Created: 04.08.2017
 * Purpose:
 */
public class NFile extends FileEntry {
    protected final AbsoluteFile _absoluteFile;

    public NFile(Path path) {
        super(path);
        _absoluteFile = AbsoluteFile.from(path);
    }

    @Override
    public AbsoluteFile toPath() {
        return _absoluteFile;
    }

    public FileStore getFileStore() throws IOException {
        return Files.getFileStore(_path);
    }

    public boolean isHidden() throws IOException {
        return Files.isHidden(_path);
    }

    public String probeContentType() throws IOException {
        return Files.probeContentType(_path);
    }

    public boolean isReadable() {
        return Files.isReadable(_path);
    }

    public boolean isWritable() {
        return Files.isWritable(_path);
    }

    public BufferedReader newBufferedReader(Charset cs) throws IOException {
        return Files.newBufferedReader(_path, cs);
    }

    public BufferedReader newBufferedReader() throws IOException {
        return Files.newBufferedReader(_path);
    }

    public BufferedWriter newBufferedWriter(Charset cs, OpenOption... options) throws IOException {
        return Files.newBufferedWriter(_path, cs, options);
    }

    public BufferedWriter newBufferedWriter(OpenOption... options) throws IOException {
        return Files.newBufferedWriter(_path, options);
    }

    public byte[] readAllBytes() throws IOException {
        return Files.readAllBytes(_path);
    }

    public List<String> readAllLines(Charset cs) throws IOException {
        return Files.readAllLines(_path, cs);
    }

    public List<String> readAllLines() throws IOException {
        return Files.readAllLines(_path);
    }

    public Path write(byte[] bytes, OpenOption... options) throws IOException {
        return Files.write(_path, bytes, options);
    }

    public Path write(Iterable<? extends CharSequence> lines,
                             Charset cs, OpenOption... options)
            throws IOException {
        return Files.write(_path, lines, cs, options);
    }

    public Path write(Iterable<? extends CharSequence> lines, OpenOption... options)
            throws IOException {
        return Files.write(_path, lines, options);
    }

    public Stream<String> lines(Charset cs) throws IOException {
        return Files.lines(_path, cs);
    }

    public Stream<String> lines() throws IOException {
        return Files.lines(_path);
    }
}