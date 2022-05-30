package com.restful.web.services.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
@GetMapping("/person/v1")
    public PersonV1 personV1() {
        return new PersonV1(" Zack Bello");
    }
@GetMapping("/person/v2")
    public  PersonV2 personV2() {
        return  new PersonV2(new Name("Maryam", "Ahmed"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 personParamV1() {
        return new PersonV1("Laila Ali");
    }
    
    @GetMapping(value = "/person/param", params = "version=2")
    public  PersonV2 personParamV2() {
        return  new PersonV2(new Name("Mohammed", "Ali"));
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 personHeaderV1() {
        return new PersonV1("Cassera Sheild");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 personHeaderV2() {
        return new PersonV2(new Name("Hammer", "Gabriel"));
    }
    
    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personProduceV1() {
        return new PersonV1("Decagon Tech");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personProduceV2() {
        return new PersonV2(new Name("Banking", "Moon"));
    }
}
