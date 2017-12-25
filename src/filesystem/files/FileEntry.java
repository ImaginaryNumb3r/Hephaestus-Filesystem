package filesystem.files;

import filesystem.path.interfaces.BasePath;
import filesystem.path.interfaces.simple.AbsolutePath;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * Represents a file on the file system at a given timestamp.
 */
public abstract class FileEntry {
    protected final LocalDateTime _timeStamp;
    protected final Path _path;

    protected FileEntry(Path path) {
        _path = path;
        _timeStamp = LocalDateTime.now();
    }

    public LocalDateTime getTimeStamp() {
        return _timeStamp;
    }

    //<editor-fold desc="Path Delegates">
    public abstract AbsolutePath toPath();

    public boolean isSameFile(BasePath other) throws IOException {
        return Files.isSameFile(_path, other.getPath());
    }

    public <V extends FileAttributeView> V getFileAttributeView(Class<V> type, LinkOption... options) {
        return Files.getFileAttributeView(_path, type, options);
    }

    public <A extends BasicFileAttributes> A readAttributes(Class<A> type, LinkOption... options)
            throws IOException {
        return Files.readAttributes(_path, type, options);
    }

    public Path setAttribute(String attribute, Object value, LinkOption... options)
            throws IOException {
        return Files.setAttribute(_path, attribute, value, options);
    }

    public Object getAttribute(String attribute, LinkOption... options) throws IOException {
        return Files.getAttribute(_path, attribute, options);
    }

    public Map<String, Object> readAttributes(String attributes, LinkOption... options)
            throws IOException {
        return Files.readAttributes(_path, attributes, options);
    }

    public Set<PosixFilePermission> getPosixFilePermissions(LinkOption... options)
            throws IOException {
        return Files.getPosixFilePermissions(_path, options);
    }

    public Path setPosixFilePermissions(Set<PosixFilePermission> perms) throws IOException {
        return Files.setPosixFilePermissions(_path, perms);
    }

    public FileTime getLastModifiedTime(LinkOption... options) throws IOException {
        return Files.getLastModifiedTime(_path, options);
    }

    public Path setLastModifiedTime(FileTime time)
            throws IOException {
        return Files.setLastModifiedTime(_path, time);
    }

    public long size() throws IOException {
        return Files.size(_path);
    }

    public boolean exists() throws IOException {
        return Files.exists(_path);
    }

    public boolean notExists(LinkOption... options) {
        return Files.notExists(_path, options);
    }

    public boolean isExecutable() {
        return Files.isExecutable(_path);
    }
    //</editor-fold>
}