package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.RoleTypeEnum;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.payload.ResultMessage;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Path;
import com.example.demo.util.Util;
import com.example.demo.util.Utils;
import com.example.demo.util.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BaseService baseService;

    @Override
    public ResultMessage addModerator(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("This user" + Path.NOT_FOUND));
        checkUser(user.isDeleted());
        if (Objects.equals(user.getRole().getRoleType(), RoleTypeEnum.MODERATOR)) {
            return new ResultMessage(false, "This user is already a moderator");
        }
        changeUserRole(user, RoleTypeEnum.MODERATOR);
        return ResultMessage.builder()
                .success(true)
                .object("This user has been made a moderator")
                .build();
    }

    private void checkUser(boolean deleted) {
        if (deleted) throw new NotFoundException("This userId not found");
    }

    @Override
    public ResultMessage deleteModerator(UUID id) {
        User user = userRepository.findByIdAndRole_RoleType(id, RoleTypeEnum.MODERATOR)
                .orElseThrow(() -> new NotFoundException("This moderator" + Path.NOT_FOUND));
        checkUser(user.isDeleted());
        if (!Objects.equals(user.getRole().getRoleType(), RoleTypeEnum.MODERATOR)) {
            return new ResultMessage(false, "This user is not a moderator");
        }
        changeUserRole(user, RoleTypeEnum.USER);
        return new ResultMessage(true, Path.DELETED);
    }

    @Override
    public ResultMessage showModerators(Integer page, Integer size) {
        Validation.checkPage(page, size, userRepository
                .countAllByRole_RoleType(RoleTypeEnum.MODERATOR));
        List<User> moderators = userRepository
                .findAllByRole_RoleType(RoleTypeEnum.MODERATOR,
                        Util.getPageable(page, size));
        return new ResultMessage(true, moderators);
    }

    @Override
    public ResultMessage showModerator(UUID id) {
        User user = userRepository.findByIdAndRole_RoleType(id, RoleTypeEnum.MODERATOR)
                .orElseThrow(() -> new NotFoundException("This moderator" + Path.NOT_FOUND));
        return new ResultMessage(true, user);
    }


    @Override
    public ResultMessage showUsers(Integer page, Integer size) {
        if (baseService.getUser().getAuthorities().size() > Utils.adminAuthority.size()) {
            Validation.checkPage(page, size, userRepository.count());
            return ResultMessage.builder()
                    .success(true)
                    .object(userRepository.findAll(Util.getPageable(page, size)))
                    .build();
        } else {
            Validation.checkPage(page, size, userRepository.countAllByRoleTypeNon(RoleTypeEnum.SUPER_ADMIN.name()));
            List<User> all = userRepository.findAllByRoleTypeNon(RoleTypeEnum.SUPER_ADMIN.name(),
                    Util.getExtraPageable(page, size, "creation_timestamp", false));
            return new ResultMessage(true, all);
        }
    }

    @Override
    public ResultMessage showUser(UUID user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User" + Path.NOT_FOUND));
        if (baseService.getUser().getAuthorities().size() <= Utils.adminAuthority.size()) {
            if (user.getAuthorities().size() > Utils.adminAuthority.size()) {
                throw new ConflictException(Path.NOT_ALLOWED);
            }
        }
        return new ResultMessage(true, user);
    }


    private void changeUserRole(User user, RoleTypeEnum roleTypeEnum) {
        Role role = roleRepository.findByRoleType(roleTypeEnum)
                .orElseThrow(() -> new NotFoundException("This role not found"));
        user.setRole(role);
        userRepository.save(user);
    }


}
