package com.rishabhlingam.travelerp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rishabhlingam.travelerp.models.TravelPackage;
import com.rishabhlingam.travelerp.repository.TravelPackageRepository;

@Service
public class TravelPackageService {
	@Autowired
	TravelPackageRepository repository;
	
	public List<TravelPackage> getAllTravelPackages(){
		List<TravelPackage> list = new ArrayList<>();
		repository.findAll().forEach(list::add);
		return list;
	}
	
	public TravelPackage addTravelPackage(TravelPackage i) {
		return repository.save(i);
	}
	
	public Optional<TravelPackage> findById(String id) {
		Optional<TravelPackage> travelPackage = repository.findById(id);
		return travelPackage;
	}
}
