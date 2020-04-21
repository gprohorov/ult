package pro.shag.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.shag.forms.StudentForm;
import pro.shag.model.Group;
import pro.shag.model.Student;
import pro.shag.service.group.impls.GroupServiceMongoImpl;
import pro.shag.service.student.impls.StudentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentRESTController {

    @Autowired
    StudentServiceImpl service;
    @Autowired
    GroupServiceMongoImpl groupService;

    @RequestMapping("/list")
    List<Student> showAll(){
        return service.getAll();
    }


    @RequestMapping("/{id}")
    Student getById(@PathVariable("id") String id){
        return service.get(id);
    }


    @PostMapping("/create")
    Student createGroupPost(@RequestBody StudentForm studentForm){
        Group group = groupService.get(studentForm.getGroup());
        Student student = new Student(studentForm.getId(), studentForm.getName(), group);
       return this.service.create(student);
       // return student;
    }



    @PostMapping("/update")
    Student updateGroupPost(@RequestBody Student student){
//        this.service.create(student);
        return service.update(student);
    }

    @RequestMapping("/delete/{id}")
    void deleteById(@PathVariable("id") String id){
       service.delete(id);
    }
}
