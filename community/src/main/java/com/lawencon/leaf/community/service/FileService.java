package com.lawencon.leaf.community.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.leaf.community.dao.FileDao;
import com.lawencon.leaf.community.model.File;

@Service
public class FileService {

	private final FileDao fileDao;

	public FileService(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	public Optional<File> getById(String id) {
		return fileDao.getById(id);
	}

}
