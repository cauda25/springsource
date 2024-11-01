package com.example.project3.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "team")
@Table(name = "team_member")
@Entity // Entity를 @Query 사용할 때 여기서 지정한 이름으로 접근해야 함
public class Member {
    @Id
    private String id;

    private String userName;

    // 관계
    // 주인이 누구인지 @ManyToOne를 설정한 entity가 주인으로 우선수위를 쥐고 있음
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team; // 외래키

}
