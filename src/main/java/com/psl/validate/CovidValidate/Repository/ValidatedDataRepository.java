package com.psl.validate.CovidValidate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.validate.CovidValidate.model.ValidatedData;


@Repository("validateddata")
public interface ValidatedDataRepository extends JpaRepository<ValidatedData, Integer> {

}
