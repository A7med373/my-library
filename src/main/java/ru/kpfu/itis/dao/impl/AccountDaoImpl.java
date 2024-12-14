package ru.kpfu.itis.dao.impl;

import ru.kpfu.itis.dao.AccountDao;
import ru.kpfu.itis.model.Account;
import ru.kpfu.itis.model.Role;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;
import ru.kpfu.itis.util.PasswordUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AccountDaoImpl implements AccountDao {

    private ConnectionProvider connectionProvider;

    //language=sql
    final String SQL_SAVE = "insert into account(uuid, username, name, lastname, birthday, email, password) " +
            "values(?, ?, ?, ?, ?, ?, ?)";


    //language=sql
    final String SQL_GET_BY_USERNAME = "select * from account where username = ?";

    //language=sql
    final String SQL_GET_BY_EMAIL = "select * from account where email = ?";

    //language=sql
    final String SQL_GET_BY_UUID = "select * from account where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_GET_ADMINS = "select * from account where role_id = 1";

    //language=sql
    final String SQL_UPDATE = "update account set username = ?, name = ?, lastname = ?, email = ?, about = ?, avatar = ? " +
            "where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_UPDATE_WITHOUT_AVATAR = "update account set username = ?, name = ?, lastname = ?, email = ?, " +
            "about = ? where uuid = cast(? as uuid)";

    //language=sql
    final String SQL_UPDATE_ADMIN = "update account set role_id = ? where uuid = cast(? as uuid)";

    public AccountDaoImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void save(Account account) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_SAVE);

            // Генерируем новый UUID, если он не задан
            UUID id = (account.uuid() == null) ? UUID.randomUUID() : account.uuid();

            // Подставляем значения в соответствии с порядком в SQL
            preparedStatement.setObject(1, id);
            preparedStatement.setString(2, account.username());
            preparedStatement.setString(3, account.name());
            preparedStatement.setString(4, account.lastname());
            preparedStatement.setDate(5, new java.sql.Date(account.birthday().getTime()));
            preparedStatement.setString(6, account.email());
            preparedStatement.setString(7, PasswordUtil.hash(account.password()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Can't save user", e);
        }
    }


    @Override
    public void update(Account account) throws DbException {
        try {
            if (account.avatar() != null) {
                PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                        .prepareStatement(SQL_UPDATE);
                preparedStatement.setString(1, account.username());
                preparedStatement.setString(2, account.name());
                preparedStatement.setString(3, account.lastname());
                preparedStatement.setString(4, account.email());
                preparedStatement.setString(5, account.about());
                preparedStatement.setString(6, account.avatar());
                preparedStatement.setObject(7, account.uuid());
                preparedStatement.executeUpdate();

            } else {
                PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                        .prepareStatement(SQL_UPDATE_WITHOUT_AVATAR);
                preparedStatement.setString(1, account.username());
                preparedStatement.setString(2, account.name());
                preparedStatement.setString(3, account.lastname());
                preparedStatement.setString(4, account.email());
                preparedStatement.setString(5, account.about());
                preparedStatement.setObject(6, account.uuid());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DbException("Can't update account from db.", e);
        }
    }

    @Override
    public Account getByUsername(String username) throws DbException {
        ResultSet resultPrepSet;
        try {
            PreparedStatement prepStatement = connectionProvider.getConnection().prepareStatement(SQL_GET_BY_USERNAME);
            prepStatement.setString(1, username);
            resultPrepSet = prepStatement.executeQuery();
            boolean res = resultPrepSet.next();
            if (res) {
                return extract(resultPrepSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get account from db.", e);
        }
    }

    @Override
    public Account getByEmail(String email) throws DbException {
        ResultSet resultPrepSet;
        try {
            PreparedStatement prepStatement = connectionProvider.getConnection().prepareStatement(SQL_GET_BY_EMAIL);
            prepStatement.setString(1, email);
            resultPrepSet = prepStatement.executeQuery();
            boolean res = resultPrepSet.next();
            if (res) {
                return extract(resultPrepSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get account from db.", e);
        }
    }

    @Override
    public Account getById(UUID id) throws DbException {
        ResultSet resultPrepSet;
        try {
            PreparedStatement prepStatement = connectionProvider.getConnection().prepareStatement(SQL_GET_BY_UUID);
            prepStatement.setObject(1, id);
            resultPrepSet = prepStatement.executeQuery();
            boolean res = resultPrepSet.next();
            if (res) {
                return extract(resultPrepSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Can't get account from db.", e);
        }
    }

    @Override
    public List<Account> getAdmins() throws DbException {
        List<Account> admins = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connectionProvider
                    .getConnection()
                    .prepareStatement(SQL_GET_ADMINS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                admins.add(extract(resultSet));
            }
            return admins;
        } catch (SQLException e) {
            throw new DbException("Can't get admins from db.", e);
        }
    }

    @Override
    public void addAdmin(Account account) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_UPDATE_ADMIN);
            preparedStatement.setInt(1, 1);
            preparedStatement.setObject(2, account.uuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't update admin.", e);
        }
    }

    @Override
    public void deleteAdmin(Account account) throws DbException {
        try {
            PreparedStatement preparedStatement = this.connectionProvider.getConnection()
                    .prepareStatement(SQL_UPDATE_ADMIN);
            preparedStatement.setInt(1, 2);
            preparedStatement.setObject(2, account.uuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Can't update admin.", e);
        }
    }

    @Override
    public Account getUserByUsernameAndPassword(String username, String password) throws DbException {
        Account account;
        if (username.contains("@")) {
            account = getByEmail(username);
        } else {
            account = getByUsername(username);
        }
        if (account != null) {
            if (PasswordUtil.check(password, account.password())) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Account extract(ResultSet result) throws DbException {
        Account account;
        try {
            account = new Account((UUID) result.getObject("uuid"),
                    result.getString("username"),
                    result.getString("name"),
                    result.getString("lastname"),
                    (Date) result.getObject("birthday"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("avatar"),
                    result.getString("about"),
                    new Role(result.getInt("role_uuid"),
                            result.getInt("role_uuid") == 1 ? "admin" : "base"));

            return account;

        } catch (SQLException e) {
            throw new DbException("Can't get book from db.", e);
        }
    }

}
