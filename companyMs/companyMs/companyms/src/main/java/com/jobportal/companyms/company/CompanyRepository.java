package com.jobportal.companyms.company;

import org.springframework.data.jpa.repository.JpaRepository;


//Repository layer, implemented as an interface, extends Spring Data’s JPA repository.
// It incorporates methods for common CRUD operations (Create, Read, Update, Delete) and often supports query methods..
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
