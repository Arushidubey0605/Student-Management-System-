package student.management.dao;

import java.util.List;

public interface DAOInterface<T> {
    void insert(T t) throws Exception;
    List<T> getAll() throws Exception;
    boolean deleteById(int id) throws Exception;
    boolean update(T t) throws Exception;
}