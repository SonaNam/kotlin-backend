package com.nsm.myapp.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Get /posts
// 게시글 목록이 JSON으로 나오게
@RestController
@RequestMapping(value ="/posts")
@ResponseBody

public class PostController {                //new Date().getTime() [ 시간 ]
//          동시성을 위한 자료 구조
//          HashMap -> ConcurrentHashMap
//          Integer -> AtomicInteger

    Map<Long, Post> map = new ConcurrentHashMap<Long, Post>();

    AtomicLong num = new AtomicLong(0);


    //      post 목록 화면을 제작 post.html,post.js
    //     fetch api를 사용하여 /posts 주소를 호출한 후
    //      배열 목록을 div(카드)로 표시한다.
    PostRepository pore;
    @Autowired
    public PostController(PostRepository p){
        this.pore = p;
    }

    //    게시자
    @GetMapping
    public List<Post> getPostList() {
//        증가시키고 값 가져오기
//        Long no = num.incrementAndGet();
//        map.put(no, Post.builder().No(no).title("남소나").content("ㅁㅁㅁ").creatorName("ㅅㄴ").createdTime(new Date().getTime()).build());
//        no = num.incrementAndGet();
//        map.put(no, Post.builder().No(no).title("칸").content("ㄴㄴㄴ").creatorName("ㅋ").createdTime(new Date().getTime()).build());
//        no = num.incrementAndGet();
//        map.put(no, Post.builder().No(no).title("쵸").content("ㅇㅇㅇ").creatorName("ㅊ").createdTime(new Date().getTime()).build());

        var list = new ArrayList<>(map.values());
//         람다식(lambda expression)
//         식이 1개인 함수식;
//         매개변수 영역과 함수 본체를 화살표로 구분함
//         함수 본체의 수식 값이 반환값
        list.sort((a, b) -> (int) (b.getNo() - a.getNo()));


        return list;
    }


//@PostMapping
//    public void addPost(...) {
//    1. 입력값 검증 (title,content)
//     -> 입력값 오류(빈 값)가 있으면 400 에러띄움
        @PostMapping
        public ResponseEntity<Map<String,Object>>addPost(@RequestBody Post post) {
//        ResponseEntity=클라이언트에게전달 및 JSON을 반환 > Map(@RequestBody JSON객체를 받아 자바로 풀어줌)
            if (post.getTitle() == null || post.getTitle().isEmpty() ) {

                Map<String, Object> res = new HashMap<>();
                res.put("data", map.get(post.getTitle()));
                res.put("message","제목확인해주세요");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

            }
            if (post.getContent() == null || post.getContent().isEmpty() ) {
                Map<String, Object> res = new HashMap<>();
                res.put("data", map.get(post.getContent()));
                res.put("message", "제목확인해주세요");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
//    2.채번: 번호를 딴다(1...2,3...)
//            데이터베이스의 auto_increment를 사용할 것이므로
//            아래 2줄은 필요없게 된다. AtomicLong 필요없음.
        Long no = num.incrementAndGet();
            post.setNo(no);
            System.out.print(post);
//    3.시간값, 게시자이름 설정(set필드명(...))
        post.setCreatedTime(new Date().getTime());
        post.setCreatorName("뭘관리"); //
        map.put(no,post);
//    4. 맵에 추가 (서버에서 생성된 값을 설정)
            Map<String,Object> res = new HashMap<>();
            res.put("data", map.get(post.getNo()));  // num.get 키,값 다 나옴
            res.put("message", "created");
            System.out.print(post);
//    5.생성된 객체를 맵에서 찾아서 반환
//}

            return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @DeleteMapping(value = "/{no}")
    public ResponseEntity removePost(@PathVariable long no){

        if (map.get(no) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        map.remove(no);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}