// ChildService.java
package org.example.services;

import org.example.DI.Component;
import org.example.DI.Inject;
import org.example.models.Child;

import java.util.HashMap;
import java.util.Map;
@Component
public class ChildService {
    private Map<String, Child> children = new HashMap<>();

    @Inject
   private GroupService groupService;

    public void addChild(Child child) {
        children.put(child.getFullName(), child);

    }

    public void removeChild(String fullName) {
        children.remove(fullName);
    }

    public Child getChildByFullName(String fullName) {
        return children.get(fullName);
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public GroupService getGroupService() {
        return groupService;
    }


}