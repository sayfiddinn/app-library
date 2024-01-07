package com.example.demo.configuration;

import com.example.demo.entity.Book;
import com.example.demo.entity.Library;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.LibraryRepo;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.RoleName;
import com.example.demo.entity.enums.RoleTypeEnum;
import com.example.demo.repository.RoleRepository;
import com.example.demo.util.Utils;

import java.util.HashSet;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final LibraryRepo libraryRepo;
    final BookRepo bookRepo;
    @Value("${spring.sql.init.mode}")
    String mode;

    @Override
    public void run(String... args) {
        if (!Objects.equals(mode, "always"))
            return;
        Role superAdminRole = new Role();
        Role adminRole = new Role();
        Role moderatorRole = new Role();
        Role userRole = new Role();

        superAdminRole.setName(RoleName.SUPER_ADMIN.name());
        superAdminRole.setAuthorities(new HashSet<>(Utils.superAdminAuthority));
        superAdminRole.setRoleType(RoleTypeEnum.SUPER_ADMIN);

        adminRole.setName(RoleName.ADMIN.name());
        adminRole.setAuthorities(new HashSet<>(Utils.adminAuthority));
        adminRole.setRoleType(RoleTypeEnum.ADMIN);

        moderatorRole.setName(RoleName.MODERATOR.name());
        moderatorRole.setAuthorities(new HashSet<>(Utils.moderAuthority));
        moderatorRole.setRoleType(RoleTypeEnum.MODERATOR);

        userRole.setName(RoleName.USER.name());
        userRole.setAuthorities(new HashSet<>(Utils.userAuthority));
        userRole.setRoleType(RoleTypeEnum.USER);

        roleRepository.save(superAdminRole);
        roleRepository.save(adminRole);
        roleRepository.save(moderatorRole);
        roleRepository.save(userRole);

        User superAdmin = new User();
        superAdmin.setRole(superAdminRole);
        superAdmin.setEmail("SuperAdmin@gmail.com");
        superAdmin.setPassword(passwordEncoder.encode("root123"));
        superAdmin.setFirstName("superAdmin");
        superAdmin.setLastName("admin");
        userRepository.save(superAdmin);

        User admin = new User();
        admin.setRole(adminRole);
        admin.setEmail("Admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("root123"));
        admin.setFirstName("admin");
        admin.setLastName("admin");
        userRepository.save(admin);

        User moder = new User();
        moder.setRole(moderatorRole);
        moder.setEmail("Moder@gmail.com");
        moder.setPassword(passwordEncoder.encode("root123"));
        moder.setFirstName("moder");
        moder.setLastName("moder");
        userRepository.save(moder);

        Library library=new Library();
        library.setName("first_library");
        libraryRepo.save(library);

        Book book=new Book();
        book.setLibrary(library);
        book.setCount(10);
        book.setAuthor("Kel Nyuport");
        book.setName("Diqqat");
        book.setPrice(30000D);
        bookRepo.save(book);

    }


}
