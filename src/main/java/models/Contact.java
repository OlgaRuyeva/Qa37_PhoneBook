package models;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
//@NoArgsConstructor = для пустого конструктора
//@AllArgsConstructor = для конструктора по всем полям
public class Contact {
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String description;


}
