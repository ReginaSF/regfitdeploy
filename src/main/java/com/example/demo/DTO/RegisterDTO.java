package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

private long id;
@NotBlank (message = "First name is required")
private String firstName;
@NotBlank (message = "Last name is required")
private String lastName;
@NotBlank (message = "Email is required")
@Email
private String email;
private String phone;
private String address;
@Size(min=6, message="min length is 6 characters")
private String password;
private String confirmPassword;
private String role;

    public RegisterDTO() {
    }
public long getId() {
    return id;
}
public void setId(long id) {
    this.id = id;
}
public String getFirstName() {
    return firstName;
}
public void setFirstName(String firstName) {
    this.firstName = firstName;
}
public String getLastName() {
    return lastName;
}
public void setLastName(String lastName) {
    this.lastName = lastName;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getPhone() {
    return phone;
}
public void setPhone(String phone) {
    this.phone = phone;
}
public String getAddress() {
    return address;
}
public void setAddress(String address) {
    this.address = address;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
}
public String getConfirmPassword() {
    return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
}
public String getRole() {
    return role;
}
public void setRole(String role) {
    this.role = role;
}
public RegisterDTO(long id, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty @Email String email,
        String phone, String address, @Size(min = 6, message = "min length is 6 characters") String password,
        String confirmPassword, String role) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.password = password;
    this.confirmPassword = confirmPassword;
    this.role = role;
}

}
