package org.example.services;

import org.example.DI.Component;
import org.example.DI.Inject;
import org.example.models.Group;

import java.util.HashMap;
import java.util.Map;
@Component
public class GroupService {
    private Map<String, Group> groups = new HashMap<>();

    @Inject
    private ChildService childService;

    public void addGroup(Group group) {
        groups.put(group.getName(), group);
    }

    public void removeGroup(String name) {
        groups.remove(name);
    }

    public Group getGroupByName(String name) {
        return groups.get(name);
    }

    public void setChildService(ChildService childService) {
        this.childService = childService;
    }
}