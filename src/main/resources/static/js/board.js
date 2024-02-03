let index = {

	//jQuery 
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});

		$("#btn-delete").on("click", () => {
			this.deleteById(); //delete 가 예약어라 다른 이름으로 함. 
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		console.log(data);

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다." + resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));

		});
	},
	
	deleteById: function() {
		var id = $("#id").text(); 

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json",
			contentType: `application/json; charset=utf-8`
		}).done(function(resp) {
			alert("삭제가 완료되었습니다." + resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
	
	
}

index.init();