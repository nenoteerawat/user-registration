package com.teerawat.registration.models;

import java.util.List;

import com.teerawat.registration.db.domains.User;

public interface GetUserInfo {
	User getUser() throws NumberFormatException;
	List<String> getRole();
}
