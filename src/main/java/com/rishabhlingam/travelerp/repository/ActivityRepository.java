package com.rishabhlingam.travelerp.repository;

import org.springframework.data.repository.CrudRepository;

import com.rishabhlingam.travelerp.models.Activity;

public interface ActivityRepository extends CrudRepository<Activity, String> {

}
