/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}

}

@Component
class SpringInfoContributor implements InfoContributor {

	@Override
	public void contribute(Info.Builder builder) {
		Map<String, Object> springBootDetails = new HashMap<>();
		springBootDetails.put("version", SpringBootVersion.getVersion());
		Map<String, Object> springDetails = new HashMap<>();
		springDetails.put("boot", springBootDetails);

		builder
			.withDetail("spring", springDetails);
	}
}

@Component
class JavaInfoContributor implements InfoContributor {

	@Override
	public void contribute(Info.Builder builder) {
		Map<String, Object> javaDetails = new HashMap<>();
		javaDetails.put("version", System.getProperty("java.version"));
		javaDetails.put("vendor", System.getProperty("java.vendor"));
		javaDetails.put("vm", System.getProperty("java.vm.name"));

		builder.withDetail("java", javaDetails);
	}
}
