package com.web.recipes.controller;

import com.web.recipes.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import javax.validation.Valid;
import com.web.recipes.dao.UsersDao;
import com.web.recipes.security.jwt.JWTFilter;
import com.web.recipes.security.jwt.TokenProvider;


@RestController
@CrossOrigin

public class AuthenticationController {

        private final TokenProvider tokenProvider;
        private final AuthenticationManagerBuilder authenticationManagerBuilder;
        private UsersDao userDao;

        public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UsersDao userDao) {
            this.tokenProvider = tokenProvider;
            this.authenticationManagerBuilder = authenticationManagerBuilder;
            this.userDao = userDao;
        }

        @RequestMapping(value = "/login", method = RequestMethod.POST)
        public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);

            Users user = userDao.findByUsername(loginDto.getUsername());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
        }

        @ResponseStatus(HttpStatus.CREATED)
        @RequestMapping(value = "/register", method = RequestMethod.POST)
        public void register(@Valid @RequestBody RegisterUserDto newUser) {
            try {
                Users user = userDao.findByUsername(newUser.getUsername());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists.");
            } catch (UsernameNotFoundException e) {
                userDao.addUser(newUser.getUsername(),newUser.getPassword(), newUser.getRole());
            }
        }

    }
