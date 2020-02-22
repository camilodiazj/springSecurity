package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Role;
import com.example.demo.daos.IRoleDao;

@Service
public class RoleService {

	@Autowired
	private IRoleDao roleDao;

	public List<Role> getAll() {
		if (roleDao.count() > 0) {
			return (List<Role>) roleDao.findAll();
		} else {
			return null;
		}
	}

	public Role create(Role role) {
		if (!roleDao.findById(role.getId()).isPresent()) {
			return roleDao.save(role);
		} else {
			return null;
		}
	}

	public Role update(Role role) {
		if (roleDao.findById(role.getId()).isPresent()) {
			return roleDao.save(role);
		} else {
			return null;
		}
	}

	public boolean delete(Role role) {
		if (roleDao.findById(role.getId()).isPresent()) {
			roleDao.delete(role);
			return true;
		} else {
			return false;
		}
	}

}
