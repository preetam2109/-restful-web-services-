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
}
