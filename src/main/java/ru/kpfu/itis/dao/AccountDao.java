package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.util.DbException;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public interface AccountDao {

    void save(Account account) throws DbException;

    void update(Account account) throws DbException;

    Account getByUsername(String username) throws DbException;

    Account getByEmail(String email) throws DbException;

    Account getById(UUID id) throws DbException;

    List<Account> getAdmins() throws DbException;

    void addAdmin(Account account) throws DbException;

    void deleteAdmin(Account account) throws DbException;

    Account getUserByUsernameAndPassword(String username, String password) throws DbException;

    Account extract(ResultSet result) throws DbException;

}
