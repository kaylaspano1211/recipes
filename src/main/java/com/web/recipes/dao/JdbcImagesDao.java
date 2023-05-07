package com.web.recipes.dao;

import com.web.recipes.model.Images;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcImagesDao implements ImagesDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcImagesDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Images addImage(String url) {
        Images image = null;

        String sql = "INSERT INTO images (url) VALUES (?) RETURNING image_id";

        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, url);

        if(id != null) {
            image = new Images();
            image.setImageId(id);
            image.setUrl(url);
        }
        return image;
    }

    @Override
    public Images getImageById(int id) {
        Images image = null;
        String sql = "SELECT image_id, url FROM images WHERE image_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if(result.next()) {
            image = mapRowToImages(result);
        } else {
            return null;
        }
        return image;
    }

    public Images mapRowToImages(SqlRowSet result) {
        Images image = new Images();

        image.setImageId(result.getInt("image_id"));
        image.setUrl(result.getString("url"));

        return image;
    }
}
