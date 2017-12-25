package filesystem.tree;

import core.tuple.Triplet;
import core.tuple.Tuple;
import core.util.ComparisonResult;
import core.util.collections.Maps;
import core.util.contracts.Contract;
import filesystem.attributes.FileSnapshot;
import filesystem.files.FileStatus;
import filesystem.path.exception.PathsNotMatchingException;
import filesystem.path.interfaces.compound.AbsoluteDirectory;
import filesystem.path.interfaces.compound.RelativeDirectory;
import graph.search.DepthFirstSearch;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.*;

import static core.util.ComparisonResult.EQUAL;

/**
 * @author Patrick
 * @since 19.11.2017
 * Saves a directory as primary and compares it with another one
 */
public class DirectoryComparer<T extends DirectoryTreeMap> {
    private T _primary;

    public DirectoryComparer(@NotNull T primary) {
        Contract.checkNull(primary, "primary");
        _primary = primary;
    }

    /**
     * Compares the primary directory with another directory given as parameter.
     * @param other the counterpart required to compare two directory hierarchies.
     */
    public void compareTo(@NotNull T other){
        Contract.checkNull(other, "other");
        List<DirectoryTreeNode> primaryDirectories = _primary.toList(new DepthFirstSearch<>());

        for (DirectoryTreeNode directory : primaryDirectories) {
            // TODO: Continue from here, the directory from the other DirTreeMap is not correctly retrieved.
            AbsoluteDirectory secondaryPath = getEquivalentPath(directory.getAbsoluteDirectory(), other.getRoot().getAbsoluteDirectory());
            List<Triplet<String, FileStatus, IOException>> triplets = resolveDirectories(directory, other, secondaryPath);

            System.out.println();
            // Remove both roots to have relative directories only, outgoing from these roots
        }
    }

    private List<Triplet<String, FileStatus, IOException>> resolveDirectories(DirectoryTreeNode directory, T other, AbsoluteDirectory secondaryPath) {
        DirectoryTreeNode directoryTreeNode = other.get(secondaryPath.getPath());

        List<BasicFileAttributeView> primaryFiles = directory.getFiles();
        List<BasicFileAttributeView> secondaryFiles = directoryTreeNode.getFiles();
        HashMap<String, BasicFileAttributeView> fileNameMap = Maps.fromValues(secondaryFiles, BasicFileAttributeView::name);

        return resolveFiles(primaryFiles, fileNameMap);
    }

    private List<Triplet<String, FileStatus, IOException>> resolveFiles(List<BasicFileAttributeView> primaryFiles, HashMap<String, BasicFileAttributeView> directoryMap){
        ArrayList<Triplet<String, FileStatus, IOException>> files = new ArrayList<>();

        // TODO: Process parallel, because comparing individual values might cause problems, due to IO Exceptions and take longer.
        for (BasicFileAttributeView file : primaryFiles) {
            final String fileName = file.name();

            // Step 1: If other directory does not have the same file, current file must be new
            if (!directoryMap.containsKey(fileName)){
                files.add(Triplet.from(fileName, FileStatus.NEW, null));
            }
            // Step 2: If equivalent file exists, check if it is new
            else {
                FileSnapshot primarySnapshot = FileSnapshot.of(file);
                BasicFileAttributeView otherFile = directoryMap.get(fileName);

                try {
                    int compareResult = primaryIsNewer(primarySnapshot, otherFile);
                    FileStatus status = compareResult >= EQUAL ? FileStatus.UNCHANGED : FileStatus.MODIFIED;
                    files.add(Triplet.from(fileName, status, null));

                } catch (IOException ex) {
                    files.add(Triplet.from(fileName, FileStatus.UNRESOLVED, ex));
                }
            }

            // Finally, remove resolved file from the map. This is to check any leftover items
            directoryMap.remove(fileName);
        }

        // Add all leftover files as new files
        for (String leftoverFile : directoryMap.keySet()) {
            files.add(Triplet.from(leftoverFile, FileStatus.DELETED, null));
        }

        return files;
    }

    private int primaryIsNewer(BasicFileAttributeView primaryFile, BasicFileAttributeView otherFile) throws IOException {
        final int maxTry = 20;
        Integer compareResult = null;
        IOException exception = null;

        for (int count = 0; compareResult == null && count != maxTry; ++count ){
            try {
                FileTime primaryTime = primaryFile.readAttributes().lastModifiedTime();
                FileTime otherTime = otherFile.readAttributes().lastModifiedTime();
                compareResult = primaryTime.compareTo(otherTime);
            } catch (IOException ex) {
                exception = ex;
            }
        }

        if (compareResult == null){
            throw exception;
        }

        return compareResult;
    }


    private AbsoluteDirectory getEquivalentPath(AbsoluteDirectory directory, AbsoluteDirectory secondaryRoot){
        try {
            AbsoluteDirectory primaryPath = getPrimaryPath();
            RelativeDirectory relativePath = directory.remove(primaryPath);
            return secondaryRoot.append(relativePath);
        } catch (PathsNotMatchingException e) {
            e.printStackTrace();
        }

        // TODO
        throw new RuntimeException("TODO");
    }

    private AbsoluteDirectory getPrimaryPath(){
        return _primary.getRoot().getAbsoluteDirectory();
    }

}
