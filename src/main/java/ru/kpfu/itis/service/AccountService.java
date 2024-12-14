package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.AccountRegistrationDto;
import ru.kpfu.itis.dto.AccountUpdateDto;
import ru.kpfu.itis.model.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    void auth(Account account, HttpServletRequest req);

    Account getCurrentAccount(HttpServletRequest req);

    void deleteSessionAndCookie(HttpServletRequest req, HttpServletResponse resp);

    boolean isNonAnonymous(HttpServletRequest req);

    void addCookie(String username, HttpServletResponse resp);

    boolean register(AccountRegistrationDto account, HttpServletRequest req, String repeatPassword);

    void save(AccountRegistrationDto account);

    boolean update(AccountUpdateDto account, Account oldAccount, HttpServletRequest req);

    Account getByUsername(String username);

    Account getByEmail(String email);

    Account getById(UUID id);

    List<Account> getAdmins();

    Account getByUsernameAndPassword(String username, String password);

    Account getByUsernameOrEmail(String username);

    void addAdmin(Account account);

    void deleteAdmin(Account account);
}
