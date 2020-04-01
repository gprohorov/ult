package pro.shag.controller.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.shag.model.Group;
import pro.shag.service.group.impls.GroupServiceMongoImpl;

import java.util.List;

//@RestController
@RequestMapping("/api/group")
@CrossOrigin("*")
@Api(value = "GroupControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupRESTController {

    @Autowired
    GroupServiceMongoImpl groupService;

    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = List.class)})
    @RequestMapping("/list")
    List<Group> showAll(){
        return groupService.getAll();
    }

    @RequestMapping("/find/{name}")
    Group getByName(@PathVariable("name") String name){
        return groupService.getByName(name);
    }

    @RequestMapping("/findAll/{name}")
    List<Group> getAllByName(@PathVariable("name") String name){
        return groupService.getAllByName(name);
    }

    @ApiOperation("Gives  group by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Group.class)})
    @RequestMapping("/{id}")
    Group getById(@PathVariable("id") String id){
        return groupService.get(id);
    }

    @RequestMapping("/create/{name}/{curator}")
    Group createGroup(@PathVariable("name") String name,
                      @PathVariable("curator") String curator){
        return groupService.create(new Group(name, curator));
    }

    @PostMapping("/create")
    Group createGroupPost(@RequestBody Group group){
        this.groupService.create(group);
        return group;
    }

    @RequestMapping("/update/{id}/{name}/{curator}")
    Group updateGroup(@PathVariable("id") String id,
                      @PathVariable("name") String name,
                      @PathVariable("curator") String curator){
        return groupService.update(new Group(id, name, curator));
    }

    @PostMapping("/update")
    Group updateGroupPost(@RequestBody Group group){
        this.groupService.create(group);
        return groupService.update(group);
    }

    @RequestMapping("/delete/{id}")
    Group deleteById(@PathVariable("id") String id){
        return groupService.delete(id);
    }
}
