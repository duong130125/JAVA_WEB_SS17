package ra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.utils.EnumAuth;

import javax.persistence.*;

import static ra.utils.EnumAuth.USER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id // đánh dấu là khoá chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự tăng
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private EnumAuth role = USER; // ADMIN, USER
    private boolean status = true; // true: active, false: inactive
}
