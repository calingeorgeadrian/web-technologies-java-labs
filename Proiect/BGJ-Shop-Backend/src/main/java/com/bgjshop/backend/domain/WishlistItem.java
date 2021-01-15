package com.bgjshop.backend.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class WishlistItem {
    private String userId;
    private Long gameId;
}
