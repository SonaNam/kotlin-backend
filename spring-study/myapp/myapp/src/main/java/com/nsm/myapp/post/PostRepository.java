package com.nsm.myapp.post;

import org.springframework.data.jpa.repository.JpaRepository;
//                                              PK값을 넣으려면 작성( Long )
import org.springframework.stereotype.Repository;

@Repository
// 데이터베이스와 상호작용을 쉽게 처리할수있게 돕는 어노테이션 (읽기,생성등)
public interface PostRepository extends JpaRepository<Post,Long > {
//                            Java자체에서 가져오는상속

}
