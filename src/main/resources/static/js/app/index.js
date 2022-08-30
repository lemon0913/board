// 굳이 var main = {} 처럼 index라는 변수의 속성으로 function을 추가한 이유는?
// 나중에 index.mustache에 index.js가 아닌 a.js가 추가되었을 때 a.js도 a.js만의 init과 save함수를 가지고 있을 수 있다
// 이럼 a.js의 init과 save 함수가 index.js의 init과 save함수를 덮어쓸 수 있다
// 플젝에서 중복된 함수 이름은 너무 빈번하게 발생하는 일이므로 이런 문제를 피하기 위해
// index.js만의 유효범위를 만든 것이다


var main = {
    init : function () {
        var _this = this;
        // id가 'btn-save'인 버튼(등록 버튼)을 click하면 save()함수가 동작한다..?
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        //댓글 저장
        $('#btn-comment-save').on('click', function () {
            _this.commentSave();        });

        // 댓글 수정
        document.querySelectorAll('#btn-comment-update').forEach(function (item) {
            item.addEventListener('click', function () { // 버튼 클릭 이벤트 발생시
                           const form = this.closest('form'); // btn의 가장 가까운 조상의 Element(form)를 반환 (closest)
                           _this.commentUpdate(form); // 해당 form으로 업데이트 수행
            });
        });

        // 회원 수정
        $('#btn-user-modify').on('click', function () {
            _this.userModify();
        });
    },

    // 게시글 등록 API
    // posts-write.mustache에서 등록 버튼을 눌렀을 때 돌아가는 함수
    save : function () {
        // 데이터 초기화인건가..??
        var data = {
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val(),
            location: $('#location').val(),
            money: $('#money').val(),
            reservation: $('#reservation').val(),
            x: $('#x').val(),
            y: $('#y').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () { // 함수가 성공하면(글 등록이 성공하면) 메시지를 출력하고 메인페이지로 이동
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error){ // 글 등록이 실패하면 에러메시지 출력인가...??
            alert(JSON.stringify(error));
        });
    },

    // 게시글 수정 API
    // posts-update.mustache에서 수정 완료 버튼을 눌렀을 때 돌아가는 함수
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            location: $('#location').val(),
            money: $('#money').val(),
            reservation: $('#reservation').val(),
            x: $('#x').val(),
            y: $('#y').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/posts/read/' + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    // 게시글 삭제 API
    // posts-update.mustache에서 삭제 버튼을 눌렀을 때 돌아가는 함수
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    // 댓글 저장 API
    commentSave : function () {
        const data = {
            postsId: $('#postsId').val(),
            comment: $('#comment').val()
        }

        // 공백 및 빈 문자열 체크
        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/api/posts/' + data.postsId + '/comments',
                // dataType: 'JSON',
                dataType: 'text',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글이 등록되었습니다.');
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    /** 댓글 수정 */
    commentUpdate : function (form) {
        const data = {
            id: form.querySelector('#id').value,
            postsId: form.querySelector('#postsId').value,
            comment: form.querySelector('#comment-content').value,
            writerUserId: form.querySelector('#writerUserId').value,
            sessionUserId: form.querySelector('#sessionUserId').value
        }
        console.log("commentWriterID : " + data.writerUserId);
        console.log("sessionUserID : " + data.sessionUserId);

        if (data.writerUserId !== data.sessionUserId) {
            alert("본인이 작성한 댓글만 수정 가능합니다.");
            return false;
        }

        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        }
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: 'PUT',
                url: '/api/posts/' + data.postsId + '/comments/' + data.id,
                // dataType: 'JSON',
                dataType: 'TEXT',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    /** 댓글 삭제 */
    commentDelete : function (postsId, commentId, commentWriterId, sessionUserId) {

        // 본인이 작성한 글인지 확인
        if (commentWriterId !== sessionUserId) {
            alert("본인이 작성한 댓글만 삭제 가능합니다.");
        } else {

            const con_check = confirm("삭제하시겠습니까?");

            if (con_check === true) {
                $.ajax({
                    type: 'DELETE',
                    url: '/api/posts/' + postsId + '/comments/' + commentId,
                    dataType: 'JSON',
                }).done(function () {
                    alert('댓글이 삭제되었습니다.');
                    window.location.reload();
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
        }
    },

    /** 회원 수정 */
    userModify : function () {
        const data = {
            id: $('#id').val(),
            username: $('#username').val(),
            nickname: $('#nickname').val(),
            password: $('#password').val()
        }
        if(!data.nickname || data.nickname.trim() === "" || !data.password || data.password.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else if(!/(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,16}/.test(data.password)) {
            alert("비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            $('#password').focus();
            return false;
        } else if(!/^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$/.test(data.nickname)) {
            alert("닉네임은 특수문자를 제외한 2~10자리여야 합니다.");
            $('#nickname').focus();
            return false;
        }
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: "PUT",
                url: "/api/user",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)

            }).done(function () {
                alert("회원수정이 완료되었습니다.");
                window.location.href = "/";

            }).fail(function (error) {
                if (error.status === 500) {
                    alert("이미 사용중인 닉네임 입니다.");
                    $('#nickname').focus();
                } else {
                    alert(JSON.stringify(error));
                }
            });
        } else {
            return false;
        }
    }
};

main.init();