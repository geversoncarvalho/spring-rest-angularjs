package com.geverson.people;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/public/people")
public class PeopleController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<People> peopleList() {
        People person = new People("Fulano", "Santos", "Brasil", 25);
        List<People> list = new ArrayList<People>();
        list.add(person);
        return list;
    }

}
