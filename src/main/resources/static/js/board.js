
//<script language="javascript">
let index = {
    init: function(){
        $("#btn-save").on("click",()=>{ // =>는 this를 바인딩하기 위해 사용
            this.save();

        });
        $("#btn-delete").on("click", ()=>{
        				this.deleteById();
        });

        $("#btn-update").on("click", ()=>{
                		this.update();
        });
        $("#btn-reply-update").on("click", ()=>{
                        this.replySave();
        });
    },

    save: function(){
        //alert('user의 save함수 호출됨');
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

//        console.log(data);

        // ajax호출시 default가 비동기 호출
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 객체로 변환됨
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(생긴게 json이라면) => javascript 객체로 변경해준다.
        // 회원가입 수행 요창
        }).done(function(resp){ // 응답의 결과가 성공이면 실행
            alert("글쓰기가 완료되었습니다.")
//            console.log(resp);
            location.href = "/";
        }).fail(function(error){ // 응답의 결과가 실패하면 실행
            alert(JSON.stringify(error));
        }); // ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
    },

    deleteById: function(){
    			let id = $("#id").text();

    			$.ajax({
    				type: "DELETE",
    				url: "/api/board/"+id,
    				dataType: "json"
        }).done(function(resp){
    				alert("삭제가 완료되었습니다.");
    				location.href = "/";
    	}).fail(function(error){
    				alert(JSON.stringify(error));
    	});
    },

    update: function(){
            let id = $("#id").val();

            let data = {
                title: $("#title").val(),
                content: $("#content").val()
            };

    //        console.log(data);

            // ajax호출시 default가 비동기 호출
            // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 객체로 변환됨
            $.ajax({
                type: "PUT",
                url: "/api/board"+id,
                data: JSON.stringify(data), // http body데이터
                contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
                dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(생긴게 json이라면) => javascript 객체로 변경해준다.
            // 회원가입 수행 요창
            }).done(function(resp){ // 응답의 결과가 성공이면 실행
                alert("수정이 완료되었습니다.")
    //            console.log(resp);
                location.href = "/";
            }).fail(function(error){ // 응답의 결과가 실패하면 실행
                alert(JSON.stringify(error));
            }); // ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
        },

    replySave: function(){
            //alert('user의 save함수 호출됨');
            let data = {

                content: $("#reply-content").val()
            };
            let boardId = $("#boardId").val();

            // ajax호출시 default가 비동기 호출
            // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 객체로 변환됨
            $.ajax({
                type: "POST",
                url: `/api/board/{id}/${boardId}/reply`,
                data: JSON.stringify(data), // http body데이터
                contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
                dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(생긴게 json이라면) => javascript 객체로 변경해준다.
            // 회원가입 수행 요창
            }).done(function(resp){ // 응답의 결과가 성공이면 실행
                alert("댓글 작성이 완료되었습니다.")
    //            console.log(resp);
                location.href = `/board/${boardId}`;
            }).fail(function(error){ // 응답의 결과가 실패하면 실행
                alert(JSON.stringify(error));
            }); // ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
        },

        replySave: function(boardId, replyId){
                    // ajax호출시 default가 비동기 호출
                    // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 객체로 변환됨
                    $.ajax({
                        type: "POST",
                        url: `/api/board/{boardId}/reply/${replyId}`,
                        dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String(생긴게 json이라면) => javascript 객체로 변경해준다.
                    // 회원가입 수행 요창
                    }).done(function(resp){ // 응답의 결과가 성공이면 실행
                        alert("댓글 삭제가 완료되었습니다.")
            //            console.log(resp);
                        location.href = `/board/${boardId}`;
                    }).fail(function(error){ // 응답의 결과가 실패하면 실행
                        alert(JSON.stringify(error));
                    }); // ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
                }


}

index.init();
