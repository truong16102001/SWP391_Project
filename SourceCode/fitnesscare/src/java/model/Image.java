package model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Image {
    private int image_id;
    private String image_url;
}
