package com.spring.security.Spring.Security2.repository;

import com.spring.security.Spring.Security2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // sử dụng tên hàm sai cũng sai ạ: tên sai findByRoleName
    Role findRoleByName(String name);
}
