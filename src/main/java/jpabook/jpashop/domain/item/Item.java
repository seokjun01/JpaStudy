package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//싱글 테이블 전
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한테이블에 다 때려박는 전략
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = " item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

}