package com.gameshop.demo.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Order {
    private String id;
    private String date;
    private String customerName;
    private String address;
    private List<Game> gameList;
}
