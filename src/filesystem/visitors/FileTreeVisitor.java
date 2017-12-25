package filesystem.visitors;

import core.exception.NoImplementationException;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

/**
 * Creator: Patrick
 * Created: 18.11.2017
 * Purpose:
 */
public interface FileTreeVisitor<V> extends FileVisitor<Path> {
    FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs, Path parent) throws IOException;

    FileVisitResult visitFile(Path file, BasicFileAttributes attrs, Path parent) throws IOException;

    FileVisitResult visitFileFailed(Path file, IOException exc, Path parent) throws IOException;

    FileVisitResult postVisitDirectory(Path dir, IOException exc, Path parent) throws IOException;

    @Override
    default FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException{
        Path parent = mapNodes(dir);
        return preVisitDirectory(dir, attrs, parent);
    }

    @Override
    default FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
        Path parent = mapNodes(file);
        return visitFile(file, attrs, parent);
    }

    @Override
    default FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException{
        Path parent = mapNodes(file);
        return visitFileFailed(file, exc, parent);
    }

    @Override
    default FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException{
        Path parent = mapNodes(dir);
        return postVisitDirectory(dir, exc, parent);
    }

    Path mapNodes(Path dir);

}
