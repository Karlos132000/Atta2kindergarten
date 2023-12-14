// GroupRepository.java
package org.example.Repository;

import org.example.models.Group;

import java.util.List;

public interface GroupRepository {

    void save(Group group);

    Group findById(int id);

    List<Group> findAll();

    void update(Group group);

    void delete(int id);
}
