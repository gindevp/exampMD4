package com.example.exampmd4.controller;

import com.example.exampmd4.model.City;
import com.example.exampmd4.model.Nation;
import com.example.exampmd4.service.nation.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nation")
@CrossOrigin("*")
public class NationController {
    @Autowired
    private NationService nationService;
    //API http://localhost:8080/api/nation
    @GetMapping
    public ResponseEntity<Iterable<Nation>> showAll(){
        List<Nation> nations= (List<Nation>) nationService.findAll();
        if(nations.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(nations,HttpStatus.OK);
    }
    //API http://localhost:8080/api/nation
    @PostMapping
    public ResponseEntity<Nation> create(@RequestBody Optional<Nation> nation){
        if(nation.isPresent()){
            if(!nationService.existsByUserName(nation.get().getName())){
                return new ResponseEntity<>(nationService.save(nation.get()), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }
    //API http://localhost:8080/api/nation/edit/{id}
    @PutMapping("/edit/{id}")
    public ResponseEntity<Nation> editUser(@RequestBody Nation nation, @PathVariable Long id){
        Optional<Nation> optionalNation= nationService.findById(id);
        if(!optionalNation.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        nation.setId(optionalNation.get().getId());
        return new ResponseEntity<>(nationService.save(nation),HttpStatus.OK);
    }
    //API http://localhost:8080/api/nation/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Nation> deleteUser(@PathVariable Long id) {
        Optional<Nation> optionalNation = nationService.findById(id);
        if(!optionalNation.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        nationService.remove(id);
        return new ResponseEntity<>(optionalNation.get(),HttpStatus.OK);
    }
    //API http://localhost:8080/api/nation/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Nation> showUser(@PathVariable Long id){
        Optional<Nation> optionalNation= nationService.findById(id);
        if(!optionalNation.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(optionalNation.get(),HttpStatus.OK);
    }
}
