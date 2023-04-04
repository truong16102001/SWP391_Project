package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Material {

    private int material_id;
    private String material_name;
    private int quantity;
    private int broken_quantity;
}
