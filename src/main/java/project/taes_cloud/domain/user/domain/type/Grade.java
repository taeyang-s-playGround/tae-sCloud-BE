package project.taes_cloud.domain.user.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Grade {
    BRONZE(0,10),
    SILVER(10,15),
    GOLD(25,20),
    PLATINUM(45,25),
    DIAMOND(70,0);
    private final int point;
    private final int nextPointLength;
}
