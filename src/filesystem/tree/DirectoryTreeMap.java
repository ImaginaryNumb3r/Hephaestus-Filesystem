package filesystem.tree;

import datastructure.traverser.Traverser;
import filesystem.attributes.FileAttributeService;
import filesystem.path.interfaces.compound.AbsoluteDirectory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Patrick
 * @since 11.11.2017
 * This is a concrete representation of a directory in the file system and all of its sub entries.
 */

// TODO: Cleanup, there was a lot of testing done to get the right approach done.
public class DirectoryTreeMap extends AbstractDirectoryTreeMap<Path, DirectoryTreeNode> {

    public DirectoryTreeMap(AbsoluteDirectory directory) {
        this(new DirectoryTreeNode(directory));
    }

    public DirectoryTreeMap(DirectoryTreeNode root) {
        super(root);
    }

    protected void updateRecursively(){
        try {
            Path location = _root.getAsPath();
            Files.walkFileTree(location, makeFileVisitor());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    protected FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        DirectoryTreeNode dirNode = new DirectoryTreeNode(AbsoluteDirectory.from(dir));
        _map.putIfAbsent(dir, dirNode);
        DirectoryTreeNode parentDir = _map.get(dir.getParent());

        if (parentDir != null){
            parentDir.addDirectory(dirNode);
        }

        return FileVisitResult.CONTINUE;
    }

    protected  FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        DirectoryTreeNode directory = _map.get(file.getParent());
        // AbsoluteFile absFile = AbsoluteFile.from(file);

        BasicFileAttributeView fileAttributes = FileAttributeService.makeBasicFileAttributeView(file, attrs);

        directory.addFile(fileAttributes);

        return FileVisitResult.CONTINUE;
    }

    protected  FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    protected  FileVisitResult postVisitDirectory(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    protected FileVisitor<Path> makeFileVisitor(){
        _map.put(_root.getAsPath(), _root);

        return new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return DirectoryTreeMap.this.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return DirectoryTreeMap.this.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return DirectoryTreeMap.this.visitFileFailed(file, exc);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return DirectoryTreeMap.this.postVisitDirectory(dir, exc);
            }
        };
    }

    @Override
    public Traverser<DirectoryTreeNode> traverser() {
        return Traverser.of(_root);
    }
}
