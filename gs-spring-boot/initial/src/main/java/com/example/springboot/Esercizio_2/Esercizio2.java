package com.example.springboot.Esercizio_2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Esercizio2 {
    private List<Meal> mealList= Arrays.asList(
            new Meal(1,"Spaghetti alla Carbonara","Uova, Parmigiano, Guanciale",12.00),
            new  Meal(2, "Lasagna al Ragù", "Carne macinata, pasta fresca, pomodoro", 15.00),
            new Meal(3, "Pizza Margherita", "Mozzarella, pomodoro, basilico", 10.00),
            new Meal(4, "Insalata di Pollo", "Petto di pollo, lattuga, pomodori, cetrioli", 8.00)

    );

    @GetMapping(value = "/meals")
    public ResponseEntity<List<Meal>>index(){
        return (ResponseEntity.ok(mealList));
    }
    @GetMapping(value = "/get/meal/{name}")
    public ResponseEntity<?>getMealList(@PathVariable("name")String  name) {
        Meal foundMeal = null;
        for (Meal meal : mealList) {
            if (meal.getName().equalsIgnoreCase(name)) {
                foundMeal = meal;
                break;
            }

            }
        if (foundMeal != null) {
            return ResponseEntity.ok(foundMeal); // Return the found Meal object
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if no matching meal found

        }


    }
    @GetMapping(value = "/get/meal/description-match/{phrase}")
    public ResponseEntity<?>getMealDescrpt(@PathVariable("phrase")String phrase){
        Meal foundDescrpt=null;
        for(Meal meal:mealList){
            if (meal.getDescription().contains(phrase)){
                foundDescrpt=meal;
                break;
            }

        }
        if(foundDescrpt!=null){
            return ResponseEntity.ok(foundDescrpt);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/meal/price")

    public ResponseEntity<?>getPriceMeal(@RequestParam("min") Double minPrice,@RequestParam("max") Double maxPrice){

        List<Meal> mealsInPriceRange = new ArrayList<>();
        for (Meal meal : mealList){
            if (meal.getPrice()>minPrice && meal.getPrice()<maxPrice){
                mealsInPriceRange.add(meal);
            }
        }
        if (!mealsInPriceRange.isEmpty()) {
            return ResponseEntity.ok(mealsInPriceRange); // Return the list of Meals within the price range
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if no meals found in the specified price range
        }

    }


}
