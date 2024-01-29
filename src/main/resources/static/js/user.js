let index = {

	//jQuery 
	init: function() {

		//function(){}, ()=>{} this 를 바인딩하기 위해서
		//코드를 줄이려고 람다식을 쓴게 아니다. 
		//jQuery 바깥 스코프의 this 와 내부 스코프의 this가 아래 처럼 처리하면 같지만
		//function(){} 형식을 사용하면 외부 this와 내부 this 가 달라지게 된다. 
		//function(){} 형식으로 사용하려면 스코프 외부에서 let _this = this; 로 바인딩해서 
		//스코프 내부에서는 _this 로 사용해야 한다. 
		$("#btn-save").on("click", () => {
			this.save();
		});

		//Spring-Security 로 삭제
		/*
		$("#btn-login").on("click", () => {
			this.login();
		});
		*/
	},

	save: function() {
		//alert('user.js의 save함수 호출됨');

		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		console.log(data);

		//Ajax를 사용하는 이유
		//1. 데이터 타입을 (html x) 리턴 하는 서버를 만들기 위해서 이다.
		//1-1. 데이터 타입을 리턴해야 서버를 하나만 만들어도 된다. 그렇지 않으면 앱과 웹 서버를 각각만들어야 된다.  
		//2. 비동기 통신을 하기 위해서 이다.

		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청.

		//ajax 호출 시 default 가 비동기 호출.
		//let data는 자바스크립트 오브젝트기 때문에 java 쪽으로 던지면 이해를 못해서 Json으로 변경.
		//통신을 수행해서 회원가입 수행 요청. (100초 가정) 
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든것은 문자열(생긴게 json이라면)=> javascript 오브젝트로 변경
			//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트를 변환 해주네요. 
			//dataType 을 주석처리 해도 자동 변환됨. 
			//dataType: text 로 하면 text 형식으로 받음. 
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다." + resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));

		});
	}

	//Spring-Security 로 삭제
	/*
	login: function() {
		//alert('user.js의 save함수 호출됨');

		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		};

		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든것은 문자열(생긴게 json이라면)=> javascript 오브젝트로 변경

		}).done(function(resp) {
			alert("로그인이 완료되었습니다." + resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}*/
}

index.init();