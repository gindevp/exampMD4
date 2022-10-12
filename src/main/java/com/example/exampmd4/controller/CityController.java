package com.example.exampmd4.controller;

import com.example.exampmd4.model.City;
import com.example.exampmd4.service.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/city")
@CrossOrigin("*")
public class CityController {
    @Autowired
    private CityService cityService;
    //API http://localhost:8080/api/city
    @GetMapping
    public ResponseEntity<Iterable<City>> showAll(){
        List<City> citySet= (List<City>) cityService.findAll();
        if(citySet.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(citySet,HttpStatus.OK);
    }
    //API http://localhost:8080/api/city
    @PostMapping
    public ResponseEntity<City> create(@RequestBody Optional<City> city){
        if(city.isPresent()){
            if(!cityService.existsByUserName(city.get().getName())){
                return new ResponseEntity<>(cityService.save(city.get()), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }
    //API http://localhost:8080/api/city/edit/{id}
    @PutMapping("/edit/{id}")
    public ResponseEntity<City> editUser(@RequestBody City city, @PathVariable Long id){
        Optional<City> appUsersOptional= cityService.findById(id);
        if(!appUsersOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(appUsersOptional.get().getId());
        return new ResponseEntity<>(cityService.save(city),HttpStatus.OK);
    }
    //API http://localhost:8080/api/city/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<City> deleteUser(@PathVariable Long id) {
        Optional<City> appUsersOptional = cityService.findById(id);
        if(!appUsersOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(id);
        return new ResponseEntity<>(appUsersOptional.get(),HttpStatus.OK);
    }
    //API http://localhost:8080/api/city/{id}
    @GetMapping("/{id}")
    public ResponseEntity<City> showUser(@PathVariable Long id){
        Optional<City> optionalCity= cityService.findById(id);
        if(!optionalCity.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(optionalCity.get(),HttpStatus.OK);
    }
}
