package pro.shag.controller.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
@RequestMapping("/web/student")
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

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String studentList(Model model){
        model.addAttribute("students", studentService.getAll());
        return "studentList";
    }

   @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addStudent(Model model){
        StudentForm studentForm = new StudentForm();

        Map<String, String> mavs = groupService.getAll().stream()
                .collect(Collectors.toMap(Group::getId, Group::getName));

        model.addAttribute("mavs", mavs);
        model.addAttribute("studentForm", studentForm);
        return "addStudent";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(Model model,
                             @ModelAttribute("studentForm") StudentForm studentForm){

        Group group = groupService.get(studentForm.getGroup());

        String name = studentForm.getName();

 /*        Pattern pattern = Pattern.compile("^[A-Z][a-z]{2,10}");
        Matcher matcher = pattern.matcher(name);

        if(!matcher.matches()) {

            System.out.println("Incorrect name");
            return null;}
*/

        Student newStudent = new Student(studentForm.getId(), studentForm.getName(), group);


        studentService.create(newStudent);
        model.addAttribute("students", studentService.getAll());
        //return "studentList";
        return "redirect:/web/student/list";

        //  "redirect:/web/person/list"
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(Model model, @PathVariable("id") String id){

        studentService.delete(id);

       // model.addAttribute("students", studentService.getAll());
        return "redirect:/web/student/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editStudent(Model model, @PathVariable("id") String id){

        Student student = studentService.get(id);
        Map<String, String> mavs = groupService.getAll().stream()
                .collect(Collectors.toMap(Group::getId, Group::getName));



        String dflt = student.getGroup().getId();
   /*     Map<String, String> mavs = new HashMap<>();
        mavs.put(id, map.get(id));

       map.entrySet().stream().forEach(entry ->{
           //if(!entry.getKey().equals(id)){
               mavs.put(entry.getKey(),entry.getValue());

       });

*/
        StudentForm studentForm = new StudentForm();
        studentForm.setId(id);
        studentForm.setName(student.getName());
        studentForm.setGroup(student.getGroup().getName());
        model.addAttribute("studentForm", studentForm);
        model.addAttribute("mavs", mavs);
        model.addAttribute("dflt", dflt);

        return "editStudent";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editStudent(Model model,
                             @ModelAttribute("studentForm") StudentForm studentForm,
                              @PathVariable("id") String id){

        Student s = new Student();
        Group group = groupService.get(studentForm.getGroup());

        s.setId(studentForm.getId());
        s.setName(studentForm.getName());
        s.setGroup(group);
        s.setMark(studentForm.getMark());
        studentService.update(s);
      //  model.addAttribute("studentForm", studentForm);
        return "redirect:/web/student/list";
    }
}
