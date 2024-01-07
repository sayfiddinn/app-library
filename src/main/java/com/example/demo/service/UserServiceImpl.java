package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Booking;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.MyMapper;
import com.example.demo.payload.PasswordDTO;
import com.example.demo.payload.ResultMessage;
import com.example.demo.payload.UserDTO;
import com.example.demo.repository.BookRepo;
import com.example.demo.repository.BookingRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Path;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ConflictException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MyMapper mapper;
    private final BaseService baseService;
    private final PasswordEncoder passwordEncoder;
    private final BookRepo bookRepo;
    private final BookingRepo bookingRepo;

    @Override
    public ResultMessage showProfile() {
        User user = baseService.getUser();
        return new ResultMessage(true, user);
    }

    @Override
    public ResultMessage editProfile(UserDTO userDTO) {
        User user = baseService.getUser();
        checkEmail(userDTO.getEmail(), user.getId());
        mapper.userDtoToUser(userDTO, user);
        userRepository.save(user);
        return new ResultMessage(true, Path.UPDATED);
    }

    private void checkEmail(String email, UUID userId) {
        User userByEmail = userRepository.findByEmail(email).orElse(null);
        if (userByEmail != null && !Objects.equals(userByEmail.getId(), userId))
            throw new ConflictException("This email exists");
    }


    @Override
    public ResultMessage deleteProfile() {
        User user = baseService.getUser();
        user.setDeleted(true);
        userRepository.save(user);
        return new ResultMessage(true, Path.DELETED);
    }


    @Override
    public ResultMessage changePassword(PasswordDTO passwordDTO) {
        User user = baseService.getUser();
        if (!passwordEncoder
                .matches(passwordDTO.getOldPassword(), user.getPassword())) {
            return new ResultMessage(false, "Old password wrong");
        }
        String newPassword = passwordDTO.getNewPassword();
        if (newPassword.isBlank()
                || !Objects.equals(passwordDTO.getRePassword(), newPassword)) {
            return new ResultMessage(false, "Re password wrong");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return new ResultMessage(true, Path.UPDATED);
    }

    @Transactional
    @Override
    public ResultMessage bronBook(UUID id, Integer count) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Book Id not found"));
        if (count > book.getCount()) {
            return new ResultMessage(false, "Bu kitobdan buncha mavjud emas");
        }
        User user = baseService.getUser();
        Booking booking = bookingRepo
                .findByUserAndBookAndBronDate(user, book,
                        LocalDate.now()).orElse(null);
        if (booking != null) {
            booking.setCount(booking.getCount() + count);
        } else {
            booking = new Booking();
            booking.setBook(book);
            booking.setUser(user);
            booking.setCount(count);
        }
        bookingRepo.save(booking);
        book.setCount(book.getCount() - count);
        bookRepo.save(book);
        return new ResultMessage(true, "book was ordered !");
    }

    @Override
    public ResultMessage showBronHistory() {
        List<Booking> bookings = bookingRepo
                .newHistory(baseService.getUser().getId());
        return new ResultMessage(true, bookings);
    }

    @Override
    public ResultMessage showOldBronHistory() {
        List<Booking> bookings = bookingRepo
                .oldHistory(baseService.getUser().getId());
        return new ResultMessage(true, bookings);
    }


}
