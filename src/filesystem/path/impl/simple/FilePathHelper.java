package filesystem.path.impl.simple;

import filesystem.path.interfaces.simple.FilePath;

import java.util.Optional;

/**
 * Creator: Patrick
 * Created: 03.08.2017
 * Purpose:
 */
public interface FilePathHelper extends FilePath{

    default Optional<String> getPostfix() {
        Optional<String> postfix = Optional.empty();
        String[] split = getPath().toString().split(".");

        if (split.length > 1){
            int lastIndex = split.length - 1;
            postfix = Optional.of(split[lastIndex]);
        }

        return postfix;
    }
}
