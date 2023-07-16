package com.preetamlahre.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPersion() {
		return new PersonV1("Atul Kacchap");
	}
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPersion() {
		return new PersonV2(new Name("Atul","Kachapp"));
	}
	@GetMapping(path = "/person",params="version=1")
	public PersonV1 getFirstVersionOfPersionRequestParameter() {
		return new PersonV1("Atul Kacchap");
	}
	@GetMapping(path="/person",params="version=2")
	public PersonV2 getSecondVersionOfPersionRequestParameter() {
		return new PersonV2(new Name("Atuldd","Kachapp"));
	}
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
