package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

//    @ManyToMany
//    private Set<Recipe> recipes;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
