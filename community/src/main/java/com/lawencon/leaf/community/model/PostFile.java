package com.lawencon.leaf.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_post_file")
public class PostFile extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
