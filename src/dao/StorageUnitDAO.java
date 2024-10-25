package dao;

public interface StorageUnitDAO<T> {

    T save(T obj);
    boolean isCodeInDatabase(String code);

}
