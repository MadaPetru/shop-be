package ro.adi.shop.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
