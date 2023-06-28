package com.example.springboot.Esercizio_3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Esercizio3 {
    private List<Meal> meals= new ArrayList<>();
    @GetMapping(value ="/get/meals")
    public ResponseEntity<List<Meal>> getMeals(){
        return ResponseEntity.ok(meals);
    }

    @PostMapping(value = "/put/meal")
    public ResponseEntity<String>putMeal(@RequestBody Meal meal){
        this.meals.add(meal);
        return ResponseEntity.ok("Meal added");
    }
    @PutMapping(value = "/post/replace-meal")
    public ResponseEntity<String>postMeal(@RequestBody Meal meal){
        this.meals.removeIf(m -> m.getName().equals(meal.getName()));
        this.meals.add(meal);
        return ResponseEntity.ok("Meal updated");

    }
    @DeleteMapping(value = "/delete/meal/{mealName}")
    public ResponseEntity<String>deleteMeal(@PathVariable String mealName){
        this.meals.removeIf(m ->m.getName().equals(mealName));
        return ResponseEntity.ok("Meal deleted!");

    }
    @DeleteMapping(value = "/delete/meal/price/{price}" )
    public ResponseEntity<String>deleteMealByPrice(@PathVariable Double price){
        this.meals.removeIf(m->m.getPrice()>price);
        return  ResponseEntity.ok("Meal above "+price+" price deleted");
    }
    @PutMapping(value = "/put/meal/{name}/price")
    public ResponseEntity<String>modifyPriceByName(@PathVariable String name,@RequestBody Double updatedPrice){
        Meal mealToUpdate = null;
        for (Meal meal :meals){
            if(meal.getName().equals(name)){
                mealToUpdate=meal;
                break;
            }
        }
        if(mealToUpdate!=null){
            mealToUpdate.setPrice(updatedPrice);
            return ResponseEntity.ok("Price updated");
        }else{
            return ResponseEntity.notFound().build();
        }


    }

}
