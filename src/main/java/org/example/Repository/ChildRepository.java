// ChildRepository.java
package org.example.Repository;

import org.example.models.Child;

import java.util.List;

public interface ChildRepository {

    void save(Child child);

    Child findById(int id);

    List<Child> findAll();

    void update(Child child);

    void delete(int id);

    List<Child> getAllChildren();
}
