package com.search_traffic.rdb;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "public")
@Table(name = "public")
public class PublicDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String author;
    private String company;
    private String year;


    public static PublicDataEntity of(String name,String author,String company, String year){
        return new PublicDataEntity(null,name,author,company,year);
    }
}
