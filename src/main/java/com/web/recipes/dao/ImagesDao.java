package com.web.recipes.dao;

import com.web.recipes.model.Images;

public interface ImagesDao {

    Images addImage(String url);

    Images getImageById(int id);
}
