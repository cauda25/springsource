package com.example.club.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClubMemberDTO {

    // email 형식인지 검증, 비어 있으면 안됨
    // password,name 비어 있으면 안됨

    @Email
    @NotBlank(message = "이메일 형식을 확인해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    private boolean fromSocial;
}
