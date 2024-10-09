package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable //JPA의 내장파일임을 의미
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

}