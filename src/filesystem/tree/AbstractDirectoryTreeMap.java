package filesystem.tree;

import core.util.collections.Lists;
import datastructure.tree.interfaces.TreeMap;
import graph.GraphIterator;
import graph.search.GraphSearchStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creator: Patrick
 * Created: 15.11.2017
 * Purpose:
 */
public abstract class AbstractDirectoryTreeMap<K, V extends Iterable<V>> implements TreeMap<K, V> {
    protected final V _root;
    protected final Map<K, V> _map;

    public AbstractDirectoryTreeMap(V root) {
        this(root, new HashMap<>());
    }

    public AbstractDirectoryTreeMap(V root, HashMap<K, V> map) {
        _root = root;
        _map = map;
    }

    protected V getRoot(){
        return _root;
    }

    @Override
    public V get(K key) {
        return _map.get(key);
    }
    public V put(K directory, V value) {
        return _map.put(directory, value);
    }

    @Override
    public List<V> toList(GraphSearchStrategy<V> strategy) {
        GraphIterator<V> iterator = GraphIterator.from(_root, strategy);
        return Lists.toArrayList(iterator);
    }
}
