package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class User {

    private int user_id;
    private String fullName;
    private String password;
    private String avatar;
    private boolean gender;
    private String email;
    private String phone;
    private String address;
    private boolean status;
    private int role_id;

    public User() {
    }

    public User(int user_id, String fullName, String password, String avatar, boolean gender, String email, String phone, String address, boolean status, int role_id) {
        this.user_id = user_id;
        this.fullName = fullName;
        this.password = password;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.role_id = role_id;
    }
}
