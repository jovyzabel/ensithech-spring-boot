package com.project.ensitech.controller.speciality;

import com.project.ensitech.enumeration.Cycle;
import com.project.ensitech.model.dto.SpecialityDto;
import com.project.ensitech.model.entity.Speciality;
import com.project.ensitech.service.common.ISpecialityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/speciality")
@CrossOrigin("*")
public class SpecialityController {
    private final ISpecialityService iSpecialityService;


    public SpecialityController(ISpecialityService iSpecialityService) {
        this.iSpecialityService = iSpecialityService;
    }

    @GetMapping("/{id}")
    public Speciality get(@PathVariable int id){
        return this.iSpecialityService.get(id);
    }

    @GetMapping
    public List<Speciality> getAll(){

        //return List.of(new Speciality(1,"cc","dddd", Cycle.LICENCE),new Speciality(1,"cc","dddd", Cycle.LICENCE));
        //return ResponseEntity.ok(this.iSpecialityService.getAll());
        return this.iSpecialityService.getAll();
    }

    @PostMapping
    public Speciality create(@RequestBody SpecialityDto dto){
        return this.iSpecialityService.create(dto);
    }

    @PutMapping("/{id}")
    public Speciality update(@PathVariable int id, @RequestBody SpecialityDto dto){
        return this.iSpecialityService.update(id, dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        this.iSpecialityService.delete(id);
    }
}
