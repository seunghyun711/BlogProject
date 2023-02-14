let index = {
    init: function(){
        $("#btn-save").on("click",()=>{ // =>는 this를 바인딩하기 위해 사용
            this.save();
        });
    },

    save: function(){
        //alert('user의 save함수 호출됨');
        let data = {
            userName: $("#userName").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

//        console.log(data);

        // ajax호출시 default가 비동기 호출
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 객체로 변환됨
        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(생긴게 json이라면) => javascript 객체로 변경해준다.
        // 회원가입 수행 요창
        }).done(function(resp){ // 응답의 결과가 성공이면 실행
            alert("회원가입이 완료되었습니다.")
//            console.log(resp);
            location.href = "/blog";
        }).fail(function(error){ // 응답의 결과가 실패하면 실행
            alert(JSON.stringify(error));
        }); // ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
    }
}

index.init();