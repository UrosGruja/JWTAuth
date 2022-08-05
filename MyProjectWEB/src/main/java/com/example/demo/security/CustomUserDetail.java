/*
 * package com.example.demo.security;
 * 
 * import java.util.Collection;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import model.User;
 * 
 * public class CustomUserDetail implements UserDetails {
 * 
 * private User u;
 * 
 * public User getU() { return u; } public void setU(User u) { this.u = u; }
 * public CustomUserDetail(User u) { this.u = u; }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() { //
 * TODO Auto-generated method stub return null; }
 * 
 * @Override public String getPassword() { // TODO Auto-generated method stub
 * return u.getPassword(); }
 * 
 * @Override public String getUsername() { // TODO Auto-generated method stub
 * return u.getEmail(); } public String getName() { return u.getName(); }
 * 
 * @Override public boolean isAccountNonExpired() { // TODO Auto-generated
 * method stub return false; }
 * 
 * @Override public boolean isAccountNonLocked() { // TODO Auto-generated method
 * stub return false; }
 * 
 * @Override public boolean isCredentialsNonExpired() { // TODO Auto-generated
 * method stub return false; }
 * 
 * @Override public boolean isEnabled() { // TODO Auto-generated method stub
 * return false; }
 * 
 * }
 */
