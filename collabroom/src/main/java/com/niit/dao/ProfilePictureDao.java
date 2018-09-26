package com.niit.dao;

import com.niit.entity.ProfilePicture;

public interface ProfilePictureDao {

	void uploadProfilePicture(ProfilePicture profilePicture);

	ProfilePicture getImage(String email);
}
