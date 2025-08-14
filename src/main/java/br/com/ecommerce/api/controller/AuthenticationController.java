package br.com.ecommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api.dto.LoginRequestDTO;
import br.com.ecommerce.api.dto.LoginResponseDTO;
import br.com.ecommerce.api.model.User;
import br.com.ecommerce.api.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;


    @Operation(summary = "authenticates client/user to validate requests")
    @ApiResponse(responseCode = "200")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data){
        var emailPassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        var response = new LoginResponseDTO(token);

        return ResponseEntity.ok(response);
    }
}
