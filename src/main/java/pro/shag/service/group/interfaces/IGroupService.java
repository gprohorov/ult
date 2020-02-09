package pro.shag.service.group.interfaces;


import pro.shag.model.Group;


import java.util.List;

public interface IGroupService  {
    Group getByName(String name);

    List<Group> getAllByName(String name);
}
