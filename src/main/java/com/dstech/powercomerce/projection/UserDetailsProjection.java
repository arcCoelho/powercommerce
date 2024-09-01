package com.dstech.powercomerce.projection;

public interface UserDetailsProjection {

    public String getUsername();
    public String getPassword();
    public Long getRoleId();
    public String getAuthority();

}
