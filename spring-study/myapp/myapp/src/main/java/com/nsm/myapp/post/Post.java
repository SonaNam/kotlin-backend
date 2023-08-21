package com.nsm.myapp.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String creatorName;       //우선 연동하고 시간뒤에 닉네임으로 변경 ( 가입까지된다면 )

    private long createdTime;

    private String image;

    private long commentCnt;

    private String latestComment;
}

//이미지 c컨텍드참고
