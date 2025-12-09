package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ActivateUser;
import com.example.demo.model.LoginCredentials;
import com.example.demo.model.RecoverPassword;
import com.example.demo.model.UserAccount;

public interface IUserMgmtService {

	public String registerUser(UserAccount user) throws Exception;

	public String ActivateUserAccount(ActivateUser user);

	public String login(LoginCredentials credentials);

	public List<UserAccount> listUsers();

	public UserAccount showUserByUserId(Integer id);

	public UserAccount showUserByEmailAndName(String email, String name);

	public String updateUser(UserAccount user);

	public String deleteUserById(Integer id);

	public String changeUserStatus(Integer id, String status);

	public String recoverPassword(RecoverPassword recover) throws Exception;
}
