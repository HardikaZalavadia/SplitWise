package repository;

import models.Expense;
import models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {
    List<Group> groups = new ArrayList<>();

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    public List<Expense> getExpenseByGroup(String groupName){
        List<Expense> expenses = new ArrayList<>();
        for(Group group: groups){
            if(group.getName().equals(groupName)){
                return group.getExpense();
            }
        }
        return new ArrayList<>();
    }
}
