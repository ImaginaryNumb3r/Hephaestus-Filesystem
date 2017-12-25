package filesystem.path.interfaces;


import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.interfaces.compound.RelativeDirectory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public interface BasePath extends Path {

    int length();

    Path append(RelativeDirectory rel);

    Path remove (RelativeDirectory removal) throws PathsNotMatchingException;

    /**
     * Returns an exact copy of this file
     * @return an exact copy of this file
     */
    Path copy();

    Path getPath();

    BasePath getTop();

    boolean hasParent();

    boolean isRoot();

    /**
     * Returns the directory this path is contained in
     * @return  the directory this path is contained in
     *          otherwise returns null if no parent is present<br>
     */
    RelativeDirectory getParentDirectory();

    //<editor-fold desc="Delegated Methods">
    @Override
    default FileSystem getFileSystem() {
        return getPath().getFileSystem();
    }

    @Deprecated
    @Override
    default boolean isAbsolute() {
        return getPath().isAbsolute();
    }

    @Override
    default Path getRoot() {
        return getPath().getRoot();
    }

    @Deprecated
    @Override
    default Path getFileName() {
        return getPath().getFileName();
    }

    @Override
    default Path getParent() {
        return getPath().getParent();
    }

    @Override
    default int getNameCount() {
        return getPath().getNameCount();
    }

    @Override
    default Path getName(int index) {
        return getPath().getName(index);
    }

    @Override
    default Path subpath(int beginIndex, int endIndex) {
        return getPath().subpath(beginIndex, endIndex);
    }

    @Override
    default boolean startsWith(Path other) {
        return getPath().startsWith(other);
    }

    @Override
    default boolean startsWith(String other) {
        return getPath().startsWith(other);
    }

    @Override
    default boolean endsWith(Path other) {
        return getPath().endsWith(other);
    }

    @Override
    default boolean endsWith(String other) {
        return getPath().endsWith(other);
    }

    @Override
    default Path normalize() {
        return getPath().normalize();
    }

    @Override
    default Path resolve(Path other) {
        return getPath().resolve(other);
    }

    @Override
    default Path resolve(String other) {
        return getPath().resolve(other);
    }

    @Override
    default Path resolveSibling(Path other) {
        return getPath().resolveSibling(other);
    }

    @Override
    default Path resolveSibling(String other) {
        return getPath().resolveSibling(other);
    }

    @Override
    default Path relativize(Path other) {
        return getPath().relativize(other);
    }

    @Override
    default URI toUri() {
        return getPath().toUri();
    }

    @Override
    default Path toAbsolutePath() {
        return getPath().toAbsolutePath();
    }

    @Override
    default Path toRealPath(LinkOption... options) throws IOException {
        return getPath().toRealPath(options);
    }

    @Override
    default File toFile() {
        return getPath().toFile();
    }

    @Override
    default WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
        return getPath().register(watcher, events, modifiers);
    }

    @Override
    default WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events) throws IOException {
        return getPath().register(watcher, events);
    }

    @Override
    default Iterator<Path> iterator() {
        return getPath().iterator();
    }

    @Override
    default int compareTo(Path other) {
        return getPath().compareTo(other);
    }

    @Override
    default void forEach(Consumer<? super Path> action) {
        getPath().forEach(action);
    }

    @Override
    default Spliterator<Path> spliterator() {
        return getPath().spliterator();
    }
    //</editor-fold>
    
}
