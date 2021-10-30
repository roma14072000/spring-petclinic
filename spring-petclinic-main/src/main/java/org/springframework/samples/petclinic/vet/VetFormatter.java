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
package org.springframework.samples.petclinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

//TODO
@Component
public class VetFormatter implements Formatter<Vet> {

	private final VetRepository vetRepository;

	@Autowired
	public VetFormatter(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public String print(Vet vet, Locale locale) {
		return vet.getFirstName() + " " + vet.getLastName();
	}

	@Override
	public Vet parse(String id, Locale locale) throws ParseException {
		Collection<Vet> vets = vetRepository.findAll();
		for (Vet vet : vets) {
			if (vet.getId().equals(Integer.valueOf(id))) {
				return vet;
			}
		}
		throw new ParseException("vet not found!", 0);
	}

}

