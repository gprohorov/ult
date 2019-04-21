package pro.shag.controller.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pro.shag.forms.StudentForm;
import pro.shag.model.Group;
import pro.shag.model.Student;
import pro.shag.service.group.impls.GroupServiceMongoImpl;
import pro.shag.service.student.impls.StudentServiceImpl;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    @Autowired
    GroupServiceMongoImpl groupService;

    @Autowired
    StudentServiceImpl studentService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("message", "Hello world!");
        return "index";
    }

    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public String studentList(Model model){
        model.addAttribute("students", studentService.getAll());
        return "studentList";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public String addStudent(Model model){
        StudentForm studentForm = new StudentForm();

        Map<String, String> mavs = groupService.getAll().stream()
                .collect(Collectors.toMap(Group::getId, Group::getName));

        model.addAttribute("mavs", mavs);
        model.addAttribute("studentForm", studentForm);
        return "addStudent";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public String addStudent(Model model,
                             @ModelAttribute("studentForm") StudentForm studentForm){

        Group group = groupService.get(studentForm.getGroup());

        Student newStudent = new Student(studentForm.getId(), studentForm.getName(), group);
        studentService.create(newStudent);
        model.addAttribute("students", studentService.getAll());
        return "studentList";
    }

    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(Model model, @PathVariable("id") String id){

        studentService.delete(id);

        model.addAttribute("students", studentService.getAll());
        return "studentList";
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.GET)
    public String editStudent(Model model, @PathVariable("id") String id){

        Student s = studentService.get(id);
        Map<String, String> mavs = groupService.getAll().stream()
                .collect(Collectors.toMap(Group::getId, Group::getName));


        StudentForm studentForm = new StudentForm();
        studentForm.setId(s.getId());
        studentForm.setName(s.getName());
        studentForm.setGroup(s.getGroup().getName());
        model.addAttribute("studentForm", studentForm);
        model.addAttribute("mavs", mavs);
        return "editStudent";
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.POST)
    public String editStudent(Model model,
                             @ModelAttribute("studentForm") StudentForm studentForm,
                              @PathVariable("id") String id){

        Student s = new Student();
        Group group = groupService.get(studentForm.getGroup());

        s.setId(studentForm.getId());
        s.setName(studentForm.getName());
        s.setGroup(group);
        studentService.update(s);
        model.addAttribute("studentForm", studentForm);
        return "studentList";
    }
}
