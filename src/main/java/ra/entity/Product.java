package ra.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    private int stock;

    private String image;
}
