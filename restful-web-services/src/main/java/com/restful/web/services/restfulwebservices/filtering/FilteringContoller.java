package com.restful.web.services.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringContoller {

    @GetMapping("/filtering")
    public  MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean =new SomeBean("Bean-1", "Bean-2", "Bean-3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field_1", "field_2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilters", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);
        return mapping;
    }

//    @GetMapping("/filtering-list")
//    public List<SomeBean> retrieveListOfBeans() {
//        return Arrays.asList(new SomeBean("Bean-1", "Bean-2", "Bean-3"), new SomeBean("Bean-4", "Bean-5", "Bean-6"));
//    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfBeans() {
        List<SomeBean> list = Arrays.asList(new SomeBean("List_1", "List_2", "List_3"), new SomeBean("List_4", "List_5", "List_6"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field_2", "field_3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilters", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }
}
