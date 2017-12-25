package filesystem.tree;

import core.util.contracts.Contract;
import filesystem.path.impl.compound.AbsoluteDirectoryPath;
import filesystem.path.impl.compound.AbsoluteFilePath;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.AbsoluteFile;
import filesystem.visitors.DirectoryVisitor;
import filesystem.visitors.FileTreeVisitor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Marks the state of a directory at the given timestamp.
 */
public class DirectoryTreeNode implements Iterable<DirectoryTreeNode> {
    private final List<BasicFileAttributeView> _files;
    private final List<DirectoryTreeNode> _children;
    private AbsoluteDirectory _location;
    private LocalDateTime _timestamp;

    public DirectoryTreeNode(@NotNull AbsoluteDirectory location) {
        Contract.checkNull(location, "location");
        _location = location;
        _timestamp = LocalDateTime.now();
        _files = new ArrayList<>();
        _children = new LinkedList<>();
    }

    @Override
    @NotNull
    public ListIterator<DirectoryTreeNode> iterator() {
        return _children.listIterator();
    }

    public LocalDateTime getTimestamp() {
        return _timestamp;
    }

    public Path getAsPath() {
        return _location.getPath();
    }

    public AbsoluteDirectory getAbsoluteDirectory() {
        return _location;
    }

    public void addDirectory(DirectoryTreeNode directoryNode){
        _children.add(directoryNode);
    }

    public void addFile(BasicFileAttributeView file){
        _files.add(file);
    }

    public List<BasicFileAttributeView> getFiles(){
        return _files;
    }

    @Override
    public String toString() {
        return _location.toString();
    }
}
