package filesystem.visitors;

import core.tuple.Tuple;
import filesystem.path.interfaces.compound.AbsoluteDirectory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiFunction;

/**
 * Creator: Patrick
 * Created: 14.11.2017
 * Purpose:
 */
@FunctionalInterface
public interface DirectoryVisitor extends FileVisitor<Path> {

    FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException;

    @Override
    default FileVisitResult visitFile(Path  file, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    default FileVisitResult visitFileFailed(Path  file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    default FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    static DirectoryVisitor toDirVisitor(BiFunction<AbsoluteDirectory, BasicFileAttributes, FileVisitResult> preVisitDirectory){
        return (path, attributes) -> preVisitDirectory.apply(AbsoluteDirectory.from(path), attributes);
    }

}