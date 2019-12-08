public interface Set<T> {
    /**
     * @param element the element to be added, must not be null
     * @return true if the element was added (i.e. no duplicates)
     */
    boolean add(T element);

    /**
     *
     * @param element the element to remove, must not be null
     * @return true if the element was found in the set and successfully removed
     */
    boolean remove(T element);
    boolean contains(T element);
    int size();
    Object[] toArray();
    Set<T> newInstance();
}
